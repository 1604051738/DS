package com.ruoyi.DS.utils;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class DSUtils {

    /*
        获取上个月 map = [上个月第一天， 上个月最后一天]
     */


    public static Map<String, String> getLastMonth(){
        String firstDay = null;
        String lastDay = null;
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        firstDay = format.format(cal_1.getTime());
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        lastDay = format.format(cale.getTime());
        map.put("firstDay", firstDay);
        map.put("lastDay", lastDay);
        return map;
    }

    public static String getFileName(MultipartFile file) throws IOException {
            //上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName  = FileUploadUtils.upload(filePath, file);

            return fileName;
    }
}
