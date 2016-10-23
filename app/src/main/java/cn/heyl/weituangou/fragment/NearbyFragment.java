package cn.heyl.weituangou.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.activity.MainActivity;
import cn.heyl.weituangou.activity.SearchShopsActivity;
import cn.heyl.weituangou.adapter.ConstellationAdapter;
import cn.heyl.weituangou.adapter.GirdDropDownAdapter;
import cn.heyl.weituangou.adapter.ListDropDownAdapter;
import cn.heyl.weituangou.adapter.NearbyAdapter;
import cn.heyl.weituangou.entity.Dis;
import cn.heyl.weituangou.entity.Shop;
import cn.heyl.weituangou.presneter.INearbyPresnter;
import cn.heyl.weituangou.presneter.NearbyPresnter;
import cn.heyl.weituangou.ui.AutoListView;

/**
 * Creaded by heyl 2016-10-9
 */
public class NearbyFragment extends Fragment implements AutoListView.OnLoadListener, INearbyFram{
	private INearbyPresnter presnter;
	private AutoListView lvNearby;
	private TextView tvEmpty;
	private NearbyAdapter adapter;
	private List<Shop> shops;
	private EditText etSearch;
	private TextView tvSearch;
	private int currentPage=1;
	private AnimationDrawable anim;


	private DropDownMenu mDropDownMenu;
	private String headers[] = {"分类", "3km", "价格", "综合排序"};
	private List<View> popupViews = new ArrayList<View>();

	private GirdDropDownAdapter d1Adapter;
	private ListDropDownAdapter d2Adapter;
    private ConstellationAdapter d3Adapter;
    private ListDropDownAdapter d4Adapter;

	private String D1[] = {"全部分类", "生活服务", "休闲娱乐", "美 食", "酒店旅游", "其 他"};
	private String D2[] = {"3km", "2km", "1km", "500m"};
    private String D3[] = {"不限","200以下","500以下","1000以下","2000以下","2000以上"};
    private String D4[] = {"综合排序","价格低","价格高","折扣高","销量高","最新发布","用户评分高"};

    private int currentSort = 0;
    private int currentRadius = 3000;

	private int constellationPosition = 0;
    private int currentCat=326;
    private boolean isLoad=true;
	private List<String> d3List;
//	private ShopModel model;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fram_nearby, null);
		presnter=new NearbyPresnter(this);
//		model=new ShopModel();
//		lvNearby = (AutoListView) view.findViewById(R.id.lvNearby);
		tvEmpty = (TextView) view.findViewById(R.id.tvEmpty_nearby);
		tvSearch = (TextView) view.findViewById(R.id.tvSearch_deal);
		etSearch=(EditText) view.findViewById(R.id.etSearch_nearby);
		etSearch.clearAnimation();
		anim = (AnimationDrawable) tvEmpty.getBackground();
		d3List=Arrays.asList(D3);

		setlistener();
		mDropDownMenu=(DropDownMenu)view.findViewById(R.id.dropDownMenu_nearby);
		initView();
		return view;
	}

	private void initView() {
		//init city menu
		final ListView d1View = new ListView(getActivity());
		d1Adapter = new GirdDropDownAdapter(getActivity(), Arrays.asList(D1));
		d1View.setDividerHeight(0);
		d1View.setAdapter(d1Adapter);

		d1Adapter.setCheckItem(3);
		mDropDownMenu.setTabText(D1[3]);

		//init age menu
		final ListView d2View = new ListView(getActivity());
		d2View.setDividerHeight(0);
		d2Adapter = new ListDropDownAdapter(getActivity(), Arrays.asList(D2));
		d2View.setAdapter(d2Adapter);

		//init sex menu
		final ListView d4View = new ListView(getActivity());
		d4View.setDividerHeight(0);
		d4Adapter = new ListDropDownAdapter(getActivity(), Arrays.asList(D4));
		d4View.setAdapter(d4Adapter);

		//init constellation

		final View d3View = getActivity().getLayoutInflater().inflate(R.layout.custom_layout, null);
		GridView constellation = ButterKnife.findById(d3View, R.id.constellation);

		d3Adapter = new ConstellationAdapter(getActivity(), d3List);
		constellation.setAdapter(d3Adapter);
		TextView ok = ButterKnife.findById(d3View, R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDropDownMenu.setTabText(constellationPosition == 0 ? headers[2] : d3List.get(constellationPosition));
				mDropDownMenu.closeMenu();
                update(false);
			}
		});

		//init popupViews
		popupViews.add(d1View);
		popupViews.add(d2View);
        popupViews.add(d3View);
        popupViews.add(d4View);

		//add item click event
		d1View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

		d2View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

		d4View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
//                presnter.updateNearbyList(currentPage,currentRadius,currentSort);
                Log.i("hyl", "sort: "+currentSort);
            }
		});

		constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				d3Adapter.setCheckItem(position);
				constellationPosition = position;
			}
		});

		//init context view
		lvNearby = new AutoListView(getActivity());
        lvNearby.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

		//init dropdownview
		mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, lvNearby);
	}

	private void setD3(List<Dis> dises){
		Log.d("hyl", "setD3: "+dises.get(0).toString());
		for (int i=0;i<dises.size();i++){
			d3List.add(dises.get(i).getDistrict_name());
		}

		d3Adapter.notifyDataSetChanged();

	}

    private void update(boolean b) {
        if(!b) {
//			model.getDis( new IModel.AsyncCallBack() {
//				@Override
//				public void onSuccess(Object obj) {
//					DisResp resp= (DisResp) obj;
//						Log.d("hyl", "onResume: main"+resp);
//					setD3(resp.getDistricts());
//				}
//
//				@Override
//				public void onError(Object obj) {
//
//				}
//			});
			currentCat=326;
			currentSort = 0;
			currentRadius = 3000;
            lvNearby.setVisibility(View.INVISIBLE);
            tvEmpty.setVisibility(View.VISIBLE);
            anim.start();
            currentPage=1;
            if(adapter!=null){
                shops.clear();
                adapter.notifyDataSetChanged();
            }
        }
        isLoad=b;
        presnter.updateNearbyList(currentCat,currentPage,currentRadius,currentSort);
    }

    @Override
	public void onLoad() {
        update(true);
	}
	private void setlistener() {
		tvSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String search=etSearch.getText().toString();
				if(!"".equals(search)&&search.length()>0){
					Intent intent=new Intent(getActivity(),SearchShopsActivity.class);
					intent.putExtra("search", search);
					startActivity(intent);
				}
				
			}
		});
		
	}

	@Override
	public void onResume() {
		super.onResume();
		etSearch.setText("");
		if(!MainActivity.isCurrent)
            update(false);
	}
	@Override
	public void updateNearbyList(List<Shop> shops) {

		if (shops == null||shops.size()==0  ) {
                Toast.makeText(getActivity(), "当前无相关结果,请重新筛选", Toast.LENGTH_SHORT).show();
            return;
		}
        lvNearby.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);
			anim.stop();
			if (adapter == null) {
				this.shops = shops;
				adapter = new NearbyAdapter(shops, getActivity());
				lvNearby.setAdapter(adapter);
				lvNearby.setOnLoadListener(this);
			} else {
                if(!isLoad)this.shops.clear();
				this.shops.addAll(shops);
				lvNearby.onLoadComplete();
				lvNearby.setResultSize(this.shops.size());
				adapter.notifyDataSetChanged();
			}
            currentPage++;
	}
}
