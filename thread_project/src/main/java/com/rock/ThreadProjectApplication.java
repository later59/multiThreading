package com.rock;

import com.rock.threadLocal.filter.HttpFilter;
import com.rock.threadLocal.interceptor.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class ThreadProjectApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(ThreadProjectApplication.class, args);
    }

    /**
     * 配置过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //设置拦截类
        filterRegistrationBean.setFilter(new HttpFilter());
        //设置拦截路径
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        return filterRegistrationBean;
    }

    /**
     * 配置拦截器需要继承WebMvcConfigurationSupport类，重写里面的addInterceptors方法
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //设置拦截器，并且设置拦截路径
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
