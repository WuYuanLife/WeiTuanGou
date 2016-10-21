package cn.heyl.weituangou.adapter;

import java.util.List;

import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.Game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Creaded by heyl 2016-10-11
 */
public class GameAdapter extends BaseAdapter {
	private List<Game> games;
	private Context context;
	private LayoutInflater inflater;

	public GameAdapter(List<Game> games, Context context) {
		super();
		this.games = games;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return games.size();
	}

	@Override
	public Game getItem(int position) {
		return games.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view==null){
			view=inflater.inflate(R.layout.list_game, null);
		}
		TextView tvName =(TextView) view.findViewById(R.id.tvGameName_mine);
		tvName.setText(games.get(position).getName());
		return view;
	}

}
