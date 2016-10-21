package cn.heyl.weituangou.model;


/**
 * Creaded by heyl 2016-10-10
 */
public interface IShopModel extends IModel {
	void getLoveList(int currentPage, AsyncCallBack callback);
	void getSearchList(String search,int cat_ids,int page,int radius,int sort, AsyncCallBack callback);
	void getNearbyShopList(int cat_ids,int currentPage,int currentRadius,int currentSort, AsyncCallBack callback);
	void getImage(AsyncCallBack callBack);
	void getDis(AsyncCallBack callBack);
}
