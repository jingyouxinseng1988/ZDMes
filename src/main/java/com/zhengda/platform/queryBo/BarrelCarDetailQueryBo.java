package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class BarrelCarDetailQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setBarrelCarId(Long barrelCarId) {
           put("barrelCarId", barrelCarId);
        }

        public void setProductName(String productName) {
           put("productName", productName);
        }

        public void setProductCode(String productCode) {
           put("productCode", productCode);
        }

        public void setSpecifications(String specifications) {
           put("specifications", specifications);
        }

        public void setTotalWeight(java.math.BigDecimal totalWeight) {
           put("totalWeight", totalWeight);
        }

        public void setUnit(String unit) {
           put("unit", unit);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setModifyTime(java.util.Date modifyTime) {
           put("modifyTime", modifyTime);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }
}
