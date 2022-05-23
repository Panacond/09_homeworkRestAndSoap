package soap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SoapTest {

    @Test
    public void postmanTest(){
        String resultSearch = new Postman().getNumber2(50);
        String expectation = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">  <soap:Body>    <m:NumberToWordsResponse xmlns:m=\"http://www.dataaccess.com/webservicesserver/\">      <m:NumberToWordsResult>fifty </m:NumberToWordsResult>    </m:NumberToWordsResponse>  </soap:Body></soap:Envelope>";
        Assert.assertEquals(expectation, resultSearch);
    }
    @Test (enabled = false)
    public void postmanTest2(){
        String resultSearch = new Postman().getNumber2(50);
        String number = new RegexXml("(<m:NumberToWordsResult>)(.*?)(<)", resultSearch, 1).result;
        Assert.assertEquals(number, "fifty ");

    }

    @Test (enabled = false)
    public void caclulatorTest(){
        Calculator item = new Calculator();
        String value = item.getResultAdd2(2,2);
        System.out.println(value);
        String result = new RegexXml("(<m:NumberToWordsResult>)(.*?)(<)",value, 1).result;
        System.out.println(result);
    }

    @Test
    public void caclulatorTest2(){
        Calculator item = new Calculator();
        String value = item.getResultAdd2(2,2);
        System.out.println(value);
        String result = new RegexXml("(<m:NumberToWordsResult>)(.*?)(<)",value, 1).result;
        System.out.println(result);
        Assert.assertTrue(false);

    }

    @Test
    public void caclulatorTestNo(){
        Calculator item = new Calculator();
        String value = item.getResultAddNoI();
        System.out.println(value);
        String result = new RegexXml("(<m:NumberToWordsResult>)(.*?)(<)",value, 1).result;
        Assert.assertTrue(false);
        System.out.println(result);
    }
}
