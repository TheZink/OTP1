DROP DATABASE IF EXISTS courseDB;
CREATE DATABASE IF NOT EXISTS courseDB;

USE courseDB;

--Not working

CREATE TABLE `Teacher` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `admin` boolean,
  `role` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `UserCourses` (
  `User_id` integer,
  `Course_id` integer,
  PRIMARY KEY (`User_id`, `Course_id`)
);

CREATE TABLE `TeachersCourses` (
  `Course_id` integer,
  `Teacher_id` integer,
  PRIMARY KEY (`Course_id`, `Teacher_id`)
);

CREATE TABLE `Courses` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `created_at` timestamp,
  `topic` varchar(255),
  `min_attendance` integer,
  `max_attendance` integer,
  `staff_id` integer
);

CREATE TABLE `Users` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `Attendance` (
  `id` integer PRIMARY KEY,
  `student_id` integer,
  `course_id` integer,
  `status` boolean,
  `current_attendance` integer,
  `staff_id` integer,
  `created_at` timestamp
);

CREATE TABLE `AttendanceInfo` (
  `id` integer PRIMARY KEY,
  `attendance_id` integer,
  `student_id` integer,
  `info` varchar(255)
);

CREATE TABLE `Topics` (
  `id` integer PRIMARY KEY,
  `name` varchar(255)
);

ALTER TABLE `Topics` ADD FOREIGN KEY (`id`) REFERENCES `Courses` (`topic`);

ALTER TABLE `Users` ADD FOREIGN KEY (`id`) REFERENCES `Attendance` (`student_id`);

ALTER TABLE `Courses` ADD FOREIGN KEY (`id`) REFERENCES `Attendance` (`course_id`);

ALTER TABLE `Attendance` ADD FOREIGN KEY (`id`) REFERENCES `AttendanceInfo` (`attendance_id`);

ALTER TABLE `Users` ADD FOREIGN KEY (`id`) REFERENCES `AttendanceInfo` (`student_id`);

ALTER TABLE `Courses` ADD FOREIGN KEY (`staff_id`) REFERENCES `Teacher` (`id`);

ALTER TABLE `Teacher` ADD FOREIGN KEY (`id`) REFERENCES `Attendance` (`staff_id`);

ALTER TABLE `Courses` ADD FOREIGN KEY (`id`) REFERENCES `UserCourses` (`Course_id`);

ALTER TABLE `Users` ADD FOREIGN KEY (`id`) REFERENCES `UserCourses` (`User_id`);

ALTER TABLE `Courses` ADD FOREIGN KEY (`id`) REFERENCES `TeachersCourses` (`Course_id`);

ALTER TABLE `Teacher` ADD FOREIGN KEY (`id`) REFERENCES `TeachersCourses` (`Teacher_id`);