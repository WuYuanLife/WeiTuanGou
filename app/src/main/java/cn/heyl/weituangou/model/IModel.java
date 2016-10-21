package cn.heyl.weituangou.model;

/**
 * Creaded by heyl 2016-9-22
 */
public interface IModel {

	public interface AsyncCallBack {
		void onSuccess(Object obj);

		void onError(Object obj);
	}

}
