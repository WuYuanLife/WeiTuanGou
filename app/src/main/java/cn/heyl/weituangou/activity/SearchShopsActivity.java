package cn.heyl.weituangou.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yyydjk.library.DropDownMenu;

import butterknife.ButterKnife;
import cn.heyl.weituangou.adapter.ConstellationAdapter;
import cn.heyl.weituangou.adapter.GirdDropDownAdapter;
import cn.heyl.weituangou.adapter.ListDropDownAdapter;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.adapter.DealAdapter;
import cn.heyl.weituangou.entity.DealD;
import cn.heyl.weituangou.presneter.ISearchPre;
import cn.heyl.weituangou.presneter.SearchPre;
import cn.heyl.weituangou.ui.AutoListView;

public class SearchShopsActivity extends Activity implements AutoListView.OnLoadListener,
		ISearchActivity {
	@ViewInject(R.id.tvTitle_search)
	private TextView tvSearch;
	@ViewInject(R.id.tvEmpty_search)
	private TextView tvEmpty;

	private AutoListView lvSearch;

	private List<DealD> list;
	private DealAdapter adapter;
	private ISearchPre pre;
	private String search;
	private int currentPage=1;
	private AnimationDrawable anim;

	private boolean isLoad= true;


	private DropDownMenu mDropDownMenu;
	private String headers[] = {"分 类", "3km", "商 圈", "综合排序"};
	private List<View> popupViews = new ArrayList<View>();

	private GirdDropDownAdapter d1Adapter;
	private ListDropDownAdapter d2Adapter;
	private ConstellationAdapter d3Adapter;
	private ListDropDownAdapter d4Adapter;

	private String D1[] = {"全部分类", "生活服务", "休闲娱乐", "美 食", "酒店旅游", "其 他"};
	private String D2[] = {"3km", "2km", "1km", "500m"};
	private String D3[] = {"不 限","不 限","不 限","不 限","不 限","不 限"};
	private String D4[] = {"综合排序","价格低","价格高","折扣高","销量高","最新发布","用户评分高"};

	private int currentSort = 0;
	private int currentRadius = 3000;
	private int constellationPosition = 0;
	private int currentCat = 0;
	private int lateP=0;
	private int lateD=0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_shops);
		x.view().inject(this);
		pre = new SearchPre(this);
		anim = (AnimationDrawable) tvEmpty.getBackground();
		search=getIntent().getStringExtra("search");

		tvSearch.setText(search+"的相关结果");
		anim.start();
		pre.getSearchList(search,currentCat,1,currentRadius,currentSort);
		mDropDownMenu=(DropDownMenu)findViewById(R.id.dropDownMenu_search);
		initView();
	}

	private void initView() {
		//init city menu
		final ListView d1View = new ListView(this);
		d1Adapter = new GirdDropDownAdapter(this, Arrays.asList(D1));
		d1View.setDividerHeight(0);
		d1View.setAdapter(d1Adapter);

		//init age menu
		final ListView d2View = new ListView(this);
		d2View.setDividerHeight(0);
		d2Adapter = new ListDropDownAdapter(this, Arrays.asList(D2));
		d2View.setAdapter(d2Adapter);

		//init sex menu
		final ListView d4View = new ListView(this);
		d4View.setDividerHeight(0);
		d4Adapter = new ListDropDownAdapter(this, Arrays.asList(D4));
		d4View.setAdapter(d4Adapter);

		//init constellation
		final View d3View = getLayoutInflater().inflate(R.layout.custom_layout, null);
		GridView constellation = ButterKnife.findById(d3View, R.id.constellation);
		d3Adapter = new ConstellationAdapter(this, Arrays.asList(D3));
		constellation.setAdapter(d3Adapter);
		TextView ok = ButterKnife.findById(d3View, R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDropDownMenu.setTabText(constellationPosition == 0 ? headers[2] : D3[constellationPosition]);
				mDropDownMenu.closeMenu();
			}
		});

		//init popupViews
		popupViews.add(d1View);
		popupViews.add(d2View);
		popupViews.add(d3View);
		popupViews.add(d4View);

		//add item click event
		d1View.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				d1Adapter.setCheckItem(position);
				mDropDownMenu.setTabText(position == 0 ? headers[0] : D1[position]);
				mDropDownMenu.closeMenu();
				switch (position){
					case 0: currentCat=0;break;
					case 1: currentCat=316;break;
					case 2: currentCat=320;break;
					case 3: currentCat=326;break;
					case 4: currentCat=377;break;
					case 5: currentCat=323;break;
				}
				update(false);

			}
		});

		d2View.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				d2Adapter.setCheckItem(position);
				mDropDownMenu.setTabText(position == 0 ? headers[1] : D2[position]);
				mDropDownMenu.closeMenu();
				switch (position){
					case 0:currentRadius=3000;break;
					case 1:currentRadius=2000;break;
					case 2:currentRadius=1000;break;
					case 3:currentRadius=500;break;
				}
				Log.i("hyl", "radius: "+currentRadius);
				update(false);
			}
		});

		d4View.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				d4Adapter.setCheckItem(position);
				mDropDownMenu.setTabText(position == 0 ? headers[3] : D4[position]);
				mDropDownMenu.closeMenu();
				switch (position){
					case 0:case 1:case 2:case 3:case 4:
						currentSort=position;break;
					case 5:currentSort=6;break;
					case 6:currentSort=8;break;
				}
				update(false);
				Log.i("hyl", "sort: "+currentSort);
			}
		});

		constellation.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				d3Adapter.setCheckItem(position);
				constellationPosition = position;
			}
		});

		//init context view
		lvSearch = new AutoListView(this);
		lvSearch.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

		lvSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Log.i("hyl", position+"   "+lvSearch.getCount());
				if(position<(lvSearch.getCount()-1)){
					Intent intent = new Intent(SearchShopsActivity.this, WebViewActivity.class);
					intent.putExtra("url", list.get(position-1).getDeal_url());
					startActivity(intent);
				}
			}
		});
		//init dropdownview
		mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, lvSearch);
	}

	private void update(boolean b) {
		if(!b) {
			tvEmpty.setVisibility(View.VISIBLE);
			lvSearch.setVisibility(View.INVISIBLE);
			anim.start();
			currentPage=1;
			if(adapter!=null){
				list.clear();
				lvSearch.setResultSize(list.size());
				adapter.notifyDataSetChanged();
			}
		}
		isLoad=b;
		pre.getSearchList(search,currentCat,currentPage,currentRadius,currentSort);
	}


	@Override
	public void onLoad() {
		update(true);
	}

	@Override
	public void updateSearchList(List<DealD> deals) {
		if (deals == null||deals.size()==0) {
			Toast.makeText(this, "当前搜索无相关结果", Toast.LENGTH_SHORT).show();
			finish();
		} else {
			lvSearch.setVisibility(View.VISIBLE);
			tvEmpty.setVisibility(View.GONE);
			anim.stop();
			if (adapter == null) {
				list = deals;
				adapter = new DealAdapter(list, this);
				lvSearch.setAdapter(adapter);
				lvSearch.setOnLoadListener(this);
//			lvSearch.onRefreshComplete();
			} else {
				if (!isLoad) list.clear();
				list.addAll(deals);
				lvSearch.onLoadComplete();
			}
			currentPage++;
			lvSearch.setResultSize(list.size());
			adapter.notifyDataSetChanged();
		}
	}
}
