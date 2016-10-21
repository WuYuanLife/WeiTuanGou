package cn.heyl.weituangou.presneter;

import java.util.List;

import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.activity.ICityActivity;
import cn.heyl.weituangou.entity.City;
import cn.heyl.weituangou.entity.CityResp;
import cn.heyl.weituangou.model.CityModel;
import cn.heyl.weituangou.model.ICityModel;
import cn.heyl.weituangou.model.IModel.AsyncCallBack;

/**
 * Creaded by heyl 2016-10-9
 */
public class CityPresneter implements ICityPresneter {
	private ICityActivity view;
	private ICityModel model;
	private MyApplication app;

	public CityPresneter(ICityActivity view) {
		this.view = view;
		model = new CityModel();
		app = MyApplication.getApp();
	}

	@Override
	public void loadRecnetlyCity() {
		model.getRecnetlyCity(new AsyncCallBack() {

			@Override
			public void onSuccess(Object obj) {
				view.updateRecentlyAdapter((List<City>) obj);

			}

			@Override
			public void onError(Object obj) {

			}
		});

	}

	@Override
	public void loadHotCity() {
		if (app.getHotCities() != null) {
			view.updateHotAdapter(app.getHotCities());
			return;
		}
		model.getHotCity(new AsyncCallBack() {

			@Override
			public void onSuccess(Object obj) {
				CityResp resp = (CityResp) obj;
				List<City> cities = resp.getCities();
				// Log.i("hyl", cities.toString());
				view.updateHotAdapter(cities);

			}

			@Override
			public void onError(Object obj) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void loadAllCity() {
		if (app.getAllCities() != null) {
			view.updateAllAdapter(app.getAllCities());
			return;
		}
		model.getAllCity(new AsyncCallBack() {

			@Override
			public void onSuccess(Object obj) {
				CityResp resp = (CityResp) obj;
				List<City> cities = resp.getCities();
				// Log.i("hyl", cities.toString());
				view.updateAllAdapter(cities);
			}

			@Override
			public void onError(Object obj) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void loadSearchCity(String search, List<City> cities) {
		model.getSearchCity(new AsyncCallBack() {

			@Override
			public void onSuccess(Object obj) {
				List<City> SearchCities = (List<City>) obj;
//				Log.i("hyl", SearchCities.toString());
				view.updateSearchAdapter(SearchCities);
			}

			@Override
			public void onError(Object obj) {

			}
		}, search, cities);
	}
}
