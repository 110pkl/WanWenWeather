package com.wanwen.weather.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * ActivityµÄ»ùÀà
 * @author Administrator
 *
 */
public class BaseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActivityCollector.AddAcitivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		ActivityCollector.removeActivity(this);
	}

}
