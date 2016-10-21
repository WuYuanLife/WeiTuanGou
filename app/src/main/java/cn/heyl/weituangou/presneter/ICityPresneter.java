package cn.heyl.weituangou.presneter;

import java.util.List;

import cn.heyl.weituangou.entity.City;

/**
 * Creaded by heyl 2016-10-9
 */
public interface ICityPresneter {
	void loadRecnetlyCity();

	void loadHotCity();

	void loadAllCity();

	void loadSearchCity(String search,List<City> cities);
}
