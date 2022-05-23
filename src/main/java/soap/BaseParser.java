package soap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseParser {
    public  String getPost(String url, String xml){
        String contentType = "application/soap+xml; charset=utf-8";
        return getBaseResponse(url, xml, contentType);
    }

    public String getPostTextXml(String url, String xml){
        String contentType = "text/xml; charset=utf-8";
        return getBaseResponse(url, xml, contentType);
    }

    private String getBaseResponse(String url, String xml, String contentType) {
        String getResponse;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", contentType);

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
            getResponse = response.toString();
        } catch (Exception e) {
//            System.out.println(e);
            getResponse = String.valueOf(e);
        }
        return getResponse;
    }
}
