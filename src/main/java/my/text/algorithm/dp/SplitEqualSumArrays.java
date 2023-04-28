package my.text.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断是否可以将一组数字分割成两组等和数组
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *  注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200
 *      示例 1:
 *      输入: [1, 5, 11, 5]
 *      输出: true
 *      解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * @Title: SplitEqualSumArrays
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/4/28 14:26
 * @Version 1.0
 */
public class SplitEqualSumArrays {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 11, 5};

        System.out.println("是否可以拆分: " + method01_dp(arr));

    }

    /**
     * 方法一：动态规划
     * 思路：
     *  1.数组大小大于1
     *  2.数组数字总和必须为偶数，否则怎么分割也不可能相等
     *  3.背包理论
     *      假设数组总和为sum，那么背包容量为sum/2，此题可以转变为：
     *          1.所有数字的价值等于其重量
     *          2.在背包容量为sum/2的情况下，所有物品（数字）的最大组合的值（这个值肯定不能超过背包容量）
     *
     * 背包理论，
     * @param arr
     * @return 是否可以分割成功
     */
    private static boolean method01_dp(int[] arr){
        // 数组大小小于2,直接返回
        if (arr.length < 2){
            return false;
        }

        // 数组所有数字总和
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        // 总数非偶数，不能拆分
        if (sum % 2 != 0){
            return false;
        }

        // 计算出平均数，即背包容量
        int avg = sum / 2;
        // 初始化数组
        int[] dp = new int[avg+1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = avg; j >= arr[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-arr[i]] + arr[i]);
            }
        }

        return dp[avg] == avg;
    }


}
