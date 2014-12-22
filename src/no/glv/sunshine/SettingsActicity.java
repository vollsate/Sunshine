package no.glv.sunshine;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class SettingsActicity extends ActionBarActivity implements WeatherForecastValues {
	
	private static final String TAG = SettingsActicity.class.getSimpleName();

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_settings_acticity );
		if ( savedInstanceState == null ) {
			getSupportFragmentManager().beginTransaction()
					.add( R.id.container, new PlaceholderFragment() ).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.settings_acticity, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if ( id == R.id.action_settings ) {
			return true;
		}
		return super.onOptionsItemSelected( item );
	}
	
	
	public void updateSettings(View view ) {
		Log.d( TAG, "ENTER updateSettigns(): " + view.toString());
		
		EditText etCityID = (EditText) findViewById( R.id.edittext_cityID );
		EditText etDays = (EditText) findViewById( R.id.editText_days );
		
		String newCityID = etCityID.getText().toString();
		int newDays = Integer.parseInt( etDays.getText().toString() );
		
		Intent intent = new Intent( this, MainActivity.class );
		intent.putExtra( EXTRA_CITYID, newCityID );
		intent.putExtra( EXTRA_DAYS, newDays );
		intent.putExtra( EXTRA_FORCE_REFRESH, true );
		
		startActivity( intent );
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState ) {
			View rootView = inflater.inflate(
					R.layout.fragment_settings_acticity, container, false );
			return rootView;
		}
	}
}
