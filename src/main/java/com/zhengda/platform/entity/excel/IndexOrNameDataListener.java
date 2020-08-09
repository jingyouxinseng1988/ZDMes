package com.zhengda.platform.entity.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.zhengda.platform.entity.FridUuid;
import com.zhengda.platform.queryBo.FridUuidQueryBo;
import com.zhengda.platform.service.FridUuidService;
import com.zhengda.platform.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class IndexOrNameDataListener extends AnalysisEventListener<CarFridDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexOrNameDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<CarFridDto> list = new ArrayList<CarFridDto>();

    @Override
    public void invoke(CarFridDto data, AnalysisContext context) {
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
        FridUuidService fridUuidService = SpringUtil.getApplicationContext().getBean(FridUuidService.class);

        for (CarFridDto dto : list) {
            FridUuidQueryBo fridUuidQueryBo = new FridUuidQueryBo();
            fridUuidQueryBo.setFrid(dto.getFrid());
            fridUuidQueryBo.setUuid(dto.getUuid());

            Long count = fridUuidService.getCount(fridUuidQueryBo);
            if (count > 0) {
                continue;
            }
            FridUuid fridUuid = new FridUuid();
            fridUuid.setFrid(dto.getFrid());
            fridUuid.setUuid(dto.getUuid());
            fridUuidService.add(fridUuid);
        }

        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        LOGGER.info("存储数据库成功！");
    }
}
