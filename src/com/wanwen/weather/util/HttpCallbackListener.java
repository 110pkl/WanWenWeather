package com.wanwen.weather.util;

/**
 * �ص����������صĽ��
 * @author Administrator
 *
 */
public interface HttpCallbackListener {
	
	void onFinish(String response);
	
	void onErrer(Exception e);

}
