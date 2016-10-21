package cn.heyl.weituangou.view;

import android.graphics.Bitmap;

/**
 * Creaded by heyl 2016-9-27
 */
public interface IRegistView extends IView {
	void registSuccess();

	void registError(String errorMessage);

	void showCodeImage(Bitmap bitmap);

}
