package cn.heyl.weituangou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.heyl.weituangou.R;
import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.adapter.MainFragAdapter;
import cn.heyl.weituangou.entity.Dis;
import cn.heyl.weituangou.entity.DisResp;
import cn.heyl.weituangou.fragment.HomeFragment;
import cn.heyl.weituangou.fragment.MineFragment;
import cn.heyl.weituangou.fragment.NearbyFragment;
import cn.heyl.weituangou.model.IModel;
import cn.heyl.weituangou.model.IShopModel;
import cn.heyl.weituangou.model.ShopModel;
import cn.heyl.weituangou.ui.MyViewPager;

public class MainActivity extends FragmentActivity {
	
	@ViewInject(R.id.ivSaoyisao_main)
	private ImageView ivSaoyisao;
	@ViewInject(R.id.viewPager_main)
	private MyViewPager vp;
	@ViewInject(R.id.radioGroup)
	private RadioGroup rg;
	@ViewInject(R.id.radioHome)
	private RadioButton rbHome;
	@ViewInject(R.id.radioNearby)
	private RadioButton rbNearby;
	@ViewInject(R.id.radioMine)
	private RadioButton rbMine;
	private ArrayList<Fragment> fragments;
	private MainFragAdapter fragAdapter;
	
	private String currentId="-1";
	public  static boolean isCurrent=false;
	private static List<Dis> disList;
	private NearbyFragment nearbyFrm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		setFragAdapter();
		setListeners();

	}

	private void setListeners() {
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioHome:
					vp.setCurrentItem(0);
					break;
				case R.id.radioNearby:
					vp.setCurrentItem(1);
					break;
				case R.id.radioMine:
					vp.setCurrentItem(2);
					break;

				}
			}
		});

		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int i) {
				switch (i) {
				case 0:
					rbHome.setChecked(true);
					break;
				case 1:
					rbNearby.setChecked(true);
					break;
				case 2:
					rbMine.setChecked(true);
					break;
				}
			}

			@Override
			public void onPageScrolled(int i, float v, int i2) {
				 if(v!=0){
				 MineFragment fragment = (MineFragment) fragments.get(2);
				 fragment.slide(v);
				 }
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
//		vp.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				return true;
//			}
//		});
	}

	private void setFragAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new HomeFragment());
		nearbyFrm=new NearbyFragment();
		fragments.add(nearbyFrm);
		fragments.add(new MineFragment());
		fragAdapter = new MainFragAdapter(getSupportFragmentManager(),
				fragments);
		vp.setAdapter(fragAdapter);
		vp.setOffscreenPageLimit(3);
	}

	@Override
	protected void onResume() {
		super.onResume();
		String id= MyApplication.getApp().getCurrentCity().getCity_id();
		if(!"-1".equals(currentId)&&currentId!=null){
			if(currentId.equals(id)){
				isCurrent=true;
			}else{
				isCurrent=false;
			}
		}else{
			isCurrent=false;
		}
		Log.d("hyl", id+"onResume: "+currentId);
		currentId=id;
	}
}
