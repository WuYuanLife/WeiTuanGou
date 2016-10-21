package cn.heyl.weituangou.entity;

import java.util.List;

/**
 * Creaded by heyl 2016-10-10
 */
public class ShopData {
	private String total;
	private List<Shop> shops;

	public ShopData(String total, List<Shop> shops) {
		super();
		this.total = total;
		this.shops = shops;
	}

	public ShopData() {
		super();
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	@Override
	public String toString() {
		return "ShopData [" + (total != null ? "total=" + total + ", " : "")
				+ (shops != null ? "shops=" + shops.get(0) : "") + "]";
	}


}
