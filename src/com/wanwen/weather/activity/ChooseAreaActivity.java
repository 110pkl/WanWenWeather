package com.wanwen.weather.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanwen.weather.R;
import com.wanwen.weather.db.WanWenWeatherDB;
import com.wanwen.weather.domain.AddCity;
import com.wanwen.weather.domain.City;
import com.wanwen.weather.domain.County;
import com.wanwen.weather.domain.Province;
import com.wanwen.weather.util.Utility;

public class ChooseAreaActivity extends BaseActivity {

	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;

	// private ProgressDialog profressDialog;

	private List<String> dataList = new ArrayList<String>();
	/**
	 * 省列表
	 */
	private List<Province> provinceList;
	/**
	 * 市列表
	 */
	private List<City> cityList;
	/**
	 * 县列表
	 */
	private List<County> countyList;
	/**
	 * 选中的省份
	 */
	private Province selectedProvince;
	/**
	 * 选中的城市
	 */
	private City selectedCity;
	/**
	 * 选中的级别
	 */
	private int currentLevel;
	private ListView listView;
	private TextView titleText;
	private ArrayAdapter<String> adapter;
	private WanWenWeatherDB wanWenWeatherDB;
	private AddCity addCity;

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
		wanWenWeatherDB = WanWenWeatherDB.getInstance(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (currentLevel == LEVEL_PROVINCE) {
					selectedProvince = provinceList.get(position);
					queryCitues();
				} else if (currentLevel == LEVEL_CITY) {
					selectedCity = cityList.get(position);
//					queryCounties();
				}

			}

		});
		queryProvinces();// 加载省级数据
		

	}

	/**
	 * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器查询
	 */
	private void queryProvinces() {
		provinceList = wanWenWeatherDB.loadProvince();
		if (provinceList.size() > 0) {
			dataList.clear();// notifyDataSetChanged前先clear
			for (Province province : provinceList) {
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("中国");
			currentLevel = LEVEL_PROVINCE;
		} else {
			Utility.json(this, wanWenWeatherDB);// 软件一进来先解析json（存放的城市信息）
			queryProvinces();
		}
	}

	/**
	 * 查询选中省内的所有市，优先从数据库查询，如果没有查询到再去服务器查询
	 */
	private void queryCitues() {
		// 数据库Province里省份ID是1 而城市province_id=0，所以-1
		cityList = wanWenWeatherDB.loadCities(selectedProvince.getId() -1);
		if (cityList.size() > 0) {
			dataList.clear();
			for (City city : cityList) {
				dataList.add(city.getCityName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel = LEVEL_CITY;
		} else {
			Utility.json(this, wanWenWeatherDB);
			queryCitues();
		}

	}

	/**
	 * 查询选中市内的所有县，优先从数据库查询，如果没有查询到再去服务器查询
	 */
	private void queryCounties() {
		countyList = wanWenWeatherDB.loadCounty(selectedCity.getId());
		if (countyList.size() > 0) {
			dataList.clear();
			for (County county : countyList) {
				dataList.add(county.getCountyName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel = LEVEL_COUNTY;
		} else {
			Utility.json(this, wanWenWeatherDB);;
			queryCounties();
		}

	}

	/**
	 * 捕获Back按键，根据当前的级别来判断，此时应该返回市列表、省列表、还是直接退出。
	 */
	@Override
	public void onBackPressed() {
		if (currentLevel == LEVEL_CITY) {
			queryProvinces();
		} else {
			finish();
		}
	}

}
