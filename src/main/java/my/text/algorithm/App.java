package my.text.algorithm;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    List<Integer> alist, blist;

    public static void main(String[] args) {
//        int[] nums = new int[]{1};
//        int search = missingNumber(nums);
//        System.out.println(search);
//        int[][] matrix = new int[2][3];
//        System.out.println(matrix.length);

        List<Character> list = new ArrayList<>();
        list.add('a');
        System.out.println(list);
        System.out.println(list.contains('a'));
        list.remove(new Character('a'));
        System.out.println(Integer.valueOf( 'a'));
        System.out.println(Character.toChars(97 + 1));
        System.out.println('1'==' ');
    }

    public App() {
        Integer[] aa = new Integer[10];
        alist = new ArrayList<Integer>();
        blist = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
    }

    public static int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        //区间为：左闭右闭

        while(left <= right) {
            int m = left + (right - left) / 2;

            if(nums[m] == m)
                left = m + 1;
            else
                right = m - 1;
        }
        return left;
    }

}

