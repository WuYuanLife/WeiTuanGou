package cn.heyl.weituangou.presneter;

import cn.heyl.weituangou.entity.DealResp;
import cn.heyl.weituangou.entity.ImageUrl;
import cn.heyl.weituangou.entity.Resp;
import cn.heyl.weituangou.fragment.IHomeFram;
import cn.heyl.weituangou.model.IModel.AsyncCallBack;
import cn.heyl.weituangou.model.IShopModel;
import cn.heyl.weituangou.model.ShopModel;
import cn.heyl.weituangou.model.WeatherModel;

/**
 * Creaded by heyl 2016-10-10
 */
public class HomePresnter implements IHomePresneter {
	private IHomeFram fram;
	private IShopModel model;
	private WeatherModel weatherModel;

	public HomePresnter(IHomeFram fram) {
		this.fram = fram;
		model = ShopModel.newShopModel();
		weatherModel=new WeatherModel();
	}

	@Override
	public void updateLove(int currentPage) {
		model.getLoveList(currentPage,new AsyncCallBack() {

			@Override
			public void onSuccess(Object obj) {
				DealResp resp=(DealResp) obj;
//				Log.i("hyl", resp.toString());
				fram.updateLove(resp.getData().getDeals());
			}

			@Override
			public void onError(Object obj) {

			}
		});
	}

	@Override
	public void getWeather(String city) {
		weatherModel.getResp(city, new AsyncCallBack() {
			
			@Override
			public void onSuccess(Object obj) {
				Resp resp=(Resp) obj;
//				Log.v("hyl", resp.toString());
				fram.weatherDialog(resp);
			}
			
			@Override
			public void onError(Object obj) {
			}
		});
		
	}

	@Override
	public void loadImage() {
model.getImage(new AsyncCallBack() {
	@Override
	public void onSuccess(Object obj) {
		fram.updateImage((ImageUrl) obj);
	}

	@Override
	public void onError(Object obj) {

	}
});
	}

}
