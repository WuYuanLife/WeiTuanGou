package cn.heyl.weituangou.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.User;
import cn.heyl.weituangou.presneter.IRegistPresnter;
import cn.heyl.weituangou.presneter.RegistPresneter;
import cn.heyl.weituangou.view.IRegistView;

public class RegistActivity extends Activity implements IRegistView{
	@ViewInject(R.id.etLoginname)
	private EditText etLoginname;
	@ViewInject(R.id.etPwd)
	private EditText etPwd;
	@ViewInject(R.id.etRealname)
	private EditText etRealname;
	@ViewInject(R.id.etCode)
	private EditText etCode;
	@ViewInject(R.id.ivCode)
	private ImageView ivCode;
	private IRegistPresnter presnter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		presnter=new RegistPresneter(this);
		x.view().inject(this);
		presnter.loadImageCode();
	}

	public void regist(View v) {
		User user=new User();
		user.setEmail(etLoginname.getText().toString());
		user.setPassword(etPwd.getText().toString());
		user.setNickname(etRealname.getText().toString());
		presnter.regist(user, etCode.getText().toString());
	}

	@Override
	public void registSuccess() {
		Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	public void registError(String errorMessage) {
		Toast.makeText(this, "注册失败"+errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showCodeImage(Bitmap bitmap) {
		if(bitmap!=null)
		ivCode.setImageBitmap(bitmap);
	}
	public void back(View view) {
		finish();
	}
}
