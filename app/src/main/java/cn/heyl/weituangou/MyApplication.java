package cn.heyl.weituangou;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;

import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.heyl.weituangou.entity.City;
import cn.heyl.weituangou.entity.User;

/**
 * Creaded by heyl 2016-10-8
 */
public class MyApplication extends Application {
	private static double latitude;
	private static double longitude;
	private static MyApplication app;
	private City currentCity;
	private List<City> allCities;
	private List<City> hotCities;
	private List<City> latelyCities;
	private boolean isHaveRecently = false;
	private LocationClient locationClient;
	private User user;

	public static double getLatitude() {
		return latitude;
	}

	public static double getLongitude() {
		return longitude;
	}

	public List<City> getAllCities() {
		return allCities;
	}

	public void setAllCities(List<City> allCities) {
		this.allCities = allCities;
	}

	public List<City> getHotCities() {
		return hotCities;
	}

	public void setHotCities(List<City> hotCities) {
		this.hotCities = hotCities;
	}

	public City getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(City currentCity) {
		try {
			this.currentCity = currentCity;
			saveCity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveCity() throws Exception {
		if (latelyCities == null)
			latelyCities = new ArrayList<City>();
		latelyCities.add(0, currentCity);
		listClear(currentCity);
		File file = new File(getCacheDir(), "CITY.INFO");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				file));
		oos.writeObject(latelyCities);
		oos.flush();
		oos.close();
	}

	public List<City> readCity() {
		try {
			File file = new File(getCacheDir(), "CITY.INFO");
			if (file.isFile()) {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file));
				latelyCities = (List<City>) ois.readObject();
				ois.close();
			}
			isHaveRecently = !(latelyCities == null||latelyCities.size()<=0);
			if (isHaveRecently)
				setCurrentCity(latelyCities.get(0));
			return latelyCities;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private void listClear(City city) {
		for (int i = 1; i < latelyCities.size(); i++) {
			if (city.getCity_id().equals(latelyCities.get(i).getCity_id())) {
				latelyCities.remove(i);
			}
		}
		while (latelyCities.size() > 5) {
			latelyCities.remove(5);
		}
	}

	private static Gson gson;
	public static final String ALLCITY_URI = "http://apis.baidu.com/baidunuomi/openapi/cities";
	public static final String SHOP_URI = "http://apis.baidu.com/baidunuomi/openapi/searchshops";
	public static final String DEALS_URI = "http://apis.baidu.com/baidunuomi/openapi/searchdeals";
	public static final String CLASS_URI = "http://apis.baidu.com/baidunuomi/openapi/categories";
	public static final String DIS_URI = "http://apis.baidu.com/baidunuomi/openapi/districts";

	public static Gson getGson() {
		return gson;
	}

	public static MyApplication getApp() {
		return app;
	}

	public boolean isHaveRecently() {
		return isHaveRecently;
	}


	@Override
	public void onCreate() {
		super.onCreate();

		new Thread(){
			@Override
			public void run() {
				latelyCities = readCity();
			}
		}.start();

		x.Ext.init(this);
		app = this;
		gson = new Gson();

		// 初始化地图
		SDKInitializer.initialize(this);
		// 让框架中的接口指向实现类
		MyBdlocationListener myBdlocationListener = new MyBdlocationListener();
		locationClient = new LocationClient(this);
		locationClient.registerLocationListener(myBdlocationListener);

		locationClient.start();

	}

	// 2.3写实现类
	class MyBdlocationListener implements BDLocationListener {



		@Override
		public void onReceiveLocation(BDLocation bdLocation) {

			// 纬度
			latitude = bdLocation.getLatitude();
			// 经度
			longitude = bdLocation.getLongitude();
			Log.i("定位成功", "纬度=" + latitude + ",经度=" + longitude);
		}

	}

	public void saveToken(String token){
		SharedPreferences pre=getSharedPreferences("token", MODE_PRIVATE);
		SharedPreferences.Editor editor=pre.edit();
		editor.putString("token", token);
		editor.commit();
	}
	public String getToken(){
		SharedPreferences pre=getSharedPreferences("token", MODE_PRIVATE);
		String token=pre.getString("token", "");
		return token;
	}

	/**
	 * 保存当前用户
	 * @param user
	 */
	public void saveCurrentUser(User user){
		this.user = user;
	}

	public User getCurrentUser(){
		return this.user;
	}

	public  void clearCity() {
		File file = new File(getCacheDir(), "CITY.INFO");
		latelyCities.clear();
		file.delete();
	}
}
