package com.ruoyi.DS.utils;

import com.ruoyi.DS.domain.Skuproduct;
import com.ruoyi.DS.service.ISkuproductService;


import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;


public  final class SkuCodeBuilder {


    public static String getSkuCode(Long productCode , ISkuproductService skuproductService){
        String code = new String();
        Format f1 = new DecimalFormat("000");
        for (int i = 1; i < 999; i++) {
            code = productCode + "-" +f1.format(i);
            Skuproduct skuproduct =  skuproductService.selectSkuproductByCode(code);
            if (skuproduct == null)
                break;
        }

        return code;
    }
}
