package cn.heyl.weituangou.model;

import cn.heyl.weituangou.entity.User;

/**
 * Creaded by heyl 2016-9-27
 */
public interface IUserModel extends IModel {
	void showImageCode(AsyncCallBack callBack);

	void regist(User user, String code, AsyncCallBack callBack);

	void login(String userName, String password, AsyncCallBack callBack);
	void loginWithOutPwd(String token, AsyncCallBack callBack);
}
