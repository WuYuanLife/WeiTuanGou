package cn.heyl.weituangou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.adapter.CityAdapter;
import cn.heyl.weituangou.adapter.CitySearchAdapter;
import cn.heyl.weituangou.entity.City;
import cn.heyl.weituangou.presneter.CityPresneter;
import cn.heyl.weituangou.presneter.ICityPresneter;
import cn.heyl.weituangou.ui.MyGridView;

public class CityActivity extends Activity implements ICityActivity {
	@ViewInject(R.id.tvClear_city)
	private TextView tvClear;
	@ViewInject(R.id.tv1_city)
	private TextView tv1;
	@ViewInject(R.id.tvReturn_city)
	private TextView tvReturn;
	@ViewInject(R.id.gvRecently_city)
	private GridView gvRecently;
	@ViewInject(R.id.gvHot_city)
	private GridView gvHot;
	@ViewInject(R.id.gvAll_city)
	private MyGridView gvAll;
	@ViewInject(R.id.rlRecently_city)
	private RelativeLayout rlRecently;
	@ViewInject(R.id.etSearch_city)
	private EditText etSearch;
	@ViewInject(R.id.slSearch_city)
	private ScrollView slSearch;
	@ViewInject(R.id.sl_city)
	private ScrollView sl_city;
	@ViewInject(R.id.lvSearch_city)
	private ListView lvSearch;

	private CitySearchAdapter searchAdapter;
	private List<City> searchCities;
	private CityAdapter RecnetlyAdapter;
	private List<City> recnetlyCities;
	private CityAdapter HotAdapter;
	private List<City> hotCities;
	private CityAdapter AllAdapter;
	private List<City> allCities;
	private ICityPresneter presneter;
	private boolean isAllOk = false;
	
	private MyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		x.view().inject(this);
		etSearch.clearFocus();
		setlisteners();
		app=MyApplication.getApp();
		if(!app.isHaveRecently())
			tvReturn.setVisibility(View.GONE);
		presneter = new CityPresneter(this);
		presneter.loadRecnetlyCity();
		presneter.loadHotCity();
		presneter.loadAllCity();
	}

	@Override
	public void updateRecentlyAdapter(List<City> cities) {
		if (cities != null && cities.size() != 0) {
			rlRecently.setVisibility(View.VISIBLE);
			tv1.setText("当前城市 - "+cities.get(0).getCity_name());
			if (RecnetlyAdapter == null) {
				recnetlyCities = cities;
				RecnetlyAdapter = new CityAdapter(cities, this);
				gvRecently.setAdapter(RecnetlyAdapter);
			} else {
				recnetlyCities.clear();
				recnetlyCities.addAll(cities);
				RecnetlyAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public void updateHotAdapter(List<City> cities) {
		app.setHotCities(cities);
		if (HotAdapter == null) {
			hotCities = cities;
			HotAdapter = new CityAdapter(cities, this);
			gvHot.setAdapter(HotAdapter);
		} else {
			hotCities.clear();
			hotCities.addAll(cities);
			HotAdapter.notifyDataSetChanged();
		}

	}

	@Override
	public void updateAllAdapter(List<City> cities) {
		app.setAllCities(cities);
		if (AllAdapter == null) {
			allCities = cities;
			AllAdapter = new CityAdapter(cities, this);
			gvAll.setAdapter(AllAdapter);
			isAllOk = true;
		} else {
			allCities.clear();
			allCities.addAll(cities);
			AllAdapter.notifyDataSetChanged();
		}

	}

	@Override
	public void updateSearchAdapter(List<City> cities) {
		if (searchAdapter == null) {
			searchCities = cities;
			searchAdapter = new CitySearchAdapter(cities, this);
			lvSearch.setAdapter(searchAdapter);
		} else {
			searchCities.clear();
			searchCities.addAll(cities);
			searchAdapter.notifyDataSetChanged();
		}

	}

	private void setlisteners() {
		tvClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyApplication.getApp().clearCity();
				rlRecently.setVisibility(View.GONE);
			}
		});
		tvReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		etSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (isAllOk) {
					String searchCity = etSearch.getText().toString().trim();
					if (searchCity.length() > 0) {
						slSearch.setVisibility(View.VISIBLE);
						sl_city.setVisibility(View.GONE);
						presneter.loadSearchCity(searchCity, allCities);
					} else {
						sl_city.setVisibility(View.VISIBLE);
						slSearch.setVisibility(View.GONE);
					}
				}
			}
		});
		gvAll.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startMain(allCities, position);
			}
		});
		gvHot.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startMain(hotCities, position);
			}
		});
		gvRecently.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startMain(recnetlyCities, position);
			}
		});
		lvSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startMain(searchCities, position);
			}
		});

	}

	protected void startMain(List<City> Cities, int position) {
		app.setCurrentCity(Cities.get(position));
		Intent intent = new Intent(CityActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}
