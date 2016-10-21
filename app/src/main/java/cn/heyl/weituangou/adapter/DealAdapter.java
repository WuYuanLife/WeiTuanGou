package cn.heyl.weituangou.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.DealD;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 *	Creaded by heyl 2016-10-10
 */
public class DealAdapter extends BaseAdapter {
	private List<DealD> deals;
	private Context context;
	private LayoutInflater inflater;
	public DealAdapter(List<DealD> deals, Context context) {
		super();
		this.deals = deals;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return deals.size();
	}

	@Override
	public DealD getItem(int position) {
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
			convertView=inflater.inflate(R.layout.list_home_love, null);
			x.view().inject(holder, convertView);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		DealD deal=deals.get(position);
		if(deal.isIs_reservation_required()){
			holder.iv2.setVisibility(View.INVISIBLE);
		}else{
			holder.iv2.setVisibility(View.VISIBLE);
		}
		Glide.with(context).load(deal.getTiny_image()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.iv1);
		String price=deal.getCurrent_price()/100+"";
		holder.tvPrice.setText("￥ "+price);
		holder.tvDescription.setText(deal.getDescription());
		holder.tvSale.setText("已售"+deal.getSale_num());
		if("0".equals(deal.getScore())){
			holder.tvScore.setVisibility(View.INVISIBLE);
		}else{
			holder.tvScore.setVisibility(View.VISIBLE);
			holder.tvScore.setText(deal.getScore()+"分");
		}
		holder.tvName.setText(deal.getTitle());
		return convertView;
	}
	class  ViewHolder{
		@ViewInject(R.id.iv1Love_list_home)
		ImageView iv1;
		@ViewInject(R.id.iv2Love_list_home)
		ImageView iv2;
		@ViewInject(R.id.tvNameLove_list_home)
		TextView tvName;
		@ViewInject(R.id.tvDescriptionLove_list_home)
		TextView tvDescription;
		@ViewInject(R.id.tvPriceLove_list_home)
		TextView tvPrice;
		@ViewInject(R.id.tvSaleLove_list_home)
		TextView tvSale;
		@ViewInject(R.id.tvScoreLove_list_home)
		TextView tvScore;
	}
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
	if (observer != null) {
	       super.unregisterDataSetObserver(observer);
	   }
	}
}
