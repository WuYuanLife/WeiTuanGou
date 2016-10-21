package cn.heyl.weituangou.entity;
/**
 *	Creaded by heyl 2016-10-10
 */
public class DealResp {
	private String errno;
	private String msg;
	private DealData data;
	public DealResp(String errno, String msg, DealData data) {
		super();
		this.errno = errno;
		this.msg = msg;
		this.data = data;
	}
	public DealResp() {
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
	public DealData getData() {
		return data;
	}
	public void setData(DealData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "DealResp [" + (errno != null ? "errno=" + errno + ", " : "")
				+ (msg != null ? "msg=" + msg + ", " : "")
				+ (data != null ? "data=" + data : "") + "]";
	}
	
	
}
