package cn.heyl.weituangou.presneter;

import cn.heyl.weituangou.entity.User;

/**
 *	Creaded by heyl 2016-9-27
 */
public interface IRegistPresnter {

	void loadImageCode();
	
	
	void regist(User user, String code);
	
	
}
