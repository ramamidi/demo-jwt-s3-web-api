CREATE TABLE `app_user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_role` (
  `app_user_id` int(11) NOT NULL,
  `role` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `location_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_info_id` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `zipcode` int(11) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


INSERT INTO `ram_demo`.`app_user`(`id`, `username`, `password`)
VALUES(1,'username1','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC');

INSERT INTO `ram_demo`.`app_user`(`id`,`username`,`password`)
VALUES(1,'username2','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC');

INSERT INTO `ram_demo`.`user_role` (`app_user_id`,`role`) VALUES (1,'ADMIN');

INSERT INTO `ram_demo`.`user_role`(`app_user_id`,`role`) VALUES (1,'USER');
