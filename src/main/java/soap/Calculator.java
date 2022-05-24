package soap;

public class Calculator extends Client {
    String textResult;
    operation action;

    public Calculator(int numberA, int numberB, operation action){
        super("http://www.dneonline.com/calculator.asmx",
                FileRead.readFile(chooseOperation(action))
                        .replace("\"integerA\"", Integer.toString(numberA))
                        .replace("\"integerB\"", Integer.toString(numberB)),
                "text/xml; charset=utf-8"
                );
        this.action = action;
    }

    public String getTextResult() {
        String a = "</MultiplyResponse>";
        String regExpression;
        if (action == operation.ADD) {
            regExpression = "(<AddResult>)([^<]*)";
        }else {
            regExpression = "(<MultiplyResult>)([^<]*)";
        }
        textResult = new RegexXml(regExpression, super.getXmlBody(), 2).result;
        return textResult;
    }

    private static String chooseOperation(operation action) {
        if (action == operation.ADD) {
            return "src/main/resources/calculatorAdd.xml";
        } else {
            return "src/main/resources/calculatorMult.xml";
        }
    }
}
