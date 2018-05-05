import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /*public static void main(String[] args) throws FileNotFoundException {
        String fileString = "";
        Scanner scanner = new Scanner(new File("/home/dmitrid/dev/archieve/JavaLibraries/MyUtils/src/main/resources/textFileForWordsSearch"));

        String regex = "(круто|класс|Круто|Класс)";
        Pattern pattern = Pattern.compile(regex);


        while (scanner.hasNext()) {
            fileString += scanner.nextLine();
        }

        StringTokenizer token = new StringTokenizer(fileString, ".");
        while (token.hasMoreTokens()) {
            String str = token.nextToken();
            str.trim();
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) System.out.println(str);
        }
    }*/

    //Как найти целое слово в строке в java
    //with ApacheCommonsLang3
    public static void main(String[] args) throws FileNotFoundException {
        String text = "I will come and meet you at the woods 123woods and all the woods";

        List<String> tokens = new ArrayList<String>();
        tokens.add("123woods");
        tokens.add("woods");

        String patternString = "\\b(" + org.apache.commons.lang3.StringUtils.join(tokens, "|") + ")\\b";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
