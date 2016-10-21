package cn.heyl.weituangou.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
	Creaded by heyl 2016-9-22
 */
public class MainFragAdapter extends FragmentPagerAdapter{
	private List<Fragment> fragments;
	public MainFragAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.fragments=fragments;
	}


	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}
