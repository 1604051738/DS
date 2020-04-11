package com.ruoyi.DS.utils;

import com.ruoyi.DS.service.impl.RandomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component

public  final class OrderNumberBuilder {

    private static Long RANDOMNUMER = null;


    public static String getOrderNumber(){
        String number = new String();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        String string = format.format(date);
        String string1 =string.replace("-", "");
        string1.substring(0,2);
        number = "S"+string1+RANDOMNUMER;
        return number;
    }


    public static Long getRANDOMNUMER() {
        return RANDOMNUMER;
    }

    public static void setRANDOMNUMER(Long RANDOMNUMER) {
        OrderNumberBuilder.RANDOMNUMER = RANDOMNUMER;
    }
}
