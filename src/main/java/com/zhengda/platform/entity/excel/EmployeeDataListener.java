package com.zhengda.platform.entity.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.zhengda.platform.common.Constants;
import com.zhengda.platform.entity.Card;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.entity.EmployeeGroup;
import com.zhengda.platform.entity.Group;
import com.zhengda.platform.service.CardService;
import com.zhengda.platform.service.EmployeeGroupService;
import com.zhengda.platform.service.EmployeeService;
import com.zhengda.platform.service.GroupService;
import com.zhengda.platform.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;

public class EmployeeDataListener extends AnalysisEventListener<EmployeeExcelDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<EmployeeExcelDto> list = new ArrayList<EmployeeExcelDto>();

    Map<String, Group> groupNameMap = new HashMap<>();

    @Override
    public void invoke(EmployeeExcelDto data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {

        EmployeeService employeeService = SpringUtil.getApplicationContext().getBean(EmployeeService.class);
        GroupService groupService = SpringUtil.getApplicationContext().getBean(GroupService.class);
        EmployeeGroupService employeeGroupService = SpringUtil.getApplicationContext().getBean(EmployeeGroupService.class);
        CardService cardService = SpringUtil.getApplicationContext().getBean(CardService.class);
        for (EmployeeExcelDto data : list) {
            if (StringUtils.isEmpty(data.getGroupName()) || StringUtils.isEmpty(data.getPlantCode())) {
                continue;
            }
            Group group = groupNameMap.get(data.getGroupName());
            if (group == null) {
                group = groupService.getByName(data.getGroupName(), data.getPlantCode());
                if (group == null) {
                    group = new Group();
                    group.setName(data.getGroupName());
                    group.setParentId(0L);
                    group.setPlantCode(data.getPlantCode());
                    group.setPlantCode(UUID.randomUUID().toString().replace("-", ""));
                    groupService.add(group);
                }
                groupNameMap.put(group.getName(), group);
            }

            Employee employee = employeeService.getByEmployeeNo(data.getEmployeeNo(), data.getPlantCode());
            if (employee == null) {
                employee = new Employee();
                SpringUtil.copyNotNullProperties(data, employee);
                employeeService.add(employee);
            } else {
                SpringUtil.copyNotNullProperties(data, employee);
                employeeService.update(employee);
            }
            EmployeeGroup employeeGroup = employeeGroupService.getEmployeeGroup(employee.getId(), group.getId(), data.getPlantCode());
            if (employeeGroup == null) {
                employeeGroup = new EmployeeGroup();
                employeeGroup.setPlantCode(data.getPlantCode());
                employeeGroup.setEmployeeId(employee.getId());
                employeeGroup.setGroupId(group.getId());
                employeeGroup.setDeleted(Constants.DELETED_NO);
                employeeGroupService.add(employeeGroup);
            }
            String cardStr = data.getCard();
            if (!StringUtils.isEmpty(cardStr)) {
                String[] split = cardStr.split(",");

                for (String cardCode : split) {
                    Card card = cardService.getByCardCode(data.getPlantCode(), cardCode, employee.getId());
                    if (card == null) {
                        card=new Card();
                        card.setEmployeeNo(employee.getEmployeeNo());
                        card.setEmployeeId(employee.getId());
                        card.setCardCode(cardCode);
                        cardService.add(card);
                    }
                }

            }


        }

        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        LOGGER.info("存储数据库成功！");
    }
}
