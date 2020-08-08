package com.zhengda.platform.entity.ext;

import lombok.Data;

@Data
public class TaskEmployeeExt {
    private String employeeNo;
    private String employeeName;
    private Long taskId;
    private Long employeeId;
    private String personnelStation;
}
