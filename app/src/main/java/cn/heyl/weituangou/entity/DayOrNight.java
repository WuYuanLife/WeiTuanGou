package cn.heyl.weituangou.entity;

public class DayOrNight {
	private String type;
	private String fengxiang;
	private String fengli;

	public DayOrNight(String type, String fengxiang, String fengli) {
		super();
		this.type = type;
		this.fengxiang = fengxiang;
		this.fengli = fengli;
	}

	public DayOrNight() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFengxiang() {
		return fengxiang;
	}

	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}

	public String getFengli() {
		return fengli;
	}

	public void setFengli(String fengli) {
		this.fengli = fengli;
	}

	@Override
	public String toString() {
		return "DayOrNight [type=" + type + ", fengxiang=" + fengxiang
				+ ", fengli=" + fengli + "]";
	}

}
