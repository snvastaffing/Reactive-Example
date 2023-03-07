DROP TABLE IF EXISTS users;
CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, age integer, salary decimal);
DROP TABLE IF EXISTS department;
CREATE TABLE department(id SERIAL  PRIMARY KEY, user_id integer, name VARCHAR(100) NOT NULL, loc VARCHAR(100))
