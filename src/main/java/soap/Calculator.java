package soap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Calculator {
    public  String getResultAdd(int firstNumber, int secondNumber){
        String getResponse;
        try {
            String url = "http://www.dneonline.com/calculator.asmx";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                    "  <soap:Body>" +
                    "    <Add xmlns=\"http://tempuri.org/\">" +
                    "      <intA>" + firstNumber + "</intA>" +
                    "      <intB>" + secondNumber + "</intB>" +
                    "    </Add>" +
                    "  </soap:Body>" +
                    "</soap:Envelope>";

            xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                    "  <soap:Body>" +
                    "    <Add xmlns=\"http://tempuri.org/\">" +
                    "      <intA>2</intA>" +
                    "      <intB>2</intB>" +
                    "    </Add>" +
                    "  </soap:Body>" +
                    "</soap:Envelope>";
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
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            getResponse = response.toString();
        } catch (Exception e) {
            System.out.println(e);
            getResponse = String.valueOf(e);
        }
        return getResponse;
    }

    public  String getResultAdd2(int firstNumber, int secondNumber){
        String url = "http://www.dneonline.com/calculator.asmx";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "  <soap:Body>" +
                "    <Add xmlns=\"http://tempuri.org/\">" +
                "      <intA>" + firstNumber + "</intA>" +
                "      <intB>" + secondNumber + "</intB>" +
                "    </Add>" +
                "  </soap:Body>" +
                "</soap:Envelope>";
        String resultResponse = new BaseParser().getPost(url, xml);
        return resultResponse;
    }

    public  String getResultAddNoI(){
        String url = "http://www.dneonline.com/calculator.asmx";
        String xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "  <soap:Body>" +
                "    <Add xmlns=\"http://tempuri.org/\">" +
                "      <intA>2</intA>" +
                "      <intB>2</intB>" +
                "    </Add>" +
                "  </soap:Body>" +
                "</soap:Envelope>";
        String resultResponse = new BaseParser().getPost(url, xml);
        return resultResponse;
    }
}
