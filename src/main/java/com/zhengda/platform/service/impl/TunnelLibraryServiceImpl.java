package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.TunnelLibraryQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.TunnelLibraryDao;
import com.zhengda.platform.entity.TunnelLibrary;
import com.zhengda.platform.service.TunnelLibraryService;
import java.util.*;



@Service("tunnelLibraryService")
public class TunnelLibraryServiceImpl  implements TunnelLibraryService {

	@Resource
	private TunnelLibraryDao tunnelLibraryDao;

	@Override
	public void add(TunnelLibrary t) {
       tunnelLibraryDao.insert(t);
	}

    @Override
    public void addList(List<TunnelLibrary> list) {
       tunnelLibraryDao.insertList(list);
    }

	@Override
	public int update(TunnelLibrary t) {
	   return tunnelLibraryDao.update(t);
	}

	@Override
	public TunnelLibrary getById(Long id) {
	   return tunnelLibraryDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return tunnelLibraryDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return tunnelLibraryDao.getCount(queryBo.getMap());
	}

	@Override
	public List<TunnelLibrary> getList(BaseQueryBo queryBo) {
		return tunnelLibraryDao.getList(queryBo.getMap());
	}

    @Override
    public List<TunnelLibrary> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            TunnelLibraryQueryBo queryBo = new TunnelLibraryQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<TunnelLibrary> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, TunnelLibrary> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, TunnelLibrary> map = new HashMap<>();
        List<TunnelLibrary> list = getListByIds(ids);
        for (TunnelLibrary entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
