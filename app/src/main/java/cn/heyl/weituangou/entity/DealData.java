package cn.heyl.weituangou.entity;

import java.util.List;

/**
 *	Creaded by heyl 2016-10-10
 */
public class DealData {
	private String total;
	private List<DealD> deals;
	public DealData(String total, List<DealD> deals) {
		super();
		this.total = total;
		this.deals = deals;
	}
	public DealData() {
		super();
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<DealD> getDeals() {
		return deals;
	}
	public void setDeals(List<DealD> deals) {
		this.deals = deals;
	}
	@Override
	public String toString() {
		return "DealData [" + (total != null ? "total=" + total + ", " : "")
				+ (deals != null ? "deals=" + deals : "") + "]";
	}
	
	
}
