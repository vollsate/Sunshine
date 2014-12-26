package no.glv.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements WeatherForecastValues {

	private static final String TAG = MainActivity.class.getSimpleName();

	/**
	 * 
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		if ( savedInstanceState == null ) {
			getSupportFragmentManager().beginTransaction()
					.add( R.id.container, new ForecastFragment() ).commit();
		}
		
		Bundle bundle = getIntent().getExtras();
		Log.d( TAG, "onCreate():" + bundle );
		if (bundle != null ) {
			boolean forceRefresh = bundle.getBoolean( EXTRA_FORCE_REFRESH );
			Log.d( TAG, "onCreate( ): " + forceRefresh);
		}	
	}
	
	
	@Override
	protected void onStart() {
		Log.d( TAG, "ENTER onStart()" );
		super.onStart();
	}

	/**
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch ( id ) {
		case R.id.action_settings:
			Intent intent = new Intent( this, SettingsActicity.class );
			startActivity( intent );
			return true;
			
		case R.id.action_exit:
			finish();

		default:
			return super.onOptionsItemSelected( item );
		}
		
	}
}
