package cn.heyl.weituangou.entity;

/**
 * Creaded by heyl 2016-10-10
 */
public class Deal {
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
	private String deal_url;
	private String deal_murl;

	public Deal(String deal_id, String image, String tiny_image, String title,
			String min_title, String description, double market_price,
			double current_price, double promotion_price, int sale_num, String score,
			int comment_num, String deal_url, String deal_murl) {
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
		this.deal_url = deal_url;
		this.deal_murl = deal_murl;
	}

	public Deal() {
		super();
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

	@Override
	public String toString() {
		return "Deal [deal_id=" + deal_id + ", image=" + image
				+ ", tiny_image=" + tiny_image + ", title=" + title
				+ ", min_title=" + min_title + ", description=" + description
				+ ", market_price=" + market_price + ", current_price="
				+ current_price + ", promotion_price=" + promotion_price
				+ ", sale_num=" + sale_num + ", score=" + score
				+ ", comment_num=" + comment_num + ", deal_url=" + deal_url
				+ ", deal_murl=" + deal_murl + "]";
	}

}
