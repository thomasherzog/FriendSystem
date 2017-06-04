CREATE TABLE friends_settings(
   uuid VARCHAR(40) PRIMARY KEY NOT NULL,
   togglerequests TINYINT(1) NOT NULL,
   togglenotify TINYINT(1) NOT NULL,
   togglemessages TINYINT(1) NOT NULL,
   togglejump TINYINT(1) NOT NULL
);

CREATE TABLE friends_friends(
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   uuidone VARCHAR(40) NOT NULL,
   uuidtwo VARCHAR(40) NOT NULL
);

CREATE TABLE friends_requests(
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   requester VARCHAR(40) NOT NULL,
   receiver VARCHAR(40) NOT NULL
);