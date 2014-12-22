package no.glv.sunshine;

public interface WeatherForecastValues {

	public static final String OPEN_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
	public static final String OWA_COUNT = "cnt";
	public static final String OWA_UNITS = "units";
	public static final String OWA_MODE = "mode";
	public static final String OWA_MODE_XML = "xml";
	public static final String OWA_MODE_JSON = "json";
	public static final String OWA_CITYID = "id";
	public static final String OWA_ROA_CITYID = "3141671";
	public static final String OWA_SEPARATOR = "&";
	
	
	public static final String WEATER_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?id=3141671&cnt=7&mode=xml";
	
	public static final String ROA_CITY_ID = "3141671";
	
	public static final String EXTRA_CITYID = "no.glv.sunshine.cityID";
	public static final String EXTRA_DAYS = "no.glv.sunshine.days";
	public static final String EXTRA_FORCE_REFRESH = "no.glv.sunshine.forceRefresh";

}
