package nyc.c4q.AnthonyFermin;

/**
 * Anthony Fermin
 * HW due 4/3
 * Linear Search on ArrayList
 */

import java.util.ArrayList;

public class LinearSearch{

    public static int search(ArrayList<Integer> alist, int x){
        int numNotFound = -1;

        for(int i = 0; i < alist.size(); i++){
            int currentNum = alist.get(i);

            if(x == currentNum){
                return i;
            }

        }

        return numNotFound;
    }


    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>();

        // Eg. if arraylist contains: [3, 45, 1, 2, 99] and x = 1
        // Returns : 2

        nums.add(3);
        nums.add(45);
        nums.add(1);
        nums.add(2);
        nums.add(99);

        System.out.println(search(nums,1));
        System.out.println(search(nums,2));
        System.out.println(search(nums,99));
        System.out.println(search(nums,21));

    }
}
