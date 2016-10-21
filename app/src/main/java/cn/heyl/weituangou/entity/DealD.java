package cn.heyl.weituangou.entity;

import java.util.List;

/**
 * Creaded by heyl 2016-10-10
 */
public class DealD {
	private String deal_id;
	private String image;
	private String tiny_image;
	private String title;
	private String min_title;
	private String description;
	private double market_price;
	private double current_price;
	private double promotion_price;
	private int sale_num;
	private String score;
	private int comment_num;
	private long publish_time;
	private long purchase_deadline;
	private boolean is_reservation_required;
	private String distance;
	private String shop_num;
	private String deal_url;
	private String deal_murl;
	private List<ShopAddress> shops;

	public DealD(String deal_id, String image, String tiny_image, String title,
			String min_title, String description, double market_price,
			double current_price, double promotion_price, int sale_num, String score,
			int comment_num, long publish_time, long purchase_deadline,
			boolean is_reservation_required, String distance, String shop_num,
			String deal_url, String deal_murl, List<ShopAddress> shops) {
		super();
		this.deal_id = deal_id;
		this.image = image;
		this.tiny_image = tiny_image;
		this.title = title;
		this.min_title = min_title;
		this.description = description;
		this.market_price = market_price;
		this.current_price = current_price;
		this.promotion_price = promotion_price;
		this.sale_num = sale_num;
		this.score = score;
		this.comment_num = comment_num;
		this.publish_time = publish_time;
		this.purchase_deadline = purchase_deadline;
		this.is_reservation_required = is_reservation_required;
		this.distance = distance;
		this.shop_num = shop_num;
		this.deal_url = deal_url;
		this.deal_murl = deal_murl;
		this.shops = shops;
	}

	public DealD() {
		super();
	}

	@Override
	public String toString() {
		return "DealD ["
				+ (deal_id != null ? "deal_id=" + deal_id + ", " : "")
				+ (image != null ? "image=" + image + ", " : "")
				+ (tiny_image != null ? "tiny_image=" + tiny_image + ", " : "")
				+ (title != null ? "title=" + title + ", " : "")
				+ (min_title != null ? "min_title=" + min_title + ", " : "")
				+ (description != null ? "description=" + description + ", "
						: "") + "market_price=" + market_price
				+ ", current_price=" + current_price + ", promotion_price="
				+ promotion_price + ", sale_num=" + sale_num + ", "
				+ (score != null ? "score=" + score + ", " : "")
				+ "comment_num=" + comment_num + ", publish_time="
				+ publish_time + ", purchase_deadline=" + purchase_deadline
				+ ", is_reservation_required=" + is_reservation_required + ", "
				+ (distance != null ? "distance=" + distance + ", " : "")
				+ (shop_num != null ? "shop_num=" + shop_num + ", " : "")
				+ (deal_url != null ? "deal_url=" + deal_url + ", " : "")
				+ (deal_murl != null ? "deal_murl=" + deal_murl + ", " : "")
				+ (shops != null ? "shops=" + shops : "") + "]";
	}

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTiny_image() {
		return tiny_image;
	}

	public void setTiny_image(String tiny_image) {
		this.tiny_image = tiny_image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMin_title() {
		return min_title;
	}

	public void setMin_title(String min_title) {
		this.min_title = min_title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}

	public double getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}

	public double getPromotion_price() {
		return promotion_price;
	}

	public void setPromotion_price(double promotion_price) {
		this.promotion_price = promotion_price;
	}

	public int getSale_num() {
		return sale_num;
	}

	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public long getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(long publish_time) {
		this.publish_time = publish_time;
	}

	public long getPurchase_deadline() {
		return purchase_deadline;
	}

	public void setPurchase_deadline(long purchase_deadline) {
		this.purchase_deadline = purchase_deadline;
	}

	public boolean isIs_reservation_required() {
		return is_reservation_required;
	}

	public void setIs_reservation_required(boolean is_reservation_required) {
		this.is_reservation_required = is_reservation_required;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getShop_num() {
		return shop_num;
	}

	public void setShop_num(String shop_num) {
		this.shop_num = shop_num;
	}

	public String getDeal_url() {
		return deal_url;
	}

	public void setDeal_url(String deal_url) {
		this.deal_url = deal_url;
	}

	public String getDeal_murl() {
		return deal_murl;
	}

	public void setDeal_murl(String deal_murl) {
		this.deal_murl = deal_murl;
	}

	public List<ShopAddress> getShops() {
		return shops;
	}

	public void setShops(List<ShopAddress> shops) {
		this.shops = shops;
	}

}
