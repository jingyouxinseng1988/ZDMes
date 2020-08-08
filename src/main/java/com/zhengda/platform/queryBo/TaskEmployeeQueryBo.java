package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class TaskEmployeeQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setTaskId(Long taskId) {
           put("taskId", taskId);
        }

        public void setEmployeeId(Long employeeId) {
           put("employeeId", employeeId);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setCreateTime(Integer createTime) {
           put("createTime", createTime);
        }

        public void setModifyTime(Integer modifyTime) {
           put("modifyTime", modifyTime);
        }
}
