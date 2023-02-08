package com.example.bootapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Controller
@RequestMapping("/sitatex")
@Slf4j
public class SitatexController {

    @GetMapping(value = "/list")
    public String list(Model model) {
        String DATA_DIRECTORY = "C:\\Users\\장현아\\Desktop\\BootApp\\src\\main\\resources\\sitatex\\";
//        String DATA_DIRECTORY = "C:\\dev\\BootApp\\src\\main\\resources\\sitatex\\";
        File dir = new File(DATA_DIRECTORY);
        String[] filenames = dir.list();
        for(int i = 0; i < filenames.length; i++) {
            System.out.println(filenames[i]);
        }

        model.addAttribute("filenames", filenames);

        return "sitatex/index";
    }

    @ResponseBody
    @RequestMapping(value = "/detail")
    public List<String> detail(@RequestBody String filename) {
        String filenames = filename.replaceAll("\"", "");

//        String sitaPath = "sitatex\\" + "0EF81E27.RCV";
        String sitaPath = "sitatex\\" + filenames;
        ClassPathResource resource = new ClassPathResource(sitaPath);

        List<String> content = null;
        try {
            Path path = Paths.get(resource.getURI());
            log.info("=======path=======");
            log.info(String.valueOf(path));
            content = Files.readAllLines(path);
            log.info("=======content=======");
            log.info(String.valueOf(content));
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }

//        자바로 항목별 나누기
//        int headerIndex = (content.indexOf("=HEADER"));
//        int priorityIndex = (content.indexOf("=PRIORITY"));
//        int destinationIndex = (content.indexOf("=DESTINATION TYPE B"));
//        int originIndex = (content.indexOf("=ORIGIN"));
//        int subjectIndex = (content.indexOf("=SUBJECT"));
//        int smiIndex = (content.indexOf("=SMI"));
//        int textIndex = (content.indexOf("=TEXT"));
//        int siIndex = (content.indexOf("SI"));
//
//        log.info(String.valueOf(headerIndex));
//        log.info(String.valueOf(priorityIndex));
//        log.info(String.valueOf(destinationIndex));
//        log.info(String.valueOf(originIndex));
//        log.info(String.valueOf(subjectIndex));
//        log.info(String.valueOf(smiIndex));
//        log.info(String.valueOf(textIndex));
//        log.info(String.valueOf(siIndex));
//
//        List<String> hearderStr = content.subList(headerIndex+1, priorityIndex);
//        List<String> priorityStr = content.subList(priorityIndex+1, destinationIndex);
//        List<String> destinationStr = content.subList(destinationIndex+1, originIndex);
//
//        List<String> originStr;
//        if (originIndex != 0 && subjectIndex == 0) {
//            originStr = content.subList(originIndex+1, smiIndex);
//        } else if (originIndex != 0 && subjectIndex != 0) {
//            originStr = content.subList(originIndex+1, subjectIndex);
//        } else {
//            originStr = null;
//        }
//
//        List<String> subjectStr;
//        if (subjectIndex == -1) {
//            subjectStr = null;
//        } else {
//            subjectStr = content.subList(subjectIndex+1, smiIndex);
//        }
//
//        List<String> smiStr;
//        if (subjectIndex == -1) {
//            smiStr = null;
//        } else {
//            smiStr = content.subList(smiIndex+1, textIndex);
//        }
//
//        List<String> textContentStr;
//        if (subjectIndex == -1) {
//            textContentStr = null;
//        } else {
//            textContentStr = content.subList(textIndex+1, siIndex);
//        }
//
//        List<String> siStr;
//        if (siIndex == -1) {
//            siStr = null;
//        } else {
//            siStr = content.subList(siIndex+1,content.size());
//        }
//
//        log.info(String.valueOf(hearderStr));
//        log.info(String.valueOf(priorityStr));
//        log.info(String.valueOf(destinationStr));
//        log.info(String.valueOf(originStr));
//        log.info(String.valueOf(subjectStr));
//        log.info(String.valueOf(smiStr));
//        log.info(String.valueOf(textContentStr));
//        log.info(String.valueOf(siStr));
        return content;
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody Object message) {
        // 파일명은 8자리 난수
        Random random = new Random();
        String temp = Integer.toString( random.nextInt(8) + 1);

        for (int i = 0; i < 7; i++) {
            temp+= Integer.toString(random.nextInt(9));
            log.info("temp===>>>"+temp);
        }
        String filename = temp + ".RCV";
        String filenames = filename.replaceAll("\"", "");
        String sitaPath = "C:\\dev\\BootApp\\src\\main\\resources\\sitatex\\" + filenames;
        Map<String, String> map = new HashMap<>((Map) message);
        log.info(map.get("headerVal"));

        String content = "=HEADER" + '\n' +
                        map.get("headerVal") + '\n' +
                         "=PRIORITY" + '\n' +
                        map.get("priorityVal") + '\n' +
                        "=DESTINATION TYPE B" + '\n' +
                        map.get("destinationVal") + '\n' +
                        "=ORIGIN" + '\n' +
                        map.get("originVal") + '\n' +
                        "=SUBJECT" + '\n' +
                        map.get("subjectVal") + '\n' +
                        "=SMI" + '\n' +
                        map.get("smiVal") + '\n' +
                        "=TEXT" + '\n' +
                        map.get("contentVal") + '\n' +
                        "SI" + '\n' +
                        map.get("siVal") + '\n';

        log.info("content==========>>>>" + content);
        File file = new File(sitaPath);

        try {
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);

                writer.write(content);

                writer.close();

                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "sitatex/index";
    }
}
