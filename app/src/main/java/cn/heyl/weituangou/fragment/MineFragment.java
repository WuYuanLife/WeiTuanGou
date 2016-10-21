package cn.heyl.weituangou.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.activity.LoginActivity;
import cn.heyl.weituangou.activity.WebViewActivity;
import cn.heyl.weituangou.adapter.GameAdapter;
import cn.heyl.weituangou.entity.Game;
import cn.heyl.weituangou.entity.User;
import cn.heyl.weituangou.presneter.IMinePresneter;
import cn.heyl.weituangou.presneter.MinePresenter;
import cn.heyl.weituangou.view.IMineView;

/**
 * Creaded by heyl 2016-10-9
 */
public class MineFragment extends Fragment implements IMineView{
	private ListView lvGame;
	private List<Game> games;
	private GameAdapter adapter;
	
	@ViewInject(R.id.rlTitle)
	private RelativeLayout mineHeader;
	@ViewInject(R.id.rlAddress)
	private RelativeLayout rlAddress;
	@ViewInject(R.id.rlShoucang)
	private RelativeLayout rlShoucang;
	@ViewInject(R.id.rlOrder)
	private RelativeLayout rlOrder;
	@ViewInject(R.id.rlSettings)
	private RelativeLayout rlSettings;
	@ViewInject(R.id.rlExit)
	private RelativeLayout rlExit;
	@ViewInject(R.id.tvName_mine)
	private TextView tvName;
	@ViewInject(R.id.civTouxiang)
	private ImageView civTouxiang;
	private IMinePresneter presneter;

	private static final int REQUEST_CODE_LOGIN_USER = 121;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fram_mine, null);
		lvGame=(ListView) view.findViewById(R.id.lvMine);
		setView();
		x.view().inject(this, view);
		presneter = new MinePresenter(this);
		setListener();
		String token = MyApplication.getApp().getToken();
		if (token != null) {
			presneter.loginWithOutPwd(token);
		}
		return view;
	}
	private void setView() {
		games=new ArrayList<Game>();
		games.add(new Game("三子棋", "http://playtictactoe.org/"));
		games.add(new Game("弹力球", "https://bouncyballs.org/"));
		games.add(new Game("采蘑菇", "http://www.kuashou.com/h5/xianggu/index5.html"));
		games.add(new Game("小蝌蚪", "https://neave.com/swarm/"));
		games.add(new Game("2048", "http://h5.gamedog.cn/games/1014779_2.html"));
		
		adapter =new GameAdapter(games, getActivity());
		lvGame.setAdapter(adapter);
		lvGame.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra("url", games.get(position).getUrl());
				startActivity(intent);
			}
		});
	}
	public void doClick(View v){
	}
	private class MineOnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rlShoucang:

				break;
			case R.id.rlAddress:

				break;
			case R.id.rlOrder:

				break;
			case R.id.rlSettings:

				break;
			case R.id.rlExit:

				break;
			case R.id.civTouxiang:
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivityForResult(intent, REQUEST_CODE_LOGIN_USER);
				break;

			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_LOGIN_USER:
			if (resultCode == Activity.RESULT_OK) {
				updateUserInfo();
			}
			break;

		default:
			break;
		}
	}


	private void setListener() {
		MineOnClick listener = new MineOnClick();
		rlShoucang.setOnClickListener(listener);
		rlAddress.setOnClickListener(listener);
		rlOrder.setOnClickListener(listener);
		rlSettings.setOnClickListener(listener);
		rlExit.setOnClickListener(listener);
		civTouxiang.setOnClickListener(listener);
	}

	public void slide(float v) {
		mineHeader.setAlpha(v);
	}

	@Override
	public void updateUserInfo() {
		Log.i("hyl", "updateUserInfo()");
		User user = MyApplication.getApp().getCurrentUser();
		String nickname = user.getNickname();
		tvName.setText(nickname);
		
	}
	
}
