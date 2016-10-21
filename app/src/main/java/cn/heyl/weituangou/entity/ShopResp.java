package cn.heyl.weituangou.entity;

import java.io.Serializable;

/**
 *	Creaded by heyl 2016-10-10
 */
public class ShopResp implements Serializable{
	private String errno;
	private String msg;
	private ShopData data;
	public ShopResp(String errno, String msg, ShopData data) {
		super();
		this.errno = errno;
		this.msg = msg;
		this.data = data;
	}
	public ShopResp() {
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
	public ShopData getData() {
		return data;
	}
	public void setData(ShopData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ShopResp [" + (errno != null ? "errno=" + errno + ", " : "")
				+ (msg != null ? "msg=" + msg + ", " : "")
				+ (data != null ? "data=" + data : "") + "]";
	}
	
}
