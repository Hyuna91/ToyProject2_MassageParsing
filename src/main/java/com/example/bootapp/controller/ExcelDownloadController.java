package com.example.bootapp.controller;

import com.example.bootapp.util.ExcelUtil;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExcelDownloadController {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<Map<String, Object>> datas = new ArrayList<>();

        Map<String, Object> data1 = new HashMap<>();
        data1.put("id", 1);
        data1.put("name", "Jang");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("id", 2);
        data2.put("name", "Jeong");

        datas.add(data1);
        datas.add(data2);

        System.out.println(datas);
        ExcelUtil excelUtil = new ExcelUtil();

        String filepath = "C:\\dev\\example1.xlsx";
        excelUtil.createExcelToFile(datas, filepath);
    }

}
