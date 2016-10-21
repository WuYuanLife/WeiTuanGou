package cn.heyl.weituangou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.heyl.weituangou.R;
import cn.heyl.weituangou.presneter.ILoginPresneter;
import cn.heyl.weituangou.presneter.LoginPresneter;
import cn.heyl.weituangou.view.ILoginView;

public class LoginActivity extends Activity implements ILoginView{
	@ViewInject(R.id.etLoginname)
	private EditText etLoginname;
	@ViewInject(R.id.etPwd)
	private EditText etPwd;
	@ViewInject(R.id.tvNewAccount)
	private TextView tvAccount;
	@ViewInject(R.id.btnLogin)
	private Button btnLogin;
	@ViewInject(R.id.ivBack)
	private ImageView ivBack;
	private ILoginPresneter presneter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		x.view().inject(this);
		presneter=new LoginPresneter(this);
		setListener();
		
	}

	class LoginOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tvNewAccount:
				Intent intent =new Intent(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
				break;
			case R.id.btnLogin:
				String name=etLoginname.getText().toString();
				String pwd=etPwd.getText().toString();
				if(name.equals("") || pwd.equals("")){
					Toast.makeText(LoginActivity.this,"请输入账号或密码",Toast.LENGTH_SHORT).show();
					return;
				}
				presneter.login(name, pwd);
				break;
			case R.id.ivBack:
				finish();
				break;
			}
		}
		
	}
	private void setListener() {
		LoginOnClick listener=new LoginOnClick();
		tvAccount.setOnClickListener(listener);
		btnLogin.setOnClickListener(listener);
		ivBack.setOnClickListener(listener);
	}
	@Override
	public void loginSeccess() {
		Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
		setResult(RESULT_OK);
		finish();
	}
	@Override
	public void loginFail(String error) {
		Toast.makeText(this, "登录失败"+error, Toast.LENGTH_SHORT).show();
	}

}
