package cn.heyl.weituangou.presneter;

import android.util.Log;
import cn.heyl.weituangou.activity.ISearchActivity;
import cn.heyl.weituangou.entity.DealResp;
import cn.heyl.weituangou.model.IModel.AsyncCallBack;
import cn.heyl.weituangou.model.IShopModel;
import cn.heyl.weituangou.model.ShopModel;

/**
 *	Creaded by heyl 2016-10-17
 */
public class SearchPre implements ISearchPre {
	private IShopModel model;
	private ISearchActivity view;
	
	public SearchPre(ISearchActivity view) {
		this.view = view;
		model=ShopModel.newShopModel();
	}


	@Override
	public void getSearchList(String search,int cat_ids,int page,int radius,int sort) {
		model.getSearchList(search,cat_ids,page,radius,sort, new AsyncCallBack() {
			
			@Override
			public void onSuccess(Object obj) {
				DealResp resp=(DealResp) obj;
//				Log.i("hyl", resp.toString());
				Log.i("hyl", "search()"+resp);
				if(Integer.parseInt(resp.getErrno())==0){
				view.updateSearchList(resp.getData().getDeals());
				}else{
					view.updateSearchList(null);
				}
			}
			
			@Override
			public void onError(Object obj) {
			}
		});

	}

}
