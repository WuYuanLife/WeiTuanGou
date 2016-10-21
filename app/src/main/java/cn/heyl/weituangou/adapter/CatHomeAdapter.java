package cn.heyl.weituangou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cn.heyl.weituangou.R;
import cn.heyl.weituangou.entity.Cat;

/**
 * Created by Administrator on 2016/10/19.
 */
public class CatHomeAdapter extends BaseAdapter {
    private Context context;
    private List<Cat> cats;
    private LayoutInflater inflater;

    public CatHomeAdapter(Context context, List<Cat> cats) {
        this.context = context;
        this.cats = cats;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cats.size();
    }

    @Override
    public Cat getItem(int position) {
        return cats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder=null;
        if (view==null){
            view=inflater.inflate(R.layout.item_cat_home,null);
            holder=new ViewHolder();
            x.view().inject(holder,view);
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        Cat cat =cats.get(position);
        holder.tv.setText(cat.getName());
        holder.iv.setImageResource(cat.getId());
        return view;
    }

    class ViewHolder{
        @ViewInject(R.id.ivCat_home_list)
        ImageView iv;
        @ViewInject(R.id.tvCat_hoem_list)
        TextView tv;

    }

}
