-- 权限表 --
CREATE TABLE permission (
  pid int(11) NOT NULL AUTO_INCREMENT,
  pname VARCHAR(255) NOT NULL DEFAULT '',
  url VARCHAR(255) DEFAULT '',
  PRIMARY KEY (pid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO permission VALUES('1','create','');
INSERT INTO permission VALUES('2','read','');
INSERT INTO permission VALUES('3','update','');
INSERT INTO permission VALUES('4','delete','');

-- 角色表 --
CREATE TABLE role (
  rid int(11) NOT NULL AUTO_INCREMENT,
  rname VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY(rid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO role VALUES('1','admin');
INSERT INTO role VALUES('2','customer');

-- 用户表 --
CREATE TABLE user (
  uid int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL DEFAULT '',
  password VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY(uid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO user VALUES('1','wanghaisheng','123');
INSERT INTO user VALUES('2','wuyaling','123');

-- 权限角色关系表 --
CREATE TABLE permission_role (
 rid int(11) NOT NULL,
 pid int(11) NOT NULL,
 KEY idx_pid (pid),
 KEY idx_rid (rid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO permission_role VALUES('1','1');
INSERT INTO permission_role VALUES('1','2');
INSERT INTO permission_role VALUES('1','3');
INSERT INTO permission_role VALUES('1','4');
INSERT INTO permission_role VALUES('2','3');
INSERT INTO permission_role VALUES('3','4');

-- 用户角色关系表 --
CREATE TABLE user_role (
 uid int(11) NOT NULL,
 rid int(11) NOT NULL,
 KEY idx_pid (uid),
 KEY idx_rid (rid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO user_role VALUES ('1','1');
INSERT INTO user_role VALUES ('2','2');
