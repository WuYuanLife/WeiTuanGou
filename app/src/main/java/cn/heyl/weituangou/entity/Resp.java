package cn.heyl.weituangou.entity;

import java.util.List;


/**
 */
public class Resp {
	private String city;
	private String updatetime;
	private String wendu;
	private String fengli;
	private String shidu;
	private String fengxiang;
	private String sunrise_1;
	private String sunset_1;
	private Environment environment;
	private List<Weather> forecast;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getWendu() {
		return wendu;
	}

	public void setWendu(String wendu) {
		this.wendu = wendu;
	}

	public String getFengli() {
		return fengli;
	}

	public void setFengli(String fengli) {
		this.fengli = fengli;
	}

	public String getShidu() {
		return shidu;
	}

	public void setShidu(String shidu) {
		this.shidu = shidu;
	}

	public String getFengxiang() {
		return fengxiang;
	}

	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}

	public String getSunrise_1() {
		return sunrise_1;
	}

	public void setSunrise_1(String sunrise_1) {
		this.sunrise_1 = sunrise_1;
	}

	public String getSunset_1() {
		return sunset_1;
	}

	public void setSunset_1(String sunset_1) {
		this.sunset_1 = sunset_1;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public List<Weather> getForecast() {
		return forecast;
	}

	public void setForecast(List<Weather> forecast) {
		this.forecast = forecast;
	}

	public Resp() {
		super();
	}

	public Resp(String city, String updatetime, String wendu, String fengli,
			String shidu, String fengxiang, String sunrise_1, String sunset_1,
			Environment environment, List<Weather> forecast) {
		super();
		this.city = city;
		this.updatetime = updatetime;
		this.wendu = wendu;
		this.fengli = fengli;
		this.shidu = shidu;
		this.fengxiang = fengxiang;
		this.sunrise_1 = sunrise_1;
		this.sunset_1 = sunset_1;
		this.environment = environment;
		this.forecast = forecast;
	}

	@Override
	public String toString() {
		String forecastS="\n";
		String zhishuS="\n";
		for(Weather f:forecast)forecastS+=f.toString();
		return "Resp [city=" + city + ", updatetime=" + updatetime + ", wendu="
				+ wendu + ", fengli=" + fengli + ", shidu=" + shidu
				+ ", fengxiang=" + fengxiang + ", sunrise_1=" + sunrise_1
				+ ", sunset_1=" + sunset_1 
				+ ", \nenvironment=\n" + environment
				+ ", \nforecast=" + forecastS +  "]"
				;
	}

}
