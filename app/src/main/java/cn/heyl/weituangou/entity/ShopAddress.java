package cn.heyl.weituangou.entity;

/**
 * Creaded by heyl 2016-10-10
 */
public class ShopAddress {
	private String shop_id;
	private String longitude;
	private String latitude;
	private String distance;
	private String shop_url;
	private String shop_murl;
	public ShopAddress(String shop_id, String longitude, String latitude,
			String distance, String shop_url, String shop_murl) {
		super();
		this.shop_id = shop_id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.distance = distance;
		this.shop_url = shop_url;
		this.shop_murl = shop_murl;
	}
	public ShopAddress() {
		super();
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
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
	@Override
	public String toString() {
		return "ShopAddress ["
				+ (shop_id != null ? "shop_id=" + shop_id + ", " : "")
				+ (longitude != null ? "longitude=" + longitude + ", " : "")
				+ (latitude != null ? "latitude=" + latitude + ", " : "")
				+ (distance != null ? "distance=" + distance + ", " : "")
				+ (shop_url != null ? "shop_url=" + shop_url + ", " : "")
				+ (shop_murl != null ? "shop_murl=" + shop_murl : "") + "]";
	}
	
}
