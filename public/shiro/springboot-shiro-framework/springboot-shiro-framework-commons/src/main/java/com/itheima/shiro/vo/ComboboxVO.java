package com.itheima.shiro.vo;

import com.itheima.shiro.utils.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 		复选框
 */
@Getter
@Setter
public class ComboboxVO extends ToString {
	
	/**传递值**/
	private String id;
	
	/**显示值**/
	private String text;
	
	/**是否选择**/
	private Boolean selected;

	public ComboboxVO() {
		super();
	}

	public ComboboxVO(String id, String text) {
		this.id = id;
		this.text = text;
	}
	

}
