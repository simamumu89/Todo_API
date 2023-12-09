DROP TABLE IF EXISTS todo_list;

CREATE TABLE task (
 id int unsigned AUTO_INCREMENT,
 user_id int(4) ,
 name VARCHAR(255) NOT NULL,
 start_date DATE NOT NULL,
 scheduled_end_date DATE NOT NULL,
 actual_end_date DATE,
 PRIMARY KEY(id)
);

INSERT INTO task (id, user_id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (1, null, "構想", 2023/12/06, null, null);

INSERT INTO task (id, user_id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (2, null, "API作成", 2023/12/07, null, null);

INSERT INTO task  (id, user_id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (3, null, "テスト", 2023/12/08, null, null);

INSERT INTO task  (id, user_id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (4, null, "リリース" 2023/12/09, null, null);


