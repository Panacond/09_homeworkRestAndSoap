package soap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SoapTest {

    @Test
    public void postmanTest(){
        Postman resultSearch = new Postman(50);
        int responseCode = resultSearch.getResponseCode();
        Assert.assertEquals(responseCode, 200, responseCode + "= 200");
        Assert.assertEquals(resultSearch.getTextResult(), "fifty ");
    }

    @Test
    public void calculatorAddTest(){
        Calculator resultSearch = new Calculator(2, 3,operation.ADD);
        int responseCode = resultSearch.getResponseCode();
        Assert.assertEquals(responseCode, 200, responseCode + "= 200");
        Assert.assertEquals(resultSearch.getTextResult(), "5", resultSearch.getTextResult() + "= 5");
    }

    @Test
    public void calculatorMultiplyTest(){
        Calculator resultSearch = new Calculator(2, 3,operation.MULTIPLY);
        int responseCode = resultSearch.getResponseCode();
        Assert.assertEquals(responseCode, 200, responseCode + "= 200");
        Assert.assertEquals(resultSearch.getTextResult(), "6", resultSearch.getTextResult() + "= 6");
    }

}
