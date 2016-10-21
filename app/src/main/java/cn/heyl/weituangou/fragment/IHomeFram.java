package cn.heyl.weituangou.fragment;

import java.util.List;

import cn.heyl.weituangou.entity.DealD;
import cn.heyl.weituangou.entity.ImageUrl;
import cn.heyl.weituangou.entity.Resp;

/**
 * Creaded by heyl 2016-10-10
 */
public interface IHomeFram {
	void updateLove(List<DealD> deals);
	void weatherDialog(Resp weather);
	void updateImage(ImageUrl imageUrl);
}
