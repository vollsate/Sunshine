package no.glv.sunshine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * @author GleVoll
 *
 */
public class ForecastFragment extends Fragment implements WeatherForecastValues {
	
	
	/**  */
	private static final String TAG = ForecastFragment.class.getSimpleName();
		
	/**
	 * 
	 */
	public ForecastFragment() {
		this (7);
	}

	
	/**
	 * 
	 * @param count
	 */
	public ForecastFragment(int count) {
		this( ROA_CITY_ID, count );
	}

	/**
	 * 
	 * @param id
	 * @param count
	 */
	public ForecastFragment(String id, int count) {
		setCityID( id );
		setCount( count );
	}
	
	
	/**
	 * 
	 * @param id
	 */
	public void setCityID( String id ) {
		WeatherRequest.GetInstance().getWeatherDataBean().setOwaCityID(  id );
	}
	
	
	public void setCount( int count ) {
		WeatherRequest.GetInstance().getWeatherDataBean().setOwaCount( count );
	}
	
	
	public void setXMLMode() {
		WeatherRequest.GetInstance().getWeatherDataBean().setOwaMode( OWA_MODE_XML );
	}
	
	
	public void setJSONMode() {
		WeatherRequest.GetInstance().getWeatherDataBean().setOwaMode( OWA_MODE_JSON );
	}

	/**
	 * 
	 */
	@Override
	public void onCreateOptionsMenu( Menu menu, MenuInflater inflater ) {
		inflater.inflate( R.menu.forecastfragment, menu );
	}

	
	
	/**
	 * 
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setHasOptionsMenu( true );
	}

	/**
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		int id = item.getItemId();
		if ( id == R.id.action_refresh ) {
			Log.d( TAG, "onOptionsItemSelected: Calling GetWeatherData( )" );
			
			//String cityID = item.getIntent().getStringExtra( EXTRA_CITYID );
			//if ( cityID != null )
			//	setCityID( cityID );
			
			WeatherRequest.GetWeatherData();
			return true;
		}

		return super.onOptionsItemSelected( item );
	}

	/**
	 * 
	 */
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState ) {

		View rootView = inflater.inflate( R.layout.fragment_main,
				container, false );

		String[] weatherData = { "I dag - Overskyet - -2/-5",
				"I morgen - Overskyet - -1/-3",
				"Ondsdag - Overskyet - -2/-5", "Torsdag - Snø - -4/-9",
				"Fredag - Sol, lette skyer - -2/-5",
				"Lørdag - Overskyet - -2/-5", "Søndag - Snø - -2/-5",
				"Mandag - Overskyet - -2/-5", "Tirsdag - Sol - -2/-5",
				"Onsdag - Overskyet - -2/-5", "Torsdag - SOL! - -2/-5",
				"Fredag - Storm - -8/-12", };

		List<String> weekForcast = new ArrayList<String>(
				Arrays.asList( weatherData ) );

		ArrayAdapter<String> forcastAdapter = new ArrayAdapter<String>(
				getActivity(), R.layout.list_item_forecast,
				R.id.list_item_forecast_textview, weekForcast );

		ListView listView = (ListView) rootView
				.findViewById( R.id.listview_forecast );
		listView.setAdapter( forcastAdapter );
		
		String forecast = getWeatherData( );
			
		return rootView;
	}
	
	
	/**
	 * 
	 * @param bundle
	 * @return
	 */
	private String getWeatherData() {
		String forecast = null;
		boolean forceRefresh = false;

		Bundle bundle = getActivity().getIntent().getExtras();
		Log.d( TAG, "getWeatherData():" + bundle );
		if (bundle != null ) {
			forceRefresh = bundle.getBoolean( EXTRA_FORCE_REFRESH );
			Log.d( TAG, "getWeatherData( ): " + forceRefresh);
		}
		
		if ( forceRefresh ) forecast = WeatherRequest.GetWeatherData();
		
		
		return forecast;
	}

}
