DROP DATABASE IF EXISTS courseDB;
CREATE DATABASE IF NOT EXISTS courseDB;

USE courseDB;

CREATE TABLE IF NOT EXISTS Topic(
    id INT PRIMARY KEY AUTO_INCREMENT,
    topic_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Degree(
    id INT PRIMARY KEY AUTO_INCREMENT,
    degree_name VARCHAR(255) NOT NULL,
    degree_ects INT(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Course(
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(255) NOT NULL,
    course_topic VARCHAR(255) DEFAULT NULL,
    course_desc VARCHAR(255) DEFAULT NULL,
    attendance_avaible BOOLEAN DEFAULT FALSE,
    attendance_key VARCHAR(6),
    min_attendance INT DEFAULT 0,
    max_attendance INT NOT NULL,
    course_active BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS Staff(
    id INT PRIMARY KEY AUTO_INCREMENT,
    staff_name VARCHAR(255) NOT NULL,
    staff_role VARCHAR(255) NOT NULL,
    staff_admin BOOLEAN DEFAULT FALSE,
    staff_passw VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    user_student_id INT(20) NOT NULL,
    user_degree VARCHAR(50) NOT NULL,
    user_passw VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Attendance(
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT,
    user_id INT,
    staff_id INT,
    atten_status BOOLEAN DEFAULT FALSE,
    atten_current INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES Course(id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (staff_id) REFERENCES Staff(id)
);

CREATE TABLE IF NOT EXISTS AttendanceInfo(
    id INT PRIMARY KEY AUTO_INCREMENT,
    info VARCHAR(250),
    approve BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS course_user_join(
    user_id int NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (user_id, course_id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

CREATE TABLE IF NOT EXISTS course_staff_join(
    staff_id int NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (staff_id, course_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

CREATE TABLE IF NOT EXISTS Attendance_info_join(
    atten_id INT,
    info_id INT,
    PRIMARY KEY (atten_id, info_id),
    FOREIGN KEY (atten_id) REFERENCES Attendance(id),
    FOREIGN KEY (info_id) REFERENCES AttendanceInfo(id)
);

INSERT INTO Topic (topic_name) VALUES ("Programming Course");
INSERT INTO Topic (topic_name) VALUES ("Mathematic Course");
INSERT INTO Topic (topic_name) VALUES ("Language Course");
INSERT INTO Topic (topic_name) VALUES ("Project Course");
INSERT INTO Topic (topic_name) VALUES ("Exam");
INSERT INTO Topic (topic_name) VALUES ("Exam Part 1");
INSERT INTO Topic (topic_name) VALUES ("Exam Part 2");
INSERT INTO Topic (topic_name) VALUES ("Exam Resit 1");
INSERT INTO Topic (topic_name) VALUES ("Exam Resit 2");

INSERT INTO Degree (degree_name, degree_ects) VALUES ("Software Engineer", 240);

INSERT INTO Users (user_name, user_student_id, user_degree, user_passw) VALUES ("Pekka Pouta", 25001, "Meteorologi", "Salasana12345");
INSERT INTO Users (user_name, user_student_id, user_degree, user_passw) VALUES ("Jukka Junitti", 25002, "Software Engineer", "Salasana12345");
INSERT INTO Users (user_name, user_student_id, user_degree, user_passw) VALUES ("Toni Testaaja", 25003, "Software Engineer", "Salasana");

INSERT INTO Staff (staff_name, staff_role, staff_admin, staff_passw) VALUES ("Olli Opettaja", "Teacher", False, "Opettaja12345");
INSERT INTO Staff (staff_name, staff_role, staff_admin, staff_passw) VALUES ("Roni Rehtori", "Principal", False, "Rehtori12345");
INSERT INTO Staff (staff_name, staff_role, staff_admin, staff_passw) VALUES ("Toni Tukihenkilö", "God", True, "godmode12345");

INSERT INTO Course ( course_name, course_topic, attendance_avaible, attendance_key, min_attendance, max_attendance, course_active ) VALUES ("Python Basic", "Programming Course", False, null, 5,10,True);
INSERT INTO Course ( course_name, course_topic, attendance_avaible, attendance_key, min_attendance, max_attendance, course_active ) VALUES ("Python Advanced", "Programming Course", False, null, 12,20,True);
INSERT INTO Course ( course_name, course_topic, attendance_avaible, attendance_key, min_attendance, max_attendance, course_active ) VALUES ("Python Exam","Exam", False, null, 1,1,True);

INSERT INTO course_user_join (user_id, course_id) VALUES (1,1);
INSERT INTO course_user_join (user_id, course_id) VALUES (2,1);
INSERT INTO course_user_join (user_id, course_id) VALUES (3,1);
INSERT INTO course_user_join (user_id, course_id) VALUES (1,2);
INSERT INTO course_user_join (user_id, course_id) VALUES (2,2);
INSERT INTO course_user_join (user_id, course_id) VALUES (3,2);

INSERT INTO course_staff_join (staff_id, course_id) VALUES (1,1);
INSERT INTO course_staff_join (staff_id, course_id) VALUES (1,2);


