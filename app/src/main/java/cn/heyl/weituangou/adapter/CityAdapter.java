package cn.heyl.weituangou.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.City;

/**
 *	Creaded by heyl 2016-10-8
 */
public class CityAdapter extends BaseAdapter {
	private List<City> cities;
	private Context context;
	private LayoutInflater inflater;
	
	public CityAdapter(List<City> cities, Context context) {
		super();
		this.cities = cities;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return cities.size();
	}

	@Override
	public City getItem(int position) {
		return cities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_city_item, null);
			holder.tvCityName= (TextView) convertView.findViewById(R.id.tvName_city);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		City city=cities.get(position);
		holder.tvCityName.setText(city.getCity_name());
		return convertView;
	}
	class ViewHolder{
		TextView tvCityName;
	}
}
