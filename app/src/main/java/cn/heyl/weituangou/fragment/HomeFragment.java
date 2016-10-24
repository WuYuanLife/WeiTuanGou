package cn.heyl.weituangou.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.heyl.weituangou.MyApplication;
import cn.heyl.weituangou.R;
import cn.heyl.weituangou.activity.CityActivity;
import cn.heyl.weituangou.activity.MainActivity;
import cn.heyl.weituangou.activity.WebViewActivity;
import cn.heyl.weituangou.adapter.CatHomeAdapter;
import cn.heyl.weituangou.adapter.DealAdapter;
import cn.heyl.weituangou.adapter.WeatherAdapter;
import cn.heyl.weituangou.entity.Cat;
import cn.heyl.weituangou.entity.City;
import cn.heyl.weituangou.entity.DealD;
import cn.heyl.weituangou.entity.Environment;
import cn.heyl.weituangou.entity.ImageUrl;
import cn.heyl.weituangou.entity.Resp;
import cn.heyl.weituangou.entity.Weather;
import cn.heyl.weituangou.presneter.HomePresnter;
import cn.heyl.weituangou.presneter.IHomePresneter;
import zxing.MipcaActivityCapture;

/**
 * Creaded by heyl 2016-10-9
 */
public class HomeFragment extends Fragment implements IHomeFram,
        ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {
    private IHomePresneter presnter;
//    private TextView tvEmpty;
    private ListView lvLove;
    private TextView tvCityName;
    private TextView tvWeather;
    private ImageView iv;
    private ImageView ivZxing;
    private SliderLayout mDemoSlider;
    private GridView gvCat;

    private DealAdapter adapter;
    private List<DealD> deals;
    private City currentCity;
    private Resp weatherResp;
    private WeatherAdapter adapterForecast;

    private List<Weather> forecast;
//    private AnimationDrawable anim;

    private HashMap<String, DealD> url_maps;
    private ImageUrl image;
    private CatHomeAdapter catAdapter;
    private List<Cat> cats;

    private String imageUrl="https://ss0.baidu.com//9vo3dSag_xI4khGko9WTAnF6hhy//lbs//pic//item//4e4a20a4462309f741d1fe9e770e0cf3d6cad684.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fram_home, null);
        presnter = new HomePresnter(this);

//        mDemoSlider.setFocusable(true);
        lvLove = (ListView) view.findViewById(R.id.lvLove_home);
//        lvLove.setFocusable(false);

        tvCityName = (TextView) view.findViewById(R.id.tvCityName_Main);
        tvWeather = (TextView) view.findViewById(R.id.tvWeather_main);
        ivZxing= (ImageView) view.findViewById(R.id.ivSaoyisao_main);
        presnter.loadImage();
        setHeadView();
        setListener();
//        anim = (AnimationDrawable) tvEmpty.getBackground();
//        anim.start();
//		setSlider();

        return view;
    }

    private void setHeadView() {
        LinearLayout hearderViewLayout = (LinearLayout) LayoutInflater.from(getActivity())
                .inflate(R.layout.headview_home, null);
        mDemoSlider = (SliderLayout) hearderViewLayout.findViewById(R.id.slider);
        iv = (ImageView) hearderViewLayout.findViewById(R.id.iv_home);
        Glide.with(this).load(imageUrl).into(iv);
        gvCat = (GridView) hearderViewLayout.findViewById(R.id.gvCat_home);
        setCats();
//        tvEmpty = (TextView) hearderViewLayout.findViewById(R.id.tvEmpty_home);
        lvLove.addHeaderView(hearderViewLayout);
    }

    private void setCats() {
        cats = new ArrayList<>();
        Cat cat = new Cat(R.drawable.home_meishi, "美食", "http://m.nuomi.com/nj/326/0-0/0-0-0-0-0");
        cats.add(cat);
        cat = new Cat(R.drawable.home_dianying, "电影", "https://mdianying.baidu.com/?sfrom=newnuomi&amp;source=nuomi&amp;sub_channel=nuomi_wap_rukou1");
        cats.add(cat);
        cat = new Cat(R.drawable.home_jiudian, "酒店", "https://m.nuomi.com/nj/642/0-0/0-0-0-0-0");
        cats.add(cat);
        cat = new Cat(R.drawable.home_ktv, "KTV", "https://m.nuomi.com/nj/341/0-0/0-0-0-0-0");
        cats.add(cat);
        cat = new Cat(R.drawable.home_waimai, "外卖", "http://waimai.baidu.com/mobile/waimai?third_party=nuomijingang&amp;utm_campaign=nuomiH5&amp;utm_source=othersource&amp;utm_medium=default&amp;cid=100001131");
        cats.add(cat);
        cat = new Cat(R.drawable.home_xiuxian, "休闲娱乐", "//m.nuomi.com/component/entertainment/1.0.4/page/home/home.html");
        cats.add(cat);
        cat = new Cat(R.drawable.home_huoguo, "火锅", "https://m.nuomi.com/nj/364/0-0/0-0-0-0-0");
        cats.add(cat);
        cat = new Cat(R.drawable.home_shouji, "手机充值", "http://m.nuomi.com/component?compid=union&amp;comppage=mobilerecharge-home");
        cats.add(cat);
        cat = new Cat(R.drawable.home_lvyou, "旅游", "http://lvyou.baidu.com/static/foreign/page/scenery/list/list.html?from=webapp");
        cats.add(cat);
        cat = new Cat(R.drawable.home_daijin, "代金券", "http://m.nuomi.com/nj/1000107/0-0/0-0-0-0-0");
        cats.add(cat);
        Log.d("hyl", "setCats: " + cats.size());
        catAdapter = new CatHomeAdapter(getActivity(), cats);
        gvCat.setAdapter(catAdapter);
        gvCat.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", cats.get(position ).getUrl());
                startActivity(intent);
            }
        });
    }

    private void setSlider(List<DealD> deals) {
        mDemoSlider.setVisibility(View.VISIBLE);

        for (int i = 8; i < 12; i++) {
            DealD deal = deals.get(i);
            url_maps.put(deal.getDescription(), deal);
        }

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name).getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
//			Glide.with(getActivity()).load(url_maps.get(name)).asBitmap().into(new SimpleTarget<Bitmap>() {
//				@Override
//				public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                    Drawable drawable = new BitmapDrawable(bitmap);
//                    drawable.
//				}
//			});
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }


//    @Override
//    public void onLoad() {
//        presnter.updateLove(1);
//    }


    private void setListener() {
        tvWeather.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                sl.scrollTo(0,20);
//				if(!MainActivity.isCurrent){
                String name = currentCity.getCity_name();
                presnter.getWeather(name.substring(0, name.length() - 1));
                tvWeather.setEnabled(false);
//				}else{
//					setADLView();
//				}
            }
        });
        tvCityName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityActivity.class);
                startActivity(intent);
            }
        });
        lvLove.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", deals.get(position - 1).getDeal_url());
//				intent.putExtra("url", "http://www.kuashou.com/h5/xianggu/index5.html");
                startActivity(intent);
            }
        });

        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (image != null) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", "http:" + image.getUrl());
                    startActivity(intent);
                }
            }
        });
        ivZxing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MipcaActivityCapture.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.i("hyl", "onResume()");
//		if(weatherResp!=null)
//		Log.i("hyl", "onResume()"+weatherResp+weatherResp.hashCode());
//		weatherResp=null;
        if (!MainActivity.isCurrent) {
            updateLove(null);
            presnter.updateLove(1);
//            tvEmpty.setVisibility(View.VISIBLE);
//            anim.start();
            currentCity = MyApplication.getApp().getCurrentCity();
            tvCityName.setText(currentCity.getCity_name());
            if (url_maps != null) {
                url_maps.clear();
                mDemoSlider.removeAllSliders();
            } else {
                url_maps = new HashMap<>();
            }
        }
//        sl.scrollTo(0,20);

    }

    public void updateImage(ImageUrl imageUrl) {
        image = imageUrl;
//        if (imageUrl != null) {
////            tvEmpty.setVisibility(View.GONE);
////            anim.stop();
//            Glide.with(this).load(imageUrl.getPic_url()).into(iv);
//            Log.i("hyl", "updateImage: " + imageUrl);
//        }

    }


    @Override
    public void updateLove(List<DealD> deals) {
        // Log.i("hyl", deals.toString());
        if (deals == null) {

            if (adapter != null) {
                this.deals.clear();
                adapter.notifyDataSetChanged();
            }
        } else {
//            tvEmpty.setVisibility(View.GONE);

            setSlider(deals);
            if (adapter == null) {
                this.deals = deals;
                adapter = new DealAdapter(this.deals, getActivity());
                lvLove.setAdapter(adapter);
            } else {
                this.deals.clear();
                this.deals.addAll(deals);
                adapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void weatherDialog(Resp resp) {
        this.weatherResp = resp;
        if (resp != null) {
//			Log.v("hyl", resp.toString());
            setADLView();
            tvWeather.setEnabled(true);
        } else {
            Toast.makeText(getActivity(), "抱歉，该城市不支持天气功能", Toast.LENGTH_SHORT).show();
        }

    }

    protected void setADLView() {
//		Log.i("hyl", "setADLView()1"+weatherResp+weatherResp.hashCode());
        Builder builder = new Builder(getActivity());
        final AlertDialog dialog = builder.create();
        dialog.show();
        Window window = dialog.getWindow();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.weather_detail, null);

        TextView tvCityName = (TextView) view.findViewById(R.id.tvCity);
        TextView tvCityWendu = (TextView) view.findViewById(R.id.adlTv_wendu);
        TextView tvCityWenduLowHigh = (TextView) view.findViewById(R.id.adlTv_wenduLowHigh);
        TextView tvCityWeather = (TextView) view.findViewById(R.id.adlTv_Weather);
        TextView tvCityShidu = (TextView) view.findViewById(R.id.adlTv_shidu);
        TextView tvCityFengxiang = (TextView) view.findViewById(R.id.adlTv_fengli);
        TextView tvCitySunrise = (TextView) view.findViewById(R.id.adlTv_sunrise);
        TextView tvCitySunset = (TextView) view.findViewById(R.id.adlTv_sunset);
        TextView tvCityPm25 = (TextView) view.findViewById(R.id.adlTv_pm25);
        TextView tvCityQuality = (TextView) view.findViewById(R.id.adlTv_quality);
        ListView lvForecast = (ListView) view.findViewById(R.id.lvForecast);
        ImageView ivWeather = (ImageView) view.findViewById(R.id.ivWeather);

        tvCityName.setText(currentCity.getCity_name());
        Weather w = weatherResp.getForecast().get(0);
        String low = w.getLow().subSequence(2, w.getLow().length() - 1) + "/";
        String high = w.getHigh().subSequence(2, w.getHigh().length()) + "";
        tvCityWendu.setText(weatherResp.getWendu() + "°C");
        tvCityWenduLowHigh.setText(low + high);
        tvCityWeather.setText(w.getDay().getType());
        int IvRId = setIvRId(w.getDay().getType());
        if (IvRId != -1) {
            ivWeather.setImageResource(IvRId);
            ;
        }
        tvCityShidu.setText("湿度：" + weatherResp.getShidu());
        tvCityFengxiang.setText(weatherResp.getFengxiang() + "：" + weatherResp.getFengli());
        tvCitySunrise.setText("日出：" + weatherResp.getSunrise_1());
        tvCitySunset.setText("日落：" + weatherResp.getSunset_1());
        Environment e = weatherResp.getEnvironment();
        tvCityPm25.setText("Pm25：" + ((e == null) ? "无数据" : e.getPm25()));
        if (e == null) {
            tvCityQuality.setText("空气质量：同上");
        } else {
            if (e.getQuality().equals("良")) {
                tvCityQuality.setText("空气质量：良");
            } else if (e.getQuality().equals("优")) {
                tvCityQuality.setText("空气质量：优");
            } else {
                tvCityQuality.setText("空气污染：" + (e.getQuality().
                        substring(0, e.getQuality().length() - 2)));
            }
        }
        ImageView ivx = (ImageView) view.findViewById(R.id.ivX);
        ivx.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                forecast.clear();

                adapterForecast.notifyDataSetChanged();
                adapterForecast = null;
            }
        });

        forecast = weatherResp.getForecast();
        adapterForecast = new WeatherAdapter(forecast, getActivity());
        lvForecast.setAdapter(adapterForecast);

        window.setContentView(view);
//		Log.i("hyl", "setADLView()2"+weatherResp);
    }

    private int setIvRId(String type) {
        int rid = -1;
        if (type.contains("雾")) return R.drawable.w30;
        if (type.contains("阴")) return R.drawable.w7;
        if (type.contains("多云")) return R.drawable.w4;
        if (type.contains("沙")) return R.drawable.w29;
        if (type.contains("雷")) return R.drawable.w16;
        if (type.contains("雪")) return R.drawable.w24;
        if (type.contains("雨")) return R.drawable.w10;
        if (type.contains("晴")) return R.drawable.w0;
        return rid;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        if (url_maps != null) {
            String extra = slider.getBundle().get("extra") + "";
//            Toast.makeText(getActivity(), extra, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("url", url_maps.get(extra).getDeal_murl());
            startActivity(intent);
        }
    }
}
