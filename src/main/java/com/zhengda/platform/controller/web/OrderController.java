package com.zhengda.platform.controller.web;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.OrderEmployee;
import com.zhengda.platform.controller.dto.PlantCodeDto;
import com.zhengda.platform.controller.dto.UserOrderDetailDto;
import com.zhengda.platform.domain.AjaxResult;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.entity.Task;
import com.zhengda.platform.entity.UserOrder;
import com.zhengda.platform.entity.UserOrderDetail;
import com.zhengda.platform.entity.ext.TaskEmployeeExt;
import com.zhengda.platform.queryBo.TaskQueryBo;
import com.zhengda.platform.queryBo.UserOrderQueryBo;
import com.zhengda.platform.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/order")
public class OrderController {
    @Resource
    private UserOrderService userOrderService;
    @Resource
    private UserOrderDetailService userOrderDetailService;
    @Resource
    private TaskService taskService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private TaskEmployeeService taskEmployeeService;

    @RequestMapping(value = "/unallocated_list")
    public AjaxResult allocatedList(@Valid PlantCodeDto plantCodeDto, Long startTime, Long endTime) {

        List<UserOrderDetail> userOrderDetailList = userOrderDetailService.getListByUnallocated(plantCodeDto.getPlantCode(), startTime, endTime);


        UserOrderQueryBo userOrderQueryBo = new UserOrderQueryBo();
        userOrderQueryBo.setDeleted(Constants.DELETED_NO);
        userOrderQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<UserOrder> userOrderList = userOrderService.getList(userOrderQueryBo);
        Map<String, UserOrder> noMapOrder = userOrderList.stream().collect(Collectors.toMap(UserOrder::getProcessOrderNo, X -> X));


        List<Object> data = new ArrayList<>();
        for (UserOrderDetail userOrderDetail : userOrderDetailList) {
            UserOrder userOrder = noMapOrder.get(userOrderDetail.getProcessOrderNo());
            UserOrderDetailDto userOrderDetailDto = new UserOrderDetailDto();
            BeanUtils.copyProperties(userOrderDetail, userOrderDetailDto);
            userOrderDetailDto.setScheduleEndDate(userOrder != null && userOrder.getScheduleEndDate() == null ? 0 : userOrder.getScheduleEndDate().getTime());
            userOrderDetailDto.setScheduleStartDate(userOrder != null && userOrder.getScheduleStartDate() == null ? 0 : userOrder.getScheduleStartDate().getTime());
            data.add(userOrderDetailDto);
        }
        return AjaxResult.success(data);
    }

    @RequestMapping(value = "/allocated_list")
    public AjaxResult list(@Valid PlantCodeDto plantCodeDto) {
        /**
         * 已经分配的订单详情
         */
        List<UserOrderDetail> userOrderDetailList = userOrderDetailService.getListByAllocated(plantCodeDto.getPlantCode());

        /**
         * 订单列表
         */
        UserOrderQueryBo userOrderQueryBo = new UserOrderQueryBo();
        userOrderQueryBo.setDeleted(Constants.DELETED_NO);
        userOrderQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<UserOrder> userOrderList = userOrderService.getList(userOrderQueryBo);
        Map<String, UserOrder> noMapOrder = userOrderList.stream().collect(Collectors.toMap(UserOrder::getProcessOrderNo, X -> X));


        /**
         * 获取任务的完成量
         */
        Set<Long> detailIdSet = userOrderDetailList.stream().map(UserOrderDetail::getId).collect(Collectors.toSet());
        List<Task> taskList = taskService.getListByOrderDetailIdSet(detailIdSet);
        Map<Long, Task> orderDetailIdMap = new HashMap<>();
        Set<Long> employeeIdSet = new HashSet<>();
        for (Task task : taskList) {
            orderDetailIdMap.put(task.getOrderDetailId(), task);
            employeeIdSet.add(task.getId());
        }

        Map<Long, Employee> employeeMap = employeeService.getMapByIds(employeeIdSet);

        List<Object> data = new ArrayList<>();
        for (UserOrderDetail userOrderDetail : userOrderDetailList) {
            Task task = orderDetailIdMap.get(userOrderDetail.getId());
            if (task == null) {
                continue;
            }
            UserOrder userOrder = noMapOrder.get(userOrderDetail.getProcessOrderNo());
            UserOrderDetailDto userOrderDetailDto = new UserOrderDetailDto();
            BeanUtils.copyProperties(userOrderDetail, userOrderDetailDto);
            userOrderDetailDto.setScheduleEndDate(userOrder != null && userOrder.getScheduleEndDate() == null ? 0 : userOrder.getScheduleEndDate().getTime());
            userOrderDetailDto.setScheduleStartDate(userOrder != null && userOrder.getScheduleStartDate() == null ? 0 : userOrder.getScheduleStartDate().getTime());
            userOrderDetailDto.setFinishedWeight(task.getFinishedWeight());
            userOrderDetailDto.setPersonnelStation(task.getPersonnelStation());
            Employee employee = employeeMap.get(task.getEmployeeId());
            userOrderDetailDto.setId(employee == null ? 0L : employee.getId());
            userOrderDetailDto.setEmployeeName(employee == null ? "" : employee.getName());
            data.add(userOrderDetailDto);

        }
        return AjaxResult.success(data);
    }
//
//    private String getEmployeeNames(List<TaskEmployeeExt> taskEmployeeExts) {
//
//        StringBuffer sb = new StringBuffer();
//        for (TaskEmployeeExt taskEmployeeExt : taskEmployeeExts) {
//            sb.append(taskEmployeeExt.getEmployeeName()).append(",");
//        }
//        String content = sb.toString();
//        if (content.length() > 0) {
//            content = content.substring(0, content.length() - 1);
//        }
//        return content;
//
//    }


    @RequestMapping(value = "/allocated_employee")
    @Transactional
    public AjaxResult list(@Valid OrderEmployee orderEmployee) {

        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        taskQueryBo.setPlantCode(orderEmployee.getPlantCode());
        taskQueryBo.setOrderDetailId(orderEmployee.getOrderDetailId());
        List<Task> list = taskService.getList(taskQueryBo);


        List<Employee> employeeList = employeeService.getListByIds(orderEmployee.getEmployeeIds());
        if (employeeList.isEmpty()) {
            return AjaxResult.warn("员工没有找到");
        }


        UserOrderDetail userOrderDetail = userOrderDetailService.getById(orderEmployee.getOrderDetailId());
        if (userOrderDetail == null) {
            return AjaxResult.warn("没有找到订单详情");
        }

        UserOrderQueryBo userOrderQueryBo = new UserOrderQueryBo();
        userOrderQueryBo.setPlantCode(orderEmployee.getPlantCode());
        userOrderQueryBo.setProcessOrderNo(userOrderDetail.getProcessOrderNo());
        userOrderQueryBo.setId(userOrderDetail.getOrderId());
        List<UserOrder> orderList = userOrderService.getList(userOrderQueryBo);
        if (orderList.isEmpty()) {
            return AjaxResult.warn("没有找到订单");
        }
        UserOrder order = orderList.get(0);
        Task task = null;
        if (list.isEmpty()) {
            task = new Task();
            task.setModifyTime(new Date());
            task.setOrderDetailId(orderEmployee.getOrderDetailId());
            task.setProcessOrderNo(userOrderDetail.getProcessOrderNo());
            task.setPlantCode(orderEmployee.getPlantCode());
            task.setProcessOrderType(userOrderDetail.getProductionType());
            task.setPlanEndTime(order.getScheduleEndDate());
            task.setPlanStartTime(order.getScheduleStartDate());
            taskService.add(task);
        } else {
            task = list.get(0);
        }
        if (task.getFinishedWeight() != null && task.getFinishedWeight().compareTo(new BigDecimal(0)) == 1) {
            return AjaxResult.warn("订单已经开始，不能修改");
        }

        taskEmployeeService.init(task, employeeList);
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/update_status")
    @Transactional
    public AjaxResult list(Long taskId, Integer status) {
        Task task = taskService.getById(taskId);
        task.setStatus(status);
        taskService.update(task);
        return AjaxResult.success("");
    }

}
