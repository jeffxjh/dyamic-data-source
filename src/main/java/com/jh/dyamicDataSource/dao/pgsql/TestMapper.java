package com.jh.dyamicDataSource.dao.pgsql;

import com.jh.dyamicDataSource.pojo.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("pgsqlTestMapper")
public interface TestMapper {
    List<Test> selectTest();
}
