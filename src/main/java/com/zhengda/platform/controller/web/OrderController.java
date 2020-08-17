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
import com.zhengda.platform.queryBo.UserOrderQueryBo;
import com.zhengda.platform.service.EmployeeService;
import com.zhengda.platform.service.TaskService;
import com.zhengda.platform.service.UserOrderDetailService;
import com.zhengda.platform.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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

    @RequestMapping(value = "/detail/list")
    public AjaxResult getListByTime(@Valid PlantCodeDto plantCodeDto, Long startTime, Long endTime) {

        List<UserOrderDetail> userOrderDetailList = userOrderDetailService.getListByTime(plantCodeDto.getPlantCode(), startTime, endTime);


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

        Set<Integer> statusSet = new HashSet<>();
        if (plantCodeDto.getStatus() != null) {
            statusSet.add(plantCodeDto.getStatus());
            if (plantCodeDto.getStatus().equals(3) || plantCodeDto.getStatus().equals(2)) {
                statusSet.add(2);
                statusSet.add(3);
            }

        }

        List<UserOrderDetail> userOrderDetailList = userOrderDetailService.getListByAllocated(plantCodeDto.getPlantCode(), statusSet);

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
            employeeIdSet.add(task.getEmployeeId());
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
            userOrderDetailDto.setEmployeeId(employee == null ? 0L : employee.getId());
            userOrderDetailDto.setEmployeeName(employee == null ? "" : employee.getName());
            userOrderDetailDto.setStatus(task.getStatus());
            userOrderDetailDto.setTaskId(task.getId());
            data.add(userOrderDetailDto);

        }
        return AjaxResult.success(data);
    }


    @RequestMapping(value = "/allocated_employee")
    @Transactional
    public AjaxResult list(@Valid OrderEmployee orderEmployee) {

        List<Employee> employeeList = employeeService.getListByIds(orderEmployee.getEmployeeIds());
        if (employeeList.isEmpty()) {
            return AjaxResult.warn("员工没有找到");
        }


        UserOrderDetail userOrderDetail = userOrderDetailService.getById(orderEmployee.getOrderDetailId());
        if (userOrderDetail == null) {
            return AjaxResult.warn("没有找到订单详情");
        }
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setEmployeeIdSet(orderEmployee.getEmployeeIds());
        taskQueryBo.setOrderDetailId(userOrderDetail.getId());
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        List<Task> list = taskService.getList(taskQueryBo);
        Map<Long, Task> oldEmployeeMap = list.stream().collect(Collectors.toMap(Task::getEmployeeId, X -> X));

        BigDecimal total = userOrderDetail.getTargetWeight() == null ? new BigDecimal(0) : new BigDecimal(userOrderDetail.getTargetWeight());
        BigDecimal average = userOrderDetail.getTargetWeight() == null ? new BigDecimal(0) : new BigDecimal(userOrderDetail.getTargetWeight());
        if (userOrderDetail.getTargetWeight() != null && employeeList.size() > 1) {
            average = new BigDecimal(userOrderDetail.getTargetWeight()).divide(new BigDecimal(employeeList.size()), 2, BigDecimal.ROUND_HALF_UP);
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
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.size() > 1 && i == employeeList.size() - 1) {
                BigDecimal front = new BigDecimal(employeeList.size() - 1);
                average = total.subtract(average.multiply(front));
            }
            Employee employee = employeeList.get(i);
            if (oldEmployeeMap.containsKey(employee.getId())) {
                Task task = oldEmployeeMap.get(employee.getId());
                task.setTargetWeight(average);
                task.setModifyTime(new Date());
                taskService.update(task);
                oldEmployeeMap.remove(employee.getId());

                continue;
            }
            Task task = new Task();
            task.setModifyTime(new Date());
            task.setOrderDetailId(orderEmployee.getOrderDetailId());
            task.setProcessOrderNo(userOrderDetail.getProcessOrderNo());
            task.setPlantCode(orderEmployee.getPlantCode());
            task.setProcessOrderType(userOrderDetail.getProductionType());
            task.setPlanEndTime(order.getScheduleEndDate());
            task.setPlanStartTime(order.getScheduleStartDate());
            task.setTargetWeight(average);
            task.setEmployeeId(employee.getId());
            task.setEmployeeCode(employee.getEmployeeNo());
            taskService.add(task);
        }
        Iterator<Map.Entry<Long, Task>> iterator = oldEmployeeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Task> next = iterator.next();
            Task value = next.getValue();
            taskService.deleteById(value.getId());
        }

        return AjaxResult.success("");
    }

    @RequestMapping(value = "/update_status")
    @Transactional
    public AjaxResult list(Long id, Integer status) {
        UserOrderDetail userOrderDetail = userOrderDetailService.getById(id);
        if (userOrderDetail == null) {
            return AjaxResult.warn("没有找到订单详情");
        }
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setOrderDetailId(userOrderDetail.getId());
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        List<Task> list = taskService.getList(taskQueryBo);
        if (list.isEmpty()) {
            return AjaxResult.warn("没有找到该任务");
        }
        Task task = list.get(0);
        task.setStatus(status);
        taskService.update(task);
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/update_task")
    @Transactional
    public AjaxResult list(Long taskId, String destination, BigDecimal finishedWeight, BigDecimal targetWeight) {
        Task task = taskService.getById(taskId);
        if (task == null) {
            return AjaxResult.warn("没有找到该任务");
        }
        if (!StringUtils.isEmpty(destination)) {
            task.setDestination(destination);
        }
        if (finishedWeight != null) {
            task.setFinishedWeight(finishedWeight);
        }
        if (targetWeight != null) {
            task.setTargetWeight(targetWeight);
        }
        taskService.update(task);
        return AjaxResult.success("");
    }

}
