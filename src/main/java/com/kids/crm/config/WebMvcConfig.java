package com.kids.crm.config;

import com.kids.crm.interceptor.GlobalVariablesInterceptor;
import com.kids.crm.interceptor.QuestionApiInterceptor;
import com.kids.crm.service.converter.IdToBatchConverter;
import com.kids.crm.service.converter.IdToStudentBatchConverter;
import com.kids.crm.service.converter.IdToStudentConverter;
import com.kids.crm.service.converter.IdToSubjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final Config config;

    @Autowired
    public WebMvcConfig(Config config) {
        this.config = config;
    }

    @Bean
    public QuestionApiInterceptor questionApiInterceptor() {
        return new QuestionApiInterceptor();
    }

    @Bean
    public GlobalVariablesInterceptor globalVariablesInterceptor(){
        return new GlobalVariablesInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(questionApiInterceptor())
                .addPathPatterns("/api/**");
        registry.addInterceptor(globalVariablesInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(config.getExamUiDomain())
                .exposedHeaders("jwtToken", "userId");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getIdToStudentConverter());
        registry.addConverter(getIdToBatchConverter());
        registry.addConverter(getIdToSubjectConverter());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("file:" + config.getResourceLocation() + "/");
    }

    @Bean
    public IdToStudentConverter getIdToStudentConverter() {
        return new IdToStudentConverter();
    }

    @Bean
    public IdToBatchConverter getIdToBatchConverter(){
        return new IdToBatchConverter();
    }

    @Bean
    public IdToSubjectConverter getIdToSubjectConverter(){
        return new IdToSubjectConverter();
    }

    @Bean
    public IdToStudentBatchConverter getIdToStudentBatchConverter(){
        return new IdToStudentBatchConverter();
    }
}
