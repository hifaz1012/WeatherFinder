package com.weatherfinder.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.weatherfinder.config.AppConfig;
import com.weatherfinder.domain.WeatherDetail;
import com.weatherfinder.service.WeatherFinderService;
import com.weatherfinder.util.ServiceException;

/**
 * @author hifaz
 * Weather Finder Service Unit Test cases
 */
public class WeatherFinderServiceTest {
	
	/**
	 * Unit Test cases for method findWeatherDetailsByZipCode
	 */
	@Test
	public void findWeatherDetailsByZipCode() {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		WeatherFinderService weatherFinderService = (WeatherFinderService)ctx.getBean("weatherFinderService");
		
		String invalidZipCodeError    = ctx.getEnvironment().getProperty("invalid.zip.code");
		String zipCodeNotFoundError   = ctx.getEnvironment().getProperty("zip.code.notfound");
		
		try {
			
			// Test for invalid zip code
			assertNotNull( weatherFinderService.findWeatherDetailsByZipCode(null).getError());
			assertThat( weatherFinderService.findWeatherDetailsByZipCode("").getError(), equalTo(invalidZipCodeError));
			assertThat( weatherFinderService.findWeatherDetailsByZipCode("abc12").getError(), equalTo(invalidZipCodeError));
			assertThat( weatherFinderService.findWeatherDetailsByZipCode("22-22").getError(), equalTo(invalidZipCodeError));
			assertThat( weatherFinderService.findWeatherDetailsByZipCode("999987").getError(), equalTo(invalidZipCodeError));
			assertThat( weatherFinderService.findWeatherDetailsByZipCode("33.33").getError(), equalTo(invalidZipCodeError));
			
			// Test for incorrect zip code result
			WeatherDetail incorrectWeatherDetail = weatherFinderService.findWeatherDetailsByZipCode("77777");
			assertNotNull(incorrectWeatherDetail.getError());
			assertThat(incorrectWeatherDetail.getError(), equalTo(zipCodeNotFoundError));
			
			// Test for correct zip code result
			WeatherDetail correctWeatherDetail = weatherFinderService.findWeatherDetailsByZipCode("94117");
			assertThat(correctWeatherDetail.getCity(), equalTo("Kite Hill, San Francisco"));
			assertThat(correctWeatherDetail.getState(), equalTo("California"));
			assertNotNull(correctWeatherDetail.getTemperatureFarenheit());
			assertNull(correctWeatherDetail.getError());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
