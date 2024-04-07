package my.text.algorithm.my.back.track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有一个整数数组，每一个数字都可以加上 +号 或-号，计算这些数字的所有组合集
 * @Title: AllCombinetion
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/3/28 10:40
 * @Version 1.0
 */
public class AllCombinetion {

    public static void main(String[] args) {
        // ervry one number can add + or - single ,calc the all conbine set of those numbers
        int[] arr = {1, 2, 3, 4, 5};

        generateCombinations(arr,0, 0, resultList);

        System.out.println("组合数量: " + resultList.size());
        System.out.println(resultList);

    }


    /**
     * the result result
     */
    public static List<Integer> resultList = new ArrayList<>();

    private static void generateCombinations(int[] arr, int index, int sum, List<Integer> result) {
        if (index == arr.length) {
            // Print the current combination
            System.out.println(result + " = " + sum);
            return;
        }

        // Include the current number with a positive sign
        result.add(arr[index]);
        generateCombinations(arr, index + 1, sum + arr[index], result);
        result.remove(result.size() - 1); // Backtrack

        // Include the current number with a negative sign
        result.add(-arr[index]);
        generateCombinations(arr, index + 1, sum - arr[index], result);
        result.remove(result.size() - 1); // Backtrack
    }


}
