DROP DATABASE IF EXISTS courseDB;
CREATE DATABASE IF NOT EXISTS courseDB;

USE courseDB;

CREATE TABLE IF NOT EXISTS Course(
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) DEFAULT NULL,
    course_topic VARCHAR(100),
    min_attendance INT DEFAULT 0,
    max_attendance INT NOT NULL,
    course_active BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Staff(
    id INT PRIMARY KEY AUTO_INCREMENT,
    staff_name VARCHAR(50) NOT NULL,
    staff_role VARCHAR(50) NOT NULL,
    staff_admin BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Attendance(
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT,
    user_id INT,
    staff_id INT,
    atten_status BOOLEAN DEFAULT FALSE,
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