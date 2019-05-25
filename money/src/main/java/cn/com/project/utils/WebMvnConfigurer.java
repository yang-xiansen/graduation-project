package cn.com.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class WebMvnConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private WebInterceptor webInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/index") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        //        registry.addInterceptor(webInterceptor).addPathPatterns("/**")
        //                .excludePathPatterns("/login", "/index");
        super.addInterceptors(registry);
    }

    //    @Override
    //    public void addViewControllers(ViewControllerRegistry registry) {
    //
    //        registry.addViewController("/").setViewName("forward:/index/MyJsp.jsp");
    //
    //        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    //
    //        super.addViewControllers(registry);
    //
    //    }

}
