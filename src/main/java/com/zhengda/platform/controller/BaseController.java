package com.zhengda.platform.controller;

import com.zhengda.platform.queryBo.BaseQueryBo;
import org.springframework.util.StringUtils;

/**
 * web层通用数据处理
 *
 * @author wolf
 */
public class BaseController {
//    protected User getCurrentUser() {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        User user = (User) request.getAttribute(Constants.USER);
//        return user;
//    }
    protected void setPage(BaseQueryBo bo, Integer pageSize, Integer currentPage) {
        if (pageSize == null) {
            pageSize = 20;
        }
        bo.setLimit(pageSize);
        if (currentPage == null) {
            currentPage = 1;
        }
        bo.setOffset((currentPage - 1) * pageSize);
    }

    protected void setOrder(BaseQueryBo bo, String order, String sort) {
        if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
            bo.setSort(order, sort);
        }
    }
}
