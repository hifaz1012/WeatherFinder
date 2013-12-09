package com.weatherfinder.domain;

/**
 * @author hifaz
 * Domain class to capture weather details and error
 */
public class WeatherDetail {
	
	private String city;
	private String state;
	private Float temperatureFarenheit; 
	private String error;
	
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public Float getTemperatureFarenheit() {
		return temperatureFarenheit;
	}
	public String getError() {
		return error;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setTemperatureFarenheit(Float temperatureFarenheit) {
		this.temperatureFarenheit = temperatureFarenheit;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	

}
