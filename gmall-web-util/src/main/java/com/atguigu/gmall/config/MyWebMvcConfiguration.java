package com.atguigu.gmall.config;

        import com.atguigu.gmall.interceptors.LoginInterceptor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Description:
 * ClassName: MyWebMvcConfiguration
 * date: 2019/12/12 22:28
 *
 * @author fancy Email:395765197@qq.com
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
    }
}
