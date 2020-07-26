package com.zhengda.platform.controller.web;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.PlantCodeDto;
import com.zhengda.platform.domain.AjaxResult;
import com.zhengda.platform.entity.TunnelLibrary;
import com.zhengda.platform.queryBo.TunnelLibraryQueryBo;
import com.zhengda.platform.service.TunnelLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/tunnelLibrary")
public class TunnelLibController {

    @Resource
    private TunnelLibraryService tunnelLibraryService;
    @RequestMapping(value = "/list")
    public AjaxResult allocatedList(@Valid PlantCodeDto plantCodeDto) {
        TunnelLibraryQueryBo tunnelLibraryQueryBo=new TunnelLibraryQueryBo();
        tunnelLibraryQueryBo.setDeleted(Constants.DELETED_NO);
        tunnelLibraryQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<TunnelLibrary> list = tunnelLibraryService.getList(tunnelLibraryQueryBo);
        return AjaxResult.success(list);
    }
}
