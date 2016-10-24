package cn.heyl.weituangou.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.activity.LoginActivity;
import cn.heyl.weituangou.adapter.GameAdapter;
import cn.heyl.weituangou.entity.Game;
import cn.heyl.weituangou.entity.User;
import cn.heyl.weituangou.presneter.IMinePresneter;
import cn.heyl.weituangou.presneter.MinePresenter;
import cn.heyl.weituangou.view.IMineView;
import obsrcollview.ObservableScrollView;
import obsrcollview.ObservableScrollViewCallbacks;
import obsrcollview.ScrollState;
import obsrcollview.ScrollUtils;

/**
 * Creaded by heyl 2016-10-9
 */
public class MineFragment extends Fragment implements ObservableScrollViewCallbacks, IMineView{
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
	//OBSV
	private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
	private static final boolean TOOLBAR_IS_STICKY = true;

	@ViewInject(R.id.llview)
	private View llview;
	@ViewInject(R.id.toolbar)
	private View mToolbar;
	private View mImageView;
	@ViewInject(R.id.overlay)
	private View mOverlayView;
	@ViewInject(R.id.scroll)
	private ObservableScrollView mScrollView;
//	@ViewInject(R.id.title)
//	private TextView mTitleView;
	private View mFab;
	@ViewInject(R.id.rlTitle)
	private RelativeLayout rlTitle;
	private int mActionBarSize;
	private int mFlexibleSpaceShowFabOffset;
	private int mFlexibleSpaceImageHeight;
	private int mToolbarColor;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fram_mine, null);
//		lvGame=(ListView) view.findViewById(R.id.lvMine);
		setView();
		x.view().inject(this, view);
		presneter = new MinePresenter(this);
		setListener();
		String token = MyApplication.getApp().getToken();
		if (token != null) {
			presneter.loginWithOutPwd(token);
		}

		//OBSV
//		setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

		mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
		mActionBarSize = getActionBarSize();
		mToolbarColor = getResources().getColor(R.color.primary);

		if (!TOOLBAR_IS_STICKY) {
			mToolbar.setBackgroundColor(Color.TRANSPARENT);
		}
		mScrollView.setScrollViewCallbacks(this);
//        mTitleView.setText(getTitle());
		getActivity().setTitle(null);

		ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
			@Override
			public void run() {
				mScrollView.scrollTo(0, mFlexibleSpaceImageHeight - mActionBarSize);
				mScrollView.scrollTo(0, 0);

			}
		});

//updateUserInfo();
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
//		lvGame.setAdapter(adapter);
//		lvGame.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Intent intent=new Intent(getActivity(),WebViewActivity.class);
//				intent.putExtra("url", games.get(position).getUrl());
//				startActivity(intent);
//			}
//		});
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
			case R.id.rlTitle:
				Log.d("hyl", "onClick:rlTitle ");
				break;
			case R.id.rlExit:

				break;
				case R.id.overlay:
					Log.d("hyl", "onClick:over ");
				break;
				case R.id.llview:
					Log.d("hyl", "onClick:ll ");
					Intent intentll = new Intent(getActivity(), LoginActivity.class);
					startActivityForResult(intentll, REQUEST_CODE_LOGIN_USER);
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
//		rlShoucang.setOnClickListener(listener);
//		rlAddress.setOnClickListener(listener);
//		rlOrder.setOnClickListener(listener);
//		rlSettings.setOnClickListener(listener);
//		rlExit.setOnClickListener(listener);
		rlTitle.setOnClickListener(listener);
		mOverlayView.setOnClickListener(listener);
		llview.setOnClickListener(listener);
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
		onScrollChanged(mScrollView.getCurrentScrollY(),true,false);
	}


	//OBSV
	protected int getActionBarSize() {
		TypedValue typedValue = new TypedValue();
		int[] textSizeAttr = new int[]{R.attr.actionBarSize};
		int indexOfAttrTextSize = 0;
		TypedArray a = getActivity().obtainStyledAttributes(typedValue.data, textSizeAttr);
		int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
		a.recycle();
		return 168;
	}

	@Override
	public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
		float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
		int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();
		ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
		ViewHelper.setTranslationY(rlTitle,  ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
//        ViewHelper.setTranslationY(rlTitle,  ScrollUtils.getFloat1(-scrollY/2, minOverlayTransitionY, 0));
//		Log.d("hyl", "onScrollChanged:2 "+(-scrollY )+ minOverlayTransitionY+firstScroll+dragging);
//        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

		// Change alpha of overlay
		ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

		// Scale title text
		float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
		ViewHelper.setPivotX(tvName, 0);
		ViewHelper.setPivotY(tvName, 0);
		ViewHelper.setScaleX(tvName, scale);
		ViewHelper.setScaleY(tvName, scale);

		// Translate title text
		int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - tvName.getHeight() * scale);
		int maxTitleTranslationX = (int) (rlTitle.getWidth()-tvName.getWidth()* scale)/2;
		int titleTranslationY = maxTitleTranslationY - scrollY;
		int titleTranslationX = titleTranslationY*100/maxTitleTranslationY*maxTitleTranslationX/100;
		if (TOOLBAR_IS_STICKY) {
			titleTranslationY = Math.max(0, titleTranslationY);

		}
		ViewHelper.setTranslationY(tvName, titleTranslationY);
		if (titleTranslationY>0) {
			ViewHelper.setTranslationX(tvName, Math.min(maxTitleTranslationX,Math.abs(titleTranslationX)));
		}
		if (TOOLBAR_IS_STICKY) {
			// Change alpha of toolbar background
			if (-scrollY + mFlexibleSpaceImageHeight <= mActionBarSize) {
				mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(1, mToolbarColor));
			} else {
				mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mToolbarColor));
			}
		} else {
			// Translate Toolbar
			if (scrollY < mFlexibleSpaceImageHeight) {
				ViewHelper.setTranslationY(mToolbar, 0);
			} else {
				ViewHelper.setTranslationY(mToolbar, -scrollY);
			}
		}
	}

	@Override
	public void onDownMotionEvent() {

	}

	@Override
	public void onUpOrCancelMotionEvent(ScrollState scrollState) {

	}

}
