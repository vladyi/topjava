DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2021-02-01 14:00', 'Admin lunch', 510, 100001),
       ('2021-02-01 19:04', 'Admin dinner', 534, 100001),
       ('2021-02-01 09:16', 'Admin breakfast', 587, 100001),
       ('2021-02-02 08:37', 'Admin breakfast', 537, 100001),
       ('2021-02-02 20:15', 'Admin dinner', 1461, 100001),
       ('2021-02-02 12:19', 'Admin lunch', 893, 100001),
       ('2021-02-03 08:30', 'Admin breakfast', 436, 100001),
       ('2021-02-03 19:21', 'Admin dinner', 1723, 100001),
       ('2021-02-03 14:11', 'Admin lunch', 1611, 100001),
       ('2021-02-04 08:12', 'Admin breakfast', 567, 100001),
       ('2021-02-01 08:11', 'User breakfast', 412, 100000),
       ('2021-02-01 12:19', 'User lunch', 1167, 100000),
       ('2021-02-01 21:00', 'User dinner', 1731, 100000),
       ('2021-02-02 08:43', 'User breakfast', 581, 100000),
       ('2021-02-02 12:32', 'User lunch', 1245, 100000),
       ('2021-02-02 18:27', 'User dinner', 1543, 100000),
       ('2021-02-03 07:53', 'User breakfast', 654, 100000),
       ('2021-02-03 13:34', 'User lunch', 1500, 100000),
       ('2021-02-03 19:14', 'User dinner', 1718, 100000),
       ('2021-02-04 08:03', 'User breakfast', 451, 100000);
