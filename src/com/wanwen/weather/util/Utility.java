package com.wanwen.weather.util;

import android.text.TextUtils;

import com.wanwen.weather.db.WanWenWeatherDB;
import com.wanwen.weather.domain.City;
import com.wanwen.weather.domain.County;
import com.wanwen.weather.domain.Province;

/**
 * ����ʡ��������
 * @author Administrator
 *
 */
public class Utility {

	/**
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvinceResponse(
			WanWenWeatherDB wanWenWeatherDB, String response) {

		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// ���������������ݴ洢��Province��
					wanWenWeatherDB.saveProvince(province);
				}
				return true;
			}
		}

		return false;

	}

	/**
	 * �����ʹ�����������ص��м�����
	 */
	public synchronized static boolean handleCityesResponse(
			WanWenWeatherDB wanWenWeatherDB, String response, int provinceId) {
		//�����Ϊ��
		if (!TextUtils.isEmpty(response)) {
			String[] allCitys = response.split(",");
			if (allCitys != null && allCitys.length > 0) {
				for (String c : allCitys) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					
					// ���������������ݴ洢��Province��
					wanWenWeatherDB.saveCity(city);
				}
				return true;
			}
		}

		return false;

	}
	
	/**
	 * �����ʹ�����������ص��м�����
	 */
	public synchronized static boolean handleCountiesResponse(
			WanWenWeatherDB wanWenWeatherDB, String response, int cityId) {
		//�����Ϊ��
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					
					// ���������������ݴ洢��Province��
					wanWenWeatherDB.saveCounty(county);
				}
				return true;
			}
		}

		return false;

	}

}
