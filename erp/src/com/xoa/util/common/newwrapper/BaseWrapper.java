package com.xoa.util.common.newwrapper;

import java.util.Date;

import com.xoa.util.DateFormat;

/**
 * 
 * @作者 韩东堂
 * @创建日期 2017-4-26 下午5:41:32
 * @类介绍 单个模型包装基类
 * @构造参数 无
 * 
 */
public class BaseWrapper {
	protected Integer status;
	protected String flag;
	protected String msg;
	protected String time;
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTime() {
		if (time == null) {
			time = DateFormat.getStrDate(new Date());
		}
		return time;
	}

	public void setTime(String time) {

		this.time = time;
	}

	

}
