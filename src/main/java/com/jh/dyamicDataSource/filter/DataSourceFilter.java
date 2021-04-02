package com.jh.dyamicDataSource.filter;

import com.jh.dyamicDataSource.config.SqlTemplate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
@WebFilter(filterName = "LoginFilter",urlPatterns = {"/"})
public class DataSourceFilter implements Filter {
    @Resource
    private SqlSessionTemplate mysqlSqlSessionTemplate;
    @Resource
    private SqlSessionTemplate pgsqlSqlSessionTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化....");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行....");
        HttpServletRequest request =(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        String path=request.getRequestURI();
        if (path.contains("/pgsql/")) {
            SqlTemplate.setSqlSessionTemplate(pgsqlSqlSessionTemplate);
        } else {
            SqlTemplate.setSqlSessionTemplate(mysqlSqlSessionTemplate);
        }
        SqlSessionTemplate sqlTemplate = SqlTemplate.getSqlSessionTemplate();
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁....");

    }
}
