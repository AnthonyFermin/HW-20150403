package nyc.c4q.AnthonyFermin;

import java.net.URL;
import java.util.Scanner;

/** Anthony Fermin
 * HW due 4/3 2015
 * Exercise 2
 * Web Page Sanitizer
 * Created by c4q-anthonyf on 4/1/15.
 */
public class WebPageSanitizer {

    public static String sanitize(String html){


        String sanitizedHTML = "";
        String scriptOpenTag = "<script";
        String scriptCloseTag = "</script>";
        int scriptOpenLength = scriptOpenTag.length();
        int scriptCloseLength = "</script>".length();
        boolean betweenScriptTag = false;

        // loop iterates through each character in the html string
        for(int i = 0; i < html.length(); i++){

            System.out.println(true);

            /**
             * if is betweenScriptTag (default to false) then checks for a closing script tag at index i, if found then set betweenScriptTag to false and move i to end of closing script tag
             * else if its not betweenScriptTag, then check for an opening script tag at index i, if found then set betweenScriptTag to true
             * else add current character to sanitizedHTML string
             *
             * This code will only work with proper HTML format. ie: It wont work properly if there are script tags within script tags or if there
             * is an opening script tag and no closing script tag afterwards.
             */
            if(betweenScriptTag){

                if(html.charAt(i) == '<'){

                    if(html.substring(i, i+scriptCloseLength).equals(scriptCloseTag))
                    {
                        i = i + scriptCloseLength - 1;
                        betweenScriptTag = false;
                    }

                }

            }else if(html.charAt(i) == '<' && i + scriptOpenLength < html.length())
            {

                if(html.substring(i, i+scriptOpenLength).equals(scriptOpenTag))
                {
                    betweenScriptTag = true;
                }

            }else{

                sanitizedHTML += html.charAt(i);

            }

        }

        return sanitizedHTML;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a webpage to sanitize: ");

        String input = scanner.nextLine();
        URL url = HTTP.stringToURL(input);
        String document = HTTP.get(url); // http://docs.oracle.com/javase/7/docs/api/

        System.out.println("Original Version of Webpage : \n\n" + document);

        //divider
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------- \n");

        System.out.println("Sanitized Version of Webpage: \n\n");
        System.out.println(sanitize(document));
    }
}
