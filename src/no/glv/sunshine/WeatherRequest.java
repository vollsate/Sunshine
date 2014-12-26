package no.glv.sunshine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class WeatherRequest implements WeatherForecastValues {

	/**  */
	private static WeatherRequest instance;
	/**  */
	private static final String TAG = WeatherRequest.class.getSimpleName();
	
	/** */
	private WeatherDataBean dataBean;
	
	/** */
	private String url;

	
	/**
	 * 
	 * @return
	 */
	static WeatherRequest GetInstance() {
		if ( instance == null ) {
			Log.d( TAG, "Creating instance." );
			instance = new WeatherRequest();
		}

		return instance;
	}

	
	/**
	 * 
	 * @param url
	 * @return
	 */
	static String GetWeatherData(  ) {
		return GetInstance().getWeatherData( );
	}

	
	/**
	 * 
	 */
	public WeatherRequest() {

	}
	
	
	WeatherDataBean getWeatherDataBean() {
		if ( dataBean == null ) 
			dataBean = new WeatherDataBean();
		
		return dataBean;
	}
	
	
	/**
	 * 
	 * @param bean
	 * @return
	 */
	private String createURL(WeatherDataBean bean ) {
		if ( ! dataBean.hasChanged() )
			return url;
		
		Uri uri = Uri.parse( OPEN_WEATHER_API_URL ).buildUpon(). 
				appendQueryParameter( OWA_CITYID, bean.getOwaCityID() ). 
				appendQueryParameter( OWA_COUNT, Integer.toString( bean.getOwaCount() ) ). 
				appendQueryParameter( OWA_MODE, bean.getOwaMode() ). 
				build();
		
		url = uri.toString();
		Log.d(TAG, "createURL(): " + url);
		return url; 
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getWeatherData( ) {
		return this.getWeatherData( dataBean);
	}

	
	/**
	 * 
	 * @param httpAddr
	 * @return
	 */
	public String getWeatherData( WeatherDataBean bean ) {
		Log.d( TAG, "Starting WeatherDataTask" );
		WeatherDataTask daTask = new WeatherDataTask( createURL( bean ) );
		daTask.execute( null, null, null );
		
		int iMax = 10;
		int c = 0;
		
		try {
			synchronized (this) {
				while (daTask.forcastData == null && (c < iMax )) {
					wait( 100 );
					c++;
				}
			}		
		} 
		catch ( Exception e ) {
			Log.e( "", "Waiting for forecastData", e );
		}
		
		Log.d( TAG, "Waited for :" + 100 * c + " millisec" );
		return daTask.forcastData;
	}

	
	
	/**
	 * 
	 * @author GleVoll
	 *
	 */
	public static class WeatherDataTask extends AsyncTask<Void, Void, Void> {

		private String forcastData = null;
		private String httpAddr = null;
		private URL url = null;

		
		public WeatherDataTask(String url ) {
			this.httpAddr = url;
		}
		
		@Override
		protected Void doInBackground( Void... params ) {
			HttpURLConnection con;
			StringBuffer sb = new StringBuffer();

			// Opening connection to Open Weather API
			try {
				url = new URL( httpAddr );

				Log.d( TAG, "Openning connection .." );
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod( "GET" );
				con.connect();

				Log.d( TAG, "Getting inputstream .." );
				BufferedReader br = new BufferedReader( new InputStreamReader(
						con.getInputStream() ) );

				Log.d( TAG, "Reading data .." );
				String line = null;
				while ( (line = br.readLine()) != null ) {
					sb.append( line );
				}

				Log.d( TAG, "Done!" );
				br.close();
			} 
			catch ( Exception e ) {
				Log.e( TAG, "Failed to read HTTP data", e );
			}

			forcastData = sb.toString();
			
			Log.v( TAG, forcastData );
			return null;
		}

	}

}
