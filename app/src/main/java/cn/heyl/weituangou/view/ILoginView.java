package cn.heyl.weituangou.view;


/**
 * Creaded by heyl 2016-9-27
 */
public interface ILoginView extends IView {
	void loginSeccess();

	void loginFail(String error);
}
