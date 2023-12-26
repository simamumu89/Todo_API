DROP TABLE IF EXISTS todo_lists;

CREATE TABLE todo_lists (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL UNIQUE,
 start_date DATE NOT NULL UNIQUE,
 scheduled_end_date DATE,
 actual_end_date DATE,
 PRIMARY KEY(id)
);

INSERT INTO todo_lists (id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (1, "構想", '2023-12-06', null, null);

INSERT INTO todo_lists (id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (2, "API作成", '2023-12-07', null, null);

INSERT INTO todo_lists (id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (3, "テスト", '2023-12-08', null, null);

INSERT INTO todo_lists (id, name, start_date, scheduled_end_date, actual_end_date)
VALUES (4, "リリース", '2023-12-09', null, null);


