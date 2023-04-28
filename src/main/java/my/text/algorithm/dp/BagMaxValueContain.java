package my.text.algorithm.dp;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 背包最大价值容量
 * 有n件物品和一个最多能背重量为w 的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
 * https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html#_01-%E8%83%8C%E5%8C%85
 * https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html
 * @Title: BagMaxValueContain
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/4/27 15:44
 * @Version 1.0
 */
public class BagMaxValueContain {

    public static void main(String[] args) {
//        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        printArray(arr);
        int[] weights = {1,3,4};
        int[] values  = {15, 20, 30};
        int bagCapacity = 4;
//        System.out.println(method01_dp(weights, values, bagCapacity));
//        System.out.println(method01_dp_roll(weights, values, bagCapacity));
        System.out.println(method02_backtrack(weights, values, bagCapacity, 0, 0, 0));
        System.out.println(maxValuePath);
    }

    /**
     * 方法一：动态规划
     * 思路：
     *  令dp[i][j]为第i个物品放入容量为j的背包，总体能够放下最大总价值
     *  每个物品无非就是装与不装，第i个装与不装的情况如下：
     *      1.不能装:该物品重量超过背包容量
     *          则此时的背包最大价值还是i-1时候的价值，即dp[i-1][j]
     *      2.能装: 该物品重量没有超过背包容量
     *          则此时需要选择装与不装，取下面两种情况最大价值：
     *          1）选择装：
     *              因为i物品占了重量，装上它则的价值为：下面两部分相加：
     *                  ①i物品本身的价值
     *                  ②减去它剩余空间，i-1物品在容量j-weights[i]情况下已经算出的能装的最大价值（这个之前就已经算出来的，已经是0~i-1d的最大价值）
     *          2）选择不装：
     *              和1（不能装）一样，此时的背包最大价值还是i-1时候的价值，即dp[i-1][j]
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * @param weights
     * @param values
     * @param bagCapacity
     * @return
     */
    private static int method01_dp(int[] weights, int[] values, int bagCapacity){
        // 初始化而为数组，bagCapacity + 1是因为数组下标是从零开始
        int[][] dp = new int[weights.length][bagCapacity + 1];

        // 初始化第1个物品对应背包容量下的最大价值
        for (int j = weights[0]; j <= bagCapacity; j++) {
            dp[0][j] = values[0];
        }

        for (int i = 1; i < weights.length; i++) {
            for (int j = 1; j <= bagCapacity; j++) {
                // 该物品的重量比背包容量大，不能装，背包最大总价值不变
                if (j < weights[i]){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], values[i] + dp[i-1][j-weights[i]]);
                }
            }
        }

        // 打印二位数组
        printArray(dp);

        // 返回结果
        return dp[weights.length-1][bagCapacity];

    }

    /**
     * 打印二位数组
     * @param resultArray
     */
    private static void printArray(int[][] resultArray){
        System.out.print(String.format("物品 \t"));
        for (int i = 0; i < resultArray[0].length; i++) {
            System.out.print(String.format("容量%s\t", i));
        }
        // 行头换行
        System.out.println();
        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(String.format("物品%s\t", i));
            for (int j = 0; j < resultArray[i].length; j++) {
                System.out.print(String.format("%s\t\t", resultArray[i][j]));
            }
            // 物品换行
            System.out.println();
        }
    }

    /**
     * 递归存放处理路径
     */
    private static LinkedList<Integer> path = new LinkedList<>();

    /**
     * 递归存放处理路径
     */
    private static LinkedList<Integer> maxValuePath = new LinkedList<>();

    /**
     * 最大价值
     */
    private static int maxValue = 0;

    /**
     * 方法二：回溯算法
     * 思路：这道题其实是一个组合问题，因为每个物品都有两种选择：装或不装，只要不超过背包容量即可，因此可以使用回溯算法
     * 时间复杂度: O(2^n)
     * 空间复杂度: O(1)
     * @param weights
     * @param values
     * @param bagCapacity
     * @return
     */
    private static int method02_backtrack(int[] weights, int[] values, int bagCapacity, int index, int currSum, int currWeight){
        for (int i = index; i < weights.length; i++) {
            if (weights[i] > bagCapacity || weights[i] + currWeight > bagCapacity){
                continue;
            }
            path.add(weights[i]);
            currWeight += weights[i];
            currSum += values[i];
            if (currSum > maxValue){
                maxValue = currSum;
                maxValuePath = new LinkedList<>(path);
            }
            method02_backtrack(weights, values, bagCapacity, i+1, currSum, currWeight);
            currWeight -= weights[i];
            currSum -= values[i];
            path.removeLast();
        }
        return maxValue;
    }


    /**
     * 方法三：动态规划—滚动数组
     * 思路：在方法一中，使用二位数组过程可以看到，后面的物品可能要使用到前面物品算出的在容量为j-weights[i]情况下最大值，即：
     *    在使用二维数组情况下的推导公式：d[i][j] = max(dp[i-1][j], dp[i-1][j-wight[i]] + value[i])
     *    其实可以发现如果把dp[i - 1]那一层拷贝到dp[i]上
     *      表达式完全可以是：dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i]);
     *    与其把dp[i - 1]这一层拷贝到dp[i]上，不如只用一个一维数组了，只用dp[j]（一维数组，也可以理解是一个滚动数组，在迭代物品时候，算出的指定背包容量的最大价值在原数组上覆盖即可）。
     * @param weight
     * @param value
     * @param bagWeight
     */
    public static int method01_dp_roll(int[] weight, int[] value, int bagWeight){
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            // 从后往前遍历，因为 j < weight[i] 之前的值没有意义，因为物品重量大于背包容量，那肯定背不下这个物品
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight[i]] +value[i]);
            }

            //打印dp数组
            for (int j = 0; j <= bagWeight; j++){
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
        return dp[bagWeight];
    }
}
