package com.zhengda.platform.controller.web;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.CardDto;
import com.zhengda.platform.domain.AjaxResult;
import com.zhengda.platform.entity.Card;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.queryBo.CardQueryBo;
import com.zhengda.platform.service.CardService;
import com.zhengda.platform.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/card")
public class CardController {

    @Resource
    private CardService cardService;
    @Resource
    private EmployeeService employeeService;

    @PostMapping(value = "/add")
    @Transactional
    public AjaxResult add(@Valid CardDto cardDto) {


        CardQueryBo cardQueryBo = new CardQueryBo();
        cardQueryBo.setDeleted(Constants.DELETED_NO);
        cardQueryBo.setCardCode(cardDto.getCardCode());
        Long count = cardService.getCount(cardQueryBo);
        if (count > 0) {
            return AjaxResult.warn("卡号已经被占用");
        }
        Employee employee = employeeService.getById(cardDto.getEmployeeId());
        if (employee == null) {
            return AjaxResult.warn("没有找到该员工");
        }
        Card card = new Card();
        BeanUtils.copyProperties(cardDto, card);
        card.setEmployeeNo(employee.getEmployeeNo());
        cardService.add(card);
        return AjaxResult.success("");
    }

    @GetMapping(value = "/get")
    public AjaxResult get(Long employeeId) {
        if (employeeId == null) {
            return AjaxResult.warn("员工Id不能为空");
        }
        CardQueryBo cardQueryBo = new CardQueryBo();
        cardQueryBo.setDeleted(Constants.DELETED_NO);
        cardQueryBo.setEmployeeId(employeeId);
        List<Card> list = cardService.getList(cardQueryBo);

        List<CardDto> data = new ArrayList<>();
        for (Card card : list) {
            CardDto cardDto = new CardDto();
            BeanUtils.copyProperties(card, cardDto);
            data.add(cardDto);
        }
        return AjaxResult.success(data);
    }

    @PostMapping(value = "/delete")
    public AjaxResult delete(Long id) {
        cardService.deleteById(id);
        return AjaxResult.success("");
    }
}
