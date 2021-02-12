create database MealDB;
use MealDB;

CREATE TABLE MealDB.`meals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `calories` varchar(45) DEFAULT NULL,

  PRIMARY KEY (`id`)
);

INSERT INTO meals(date, description, calories)
VALUES ('2020-01-30 10:00', 'Завтрак', 500)
     , ('2020-01-30 13:00', 'Обед', 1000)
     , ('2020-01-30 20:00', 'Ужин', 500)
	 , ('2020-01-31 0:00', 'Еда на граничное значение', 100)
	 , ('2020-01-31 10:00', 'Завтрак', 1000)
	 , ('2020-01-31 13:00', 'Обед', 500)
	 , ('2020-01-31 20:00', 'Ужин', 410)