package cn.heyl.weituangou.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.entity.User;
import cn.heyl.weituangou.util.CommonResponse;
import cn.heyl.weituangou.util.GlobalConsts;
import cn.heyl.weituangou.util.JSONParser;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Creaded by heyl 2016-9-27
 */
public class UserModel implements IUserModel {
	private RequestQueue queue;
	private MyApplication app;

	public UserModel() {
		app=MyApplication.getApp();
		queue =Volley.newRequestQueue(app) ;
	}

	@Override
	public void regist(final User user, final String code,
					   final AsyncCallBack callBack) {
		String url = GlobalConsts.URL_USER_REGIST;
		CommonResponse response = new CommonResponse(Request.Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject jobj = new JSONObject(response);
							int id = jobj.getInt("code");
							if (id == GlobalConsts.RESPONSE_CODE_SUCCESS) {
								
								callBack.onSuccess(null);
							} else {
								callBack.onError(jobj.getString("error_msg"));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("user.email", user.getEmail());
				map.put("user.nickname", user.getNickname());
				map.put("user.password", user.getPassword());
				map.put("number", code);
				return map;
			}
		};
		queue.add(response);
	}

	@Override
	public void showImageCode(final AsyncCallBack callBack) {
		String uri = GlobalConsts.URL_GET_IMAGE_CODE;
		ImageRequest request = new ImageRequest(uri, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				callBack.onSuccess(response);
			}
		}, 130, 50, Bitmap.Config.ARGB_8888, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}) {
			@Override
			protected Response<Bitmap> parseNetworkResponse(
					NetworkResponse response) {
				Map<String, String> headers = response.headers;
				String jsessionid = headers.get("Set-Cookie");
				if (jsessionid != null) {
					CommonResponse.JSESSIONID = jsessionid.split(";")[0];
				}
				return super.parseNetworkResponse(response);
			}
		};
		queue.add(request);
	}

	@Override
	public void login(final String userName, final String password,
			final AsyncCallBack callBack) {
		String url = GlobalConsts.URL_USER_LOGIN;
		CommonResponse response = new CommonResponse(Request.Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject obj=new JSONObject(response);
							int code=obj.getInt("code");
							if(code==GlobalConsts.RESPONSE_CODE_SUCCESS){
								JSONObject userobj=obj.getJSONObject("user");
								app.saveCurrentUser(JSONParser.parseUser(userobj));
								String token=obj.getString("token");
								app.saveToken(token);
								callBack.onSuccess(null);
							}else{
								callBack.onError(obj.getString("error_msg"));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
					}
				}) {
			@Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				Map<String, String> map =new HashMap<String, String>();
				map.put("email", userName);
				map.put("password", password);
				return map;
			}
		};
		queue.add(response);

	}

	@Override
	public void loginWithOutPwd(String token,
			final AsyncCallBack callBack) {
		String url=GlobalConsts.URL_USER_LOGIN_WITHOUT_PWD+"?token="+token;
		CommonResponse respose=new CommonResponse(Request.Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						try {
							JSONObject obj=new JSONObject(response);
							if(obj.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS){
								JSONObject user=obj.getJSONObject("user");
								app.saveCurrentUser(JSONParser.parseUser(user));
								callBack.onSuccess(null);
							}else{
								callBack.onError(obj.getString("error-msg"));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
					}
				}){
			
		};
		queue.add(respose);
	}

}
