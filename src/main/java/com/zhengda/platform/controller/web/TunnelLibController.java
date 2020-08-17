package com.zhengda.platform.controller.web;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.PlantCodeDto;
import com.zhengda.platform.domain.AjaxResult;
import com.zhengda.platform.entity.TunnelLibrary;
import com.zhengda.platform.enums.TunnelLibraryType;
import com.zhengda.platform.queryBo.TunnelLibraryQueryBo;
import com.zhengda.platform.service.TunnelLibraryService;
import com.zhengda.platform.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/tunnelLibrary")
public class TunnelLibController {

    @Resource
    private TunnelLibraryService tunnelLibraryService;

    @RequestMapping(value = "/list")
    public AjaxResult allocatedList(@Valid PlantCodeDto plantCodeDto) {
        Date startTimeByDay = plantCodeDto.getStartTime() == null ? null : DateUtils.getStartTimeByDay(new Date(plantCodeDto.getStartTime()));
        Date endTimeByDay = plantCodeDto.getEndTime() == null ? null : DateUtils.getEndTimeByDay(new Date(plantCodeDto.getEndTime()));
        TunnelLibraryQueryBo tunnelLibraryQueryBo = new TunnelLibraryQueryBo();
        tunnelLibraryQueryBo.setDeleted(Constants.DELETED_NO);
        tunnelLibraryQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        tunnelLibraryQueryBo.setType(plantCodeDto.getType());
        tunnelLibraryQueryBo.setStartTime(startTimeByDay);
        tunnelLibraryQueryBo.setEnterTime(endTimeByDay);
        List<TunnelLibrary> list = tunnelLibraryService.getList(tunnelLibraryQueryBo);
        return AjaxResult.success(list);
    }

    @RequestMapping(value = "/statistics")
    public AjaxResult statistics(@Valid PlantCodeDto plantCodeDto) {
        TunnelLibraryQueryBo tunnelLibraryQueryBo = new TunnelLibraryQueryBo();
        tunnelLibraryQueryBo.setDeleted(Constants.DELETED_NO);
        tunnelLibraryQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<TunnelLibrary> list = tunnelLibraryService.getList(tunnelLibraryQueryBo);
        BigDecimal out = new BigDecimal(0);
        BigDecimal enter = new BigDecimal(0);
        for (TunnelLibrary tl : list) {
            if (tl.getWeight() == null) {
                continue;
            }
            if (TunnelLibraryType.ENTER.equals(tl.getType())) {
                enter = enter.add(tl.getWeight());
            }
            if (TunnelLibraryType.OUT.equals(tl.getType())) {
                out = out.add(tl.getWeight());
            }
        }
        Map map = new HashMap<>();
        map.put("enter", enter);
        map.put("out", out);
        return AjaxResult.success(map);
    }

}
