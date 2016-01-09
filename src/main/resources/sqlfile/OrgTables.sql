CREATE TABLE `StudentContactDetails` (
  `familyid` int(11) NOT NULL AUTO_INCREMENT,
  `guardianname` varchar(100) NOT NULL,
  `guardiantype` tinyint(4) NOT NULL DEFAULT '-1',
  `fathername` varchar(100) NOT NULL,
  `mothername` varchar(100) NOT NULL,
  `caste` varchar(100) NOT NULL,
  `countrycode` varchar(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `contactnumber` bigint(20) DEFAULT '-1',
  PRIMARY KEY (`familyid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `StudentDetails` (
  `studentid` int(11) NOT NULL AUTO_INCREMENT,
  `studentname` varchar(100) NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '-1',
  `status` tinyint(4) NOT NULL,
  `regdate` bigint(20) NOT NULL,
  `familyid` int(11) NOT NULL,
  PRIMARY KEY (`studentid`),
  KEY `StudentDetails_fid` (`familyid`),
  CONSTRAINT `StudentDetails_fk_familyid` FOREIGN KEY (`familyid`) REFERENCES `StudentContactDetails` (`familyid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `DestinationGroup` (
  `destinationgroupid` int(11) NOT NULL AUTO_INCREMENT,
  `destinationgroupname` varchar(100) NOT NULL,
  PRIMARY KEY (`destinationgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `Destinations` (
  `destinationid` int(11) NOT NULL AUTO_INCREMENT,
  `destinationname` varchar(100) NOT NULL,
  `destinationgroupid` int(11) NOT NULL,
  PRIMARY KEY (`destinationid`),
  KEY `fk_did` (`destinationgroupid`),
  CONSTRAINT `Destinations_fk_destinationgroupid` FOREIGN KEY (`destinationgroupid`) REFERENCES `DestinationGroup` (`destinationgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffDetails` (
  `staffid` int(11) NOT NULL AUTO_INCREMENT,
  `staffname` varchar(100) NOT NULL,
  `contactnumber` bigint(20) DEFAULT '-1',
  `email` varchar(100) NOT NULL,
  `joiningdate` date DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  `prv_experience` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`staffid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffDestination` (
  `staffdestinationid` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) NOT NULL,
  `destinationid` int(11) NOT NULL,
  PRIMARY KEY (`staffdestinationid`),
  KEY `fk_sid` (`staffid`),
  KEY `fk_desid` (`destinationid`),
  CONSTRAINT `StaffDestination_fk_staffid` FOREIGN KEY (`staffid`) REFERENCES `StaffDetails` (`staffid`),
  CONSTRAINT `StaffDestination_fk_destinationid` FOREIGN KEY (`destinationid`) REFERENCES `Destinations` (`destinationid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `Subjects` (
  `subjectid` int(11) NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(100) NOT NULL,
  `subjecttype` tinyint(4) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `courses` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(50) NOT NULL,
  `section` varchar(20) NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffSubjectCourseHistory` (
  `staffsubjectcourseid` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) NOT NULL,
  `subjectid` int(11) NOT NULL,
  `acadamicyear` year(4) DEFAULT NULL,
  `courseid` int(11) NOT NULL,
  PRIMARY KEY (`staffsubjectcourseid`),
  KEY `StaffSubjectCourseHistory_sid` (`staffid`),
  KEY `StaffSubjectCourseHistory_desid` (`subjectid`),
  KEY `StaffSubjectCourseHistory_courseid` (`courseid`),
  CONSTRAINT `StaffSubjectCourseHistory_fk_staffid` FOREIGN KEY (`staffid`) REFERENCES `StaffDetails` (`staffid`),
  CONSTRAINT `StaffSubjectCourseHistory_fk_subjectid` FOREIGN KEY (`subjectid`) REFERENCES `Subjects` (`subjectid`),
  CONSTRAINT `StaffSubjectCourseHistory_fk_courseid` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `StudentCourseHistory` (
  `studentcoursehistoryid` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `acadamicyear` year(4) DEFAULT NULL,
  PRIMARY KEY (`studentcoursehistoryid`),
  KEY `StudentCourseHistory_courseid` (`courseid`),
  KEY `StudentCourseHistory_sid` (`studentid`),
  CONSTRAINT `StudentCourseHistory_fk_courseid` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`),
  CONSTRAINT `StudentCourseHistory_fk_studentid` FOREIGN KEY (`studentid`) REFERENCES `StudentDetails` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `ExamType` (
  `examtypeid` int(11) NOT NULL AUTO_INCREMENT,
  `examtypename` varchar(100) NOT NULL,
  PRIMARY KEY (`examtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `ExamDetails` (
  `examid` int(11) NOT NULL AUTO_INCREMENT,
  `examname` varchar(100) NOT NULL,
  `examtypeid` int(11) NOT NULL,
  PRIMARY KEY (`examid`),
  KEY `ExamDetails_examtypeid` (`examtypeid`),
  CONSTRAINT `ExamDetails_fk_examtypeid` FOREIGN KEY (`examtypeid`) REFERENCES `ExamType` (`examtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

 CREATE TABLE `ExamSubjectMapping` (
  `examsubjectid` int(11) NOT NULL AUTO_INCREMENT,
  `staffsubjectcourseid` int(11) NOT NULL,
  `examid` int(11) NOT NULL,
  PRIMARY KEY (`examsubjectid`),
  KEY `ExamSubjectMapping_courseid` (`staffsubjectcourseid`),
  KEY `ExamSubjectMapping_examid` (`examid`),
  CONSTRAINT `ExamSubjectMapping_fk_staffsubjectcourseid` FOREIGN KEY (`staffsubjectcourseid`) REFERENCES `StaffSubjectCourseHistory` (`staffsubjectcourseid`),
  CONSTRAINT `ExamSubjectMapping_fk_examid` FOREIGN KEY (`examid`) REFERENCES `ExamDetails` (`examid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `StudentAcadamicHistory` (
  `studenthistoryid` int(11) NOT NULL AUTO_INCREMENT,
  `historytype` tinyint(4) NOT NULL DEFAULT '-1',
  `examsubjectid` int(11) NOT NULL,
  `studentid` int(11) NOT NULL,
  PRIMARY KEY (`studenthistoryid`),
  KEY `ExamSubjectMapping_examsubjectid` (`examsubjectid`),
  KEY `StudentAcadamicHistory_sid` (`studentid`),
  CONSTRAINT `ExamSubjectMapping_fk_examsubjectid` FOREIGN KEY (`examsubjectid`) REFERENCES `ExamSubjectMapping` (`examsubjectid`),
  CONSTRAINT `StudentAcadamicHistory_fk_studentid` FOREIGN KEY (`studentid`) REFERENCES `StudentDetails` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

