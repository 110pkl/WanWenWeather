package com.wanwen.weather.domain;

import java.util.List;

/**
 * ʡ����Ϣ��װ
 * @author Administrator
 *
 */
public class Data {
	
	public List<sheng> data;
	
	public class sheng{
		
		public String ʡ;
		public List<shi> ��;
		
	}
	
	public class shi{
		public String ����;
		public String �ʱ�;
	}

}
