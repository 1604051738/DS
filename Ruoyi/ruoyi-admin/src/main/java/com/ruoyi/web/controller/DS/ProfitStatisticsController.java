package com.ruoyi.web.controller.DS;

import com.ruoyi.DS.utils.DSUtils;
import com.ruoyi.DS.domain.SalesOrder;
import com.ruoyi.DS.service.ISalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;


@Transactional(rollbackFor = Exception.class)
@Controller
@RequestMapping("/profitStatistics")
public class ProfitStatisticsController {

    /*
        利润统计
     */
    @Autowired
    private ISalesOrderService salesOrderService;


    @GetMapping("/getProfiStatisticsGET")
    @ResponseBody
    public Map<String, Object> getProfiStatisticsGET(){
        return getProfiStatistics("", "");
    }


    @PostMapping("/getProfiStatistics")
    @ResponseBody
    public Map<String, Object> getProfiStatistics(@RequestParam(name = "firstDay") String firstDay, @RequestParam(name = "lastDay") String lastDay){
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, String> map1 = DSUtils.getLastMonth();

        //默认查询上个月
        if(firstDay == "" && lastDay == ""){
            firstDay = map1.get("firstDay").concat(" 00:00:00");
            lastDay = map1.get("lastDay").concat(" 23:59:59");
        }
        if(firstDay.length() < 13 && lastDay.length() < 13) {
            firstDay = firstDay.concat(" 00:00:00");
            lastDay = lastDay.concat(" 23:59:59");
        }


        //总销售额
        BigDecimal total = new BigDecimal(0.00);
        //总运费
        BigDecimal freight = new BigDecimal(0.00);;
        //总采购货本
        BigDecimal totalPurchase = new BigDecimal(0.00);;
        //退款订单数
        Integer isRefundedNumber = 0;
        //订单总数
        Integer number = 0;
        //退款订单金额
        BigDecimal totalRefundedCost = new BigDecimal(0.00);;
        //退款订单运费
        BigDecimal refundedFreight = new BigDecimal(0.00);;


        List<SalesOrder> salesOrders = salesOrderService.queryProfit(firstDay, lastDay);
        List<SalesOrder>  totalNumber = salesOrderService.selectSalesOrderList(null);
        for (SalesOrder sale:
             salesOrders) {
            number += 1;
            total = total.add(sale.getTotalCost());
            if (sale.getForwarderFreight() != null){
                freight = freight.add(sale.getForwarderFreight());
            }else{
                if (sale.getExceptFreight() == null)
                    sale.setExceptFreight(new BigDecimal(0.00));
                freight = freight.add(sale.getExceptFreight());
            }

            if (sale.getIsRefunded() == 1){
                isRefundedNumber += 1;
                totalRefundedCost = totalRefundedCost.add(sale.getTotalCost());
                if (sale.getForwarderFreight() != null){
                    refundedFreight = refundedFreight.add(sale.getForwarderFreight());
                }else{
                    if (sale.getExceptFreight() == null)
                        sale.setExceptFreight(new BigDecimal(0.00));
                    refundedFreight = refundedFreight.add(sale.getExceptFreight());
                }
            }
            if (sale.getTotalPurchase() == null)
                sale.setTotalPurchase(new BigDecimal(0.00));
            totalPurchase  = totalPurchase.add(sale.getTotalPurchase());
        }
        BigDecimal profit = total.subtract(freight).subtract(totalPurchase).subtract(totalRefundedCost).subtract(refundedFreight);

        map.put("订单总数",number);
        map.put("总销售额", total);
        map.put("总运费", freight);
        map.put("总采购货本", totalPurchase);
        map.put("总利润", profit);
        map.put("历史订单总数", totalNumber.size());
        map.put("起始日期", firstDay);
        map.put("终止日期", lastDay);
        return map;
    }



}
