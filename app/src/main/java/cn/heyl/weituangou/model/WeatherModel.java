package cn.heyl.weituangou.model;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.os.AsyncTask;

import cn.heyl.weituangou.entity.DayOrNight;
import cn.heyl.weituangou.entity.Environment;
import cn.heyl.weituangou.entity.Resp;
import cn.heyl.weituangou.entity.Weather;


public class WeatherModel implements IWeatherModel{

	public void getResp(final String city, final AsyncCallBack Callback) {
		AsyncTask<String, String, Resp> task=new AsyncTask<String, String, Resp>(){

			@Override
			protected Resp doInBackground(String... params) {
				try {
					Resp resp=queryWeather(city);
					return resp;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		@Override
		protected void onPostExecute(Resp result) {
			Callback.onSuccess(result);
		}	
		};
		task.execute();
	}

	protected Resp queryWeather(String city) throws Exception {
		city = URLEncoder.encode(city, "utf-8");
		URL url = new URL("http://wthrcdn.etouch.cn/WeatherApi?city=" + city);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();

		SAXReader reader = new SAXReader();
		Document document = reader.read(is);
		Element root = document.getRootElement();
//		Log.i("hyl", "queryWeather()"+root);
		return setResp(root);
	}

	private Resp setResp(Element root) {
		Resp resp = new Resp();
		if (root.element("city") != null) {
			resp.setCity(root.element("city").getText());
			resp.setWendu(root.elementText("wendu"));
			resp.setUpdatetime(root.elementText("updatetime"));
			resp.setFengli(root.elementText("fengli"));
			resp.setShidu(root.elementText("shidu"));
			resp.setFengxiang(root.elementText("fengxiang"));
			resp.setSunset_1(root.elementText("sunset_1"));
			resp.setSunrise_1(root.elementText("sunrise_1"));

			Element eleEN = root.element("environment");
			resp.setEnvironment(getEnvironment(eleEN));

			Element eleForecast = root.element("forecast");
			resp.setForecast(getForecast(eleForecast));
			return resp;
		} else {
			return null;
		}

	}

	private Environment getEnvironment(Element eleEN) {
		if (eleEN != null) {
			Environment en = new Environment();
			en.setAqi(eleEN.elementText("aqi"));
			en.setPm25(eleEN.elementText("pm25"));
			en.setSuggest(eleEN.elementText("suggest"));
			en.setQuality(eleEN.elementText("quality"));
			en.setO3(eleEN.elementText("o3"));
			en.setCo(eleEN.elementText("co"));
			en.setPm10(eleEN.elementText("pm10"));
			en.setSo2(eleEN.elementText("so2"));
			en.setNo2(eleEN.elementText("no2"));
			en.setTime(eleEN.elementText("time"));
			return en;
		}
		return null;
	}


	private List<Weather> getForecast(Element eleForecast) {
		List<Weather> forecast = new ArrayList<Weather>();
		List<Element> eleForecasts = eleForecast.elements();
		for (Element eleW : eleForecasts) {
			Weather w = new Weather();
			w.setDate(eleW.elementText("date"));
			w.setHigh(eleW.elementText("high"));
			w.setLow(eleW.elementText("low"));

			Element eleDay = eleW.element("day");
			DayOrNight day = new DayOrNight();
			day.setType(eleDay.elementText("type"));
			day.setFengxiang(eleDay.elementText("fengxiang"));
			day.setFengli(eleDay.elementText("fengli"));
			w.setDay(day);

			Element eleNight = eleW.element("night");
			DayOrNight night = new DayOrNight();
			night.setType(eleNight.elementText("type"));
			night.setFengxiang(eleNight.elementText("fengxiang"));
			night.setFengli(eleNight.elementText("fengli"));
			w.setNight(night);

			forecast.add(w);
		}
		return forecast;

	}

}
