package cn.heyl.weituangou.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

/**
 * Creaded by heyl 2016-9-27
 */
public class CommonResponse extends StringRequest {

	public static String JSESSIONID=null;

	public CommonResponse(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers=super.getHeaders();
		if(headers==null||headers.equals(Collections.emptyMap())){
			headers=new HashMap<String, String>();
		}
		if(JSESSIONID!=null){
			headers.put("Cookie",JSESSIONID);
		}
		return headers;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		Map<String, String> headers=response.headers;
		String jsessionId=headers.get("Set-Cookie");
		if(jsessionId!=null){
			JSESSIONID=jsessionId.split(";")[0];
		}
		return super.parseNetworkResponse(response);
	}
}
