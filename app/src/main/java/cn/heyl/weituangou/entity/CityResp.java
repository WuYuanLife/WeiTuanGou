package cn.heyl.weituangou.entity;

import java.util.List;

/**
 * Creaded by heyl 2016-10-8
 */
public class CityResp {
	private String errno;
	private String msg;
	private List<City> cities;

	public CityResp(String errno, String msg, List<City> cities) {
		super();
		this.errno = errno;
		this.msg = msg;
		this.cities = cities;
	}

	public CityResp() {
		super();
	}

	public String getErrno() {
		return errno;
	}

	public void setErrno(String errno) {
		this.errno = errno;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "CtiyResp [" + (errno != null ? "errno=" + errno + ", " : "")
				+ (msg != null ? "msg=" + msg + ", " : "")
				+ (cities != null ? "cities=" + cities : "") + "]";
	}

}
