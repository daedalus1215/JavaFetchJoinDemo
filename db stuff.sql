ALTER TABLE hb_student_tracker.student AUTO_INCREMENT=1;


SELECT * FROM hb_student_tracker.student;

SELECT * FROM hb_student_tracker.employee;
SELECT * FROM hb_student_tracker.course;

ALTER TABLE `hb_student_tracker`.`student`
ADD COLUMN `date_of_birth` DATETIME NULL AFTER `last_name`;



CREATE TABLE `instructor_detail` (
`id` int(11) PRIMARY KEinstructorY NOT NULL AUTO_INCREMENT,
`youtube_channel` varchar(28) DEFAULT NULL,
`hobby` varchar(45) DEFAULT NULL
);

CREATE TABLE `instructor` (
`id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) DEFAULT NULL,
`last_name` VARCHAR(45) DEFAULT NULL,
`email` VARCHAR(45) DEFAULT NULL,
`instructor_detail_id` INT(11) DEFAULT NULL,
CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
REFERENCES `instructor_detail` (`id`)
);

DROP TABLE `instructor`;
DROP TABLE `course`;


SELECT * FROM `instructor`;

SELECT * FROM `instructor_detail`;



CREATE TABLE `course` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(128) DEFAULT NULL,
`instructor_id` int(11) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `TITLE_UNIQUE` (`title`)course,

KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
CONSTRAINT `FK_INSTRUCTOR`
FOREIGN KEY (`instructor_id`)
REFERENCES `instructor` (`id`)

ON DELETE NO ACTION ON UPDATE NO ACTION)
ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;




SELECT * FROM hb_student_tracker.instructor;
SELECT * FROM hb_student_tracker.instructor_detail;
SELECT * FROM hb_student_tracker.course;
SELECT * FROM hb_student_tracker.review;
SELECT * FROM hb_student_tracker.course_student;



CREATE TABLE `review` (
	`id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `comment` varchar(256) DEFAULT NULL,
    `course_id` int(11) DEFAULT NULL,
    /* Foreign key relationship*/
    KEY `FK_COURSE_ID_idx` (`course_id`),
    CONSTRAINT `FK_COURSE`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
);



CREATE TABLE `course_student` (
	`course_id` int(11) NOT NULL,
    `student_id` int(11) NOT NULL,
    
    PRIMARY KEY (`course_id`, `student_id`),
	CONSTRAINT `FK_COURSE_05`
	FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`),
    
    CONSTRAINT `FK_STUDENT`
    foreign key (`student_id`)
    REFERENCES `student` (`id`)
)









