package soap;

import java.io.FileInputStream;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) {
        System.out.println(readFile("src/main/resources/postman.xml"));
    }

    public static String readFile(String path) {
        String text = "";
        try (FileInputStream fin = new FileInputStream(path)) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                text +=(char)i;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return text;
    }
}
