package com.zhengda.platform.queryBo;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询基础类 所有业务的简单查询条件封装都可以继承此类
 * 以对akeeta_vendor表的查询为例，VendorQueryBo->AkeetaVendorDao.getList(map)
 */
public class BaseQueryBo {
	private Map<String, Object> map = new HashMap<>();

	public void setOffset(int offset) {
		if (offset < 0) {
			offset = 0;
		}
		put("offset", offset);
	}

	public void setLimit(int limit) {
		if (limit < 1) {
			limit = 10;
		}
		put("limit", limit);
	}

	protected void put(String key, Object value) {
		this.map.put(key, value);
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setSort(String column, String asc) {
		put("column", column);
		put("sort", asc);
	}
}
