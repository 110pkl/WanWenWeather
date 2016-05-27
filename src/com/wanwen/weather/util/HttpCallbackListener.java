package com.wanwen.weather.util;

/**
 * 回调服务器返回的结果
 * @author Administrator
 *
 */
public interface HttpCallbackListener {
	
	void onFinish(String response);
	
	void onErrer(Exception e);

}
