package com.jh.dyamicDataSource.api;

import com.jh.dyamicDataSource.config.SqlTemplate;
import com.jh.dyamicDataSource.pojo.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test/{type}")
    public ResponseEntity<String> test1(@PathVariable String type) {
        SqlSessionTemplate sqlTemplate = SqlTemplate.getSqlTemplate();
        List<Test> selectTest = sqlTemplate.selectList("selectTest");
        StringBuffer sb = new StringBuffer();
        for (Test o : selectTest) {
            sb.append(o.toString()).append("-");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}
