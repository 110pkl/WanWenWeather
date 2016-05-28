package com.wanwen.weather.domain;

import java.util.List;

/**
 * 省市信息封装
 * @author Administrator
 *
 */
public class Data {
	
	public List<sheng> data;
	
	public class sheng{
		
		public String 省;
		public List<shi> 市;
		
	}
	
	public class shi{
		public String 市名;
		public String 邮编;
	}

}
