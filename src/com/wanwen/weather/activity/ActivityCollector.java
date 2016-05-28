package com.wanwen.weather.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * Activity������
 * 
 * @author Administrator
 * 
 */
public class ActivityCollector {

	public static List<Activity> activitys = new ArrayList<Activity>();

	// ���Activity
	public static void AddAcitivity(Activity activity) {

		// ��������ɾ��
		if (!activitys.contains(activity)) {
			activitys.add(activity);
		}

	}
	
	// ɾ��Activity
	public static void removeActivity(Activity activity){
		
		if(activitys.contains(activity)){
			activitys.remove(activity);
		}
	}
	
	//ɾ������Activity
	public static void finishAll(){
		
		for(Activity activity : activitys){
			if(!activity.isFinishing()){
				activity.finish();
			}
			
		}
	}

}
