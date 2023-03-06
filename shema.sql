DROP TABLE IF EXISTS users;
CREATE TABLE users(id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, age integer, salary decimal);
CREATE TABLE department(id INT PRIMARY KEY, user_id integer, name VARCHAR(100) NOT NULL, loc VARCHAR(100))
