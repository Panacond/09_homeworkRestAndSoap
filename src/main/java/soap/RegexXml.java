package soap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexXml {
    public String result;
    public RegexXml(String pattern, String text, int group) {

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);
        try {
            String a = String.valueOf(m.find( ));
            System.out.println(a);
            result = m.group(group);
            System.out.println("Found value: " + result);
        } catch (Exception e) {
            result = null;
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        // String to be scanned to find the pattern.
        String line = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">  <soap:Body>    <m:NumberToWordsResponse xmlns:m=\"http://www.dataaccess.com/webservicesserver/\">      <m:NumberToWordsResult>fifty </m:NumberToWordsResult>    </m:NumberToWordsResponse>  </soap:Body></soap:Envelope>\n" +
                "Found value: <m:NumberToWordsResult>";
        String pattern = "(<m:NumberToWordsResult>)([^<]*)";
        System.out.println(new RegexXml(pattern,line, 2).result);
    }
}