package com.example.Spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
public class MessageConfig {
    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(new Locale("vi"));

        resolver.setSupportedLocales(Arrays.asList(new Locale("vi"),Locale.ENGLISH));
        return resolver;
    }
}
