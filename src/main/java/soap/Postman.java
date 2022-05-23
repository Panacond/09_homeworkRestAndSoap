package soap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Postman {
    public String getNumber2(int number){
        String url = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "    <soap:Body>" +
                "        <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">" +
                "            <ubiNum>" +Integer.toString(number) +  "</ubiNum>" +
                "        </NumberToWords>" +
                "    </soap:Body>" +
                "</soap:Envelope>";
        String resultResponse = new BaseParser().getPost(url, xml);
        return resultResponse;
    }
}
