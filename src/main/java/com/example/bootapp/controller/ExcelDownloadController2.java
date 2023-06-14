package com.example.bootapp.controller;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDownloadController2 {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // 데이터 추가 예시
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("이름");
        headerRow.createCell(1).setCellValue("나이");
        headerRow.createCell(2).setCellValue("이메일");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("John Doe");
        dataRow.createCell(1).setCellValue(30);
        dataRow.createCell(2).setCellValue("johndoe@example.com");

        // Excel 파일 저장
        try (FileOutputStream fileOut = new FileOutputStream("C:\\dev\\example2.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel 파일이 성공적으로 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
