package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.shima.todo_list.TodoListApplication;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = TodoListApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoListRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

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

    @Test
    @DataSet(value = "datasets/todolists.yml")
    @Transactional
    public void 存在しないIDを指定するとステータスコード404のエラーメッセージを返すこと() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo_lists/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getErrorMessage();
    }
}


