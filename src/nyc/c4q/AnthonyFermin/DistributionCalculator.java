package nyc.c4q.AnthonyFermin;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/** Anthony Fermin
 *  Homework due 4/3
 *  Distribution Calculator
 *  Calculates percentage that each word appears in a text file
 */
public class DistributionCalculator
{

    public static ArrayList<String> calculate(File textFile) throws FileNotFoundException
    {
        ArrayList<String> percentages = new ArrayList<String>();
        Scanner textScanner = new Scanner(textFile);
        TreeMap<Character, Integer> charCount = new TreeMap<Character, Integer>();

        String text = "";

        while(textScanner.hasNext()){
            text += textScanner.nextLine().toLowerCase() + "\n";

        }

        for(int i = 0; i < text.length(); i++){

            char currentChar = text.charAt(i);
            if ((int)currentChar >= 97 && (int)currentChar <= 122){

                if(charCount.containsKey(currentChar)){
                    int newCount = charCount.get(currentChar) + 1;
                    charCount.put(currentChar, newCount);
                }else{
                    charCount.put(currentChar, 1);
                }
            }

        }
        // percentage = (count*100)/total
        int sum = 0;

        for(Character letter : charCount.keySet()){
            sum += charCount.get(letter);
        }
        for(Character letter : charCount.keySet()){
            double letterPercentage = ((double)charCount.get(letter) * 100) / sum;
            percentages.add(letter + " == " + letterPercentage + " %");
        }

        return percentages;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File text = new File("/Users/c4q-anthonyf/Desktop/accesscode/HW-20150403/src/nyc/c4q/AnthonyFermin/mobyDick.txt");
        ArrayList<String> percentages = calculate(text);
        for(String charPercentages : percentages){
            System.out.println(charPercentages);
        }
    }


}
