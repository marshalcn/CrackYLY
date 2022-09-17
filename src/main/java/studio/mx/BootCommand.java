package studio.mx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component
public class BootCommand implements CommandLineRunner {
    private int connectCounter = 0;
    @Value("${loop}")
    private int limitLoop;
    @Value("${token}")
    private String token;
    @Value("${ignore-sleep}")
    private boolean noneFakePlayFlag;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting LOOP : " + limitLoop + " \nIGNORE SLEEP : " + noneFakePlayFlag + " \nTOKEN : " + token);
        System.out.println("------------------------ START - LIMIT - LINE ------------------------");
        for (int i = 0; i< limitLoop; i++){
            getContent();
        }
    }
    public void getContent() throws Exception{
        Random random = new Random();
        connectCounter ++;
        System.out.println("CURRENT LOOP : " + connectCounter);
        Integer timeCost = 362 + random.nextInt(93);
        String urls = "https://cat-match.easygame2021.com/sheep/v1/game/game_over?rank_score=1&rank_state=1&rank_time=" + timeCost + "&rank_role=1&skin=1";
        URL url = new URL(urls);
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQ0OTQ1NjksIm5iZiI6MTY2MzM5MjM2OSwiaWF0IjoxNjYzMzkwNTY5LCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjo2MTU1NzUyMSwiZGVidWciOiIiLCJsYW5nIjoiIn0.qSQctKrEWdz5uNZTZWMVpBL_xP8xLT2UXlv_dqir7-I" ;
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.setConnectTimeout(11000);
        httpConn.setRequestProperty("accept", "*/*");
        httpConn.setRequestProperty("accept-language", "en-us,en");
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("Host", "cat-match.easygame2021.com");
        httpConn.setRequestProperty("Refer", "https://servicewechat.com/wx141bfb9b73c970a9/14/index.html");
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36 MicroMessenger/7.0.4.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF");
        httpConn.setRequestProperty("Xweb_xhr", "1");
        httpConn.setRequestProperty("t", token);
        httpConn.setRequestProperty("sec-fetch-mode", "cors");
        httpConn.setRequestProperty("sec-fetch-site", "cross-site");
        httpConn.setRequestProperty("sec-fetch-dest", "empty");

        boolean erFlg = false;
        try {
            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            System.out.println(response);
        }catch (Exception e){
            erFlg = true;
            System.out.println(e.getMessage());
        }
        if (!noneFakePlayFlag){
            int whatEverSleep = random.nextInt(12);
            System.out.println("Whatever sleep for : " + whatEverSleep + " secs");
            TimeUnit.SECONDS.sleep(whatEverSleep);
        }else {
            int asSleep = random.nextInt(5);
            System.out.println("As if sleep : " + asSleep + " secs");
            TimeUnit.SECONDS.sleep(asSleep);
        }
        if (!erFlg && !noneFakePlayFlag){
            System.out.println("Sleep for fake play : " + timeCost + " secs");
            TimeUnit.SECONDS.sleep(timeCost);
        }else {
            getContent();
        }
    }
}
