package cn.heyl.weituangou.presneter;

import android.util.Log;
import cn.heyl.weituangou.entity.ShopResp;
import cn.heyl.weituangou.fragment.INearbyFram;
import cn.heyl.weituangou.model.IModel.AsyncCallBack;
import cn.heyl.weituangou.model.IShopModel;
import cn.heyl.weituangou.model.ShopModel;

/**
 * Creaded by heyl 2016-10-10
 */
public class NearbyPresnter implements INearbyPresnter {
	private INearbyFram fram;
	private IShopModel model;

	public NearbyPresnter(INearbyFram fram) {
		this.fram = fram;
		model = ShopModel.newShopModel();
	}

	@Override
	public void updateNearbyList(int cat_ids,int currentPage,int currentRadius,int currentSort) {
		model.getNearbyShopList(cat_ids,currentPage,currentRadius,currentSort,new AsyncCallBack() {
			@Override
			public void onSuccess(Object obj) {
				ShopResp resp=(ShopResp) obj;
//				Log.i("hyl", resp.toString());
				Log.i("hyl", "updateNearbyList()");
				if(Integer.parseInt(resp.getErrno())==0){
					fram.updateNearbyList(resp.getData().getShops());
				}else{
					fram.updateNearbyList(null);
				}
			}

			@Override
			public void onError(Object obj) {
			}
		});
	}

}
