package cn.heyl.weituangou.model;
/**
 *	Creaded by heyl 2016-10-17
 */
public interface IWeatherModel extends IModel {
void getResp(String city,AsyncCallBack callback);
}
