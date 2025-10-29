package org.nvcud.ms.ag.tr;

import org.nvcud.ms.ag.lst.ParsedData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FD {
    static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    public static AtomicBoolean isDown = new AtomicBoolean(false);
    public static AtomicReference<ParsedData> parsedDataAtomicReference = new AtomicReference<>( null);
    public static ParsedData gFd(){
        if(isDown.get()){
           return parsedDataAtomicReference.get();
        }else{
            ParsedData parsedData = downFd();
            parsedDataAtomicReference.set(parsedData);
            isDown.set(true);
            executor.scheduleAtFixedRate(() -> {
                parsedDataAtomicReference.set(downFd());
                isDown.set(true);
            }, 0, 1, TimeUnit.MINUTES);
        }
        return parsedDataAtomicReference.get();
    }
    public static ParsedData downFd() {
        String urlString = "https://gschaos.club/da.j";

        try {
            URL url = new URL(urlString);
            ParsedData data = new ParsedData();
            data.setArgs(new ArrayList<>());
            // 2. 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            // 3. 读取响应
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }

            reader.close();
            String input = jsonText.toString();
            input = input.substring(2);
            int listStartIdx = input.indexOf("L[");
            String prefix = input.substring(0, listStartIdx);
            String[] parts = prefix.split(",");
            data.setCln(parts[0]);
            data.setMtd(parts[1]);
            int listEndIdx = input.indexOf("]E");
            String listContent = input.substring(listStartIdx + 2, listEndIdx);
            String[] rows = listContent.split(";");
            for (String row : rows) {
                row = row.trim();
                if (row.isEmpty()) continue;

                String[] nums = row.split(",");
                ArrayList<Integer> argList = new ArrayList<>();
                for (String num : nums) {
                    argList.add(Integer.parseInt(num.trim()));
                }
                data.getArgs().add(argList);
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ParsedData();
    }
}

