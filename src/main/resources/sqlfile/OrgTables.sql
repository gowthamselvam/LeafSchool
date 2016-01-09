
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