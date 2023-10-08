CREATE DATABASE store CHARACTER SET utf8;

CREATE TABLE t_user (
	uid INT AUTO_INCREMENT COMMENT '用户id',
	username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
	password CHAR(32) NOT NULL COMMENT '密码',
	salt CHAR(36) COMMENT '盐值',
	phone VARCHAR(20) COMMENT '电话号码',
	email VARCHAR(30) COMMENT '电子邮箱',
	gender INT COMMENT '性别:0-女，1-男',
	avatar VARCHAR(50) COMMENT '头像',
	is_delete INT COMMENT '是否删除：0-未删除，1-已删除',
	created_user VARCHAR(20) COMMENT '日志-创建人',
	create_time DATETIME COMMENT '日志-创建时间',
	modified_user VARCHAR(20) COMMENT '日志-最后修改执行人',
	modified_time DATETIME COMMENT '日志-最后修改时间',
	PRIMARY KEY (uid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE t_address (
	aid INT AUTO_INCREMENT COMMENT '收货地址id',
	uid INT COMMENT '归属的用户id',
	name VARCHAR(20) COMMENT '收货人姓名',
	province_name VARCHAR(15) COMMENT '省-名称',
	province_code CHAR(6) COMMENT '省-行政代号',
	city_name VARCHAR(15) COMMENT '市-名称',
	city_code CHAR(6) COMMENT '市-行政代号',
	area_name VARCHAR(15) COMMENT '区-名称',
	area_code CHAR(6) COMMENT '区-行政代号',
	zip CHAR(6) COMMENT '邮政编码',
	address VARCHAR(50) COMMENT '详细地址',
	phone VARCHAR(20) COMMENT '手机',
	tel VARCHAR(20) COMMENT '固话',
	tag VARCHAR(6) COMMENT '标签',
	is_default INT COMMENT '是否默认：0-不默认，1-默认',
	created_user VARCHAR(20) COMMENT '创建人',
	create_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (aid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;