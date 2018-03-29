drop database if exists utcn;
create database utcn;
use utcn;

CREATE TABLE courses (
                courseid INT AUTO_INCREMENT NOT NULL,
                name VARCHAR(50) NOT NULL,
                PRIMARY KEY (courseid)
);


CREATE TABLE users (
                userid INT AUTO_INCREMENT NOT NULL,
                username VARCHAR(50) NOT NULL,
                userpassword VARCHAR(50) NOT NULL,
                usertype INT NOT NULL,
                PRIMARY KEY (userid)
);


CREATE TABLE teachers (
                userid INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                icnumber CHAR(6) NOT NULL,
                cnp CHAR(13) NOT NULL,
                address VARCHAR(50),
                PRIMARY KEY (userid)
);


CREATE TABLE courses_teachers (
                userid INT NOT NULL,
                courseid INT NOT NULL,
                PRIMARY KEY (userid, courseid)
);


CREATE TABLE students (
                userid INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                icnumber CHAR(6) NOT NULL,
                cnp CHAR(13) NOT NULL,
                address VARCHAR(50),
                identificationnumber CHAR(8) NOT NULL,
                group_1 CHAR(5) NOT NULL,
                PRIMARY KEY (userid)
);


CREATE TABLE enrolments (
                userid INT NOT NULL,
                courseid INT NOT NULL,
                grade INT,
                date DATE NOT NULL,
                PRIMARY KEY (userid, courseid)
);


ALTER TABLE enrolments ADD CONSTRAINT courses_enrolments_fk
FOREIGN KEY (courseid)
REFERENCES courses (courseid)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE courses_teachers ADD CONSTRAINT courses_courses_teachers_fk
FOREIGN KEY (courseid)
REFERENCES courses (courseid)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE students ADD CONSTRAINT users_students_fk
FOREIGN KEY (userid)
REFERENCES users (userid)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE teachers ADD CONSTRAINT users_teachers_fk
FOREIGN KEY (userid)
REFERENCES users (userid)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE courses_teachers ADD CONSTRAINT teachers_courses_teachers_fk
FOREIGN KEY (userid)
REFERENCES teachers (userid)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE enrolments ADD CONSTRAINT students_enrolments_fk
FOREIGN KEY (userid)
REFERENCES students (userid)
ON DELETE NO ACTION
ON UPDATE NO ACTION;