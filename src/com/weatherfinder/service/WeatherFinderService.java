package com.weatherfinder.service;

import com.weatherfinder.domain.WeatherDetail;
import com.weatherfinder.util.ServiceException;

/**
 * @author hifaz
 * Weather Finder Service Interface
 */
public interface WeatherFinderService {
	
	/**
	 * @param zipCode
	 * @return
	 * @throws ServiceException
	 *  This method does validation for 5 digit zip code and fetches weather details from wunderground api in JSON format.
	 *  Wunderground JSON is parsed to retrieve weather details : city, state and temperature in farenheit 
	 *  and mapped to WeatherDetail domain class.
	 */
	public WeatherDetail findWeatherDetailsByZipCode(String zipCode) throws ServiceException;

}
