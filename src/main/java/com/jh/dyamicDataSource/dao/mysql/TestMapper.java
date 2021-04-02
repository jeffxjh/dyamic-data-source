package com.jh.dyamicDataSource.dao.mysql;

import com.jh.dyamicDataSource.pojo.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("mysqlTestMapper")
public interface  TestMapper {
    List<Test> selectTest();
}
