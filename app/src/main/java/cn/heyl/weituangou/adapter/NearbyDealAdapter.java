package cn.heyl.weituangou.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.Deal;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 *	Creaded by heyl 2016-10-10
 */
public class NearbyDealAdapter extends BaseAdapter {
	private List<Deal> deals;
	private Context context;
	private LayoutInflater inflater;
	public NearbyDealAdapter(List<Deal> deals, Context context) {
		super();
		this.deals = deals;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if(deals.size()<4)
		return deals.size();
		return 3;
	}

	@Override
	public Deal getItem(int position) {
		return deals.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_nearby_deal, null);
			x.view().inject(holder, convertView);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		Deal deal=deals.get(position);
		Glide.with(context).load(deal.getTiny_image()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.iv1);
		String price=deal.getCurrent_price()/100+"";
		holder.tvPrice.setText("￥ "+price);
		holder.tvDescription.setText(deal.getDescription());
		holder.tvSale.setText("已售"+deal.getSale_num());
		if("0".equals(deal.getScore())){
			holder.tvScore.setVisibility(View.GONE);
		}else{
			holder.tvScore.setVisibility(View.VISIBLE);
			holder.tvScore.setText(deal.getScore()+"分");
		}
		return convertView;
	}
	
	class  ViewHolder{
		@ViewInject(R.id.iv1_list_deal_nearby)
		ImageView iv1;
		@ViewInject(R.id.tvDescription_list_deal_nearby)
		TextView tvDescription;
		@ViewInject(R.id.tvPrice_list_deal_nearby)
		TextView tvPrice;
		@ViewInject(R.id.tvSale_list_deal_nearby)
		TextView tvSale;
		@ViewInject(R.id.tvScore_list_deal_nearby)
		TextView tvScore;
	}
}
