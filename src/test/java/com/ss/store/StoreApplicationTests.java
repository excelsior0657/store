package com.ss.store;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Resource
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }

    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
