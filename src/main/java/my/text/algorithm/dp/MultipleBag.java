package my.text.algorithm.dp;

import java.util.Arrays;
import java.util.logging.SocketHandler;

/**
 * 多重背包
 * 有N种物品和一个容量为V 的背包。第i种物品最多有Mi件可用，每件耗费的空间是Ci ，价值是Wi 。
 * 求解将哪些物品装入背包可使这些物品的耗费的空间 总和不超过背包容量，且价值总和最大。
 *
 * 思路：每件物品最多有Mi件可用，把Mi件摊开，其实就是一个01背包问题了。
 * https://programmercarl.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80%E5%A4%9A%E9%87%8D%E8%83%8C%E5%8C%85.html#%E5%A4%9A%E9%87%8D%E8%83%8C%E5%8C%85
 * @Title: MultipleBag
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/16 14:41
 * @Version 1.0
 */
public class MultipleBag {

    public static void main(String[] args) {
        int[] weights = new int[]{1, 3, 4};
        int[] values = new int[]{15, 20, 30};
        int[] nums = new int[]{2, 3, 2};
        int bagSize = 10;
        System.out.println("背包容量最大荷载价值：" + method01_dp(weights, values, nums, bagSize));
    }

    /**
     * 方法一：动态规划
     * 思路：相对于01背包，多重背包多了个物品数量，把物品摊开来其实就是个01背包。这里就不摊开了。
     * @param weights   物品重量数组
     * @param values    物品价值数组
     * @param nums      物品数量数组
     * @param bagSize   背包容量
     * @return  背包容量最大装载价值
     */
    public static int method01_dp(int[] weights, int[] values, int[] nums, int bagSize){

        // define dp array and init(no need)
        int[] dp = new int[bagSize + 1];

        // outer iterate goods
        for (int i = 0; i < weights.length; i++) {
            // inner iterate bag(reverse order)
            for (int j = bagSize; j >= weights[i]; j--) {
                for (int k = 1; k <= nums[i] && (j - k * weights[i]) >= 0; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println("dp [" +i+ "] = " + dp[i]);
        }

        return dp[bagSize];
    }
}
