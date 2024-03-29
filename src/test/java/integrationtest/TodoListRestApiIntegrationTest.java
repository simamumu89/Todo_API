package integrationtest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.shima.todo_list.TodoListApplication;
import com.shima.todo_list.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TodoListApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoListRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    //Read機能のIntegrationTest(All)
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    void タスク情報をステータスコード200で全件取得すること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/todo_lists"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                [
                    {
                        "id": 1,
                        "name": "構想",
                        "startDate": "2023-12-06",
                        "scheduledEndDate": null,
                        "actualEndDate": null
                    },
                    {
                        "id": 2,
                        "name": "API作成",
                        "startDate": "2023-12-07",
                        "scheduledEndDate": null,
                        "actualEndDate": null
                    },
                    {
                        "id": 3,
                        "name": "テスト",
                        "startDate": "2023-12-08",
                        "scheduledEndDate": null,
                        "actualEndDate": null
                    },
                    {
                        "id": 4,
                        "name": "リリース",
                        "startDate": "2023-12-09",
                        "scheduledEndDate": null,
                        "actualEndDate": null
                    }
                ]
                """, response, JSONCompareMode.STRICT);
    }

    //Read機能のIntegrationTest(指定id)
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    public void 指定したIDでタスク情報をステータスコード200を取得すること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/todo_lists/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                           
                {
                  "id": 2,
                  "name": "API作成",
                  "startDate": "2023-12-07",
                  "scheduledEndDate": null,
                  "actualEndDate": null
                }
                                
                """, response, JSONCompareMode.STRICT);
    }

    //Read機能のIntegrationTest(例外処理)
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    public void 存在しないIDを指定するとステータスコード404のエラーメッセージを返すこと() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo_lists/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getErrorMessage();
    }

    //Create機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/insert_todolists.yml", ignoreCols = "id")
    @Transactional
    public void 新規のタスク情報がDBに登録されるとステータスコード201が返ってくること() throws Exception {
        Todo todo = new Todo(5, "承認", LocalDate.of(2023, 12, 10), null, null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String requestJson = mapper.writeValueAsString(todo);
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/todo_lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                    "message": "Add new task"
                }
                            """, response, JSONCompareMode.STRICT);

    }

    //Update機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/update_todolists.yml")
    @Transactional
    public void 指定したidにタスク情報を更新するとステータスコード200のメッセージを取得すること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/todo_lists/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""                                                        
                                {
                                  "name": "設計",
                                  "starDate": "2023-12-06",
                                  "scheduledEndDate": "2023-12-08",
                                  "actualEndDate": "2023-12-09"
                                }
                                """)).andExpect(MockMvcResultMatchers.status().isOk()).
                andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                    "message": "Update completed!"
                }
                """, response, JSONCompareMode.STRICT);
    }

    //Update機能のIntegrationTest(例外処理)
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    public void 存在しないIDでタスク情報の更新処理をするとステータスコード404とエラーメッセージを取得すること() throws Exception {
        Assertions.assertTrue(mockMvc.perform(MockMvcRequestBuilders.patch("/todo_lists/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "設計",
                                  "starDate": "2023-12-06",
                                  "scheduledEndDate": "2023-12-08",
                                  "actualEndDate": "2023-12-09"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString().contains("task not found"));
    }

    //Delete機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @ExpectedDataSet(value = "datasets/delete_todolists.yml")
    @Transactional
    public void 存在するIDを削除しステータスコード200のメッセージを取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/todo_lists/{id}", 3))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                    "message": "Task deleted"
                }
                            """, response, JSONCompareMode.STRICT);
    }

    //Delete機能のIntegrationTest(例外処理)
    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    void 指定したIDが存在しない時にエラーメッセージが返されること() throws Exception {
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/todo_lists/99"))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseBody);
        String errorMessage = responseJson.get("message").asText();
        assertEquals("Task not found", errorMessage);
    }
}


