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

    // method calculates percentage of occurrence of all alphabet characters in a given text file
    public static ArrayList<String> calculate(File textFile) throws FileNotFoundException
    {

        ArrayList<String> percentages = new ArrayList<String>();
        Scanner textScanner = new Scanner(textFile);

        // using a TreeMap instead of a HashMap because a TreeMap sorts the Keys alphabetically.
        TreeMap<Character, Integer> charCount = new TreeMap<Character, Integer>();

        String text = "";

        // lowercases and copies all text in the .txt file int the String text variable
        while(textScanner.hasNext()){
            text += textScanner.nextLine().toLowerCase() + "\n";
        }

        // iterates through the string character by character and stores count in the TreeMap charCount
        for(int i = 0; i < text.length(); i++){

            char currentChar = text.charAt(i);
            if ((int)currentChar >= 97 && (int)currentChar <= 122){

                // if character is already in TreeMap charCount, add one to count
                if(charCount.containsKey(currentChar)){
                    int newCount = charCount.get(currentChar) + 1;
                    charCount.put(currentChar, newCount);

                 // else if character is not in TreeMap charCount, add new Key for this character with a value of 1
                }else{
                    charCount.put(currentChar, 1);
                }
            }

        }

        int sum = 0;

        // calculates total count of alphabet characters
        for(Character letter : charCount.keySet()){
            sum += charCount.get(letter);
        }
        // calculates percentage for each character and adds result to ArrayList<String> percentages. Percentage = (specificLetterCount * 100) /sum
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
