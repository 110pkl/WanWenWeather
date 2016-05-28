package com.wanwen.weather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.wanwen.weather.R;
import com.wanwen.weather.db.WanWenWeatherDB;
import com.wanwen.weather.domain.City;
import com.wanwen.weather.domain.Data;
import com.wanwen.weather.domain.Province;

/**
 * ����ʡ��������
 * 
 * @author Administrator
 * 
 */
public class Utility {

	private static Data mdata;

	public static void json(Context context, WanWenWeatherDB wanWenWeatherDB) {
		
//		try {
//			// ��ȡ json�ļ�
//			InputStream is = context.getResources().openRawResource(
//					R.raw.cityinfo);
//			BufferedReader reader = new BufferedReader(
//					new InputStreamReader(is));
//			StringBuilder response = new StringBuilder();
//			String line;
//			while ((line = reader.readLine()) != null) {
//				response.append(line);
//			}
//			String json = response.toString();
//			// ���ַ���jsonת��Ϊjson�����Ա���ȡ������
//			JSONObject jsonObject = new JSONObject(json);
//			// ����info���飬���������������������ݾͱ�ʾһ�����飬ʹ��JSONArray�������
//			JSONArray provinceArray = jsonObject.getJSONArray("���д���");
//			// ����JSONArray����
//			for (int i = 0; i < provinceArray.length(); i++) {
//				// ȡ��ʡ����
//				JSONObject provinceObj = provinceArray.getJSONObject(i);
//				// ���ʡ������
//				String provinceName = provinceObj.getString("ʡ");
//				// �����������������ݾͱ�ʾһ��JSONArray����������Ҫ�ٴ���һ��JSONArray����
//				JSONArray cityArray = provinceObj.getJSONArray("��");
//				for (int j = 0; j < cityArray.length(); j++) {
//					JSONObject cityObj = cityArray.getJSONObject(j);
//					String cityName = cityObj.getString("����");
//					String cityCode = cityObj.getString("����");
//					City city = new City();
//
//					city.setCityName(cityName);
//					city.setCityCode(cityCode);
//					city.setProvinceId(i);
//					wanWenWeatherDB.saveCity(city);
//				}
//				Province province = new Province();
//
//				province.setProvinceName(provinceName);
//				// ���������������ݴ洢��Province��
//				wanWenWeatherDB.saveProvince(province);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
		
		try {
			InputStream is = context.getResources().openRawResource(
					R.raw.cityinfo);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			String json = response.toString();
			
			Gson gson = new Gson();
			mdata = gson.fromJson(json, Data.class);
			String privinceName = mdata.data.get(0).ʡ.toString();
			Province province = new Province();
			province.setProvinceName(privinceName);
			wanWenWeatherDB.saveProvince(province);
			String cityName = mdata.data.get(0).��.toString();
			City city = new City();
			city.setCityName(cityName);
			wanWenWeatherDB.saveCity(city);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
