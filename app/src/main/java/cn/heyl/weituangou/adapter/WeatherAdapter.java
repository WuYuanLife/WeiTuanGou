package cn.heyl.weituangou.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.Weather;

public class WeatherAdapter extends BaseAdapter {
	private List<Weather> forecast;
	private Context context;
	private LayoutInflater inflater;
	
	public WeatherAdapter(List<Weather> forecast, Context context) {
		super();
		this.forecast = forecast;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Weather weather=forecast.get(position);
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_forecast_list, null);
			holder = new ViewHolder();
			holder.tvXingqi = (TextView) convertView.findViewById(R.id.tv_xingqi);
			holder.tvRiqi = (TextView) convertView.findViewById(R.id.tv_riqi);
			holder.tvTypeDay = (TextView) convertView.findViewById(R.id.tv_type_day);
			holder.tvHigh = (TextView) convertView.findViewById(R.id.tv_high);
			holder.tvLow = (TextView) convertView.findViewById(R.id.tv_low);
			holder.tvTypeNight = (TextView) convertView.findViewById(R.id.tv_type_night);
			holder.tvFengxiang = (TextView) convertView.findViewById(R.id.tv_item_fengxiang);
			holder.tvFengli = (TextView) convertView.findViewById(R.id.tv_fengli);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		String date=weather.getDate();
		holder.tvXingqi.setText(date.subSequence(date.length()-3, (date.length())));
		holder.tvRiqi.setText(date.subSequence(0, (date.length()-3)));
		holder.tvHigh.setText(weather.getDay().getType()+weather.getHigh().subSequence(2,weather.getHigh().length()));
		holder.tvLow.setText(weather.getDay().getType()+weather.getLow().subSequence(2,weather.getLow().length()));
		holder.tvFengxiang.setText(weather.getDay().getFengxiang());
		holder.tvFengli.setText(weather.getDay().getFengli());
		
		return convertView;
	}
	
	
	class ViewHolder{
		TextView tvXingqi;
		TextView tvRiqi;
		TextView tvTypeDay;
		TextView tvHigh;
		TextView tvLow;
		TextView tvTypeNight;
		TextView tvQuality;
		TextView tvFengxiang;
		TextView tvFengli;
	}
	
	@Override
	public int getCount() {
		return forecast.size();
	}

	@Override
	public Weather getItem(int position) {
		return forecast.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	

}
