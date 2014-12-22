package no.glv.sunshine;


/**
 * 
 * @author GleVoll
 *
 */
class WeatherDataBean {

	
	/**  */
	private String owaCityID;
	private String owaMode;
	private int owaCount;
	
	private boolean changed;

	
	public WeatherDataBean() {
		this(WeatherForecastValues.ROA_CITY_ID, WeatherForecastValues.OWA_MODE_JSON, 7);
	}

	public WeatherDataBean(String id, String mode, int count) {
		this.owaCityID = id;
		this.owaMode = mode;
		this.owaCount = count;
	}


	public String getOwaCityID() {
		return owaCityID;
	}


	public void setOwaCityID( String owaCityID ) {
		this.owaCityID = owaCityID;
		changed = true;
	}


	public String getOwaMode() {
		return owaMode;
	}


	public void setOwaMode( String owaMode ) {
		this.owaMode = owaMode;
		changed = true;
	}


	public int getOwaCount() {
		return owaCount;
	}


	public void setOwaCount( int owaCount ) {
		this.owaCount = owaCount;
		changed = true;
	}
	
	
	public boolean hasChanged() {
		boolean value = changed;
		if (changed == true)
			changed = false;

		return value;
	}
}
