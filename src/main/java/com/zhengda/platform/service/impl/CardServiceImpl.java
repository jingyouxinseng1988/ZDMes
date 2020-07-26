package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.CardQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.CardDao;
import com.zhengda.platform.entity.Card;
import com.zhengda.platform.service.CardService;
import java.util.*;



@Service("cardService")
public class CardServiceImpl  implements CardService {

	@Resource
	private CardDao cardDao;

	@Override
	public void add(Card t) {
       cardDao.insert(t);
	}

    @Override
    public void addList(List<Card> list) {
       cardDao.insertList(list);
    }

	@Override
	public int update(Card t) {
	   return cardDao.update(t);
	}

	@Override
	public Card getById(Long id) {
	   return cardDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return cardDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return cardDao.getCount(queryBo.getMap());
	}

	@Override
	public List<Card> getList(BaseQueryBo queryBo) {
		return cardDao.getList(queryBo.getMap());
	}

    @Override
    public List<Card> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            CardQueryBo queryBo = new CardQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<Card> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, Card> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, Card> map = new HashMap<>();
        List<Card> list = getListByIds(ids);
        for (Card entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
