package cn.heyl.weituangou.presneter;

import android.graphics.Bitmap;
import cn.heyl.weituangou.entity.User;
import cn.heyl.weituangou.model.IModel.AsyncCallBack;
import cn.heyl.weituangou.model.IUserModel;
import cn.heyl.weituangou.model.UserModel;
import cn.heyl.weituangou.view.IRegistView;

/**
 * Creaded by heyl 2016-9-27
 */
public class RegistPresneter implements IRegistPresnter {
	private IUserModel model;
	private IRegistView view;
	

	public RegistPresneter(IRegistView view) {
		super();
		this.view = view;
		model = new UserModel();
	}

	@Override
	public void regist(User user, String code) {
		model.regist(user, code, new AsyncCallBack() {
			
			@Override
			public void onSuccess(Object obj) {
				view.registSuccess();
			}
			
			@Override
			public void onError(Object obj) {
				view.registError((String) obj);
				
			}
		});
	}

	@Override
	public void loadImageCode() {
		model.showImageCode(new AsyncCallBack() {

			@Override
			public void onSuccess(Object obj) {
				view.showCodeImage((Bitmap) obj);
			}

			@Override
			public void onError(Object obj) {
			}
		});
	}

}
