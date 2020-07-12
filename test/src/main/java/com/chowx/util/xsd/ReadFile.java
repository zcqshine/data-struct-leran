package com.chowx.util.xsd;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zcqshine
 * @date 2020/7/8
 */
public class ReadFile {
    public static Map<String,String> read(String filePath){
        Map<String,String> map = new HashMap<>();
        Path path = Paths.get(filePath);
        Charset charset = StandardCharsets.UTF_8;
        try (BufferedReader reader = Files.newBufferedReader(path, charset)){
            String line = null;
            String key = null;
            String value = null;
            while ((line = reader.readLine()) != null){
                String regx = "\"(.*?)\"";
                Pattern pattern = Pattern.compile(regx);
                if (line.trim().startsWith("<xs:simpleType")){
                    key = null;
                    Matcher matcher = pattern.matcher(line); 
                    while (matcher.find()){
                        key = matcher.group(1);
                    }
                }
                if (line.trim().startsWith("<xs:restriction")){
                    value = null;
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()){
                        value = matcher.group(1);
                        if (value != null && value.trim().length() > 3){
                            value = value.substring(3);
                            if ("double".equals(value)){
                                value = "BigDecimal";
                            }
                            value = StrUtil.upperFirst(value);
                        }
                        map.put(key,value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        String filePath = "/Users/zcqshine/Downloads/test/TaxMLPublic.xsd";
        ReadFile.read(filePath);
    }
}
