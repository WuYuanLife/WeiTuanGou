package cn.heyl.weituangou.entity;

import java.util.List;

/**
 * Creaded by heyl 2016-10-10
 */
public class Shop {

	

	private String shop_id;
	private String shop_name;
	private String longitude;
	private String latitude;
	private String distance;
	private String deal_num;
	private String shop_url;
	private String shop_murl;
	private String per_price;
	private String average_score;
	private String address;
	private String tiny_image;
	private List<Deal> deals;

	public Shop(String shop_id, String shop_name, String longitude,
			String latitude, String distance, String deal_num, String shop_url,
			String shop_murl, String per_price, String average_score,
			String address, String tiny_image, List<Deal> deals) {
		super();
		this.shop_id = shop_id;
		this.shop_name = shop_name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.distance = distance;
		this.deal_num = deal_num;
		this.shop_url = shop_url;
		this.shop_murl = shop_murl;
		this.per_price = per_price;
		this.average_score = average_score;
		this.address = address;
		this.tiny_image = tiny_image;
		this.deals = deals;
	}

	public Shop() {
		super();
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDeal_num() {
		return deal_num;
	}

	public void setDeal_num(String deal_num) {
		this.deal_num = deal_num;
	}

	public String getShop_url() {
		return shop_url;
	}

	public void setShop_url(String shop_url) {
		this.shop_url = shop_url;
	}

	public String getShop_murl() {
		return shop_murl;
	}

	public void setShop_murl(String shop_murl) {
		this.shop_murl = shop_murl;
	}

	public String getPer_price() {
		return per_price;
	}

	public void setPer_price(String per_price) {
		this.per_price = per_price;
	}

	public String getAverage_score() {
		return average_score;
	}

	public void setAverage_score(String average_score) {
		this.average_score = average_score;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTiny_image() {
		return tiny_image;
	}

	public void setTiny_image(String tiny_image) {
		this.tiny_image = tiny_image;
	}

	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

	@Override
	public String toString() {
		return "Shop ["
				+ (shop_id != null ? "shop_id=" + shop_id + ", " : "")
				+ (shop_name != null ? "shop_name=" + shop_name + ", " : "")
				+ (longitude != null ? "longitude=" + longitude + ", " : "")
				+ (latitude != null ? "latitude=" + latitude + ", " : "")
				+ (distance != null ? "distance=" + distance + ", " : "")
				+ (deal_num != null ? "deal_num=" + deal_num + ", " : "")
				+ (shop_url != null ? "shop_url=" + shop_url + ", " : "")
				+ (shop_murl != null ? "shop_murl=" + shop_murl + ", " : "")
				+ "per_price="
				+ per_price
				+ ", "
				+ (average_score != null ? "average_score=" + average_score
						+ ", " : "")
				+ (address != null ? "address=" + address + ", " : "")
				+ (tiny_image != null ? "tiny_image=" + tiny_image + ", " : "")
				+ (deals != null ? "deals=" + deals : "") + "]";
	}


}