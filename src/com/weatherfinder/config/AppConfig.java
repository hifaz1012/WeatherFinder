package com.weatherfinder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.weatherfinder.service.WeatherFinderService;
import com.weatherfinder.service.WeatherFinderServiceImpl;

/**
 * @author hifaz
 * Spring 3 application beans and property configuration
 */
@Configuration
@ComponentScan(basePackages = { "com.weatherfinder.service"})
@PropertySource("classpath:weather.properties")
public class AppConfig {
	
	@Value("${zip.code.notfound}") 
    private String zipCodeNotFoundError;
	
	@Value("${invalid.zip.code}") 
    private String invalidZipCodeError;
	
	@Value("${weather.data.url}") 
	private String weatherDataURL;
	
	@Bean
	public WeatherFinderService weatherFinderService() {
		WeatherFinderServiceImpl weatherFinderServiceImpl = new WeatherFinderServiceImpl();
		weatherFinderServiceImpl.setZipCodeNotFoundError(zipCodeNotFoundError);
		weatherFinderServiceImpl.setInvalidZipCodeError(invalidZipCodeError);
		weatherFinderServiceImpl.setWeatherDataURL(weatherDataURL);
		return weatherFinderServiceImpl;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}

}
