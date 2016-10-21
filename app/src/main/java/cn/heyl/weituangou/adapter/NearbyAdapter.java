package cn.heyl.weituangou.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.activity.WebViewActivity;
import cn.heyl.weituangou.entity.Deal;
import cn.heyl.weituangou.entity.Shop;

/**
 * Creaded by heyl 2016-10-11
 */
public class NearbyAdapter extends BaseAdapter {
	private List<Shop> shops;
	private Context context;
	private LayoutInflater inflater;
	private NearbyDealAdapter adapter;

	public NearbyAdapter(List<Shop> shops, Context context) {
		super();
		this.shops = shops;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return shops.size();
	}

	@Override
	public Shop getItem(int position) {
		return shops.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder=null;
		if(view==null){
			holder=new ViewHolder();
			view=inflater.inflate(R.layout.list_shop_nearby_item, null);
			x.view().inject(holder,view);
			view.setTag(holder);
		}
		holder=(ViewHolder) view.getTag();
		Shop shop=shops.get(position);
		
		setListener(shop,holder);
		setAdapter(shop.getDeals(),holder.lvdeal);
		
		holder.tvShopName.setText(shop.getShop_name());
		String price=shop.getPer_price();
		if(!("".equals(price)||price.length()==0)){
			holder.tvPrePrice.setText("￥"+Integer.parseInt(price)/100+"/人");
		}
		int len=MyApplication.getApp().getCurrentCity().getCity_name().length();
		String address=shop.getAddress();
		holder.tvAddress.setText(address.substring(len, address.length()));
//		Log.i("hyl", address.substring(len, address.length()));
		return view;
	}

	private void setListener(final Shop shop, ViewHolder holder) {
		holder.rlTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("url", shop.getShop_murl());
				context.startActivity(intent);
			}
		});
		holder.lvdeal.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("url", shop.getDeals().get(position).getDeal_murl());
				context.startActivity(intent);
			}
		});

	}
	private void setAdapter(List<Deal> deals, ListView lvdeal) {
		adapter=new NearbyDealAdapter(deals, context);
		lvdeal.setAdapter(adapter);
	}

	class ViewHolder {
		@ViewInject(R.id.rlShop_title_nearby)
		RelativeLayout rlTitle;
		@ViewInject(R.id.tvShopName_nearby)
		TextView tvShopName;
		@ViewInject(R.id.tvScore_nearby)
		TextView tvScore;
		@ViewInject(R.id.tvPrePrice_nearby)
		TextView tvPrePrice;
		@ViewInject(R.id.tvAddress_nearby)
		TextView tvAddress;
		@ViewInject(R.id.lvItemDeal_nearby)
		ListView lvdeal;
	}
}
