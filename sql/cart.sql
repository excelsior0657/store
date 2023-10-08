CREATE TABLE t_cart (
	cid INT AUTO_INCREMENT COMMENT '购物车数据id',
	uid INT NOT NULL COMMENT '用户id',
	pid INT NOT NULL COMMENT '商品id',
	price BIGINT COMMENT '加入时商品单价',
	num INT COMMENT '商品数量',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (cid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;