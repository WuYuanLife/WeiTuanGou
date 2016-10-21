package cn.heyl.weituangou.entity;

import java.io.Serializable;

/**
 *	Creaded by heyl 2016-10-8
 */
public class City implements Serializable{
	private String city_id;
	private String city_name;
	private String short_name;
	private String map_id;
	private String city_pinyin;
	private String short_pinyin;
	public City() {
		super();
	}
	public City(String city_id, String city_name, String short_name,
			String map_id, String city_pinyin, String short_pinyin) {
		super();
		this.city_id = city_id;
		this.city_name = city_name;
		this.short_name = short_name;
		this.map_id = map_id;
		this.city_pinyin = city_pinyin;
		this.short_pinyin = short_pinyin;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getMap_id() {
		return map_id;
	}
	public void setMap_id(String map_id) {
		this.map_id = map_id;
	}
	public String getCity_pinyin() {
		return city_pinyin;
	}
	public void setCity_pinyin(String city_pinyin) {
		this.city_pinyin = city_pinyin;
	}
	public String getShort_pinyin() {
		return short_pinyin;
	}
	public void setShort_pinyin(String short_pinyin) {
		this.short_pinyin = short_pinyin;
	}
	@Override
	public String toString() {
		return "City ["
				+ (city_id != null ? "city_id=" + city_id + ", " : "")
				+ (city_name != null ? "city_name=" + city_name + ", " : "")
				+ (short_name != null ? "short_name=" + short_name + ", " : "")
				+ (map_id != null ? "map_id=" + map_id + ", " : "")
				+ (city_pinyin != null ? "city_pinyin=" + city_pinyin + ", "
						: "")
				+ (short_pinyin != null ? "short_pinyin=" + short_pinyin : "")
				+ "]";
	}
	
}
