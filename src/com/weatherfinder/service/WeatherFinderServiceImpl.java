package com.weatherfinder.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.weatherfinder.domain.WeatherDetail;
import com.weatherfinder.util.ServiceException;

/**
 * @author hifaz
 * Weather Finder Service Implementation
 */
@Service
public class WeatherFinderServiceImpl implements WeatherFinderService {
	
	private String zipCodeNotFoundError;
	private String invalidZipCodeError;
	private String weatherDataURL;
	
	/* (non-Javadoc)
	 * @see com.weatherfinder.service.WeatherFinderService#findWeatherDetailsByZipCode(java.lang.String)
	 */
	@Override
	public WeatherDetail findWeatherDetailsByZipCode(String zipCode)
			throws ServiceException {
		
		WeatherDetail weatherDetail = new WeatherDetail();
		
		try {
			
			if(null == zipCode || ("").equals(zipCode) || (!zipCode.matches("\\d{5}")) ) {
				weatherDetail.setError(invalidZipCodeError);
				return weatherDetail;
			}
			
			StringBuffer wundergroundURL = new StringBuffer(weatherDataURL).append(zipCode).append(".json");			
			JsonNode node 				 = new ObjectMapper().readTree(new URL(wundergroundURL.toString()));
			JsonNode errorNode 			 = node.get("response").get("error");
			
			if(null == errorNode) {
				JsonNode currentObservationNode  = node.get("current_observation");
				JsonNode observationLocationNode = currentObservationNode.get("observation_location");
				weatherDetail.setCity(observationLocationNode.get("city").asText());
				weatherDetail.setState(observationLocationNode.get("state").asText());
				weatherDetail.setTemperatureFarenheit((float)currentObservationNode.get("temp_f").asDouble());
			}
			else {
				weatherDetail.setError(zipCodeNotFoundError);
			}
			
		} catch (JsonProcessingException | MalformedURLException e) {
			throw new ServiceException("Error during weather details fetch using wunderground", e);
		} catch(IOException e) {
			throw new ServiceException("Error during weather details fetch using wunderground", e);
		} catch(Exception e) {
			throw new ServiceException("Error during weather details fetch by zipcode", e);
		}
		
		return weatherDetail;
	}

	public void setZipCodeNotFoundError(String zipCodeNotFoundError) {
		this.zipCodeNotFoundError = zipCodeNotFoundError;
	}

	public void setInvalidZipCodeError(String invalidZipCodeError) {
		this.invalidZipCodeError = invalidZipCodeError;
	}

	public void setWeatherDataURL(String weatherDataURL) {
		this.weatherDataURL = weatherDataURL;
	}

}
