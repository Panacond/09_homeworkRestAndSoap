package soap;

public class Postman extends Client{
    String textResult;

    public Postman(int number){
        super("https://www.dataaccess.com/webservicesserver/NumberConversion.wso",
                FileRead.readFile("src/main/resources/postman.xml")
                            .replace("\"integer\"", Integer.toString(number)));
    }

    public String getTextResult() {
        textResult = new RegexXml("(<m:NumberToWordsResult>)([^<]*)", super.getXmlBody(), 2).result;
        return textResult;
    }
}
