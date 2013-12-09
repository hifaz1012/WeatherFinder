package com.weatherfinder.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weatherfinder.domain.WeatherDetail;
import com.weatherfinder.service.WeatherFinderService;
import com.weatherfinder.util.ServiceException;

/**
 * @author hifaz
 * Weather Finder Controller
 */
@Controller
public class WeatherFinderController {
	
	@Autowired
    private WeatherFinderService weatherFinderService;
	
	private static final Logger logger = Logger.getLogger(WeatherFinderController.class);
	
	/**
	 * @param model
	 * @return
	 *  Weather Finder home page
	 */
	@RequestMapping(value="/weatherFinder",method=RequestMethod.GET)
	public String weatherFinderHomePage(Model model){
		return "weatherFinder";
	}
	
	/**
	 * @param zipCode
	 * @param model
	 * @return
	 * Controller method takes zip code as input and return weather details : city, state and temperature in farenheit
	 */
	@RequestMapping(value="/findWeatherDetails", method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody WeatherDetail findWeatherDetails(@RequestParam("zipCode") String zipCode, Model model) {
		
		try {
			WeatherDetail weatherDetail = weatherFinderService.findWeatherDetailsByZipCode(zipCode);
			return weatherDetail;
		} catch (ServiceException e) {
			model.addAttribute("weatherFinderErrorMsg", e.getMessage());
		}
		catch (Exception e) {
			model.addAttribute("weatherFinderErrorMsg", "error.operation");
			logger.error("Error in Find Weather Details Controller", e);
		}
		return null;
	}

}
