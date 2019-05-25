package cn.com.project;

import org.jfree.chart.servlet.DisplayChart;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.com.project.mapper")
public class MoneyApplication {
    @Bean //注册Servlet
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new DisplayChart(), "/DisplayChart");
    }
    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
    }
}
