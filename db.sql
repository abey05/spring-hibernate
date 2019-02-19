/*USERS.sql*/

CREATE TABLE USERS(
	username VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL,
	enabled TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (username));

/*USER_ROLES.sql*/

CREATE TABLE USER_ROLES( 
	user_role_id INT(11) NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	role VARCHAR(45) NOT NULL,
	PRIMARY KEY (user_role_id),
	UNIQUE KEY uni_username_role (role,username),
	KEY fk_username_idx (username),
	CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES USERS(username));

