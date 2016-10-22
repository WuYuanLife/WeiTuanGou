package cn.heyl.weituangou.presneter;

import android.util.Log;
import cn.heyl.weituangou.model.IModel.AsyncCallBack;
import cn.heyl.weituangou.model.IUserModel;
import cn.heyl.weituangou.model.UserModel;
import cn.heyl.weituangou.view.IMineView;

/**
 *	Creaded by heyl 2016-9-28
 */
public class MinePresenter implements IMinePresneter {
	private IUserModel model;
	private int i=0;
	private IMineView view;

	public MinePresenter(IMineView view) {
		this.view = view;
		this.model = new UserModel();
	}
	@Override
	public void loginWithOutPwd(String token) {
		model.loginWithOutPwd(token, new AsyncCallBack() {
			

			@Override
			public void onSuccess(Object obj) {
				if (view!=null){
				view.updateUserInfo();
			}
			}
			@Override
			public void onError(Object obj) {
				Log.i("hyl", obj.toString());
			}
		});
		i++;
	}

}
