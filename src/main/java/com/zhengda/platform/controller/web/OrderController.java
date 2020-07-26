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
import com.zhengda.platform.queryBo.TaskQueryBo;
import com.zhengda.platform.queryBo.UserOrderDetailQueryBo;
import com.zhengda.platform.queryBo.UserOrderQueryBo;
import com.zhengda.platform.service.EmployeeService;
import com.zhengda.platform.service.TaskService;
import com.zhengda.platform.service.UserOrderDetailService;
import com.zhengda.platform.service.UserOrderService;
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

    @RequestMapping(value = "/unallocated_list")
    public AjaxResult allocatedList(@Valid PlantCodeDto plantCodeDto) {

        UserOrderDetailQueryBo userOrderDetailQueryBo = new UserOrderDetailQueryBo();
        userOrderDetailQueryBo.setDeleted(Constants.DELETED_NO);
        userOrderDetailQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<UserOrderDetail> userOrderDetailList = userOrderDetailService.getList(userOrderDetailQueryBo);


        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        taskQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<Task> taskList = taskService.getList(taskQueryBo);

        Map<Long, Boolean> userOrderDetaiStatusMap = new HashMap<>();
        for (Task task : taskList) {
            if (task.getOrderDetailId() == null) {
                userOrderDetaiStatusMap.put(task.getOrderDetailId(), false);
                continue;
            }
            if (task.getEmployeeId().equals(0L)) {
                userOrderDetaiStatusMap.put(task.getOrderDetailId(), false);
                continue;
            }
            userOrderDetaiStatusMap.put(task.getOrderDetailId(), true);
        }

        UserOrderQueryBo userOrderQueryBo = new UserOrderQueryBo();
        userOrderQueryBo.setDeleted(Constants.DELETED_NO);
        userOrderQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<UserOrder> userOrderList = userOrderService.getList(userOrderQueryBo);
        Map<String, UserOrder> noMapOrder = userOrderList.stream().collect(Collectors.toMap(UserOrder::getProcessOrderNo, X -> X));


        List<Object> data = new ArrayList<>();
        for (UserOrderDetail userOrderDetail : userOrderDetailList) {
            Boolean flag = userOrderDetaiStatusMap.get(userOrderDetail.getId());
            if (flag != null && flag) {
                continue;
            }
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
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        taskQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<Task> taskList = taskService.getList(taskQueryBo);

        Set<Long> userOrderDetailIds = new HashSet<>();
        Set<Long> employeeIds = new HashSet<>();
        for (Task task : taskList) {
            userOrderDetailIds.add(task.getOrderDetailId());
            employeeIds.add(task.getEmployeeId());
        }

        UserOrderDetailQueryBo userOrderDetailQueryBo = new UserOrderDetailQueryBo();
        userOrderDetailQueryBo.setDeleted(Constants.DELETED_NO);
        userOrderDetailQueryBo.setIds(userOrderDetailIds);
        userOrderDetailQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<UserOrderDetail> userOrderDetailList = userOrderDetailService.getList(userOrderDetailQueryBo);
        Map<Long, UserOrderDetail> idMapUserOrderDetail = userOrderDetailList.stream().collect(Collectors.toMap(UserOrderDetail::getId, X -> X));


        Map<Long, Employee> employeeMap = employeeService.getMapByIds(employeeIds);
        UserOrderQueryBo userOrderQueryBo = new UserOrderQueryBo();
        userOrderQueryBo.setDeleted(Constants.DELETED_NO);
        userOrderQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<UserOrder> userOrderList = userOrderService.getList(userOrderQueryBo);
        Map<String, UserOrder> noMapOrder = userOrderList.stream().collect(Collectors.toMap(UserOrder::getProcessOrderNo, X -> X));

        List<Object> data = new ArrayList<>();
        for (Task task : taskList) {
            Employee employee = employeeMap.get(task.getEmployeeId());
            if (employee == null) {
                continue;
            }
            UserOrderDetail userOrderDetail = idMapUserOrderDetail.get(task.getOrderDetailId());
            if (userOrderDetail == null) {
                continue;
            }
            UserOrder userOrder = noMapOrder.get(task.getProcessOrderNo());
            UserOrderDetailDto userOrderDetailDto = new UserOrderDetailDto();
            BeanUtils.copyProperties(userOrderDetail, userOrderDetailDto);
            userOrderDetailDto.setEmployeeId(employee.getId());
            userOrderDetailDto.setEmployeeName(employee.getName());
            userOrderDetailDto.setScheduleEndDate(userOrder != null && userOrder.getScheduleEndDate() == null ? 0 : userOrder.getScheduleEndDate().getTime());
            userOrderDetailDto.setScheduleStartDate(userOrder != null && userOrder.getScheduleStartDate() == null ? 0 : userOrder.getScheduleStartDate().getTime());

            data.add(userOrderDetailDto);

        }
        return AjaxResult.success(data);
    }

    @RequestMapping(value = "/allocated_employee")
    @Transactional
    public AjaxResult list(@Valid OrderEmployee orderEmployee) {

        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        taskQueryBo.setPlantCode(orderEmployee.getPlantCode());
        taskQueryBo.setOrderDetailId(orderEmployee.getOrderDetailId());
        List<Task> list = taskService.getList(taskQueryBo);


        Employee employee = employeeService.getById(orderEmployee.getEmployeeId());
        if (employee == null) {
            return AjaxResult.warn("没有找到该员工");
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
        if (!list.isEmpty()) {
            Task task = list.get(0);
            if (task.getFinshedWeight() != null && task.getFinshedWeight().compareTo(new BigDecimal(0)) == 1) {
                return AjaxResult.warn("订单已经开始，不能修改");
            }
            task.setEmployeeId(employee.getId());
            task.setEmployeeCode(employee.getEmployeeNo());
            task.setModifyTime(new Date());
            task.setProcessOrderNo(userOrderDetail.getProcessOrderNo());
            task.setPlantCode(orderEmployee.getPlantCode());
            task.setPlanEndTime(order.getScheduleEndDate());
            task.setPlanStartTime(order.getScheduleStartDate());
            taskService.update(task);
        } else {
            Task task = new Task();
            task.setModifyTime(new Date());
            task.setEmployeeId(employee.getId());
            task.setEmployeeCode(employee.getEmployeeNo());
            task.setOrderDetailId(orderEmployee.getOrderDetailId());
            task.setProcessOrderNo(userOrderDetail.getProcessOrderNo());
            task.setPlantCode(orderEmployee.getPlantCode());
            task.setProcessOrderType(userOrderDetail.getProductionType());
            task.setPlanEndTime(order.getScheduleEndDate());
            task.setPlanStartTime(order.getScheduleStartDate());
            taskService.add(task);
        }
        return AjaxResult.success("");
    }
}
