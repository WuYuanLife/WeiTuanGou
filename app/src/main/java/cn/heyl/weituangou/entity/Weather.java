package cn.heyl.weituangou.entity;

public class Weather {
	private String date;
	private String high;
	private String low;
	private DayOrNight day;
	private DayOrNight night;
	

	public Weather() {
	}

	public Weather(String date, String high, String low, DayOrNight day,
			DayOrNight night) {
		super();
		this.date = date;
		this.high = high;
		this.low = low;
		this.day = day;
		this.night = night;
	}

	public DayOrNight getDay() {
		return day;
	}

	public void setDay(DayOrNight day) {
		this.day = day;
	}

	public DayOrNight getNight() {
		return night;
	}

	public void setNight(DayOrNight night) {
		this.night = night;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	@Override
	public String toString() {
		return "Weather [date=" + date + ", high=" + high + ", low=" + low
				+ ", day=" + day + ", night=" + night + "]";
	}


}
