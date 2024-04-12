package my.text.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 目标数
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 *
 * https://programmercarl.com/0494.%E7%9B%AE%E6%A0%87%E5%92%8C.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * @Title: FindTargetSumWays
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/9/7 11:30
 * @Version 1.0
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};
//        int[] arr = new int[]{1, 2, 3, 4, 5};
        int target = 3;
        System.out.println("方法一组合数量：" + method01_dp(arr, target));

        // 方法二
        method02_backtrack(arr, target);

        // 方法三
//        method03_back_track(arr, 0, 0, target);
        System.out.println("组合数量: " + waysPath.size());
        System.out.println(waysPath);
    }

    /**
     * // todo
     * 方法一：动态规划
     * 看不太懂，见资料
     *   感觉这题和上楼梯的有点像
     */
    private static int method01_dp(int[] arr, int target) {
        // check whether array can be split
        int sum = 0;
        for (int i = 0; i <arr.length; i++) {
            sum += arr[i];
        }

        if (sum < Math.abs(target)){
            return 0;
        }

        if ((sum + target) % 2 != 0){
            return 0;
        }

        // define dp array
        int bagSize = (sum + target) / 2;
        int[] dp = new int[bagSize + 1];

        // init dp array, dp[1]=1, otherwise dp array will all zero value
        dp[1] = 1;

        // dp iterate
        for (int i = 0; i < arr.length; i++) {
            for (int j = bagSize; j >= arr[i]; j--) {
                dp[j] += dp[j-arr[i]];
            }
        }

        return dp[bagSize];
    }

    /**
     * 存放递归处理路径
     */
    private static LinkedList<Integer> path = new LinkedList<>();

    /**
     * 方法路径集合
     */
    private static LinkedList<List<Integer>> waysPath = new LinkedList<>();

    /**
     * 方法2: 回溯算法
     * 思路：
     *  1.公式推导
     *      既然为target，那么就一定有 left组合 - right组合 = target。
     *      left + right = sum，而sum是固定的。right = sum - left
     *      公式来了， left - (sum - left) = target 推导出 left = (target + sum)/2 。
     *
     *  2.target是固定的，sum是固定的，left就可以求出来。
     *    此时问题就是在集合nums中找出和为left的组合。
     */
    private static void method02_backtrack(int[] arr, int target){
        // 如果给定数组的总和 小于 target，则不存在这样的组合
        int sum = Arrays.stream(arr).sum();
        if (sum < target){
            System.out.println("组合不存在");
            return;
        }

        // 如果 (target + sum)%2不等于0，则也不存在这样的组合
        if ((target + sum) % 2 != 0){
            System.out.println("组合不存在");
            return;
        }

        // 计算出left组合目标和
        int left = (target + sum) / 2;

        System.out.println("left sum: " + left);

        method02_backtrack_body(arr, left, 0, 0);

        System.out.println("组合数量: " + waysPath.size());

        System.out.println(waysPath);

    }

    /**
     * 方法2回溯体
     * @param arr
     * @param targetSum 目标和
     * @param currSum   当前和
     */
    private static void method02_backtrack_body(int[] arr, int targetSum, int currIndex, int currSum){
        if (currSum == targetSum){
            waysPath.add(new LinkedList<Integer>(path));
        }

        for (int i = currIndex; i < arr.length; i++) {
            currSum += arr[i];
            path.addLast(arr[i]);

            method02_backtrack_body(arr, targetSum, i+1, currSum);

            currSum -= arr[i];
            path.removeLast();
        }
    }

    /**
     * 方法3：回溯算法
     * 思路：
     *   每个数字前面可以加 + 或 -号，那意味着每个数字2种方式，如果数字数量有n个，那总的组合数量为2^n个
     *   选择目标和为target的组合即可
     */
    private static void method03_back_track(int[] arr, int currIndex, int currentSum, int target){

        // 如果完成一组组合，则判断时候时候满足目标组合，并且结束此次回溯
        if (path.size() == arr.length){
            if (currentSum == target){
                waysPath.add(new ArrayList<>(path));
            }
            return;
        }

        // 回溯1：当前符号 + 的组合
        path.add(arr[currIndex]);
        method03_back_track(arr, currIndex + 1, currentSum + arr[currIndex], target);
        path.removeLast();

        // 回溯2：当前符号 - 的组合
        path.add(-arr[currIndex]);
        method03_back_track(arr, currIndex + 1, currentSum - arr[currIndex], target);
        path.removeLast();
    }
}
