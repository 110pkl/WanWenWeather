package com.wanwen.weather.activity;

import java.util.ArrayList;
import java.util.List;

import com.wanwen.weather.R;
import com.wanwen.weather.R.layout;
import com.wanwen.weather.db.WanWenWeatherDB;
import com.wanwen.weather.domain.City;
import com.wanwen.weather.domain.County;
import com.wanwen.weather.domain.Province;
import com.wanwen.weather.util.Utility;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseAreaActivity extends Activity {

	
	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CIRTY = 1;
	public static final int LEVEL_COUNTY = 2;

	// private ProgressDialog profressDialog;

	private List<String> dataList = new ArrayList<String>();
	/**
	 * ʡ�б�
	 */
	private List<Province> provinceList;
	/**
	 * ���б�
	 */
	private List<City> cityList;
	/**
	 * ���б�
	 */
	private List<County> countyList;
	/**
	 * ѡ�е�ʡ��
	 */
	private Province selectedProvince;
	/**
	 * ѡ�еĳ���
	 */
	private City selectedCity;
	/**
	 * ѡ�еļ���
	 */
	private int currentLevel;
	private ListView listView;
	private TextView titleText;
	private ArrayAdapter<String> adapter;
	private WanWenWeatherDB wenWeatherDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_ares);
		listView = (ListView) findViewById(R.id.list_view);
		titleText = (TextView) findViewById(R.id.title_text);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		wenWeatherDB = WanWenWeatherDB.getInstance(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(currentLevel == LEVEL_PROVINCE){
					selectedProvince = provinceList.get(position);
					queryCitues();
				}else if(currentLevel == LEVEL_CIRTY){
					selectedCity = cityList.get(position);
					queryCounties();
				}
				
			}

		});
		queryProvinces();//����ʡ������
		
	}
	/**
	 * ��ѯȫ�����е�ʡ�����ȴ����ݿ��ѯ�����û�в�ѯ����ȥ��������ѯ
	 */
	private void queryProvinces() {
		provinceList = wenWeatherDB.loadProvince();
		if(provinceList.size() > 0){
			dataList.clear();
			for(Province province : provinceList){
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("�й�");
			currentLevel = LEVEL_PROVINCE;
		}else{
			queryFromServer(null, "Province");
		}
		
	}
	
	/**
	 * ��ѯѡ��ʡ�ڵ������У����ȴ����ݿ��ѯ�����û�в�ѯ����ȥ��������ѯ
	 */
	private void queryCitues() {
		cityList = wenWeatherDB.loadCities(selectedProvince.getId());
		if(cityList.size() > 0){
			dataList.clear();
			for(City city : cityList){
				dataList.add(city.getCityName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel = LEVEL_CIRTY;
		}else{
			queryFromServer(selectedProvince.getProvinceCode(), "city");
		}
		
	}

	/**
	 * ��ѯѡ�����ڵ������أ����ȴ����ݿ��ѯ�����û�в�ѯ����ȥ��������ѯ
	 */
	private void queryCounties() {
		countyList = wenWeatherDB.loadCounty(selectedCity.getId());
		if(countyList.size() > 0){
			dataList.clear();
			for(County county : countyList){
				dataList.add(county.getCountyName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel = LEVEL_COUNTY;
		}else{
			queryFromServer(selectedCity.getCityCode(), "city");
		}
		
	}
	
	private void queryFromServer(Object object, String string) {
		// TODO Auto-generated method stub
	}
	
	
}
