package nyc.c4q.AnthonyFermin;

import java.net.URL;
import java.util.ArrayList;
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

        Scanner htmlSearch = new Scanner(html);

        ArrayList<String> lines = new ArrayList<String>();

        // adds all HTML lines to a ArrayList<String> lines
        while(htmlSearch.hasNextLine()) {
            lines.add(htmlSearch.nextLine());
        }

        boolean scriptLine = false; // boolean determines whether currentLine is within a script tag or not. Default is false;

        // loop iterates through all lines of HTML
        for(int i = 0; i < lines.size(); i++){
            String currentLine = lines.get(i);

            // checks to see if current line of HTML contains the opening script tag
            if(currentLine.contains("<script") && !scriptLine){

                // gets index of first script open tag
                int scriptLoc = currentLine.indexOf("<script");

                // adds all text up to the open script tag to sanitizedHTML
                for(int j = 0; j < scriptLoc; j++){
                    sanitizedHTML += currentLine.charAt(j);
                }

                // gets text contained after open script tag
                String afterScript = currentLine.substring(scriptLoc);

                // if text after open tag also contains closing script tag, adds the text after script tag, if any
                // else, it sets scriptLine boolean to true, letting the program know that it's now search for a closing script tag as a priority
                if(afterScript.contains("</script>")){

                    int endScriptLoc = afterScript.indexOf("</script>");

                    if(!(endScriptLoc + 8 >= currentLine.length() - 1)){
                        for(int k = endScriptLoc + 1; k < currentLine.length(); k++){
                            sanitizedHTML += currentLine.charAt(k);
                        }
                    }
                }else{
                    scriptLine = true;
                }

             // adds entire currentLine if it is currently not searching for closing script tag(scriptLine = false)
             // and there is no open script tag in the line
            }else if ((!currentLine.contains("<script")) && !scriptLine){
                sanitizedHTML += currentLine;
                sanitizedHTML += "\n";

             // skips the currentLine if currently searching for a closing script tag and currentLine does not contain it
            }else if (scriptLine && !currentLine.contains("</script>")){
                continue;

             // adds text after closing script tag to sanitizedHTML String if currentLine has a closing tag
             // and program is currently searching for a closing script tag (scriptLine = true)
             //  - sets scriptLine to false once done
            }else if(scriptLine && currentLine.contains("</script>")){
                int endScriptLoc = currentLine.indexOf("</script>");

                if(!(endScriptLoc + 8 >= currentLine.length() - 1)){
                    for(int k = endScriptLoc + 8; k < currentLine.length(); k++){
                        sanitizedHTML += currentLine.charAt(k);
                    }
                }
                scriptLine = false;

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
