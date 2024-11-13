CREATE DATABASE IF NOT EXISTS schedule_db;

USE schedule_db;

DROP TABLE IF EXISTS schedule_tbl, user_tbl;

CREATE TABLE user_tbl (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL
) ENGINE = InnoDB;

CREATE TABLE schedule_tbl (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(30) NOT NULL,
    content TINYTEXT NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    FOREIGN KEY(user_id) REFERENCES user_tbl(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
) ENGINE = InnoDB;
