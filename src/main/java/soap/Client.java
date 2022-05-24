package soap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    protected String xmlBody;
    protected int responseCode;
    public Client(String url, String xml, String contentType){
        this.getClient(url, xml, contentType);
    }
    public Client(String url, String xml){
        String contentType = "application/soap+xml; charset=utf-8";
        this.getClient(url, xml, contentType);
    }

    private void getClient(String url, String xml, String contentType){
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
            responseCode = con.getResponseCode();
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
            xmlBody = response.toString();
        } catch (Exception e) {
            xmlBody = String.valueOf(e);
        }
    }

    public String getXmlBody() {
        return xmlBody;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
