package cn.heyl.weituangou.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DisResp {
    private String errno;
    private String msg;
    private List<Dis> districts;

    public DisResp() {
    }

    public DisResp(List<Dis> districts, String errno, String msg) {
        this.districts = districts;
        this.errno = errno;
        this.msg = msg;
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

    public List<Dis> getDistricts() {
        return districts;
    }

    public void setDistricts(List<Dis> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "DisResp{" +
                "districts=" + districts +
                ", errno='" + errno + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
