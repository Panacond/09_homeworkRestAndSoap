package soap;

public class Calculator {
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
        return new BaseParser().getPostTextXml(url, xml);
    }

}
