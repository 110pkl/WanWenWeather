package com.wanwen.weather.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * Activity管理器
 * 
 * @author Administrator
 * 
 */
public class ActivityCollector {

	public static List<Activity> activitys = new ArrayList<Activity>();

	// 添加Activity
	public static void AddAcitivity(Activity activity) {

		// 不包含才删除
		if (!activitys.contains(activity)) {
			activitys.add(activity);
		}

	}
	
	// 删除Activity
	public static void removeActivity(Activity activity){
		
		if(activitys.contains(activity)){
			activitys.remove(activity);
		}
	}
	
	//删除所有Activity
	public static void finishAll(){
		
		for(Activity activity : activitys){
			if(!activity.isFinishing()){
				activity.finish();
			}
			
		}
	}

}
