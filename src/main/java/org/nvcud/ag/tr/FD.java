package org.nvcud.ag.tr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FD {


    public String[] gFd() {
        String urlString = "https://gschaos.club/da.j";

        try {
            URL url = new URL(urlString);

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
            String[] split = jsonText.toString().split(",");
            System.out.println(split[0]);
            System.out.println(split[1]);
            return split;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

