import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReformat {
    public static void main(String[] args) {
        String fileName = args[0];
        File inputFile = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader inr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(inr);

            String regex1 = "[\\d\\s]*NOT NAMED.*";


            String regex2 = "([\\d\\s]*)([a-zA-z\\s]*)([\\.{1}\\d+])\\s+([\\.{1}\\d+])([\\s\\d]*)";
            Pattern pattern = Pattern.compile(regex2);
            Matcher matcher = null;


            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {

                if (!Pattern.matches(regex1, line)) {
                    matcher = pattern.matcher(line);
                    System.out.println(line);
                    System.out.println(matcher.group(0));
                    /*System.out.println(matcher.group(3));
                    System.out.println(matcher.group(4));*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
