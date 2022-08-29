import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveComment {

    public static void main(String[] args) throws IOException {
        RemoveComment removeComment= new RemoveComment();
        removeComment.createUncommentedSourceCode("src/BinarySearchTree.java",1);
    }

    public void createUncommentedSourceCode(String sourceCodePath,int number) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Path path = Path.of(sourceCodePath);//"src/code.txt");

        String content = Files.readString(path, StandardCharsets.US_ASCII);

        String str = content;//scanner.nextLine();
        String multiCommentRegex = "\\/\\*([\\S\\s]+?)\\*\\/", singleCommentRegex = "(/\\*((.|\n)*?)\\*/)|//.*";
        String outputFilePath = "src/clean_data"+number+".txt";
        String result1 = deleteComments(str,multiCommentRegex);
        String result2 = deleteComments(result1,singleCommentRegex);
        //System.out.println(result);

        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter( outputFilePath));
            writer.write( result2);

        }
        catch ( IOException e)
        {
        }
        finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }

        System.out.println(removeNewLine());
    }



    public static String deleteComments(String myString,String regex)
    {
        String newString = "";

        Pattern commentaryPattern = Pattern.compile(regex);
        //Pattern commentaryPattern = Pattern.compile("(/\\*((.|\n)*?)\\*/)|//.*");
        //Pattern commentaryPattern = Pattern.compile("\\/\\*([\\S\\s]+?)\\*\\/");

        Matcher m = commentaryPattern.matcher(myString);
        newString += m.replaceAll("");

        return newString;
    }

    public static String removeNewLine() throws FileNotFoundException
    {
        String str = "";
        FileInputStream fis=new FileInputStream("src/clean_data1.txt");
        Scanner sc=new Scanner(fis);
        while(sc.hasNextLine())
        {
            /*if(sc.nextLine().length()>0)
                str += sc.nextLine();*/
          //String string = String.valueOf(sc.nextLine());
           // String line = sc.nextLine();
            System.out.println(sc.nextLine().length());
        }
        sc.close();     //closes the scanner

        return str;
    }
}


