package cn.heyl.weituangou.model;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xutils.common.Callback;
import org.xutils.x;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.util.Log;
import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.entity.DealResp;
import cn.heyl.weituangou.entity.DisResp;
import cn.heyl.weituangou.entity.ImageUrl;
import cn.heyl.weituangou.entity.ShopResp;

/**
 * Creaded by heyl 2016-10-10
 */
public class ShopModel implements IShopModel {
	private Gson gson;
	private static ShopModel model;
	public synchronized static IShopModel newShopModel() {
		if(model==null){
			model=new ShopModel();
		}
		return model;
	}
	
	public ShopModel() {
		gson = MyApplication.getGson();
	}

	@Override
	public void getLoveList(int currentPage,final AsyncCallBack callback) {
		Log.i("hyl", "getLoveList()"+currentPage);
		String city_id = MyApplication.getApp().getCurrentCity().getCity_id();
		String uri = MyApplication.DEALS_URI + "?city_id=" + city_id
				+ "&sort=4&page="+currentPage+"&page_size=20&cat_ids=326";
//		uri+="&location="+MyApplication.getLongitude()+","+MyApplication.getLatitude();
		RequestParams requestParams = new RequestParams(uri);
		requestParams.addHeader("apikey", "5273086672dfbccfbf6079cd6b20bfb4");
		x.http().get(requestParams, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				Log.i("hyl", arg1+"网络onError: "+arg0);
			}


			@Override
			public void onFinished() {
			}

			@Override
			public void onSuccess(String resp) {
				// Log.i("hyl", resp);
				DealResp jsonResp = gson.fromJson(resp, DealResp.class);
				callback.onSuccess(jsonResp);
			}

		});

	}

	@Override
	public void getNearbyShopList(int cat_ids,int currentPage,int currentRadius,int currentSort,final AsyncCallBack callback) {
		String city_id = MyApplication.getApp().getCurrentCity().getCity_id();
		String uri = MyApplication.SHOP_URI + "?city_id=" + city_id
				+ "&page="+currentPage+"&page_size=10&deals_per_shop=4";
//		uri+="&radius="+currentRadius+"&location="+MyApplication.getLongitude()+"%2C"+MyApplication.getLatitude();
		if(cat_ids!=0)uri+="&cat_ids="+cat_ids;
		RequestParams requestParams = new RequestParams(uri);
		requestParams.addHeader("apikey", "5273086672dfbccfbf6079cd6b20bfb4");
		x.http().get(requestParams, new CommonCallback<String>() {
			@Override
			public void onCancelled(CancelledException arg0) {
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				Log.i("hyl", arg1+"网络onError: "+arg0);
			}

			@Override
			public void onFinished() {
			}

			@Override
			public void onSuccess(String resp) {
//				Log.i("hyl", resp);
//				Log.i("hyl", "ons1");
				ShopResp jsonResp = gson.fromJson(resp, ShopResp.class);
//				Log.i("hyl", "ons2");
				callback.onSuccess(jsonResp);
//				Log.i("hyl", "ons3");
				}
		});

	}

	@Override
	public void getImage(final AsyncCallBack callBack) {
		AsyncTask<String,String,ImageUrl> task=new AsyncTask<String, String, ImageUrl>() {
			@Override
			protected ImageUrl doInBackground(String... params) {
				try {
				Document doc = Jsoup.connect("https://m.nuomi.com/").get();
				String ele = doc.getElementById("j-index-topten").html();
				String ele1=ele.substring(29, ele.length()-12);
					ImageUrl IUrl=gson.fromJson(ele1, ImageUrl.class);
					return IUrl;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(ImageUrl s) {
				if (s!=null){
					callBack.onSuccess(s);
				}
			}
		};
				task.execute();
	}

	@Override
	public void getDis( final AsyncCallBack callBack) {
		String city_id = MyApplication.getApp().getCurrentCity().getCity_id();
		String uri = MyApplication.DIS_URI+"?city_id="+city_id;
		Log.d("hyl", "getDis: "+uri);
		RequestParams requestParams = new RequestParams(uri);
		requestParams.addHeader("apikey", "5273086672dfbccfbf6079cd6b20bfb4");
		x.http().get(requestParams, new CommonCallback<String>() {
			@Override
			public void onSuccess(String s) {

				DisResp jsonResp = gson.fromJson(s, DisResp.class);
//				Log.d("hyl", "onSuccess: "+jsonResp);
				callBack.onSuccess(jsonResp);
			}

			@Override
			public void onError(Throwable throwable, boolean b) {
			}

			@Override
			public void onCancelled(CancelledException e) {
			}

			@Override
			public void onFinished() {
			}
		});
	}

	@Override
	public void getSearchList(String keyword,int cat_ids,int page, int radius,int sort,final AsyncCallBack callback) {
		try {
			keyword = URLEncoder.encode(keyword, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String city_id = MyApplication.getApp().getCurrentCity().getCity_id();
		String uri = MyApplication.DEALS_URI + "?city_id=" + city_id
				+ "&keyword="+keyword+"&sort="+sort+"&page="+page+"&page_size=20";
//		uri+="&radius="+radius+"&location="+MyApplication.getLongitude()+","+MyApplication.getLatitude();
		if(cat_ids!=0)uri+="&cat_ids="+cat_ids;
		Log.i("hyl", "getSearchList: "+uri);
		RequestParams requestParams = new RequestParams(uri);
		requestParams.addHeader("apikey", "5273086672dfbccfbf6079cd6b20bfb4");
		x.http().get(requestParams, new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				StackTraceElement[] stackElements = arg0.getStackTrace();
				if (stackElements != null) {
					for (int i = 0; i < stackElements.length; i++) {
						Log.i("hyl", arg1 + "网络onError: " + stackElements[i].getClassName()+stackElements[i]);
					}
				}
			}

			@Override
			public void onFinished() {
			}

			@Override
			public void onSuccess(String resp) {
//				 Log.i("hyl", resp);
				DealResp jsonResp = gson.fromJson(resp, DealResp.class);
				callback.onSuccess(jsonResp);
				Log.i("hyl", "onSuccess: 2");
			}

		});
	}

	

}
