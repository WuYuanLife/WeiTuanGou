package cn.heyl.weituangou.model;

import java.util.List;

import cn.heyl.weituangou.entity.City;

/**
 *	Creaded by heyl 2016-10-9
 */
public interface ICityModel extends IModel{
	void getRecnetlyCity(AsyncCallBack callback);

	void getHotCity(AsyncCallBack callback);

	void getAllCity(AsyncCallBack callback);
	void getSearchCity(AsyncCallBack callback,String search, List<City> cities);
}
