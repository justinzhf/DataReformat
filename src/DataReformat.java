import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataReformat {
    public static void reformatASTS() {
        String[] fileNames = {"1950.data", "1960.data", "1970.data", "1980.data", "1990.data", "2000.data", "2010.data"};
        String parentDir = "E:\\study\\ATST";
        FileWriter fw = null;
        try {
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = parentDir + File.separator + fileNames[i];
                File inputFile = new File(fileName);
                FileInputStream fis = new FileInputStream(inputFile);
                InputStreamReader inr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(inr);

                File outFile = new File("output.data");
                if (outFile.exists()) {
                    outFile.delete();
                }
                fw = new FileWriter(outFile, true);
                String regex1 = "[\\d\\s]*NOT NAMED.*";

                String regex2 = "([\\d]*)([\\s]*)([\\d\\s]*)([a-zA-z]*)([\\s]*)([\\.\\d]*)([\\s]*)(\\-[\\.\\d]*).*";
                Pattern pattern = Pattern.compile(regex2);
                Matcher matcher = null;

                String line = null;

                while ((line = br.readLine()) != null) {

                    if (!Pattern.matches(regex1, line)) {
                        matcher = pattern.matcher(line);
                        if (matcher.matches()) {

                            fw.write(matcher.group(1) + "," + matcher.group(4) + "," + matcher.group(6) + "," + matcher.group(8) + "\r\n");
                        }

                    }
                }
            }
            fw.flush();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reformatTRACK() {
        String parentDir = "E:\\study\\TRACKS\\";
        String fileName = parentDir + "spline25Aug.txt";
        FileWriter fw = null;
        try {


            File inputFile = new File(fileName);
            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader inr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(inr);

            File outFile = new File("trk_output.data");
            if (outFile.exists()) {
                outFile.delete();
            }
            fw = new FileWriter(outFile, true);

            String regex = "(R[\\d]*)(=)\\[" +
                    "\\[([\\s\\d\\.\\-]*)\\]\\;" +
                    "\\[([\\s\\d\\.\\-]*)\\]\\;" +
                    "\\[([\\s\\d\\.\\-]*)\\]\\;" +
                    "\\[([\\s\\d\\.\\-]*)\\]\\;" +
                    "\\[([\\s\\d\\.\\-]*)\\]\\;" +
                    "\\[([\\s\\d\\.\\-]*)\\]\\]\\;";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = null;
            ;

            String line = null;
            while ((line = br.readLine()) != null) {

                matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    fw.write(matcher.group(1) + " " + matcher.group(3)  + "\r\n");
                    fw.write(matcher.group(1) + " " + matcher.group(4)  + "\r\n");
                    fw.write(matcher.group(1) + " " + matcher.group(5)  + "\r\n");
                    fw.write(matcher.group(1) + " " + matcher.group(6)  + "\r\n");
                    fw.write(matcher.group(1) + " " + matcher.group(7)  + "\r\n");
                    fw.write(matcher.group(1) + " " + matcher.group(8)  + "\r\n");
                }


            }

            fw.flush();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reformatTRACK();

    }
}
