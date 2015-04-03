package nyc.c4q.AnthonyFermin;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
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

            //
            if(currentLine.contains("<script") && !scriptLine){
                int scriptLoc = currentLine.indexOf("<script");
                for(int j = 0; j < scriptLoc; j++){
                    sanitizedHTML += currentLine.charAt(j);
                }
                String afterScript = currentLine.substring(scriptLoc);
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

            }else if ((!currentLine.contains("<script")) && !scriptLine){
                sanitizedHTML += currentLine;
                sanitizedHTML += "\n";
            }else if (scriptLine && !currentLine.contains("</script>")){
                continue;
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
