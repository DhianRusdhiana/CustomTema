package com.dhian;

import android.os.*;
import android.app.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.widget.ListView.OnItemClickListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;

public class MainActivity extends ActionBarActivity implements OnItemClickListener 
{
	private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
	private ListView listView;
		
	private PackageManager packageManager = null;
	private List<ApplicationInfo> applist = null;
	private ApplicationAdapter listadaptor = null;
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
		
		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		drawer = (DrawerLayout) findViewById(R.id.drawer);
		toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};

		drawer.setDrawerListener(toggle);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	   CheckBox cb = (CheckBox)findViewById(R.id.customCheckBox);
	   cb.setChecked(true);
	   
	   SwitchCompat sw = (SwitchCompat)findViewById(R.id.customSwitchCompat);
	   sw.setChecked(true);
	   
	   SeekBar sb = (SeekBar)findViewById(R.id.customSeekBar);
	   sb.setMax(100);
	   sb.setProgress(50);
		
		packageManager = getPackageManager();

		listView = (ListView)findViewById(R.id.listApp);

		applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
		listadaptor = new ApplicationAdapter(MainActivity.this,
											 R.layout.app_list, applist);
		
		// Assign adapter to ListView
		listView.setAdapter(listadaptor); 

		// ListView Item Click Listener
		listView.setOnItemClickListener(this);
		
			}
			
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ApplicationInfo app = applist.get(arg2);
		try {
			Intent intent = packageManager
				.getLaunchIntentForPackage(app.packageName);

			if (null != intent) {
				startActivity(intent);
			}
		} catch (ActivityNotFoundException e) {
			Toast.makeText(MainActivity.this, e.getMessage(),
						   Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, e.getMessage(),
						   Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		toggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration configuration) {
		super.onConfigurationChanged(configuration);
		toggle.onConfigurationChanged(configuration);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			

			case R.id.item2:
				Intent intent = new Intent(MainActivity.this, Tema.class);
				startActivity(intent);
				return true;

			case R.id.item3:
				this.finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
	Context con = this;


	public static class viewHolder {
		TextView tname;
		
	}

	private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
		ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
		for (ApplicationInfo info : list) {
			try {
				if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
					applist.add(info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return applist;
	}

	
	@Override
    public void onBackPressed() {
        this.finish();
    }
	public void gantiTema(View view){
		Intent intent = new Intent(MainActivity.this, Tema.class);
		startActivity(intent);
	}
	
}
