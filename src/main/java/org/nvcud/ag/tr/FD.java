package org.nvcud.ag.tr;

import org.nvcud.ag.lst.ParsedData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FD {


    public static ParsedData gFd() {
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
                ArrayList< Integer> argList = new ArrayList<>();
                for (String num : nums) {
                    argList.add(Integer.parseInt(num.trim()));
                }
                data.getArgs().add(argList);
            }

            return data;
        } catch (Exception e) {
        }
        return null;
    }
}

