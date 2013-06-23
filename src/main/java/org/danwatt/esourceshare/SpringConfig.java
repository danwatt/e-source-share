package org.danwatt.esourceshare;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses=SpringConfig.class)
public class SpringConfig extends WebMvcConfigurerAdapter {
	 @Bean
     public ViewResolver viewResolver() {
             InternalResourceViewResolver vr = new InternalResourceViewResolver();
             vr.setPrefix("/WEB-INF/views/");
             vr.setSuffix(".jsp");
             return vr;
     }

     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
             registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
     }
}
