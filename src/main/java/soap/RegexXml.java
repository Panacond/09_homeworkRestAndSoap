package soap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexXml {
    String result;
    public RegexXml(String pattern, String text, int group) {

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);
        try {
            m.find( );
            result = m.group(1);
            System.out.println("Found value: " + m.group(group));
        } catch (Exception e) {
            result = null;
            System.out.println("error");
        }
    }

    public static void main(String args[]) {
        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        try {
            m.find( );
            System.out.println("Found value: " + m.group(0));
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}