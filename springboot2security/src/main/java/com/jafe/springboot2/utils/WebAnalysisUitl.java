package com.jafe.springboot2.utils;

import com.jafe.springboot2.service.SpringBoot2UserService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebAnalysisUitl {
    //把从服务器获得图片的输入流InputStream写到本地磁盘
    public static void saveImageToDisk(String str,int i) {

        InputStream inputStream = getInputStream(str);
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("E:\\PAZL\\设备库文档\\兄弟机床维修手册文件tmp\\"+i+".png");
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    // 从服务器获得一个输入流(本例是指从服务器获得一个image输入流)
    public static InputStream getInputStream(String str) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(str);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();

            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inputStream;

    }

    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args){
        File file = new File("E:\\PAZL\\设备库文档\\aaa.txt");
        String str = txt2String(file);
        System.out.println(str);

        Matcher m = Pattern.compile("data-src=\"//.*?\"").matcher(str);
        int i = 3;
        while(m.find()){
            String match = m.group();
            //Pattern.CASE_INSENSITIVE忽略'jpg'的大小写
            Matcher k=Pattern.compile("data-src=\"//.*?.png",Pattern.CASE_INSENSITIVE).matcher(match);
            if(k.find()){
                String match1 = "http:"+match.substring(10,match.length()-1);
                System.out.println(match1);
                saveImageToDisk(match1,i++);
            }
        }
    }

}