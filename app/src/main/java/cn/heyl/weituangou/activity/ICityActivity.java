package cn.heyl.weituangou.activity;

import java.util.List;

import cn.heyl.weituangou.entity.City;

/**
 *	Creaded by heyl 2016-10-8
 */
public interface ICityActivity {
	void updateRecentlyAdapter(List<City> cities);
	void updateHotAdapter(List<City> cities);
	void updateAllAdapter(List<City> cities);
	void updateSearchAdapter(List<City> cities);
}
