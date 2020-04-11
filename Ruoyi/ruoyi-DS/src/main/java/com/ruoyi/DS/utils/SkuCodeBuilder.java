package com.ruoyi.DS.utils;

import com.ruoyi.DS.domain.Skuproduct;
import com.ruoyi.DS.service.ISkuproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;


public  final class SkuCodeBuilder {


    public static String getSkuCode(Long productId , ISkuproductService skuproductService){
        String code = new String();
        Format f1 = new DecimalFormat("000");
        for (int i = 1; i < 999; i++) {
            code = productId + "-" +f1.format(i);
            Skuproduct skuproduct = new Skuproduct();
            skuproduct.setSkucode(code);
            List<Skuproduct> list= skuproductService.selectSkuproductList(skuproduct);
            if (list.size() == 0)
                break;
        }

        return code;
    }
}
