package com.jh.dyamicDataSource.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqlTemplate {
    private static SqlSessionTemplate sqlSessionTemplate;

    public static void instance(SqlSessionTemplate sqlSessionTemplate) {
            sqlSessionTemplate = sqlSessionTemplate;
    }

    public static SqlSessionTemplate getSqlTemplate() {
        return sqlSessionTemplate;
    }

    public static SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        SqlTemplate.sqlSessionTemplate = sqlSessionTemplate;
    }
}
