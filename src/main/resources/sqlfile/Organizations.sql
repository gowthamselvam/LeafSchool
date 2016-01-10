CREATE DATABASE IF NOT EXISTS Organizations; 
 
CREATE TABLE `OrgDetails` (
  `orgid` int(11) NOT NULL AUTO_INCREMENT,
  `orgname` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `timetype` varchar(25) DEFAULT NULL,
  `dateformat` varchar(25) DEFAULT NULL,
  `currencycode` varchar(25) DEFAULT NULL,
  `createdtime` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '-1',
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`orgid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `OrgUsers` (
  `luid` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `createtime` bigint(20) NOT NULL,
  `defaultorgid` int(11) DEFAULT '-1',
  PRIMARY KEY (`luid`),
  KEY `defaultorgid` (`defaultorgid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `OrgUserRoles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `orgid` int(11) NOT NULL,
  `luid` int(11) NOT NULL,
  `rolename` varchar(100) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_userid_role` (`rolename`,`luid`,`orgid`),
  KEY `fk_luid_idx` (`luid`),
  KEY `orgid` (`orgid`),
  CONSTRAINT `fk_luid` FOREIGN KEY (`luid`) REFERENCES `OrgUsers` (`luid`),
  CONSTRAINT `orguserroles_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `OrgDetails` (`orgid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;