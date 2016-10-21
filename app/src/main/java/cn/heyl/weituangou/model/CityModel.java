package cn.heyl.weituangou.model;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;

import com.google.gson.Gson;

import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.City;
import cn.heyl.weituangou.entity.CityResp;

/**
 * Creaded by heyl 2016-10-9
 */
public class CityModel implements ICityModel {
	private Gson gson;

	public CityModel() {
		gson = MyApplication.getGson();
	}

	@Override
	public void getRecnetlyCity(AsyncCallBack callback) {
		List<City> cities=MyApplication.getApp().readCity();
		if(cities==null){
			callback.onError(null);
		}else{
			callback.onSuccess(cities);
		}
	}

	@Override
	public void getHotCity(AsyncCallBack callback) {
		String hotCity = MyApplication.getApp().getString(R.string.hot_city)
				.toString();
		CityResp jsonResp = gson.fromJson(hotCity, CityResp.class);
		callback.onSuccess(jsonResp);
	}

	@Override
	public void getAllCity(final AsyncCallBack callback) {
		
		String uri = MyApplication.ALLCITY_URI;
		RequestParams requestParams = new RequestParams(uri);
		requestParams.addHeader("apikey", "5273086672dfbccfbf6079cd6b20bfb4");
		x.http().get(requestParams, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
			}

			@Override
			public void onFinished() {
			}

			@Override
			public void onSuccess(String resp) {
				// Log.i("hyl", resp);
				CityResp jsonResp = gson.fromJson(resp, CityResp.class);
				callback.onSuccess(jsonResp);
			}
		});
	}

	@Override
	public void getSearchCity(AsyncCallBack callback, String search,
			List<City> cities) {
		List<City> searchCities = new ArrayList<City>();
		for (City city : cities) {
			if (search.length() <= city.getCity_pinyin().length()) {
				String cityS = city.getCity_pinyin().substring(0,
						search.length());
//				Log.i("hyl", city.getCity_pinyin() + "!!" + cityS + "``"
//						+ search);
				if (cityS.equalsIgnoreCase(search)) {
					searchCities.add(city);
					continue;
				}
			}
			if (search.length() <= city.getCity_name().length()) {
				String cityS = city.getCity_name().substring(0,
						search.length());
//				Log.i("hyl", city.getCity_name() + "!!" + cityS + "``"
//						+ search);
				if (cityS.equalsIgnoreCase(search)) {
					searchCities.add(city);
					continue;
				}
			}
		}
		callback.onSuccess(searchCities);
	}

}
