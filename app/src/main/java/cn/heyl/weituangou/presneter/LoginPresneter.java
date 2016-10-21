package cn.heyl.weituangou.presneter;

import cn.heyl.weituangou.model.IUserModel;
import cn.heyl.weituangou.model.UserModel;
import cn.heyl.weituangou.view.ILoginView;
import cn.heyl.weituangou.model.IModel;

/**
 * Creaded by heyl 2016-9-27
 */
public class LoginPresneter implements ILoginPresneter {
	private ILoginView view;
	private IUserModel model;
	public LoginPresneter(ILoginView view) {
		this.view = view;
		model=new UserModel();
	}

	@Override
	public void login(String userName, String password) {
		model.login(userName, password, new IModel.AsyncCallBack() {
			
			@Override
			public void onSuccess(Object obj) {
				view.loginSeccess();
			}
			@Override
			public void onError(Object obj) {
				view.loginFail((String) obj);
			}
		});
	}

	

}
