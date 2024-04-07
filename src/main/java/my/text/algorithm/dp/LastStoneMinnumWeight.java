package my.text.algorithm.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 石头碰撞问题，剩余最小石块，感觉不是动态规划问题。更像是贪心算法
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 *
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 *
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 * https://programmercarl.com/1049.%E6%9C%80%E5%90%8E%E4%B8%80%E5%9D%97%E7%9F%B3%E5%A4%B4%E7%9A%84%E9%87%8D%E9%87%8FII.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
 * @Title: LastStoneMinnumWeight
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/9/6 19:55
 * @Version 1.0
 */
public class LastStoneMinnumWeight {

    public static void main(String[] args) {
        int[] arr = new int[] {2,7,4,1,8,1};
//        System.out.println(method01_greedy(arr));
        System.out.println(method02_dp(arr));

    }

    /**
     * 方法二：动态规划
     * 思路：
     *  转化成背包模型，令石头的重量=石头的价值，这石头的重量和价值都为stones[i]
     *  假设石头总重量为weight，则需要求出容量为 weight/2的背包能装下石头的最大价值。
     *
     * @param stones stone array
     * @return  the min stone colliding result。
     */
    public static int method02_dp(int[] stones){
        // 1.确定数组含义 dp[j]为容量为 j的背包能放下十块的最大价值。
        // 2.确定递推公式: ap[j] = max(dp[j], dp[j-stone[i] + stone[i]]),其中 -stone[i]代表容量， 加stone[i]代表价值
        // 3.dp数组初始化: 使用的是一维数组不需要额外的初始化,因为遍历过程是从第0个石头开始遍历的；如果使用的二维数组则需要初始化，第0个石头分别放入不同背包容量的背包的价值。
        // 4.遍历顺序：如果是一维数组使用倒序遍历（不然前面的石头会被放两边，可以自行演示推导过程得出；）；如果是二维数组则使用顺序遍历。
        // 5.遍历推导过程：纸面上演示推导过程。

        int weightSum = 0;
        for (int i = 0; i < stones.length; i++) {
            weightSum += stones[i];
        }

        // 背包容量为 weightSum / 2
        int bagSize = weightSum / 2;

        int[] dp = new int[bagSize + 1];

        for (int i = 0; i < stones.length; i++) {
            for (int j = bagSize; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        return weightSum - dp[bagSize] - dp[bagSize];
    }

    /**
     * 方法一：贪心算法
     *  思路：
     *      令石头的总质量未sumWeight，需要一一碰撞之后产生的结果最小，那需要把石头分成两组。
     *      其中一组为：总质量不超过 sumWeight的最大质量组合。
     *      其他的为另外一组即可。
     *      结果为：最后求两个数组相减的绝对值得
     * @param arr 石头数组
     * @return  碰撞结果
     */
    public static int method01_greedy(int[] arr){
        // 石头总质量
        int sumWeight = 0;
        for (int i = 0; i < arr.length; i++) {
            sumWeight += arr[i];
        }

        // 求出重质量小于 sumWeight/2的最大重质量组合
        back_trace_body(arr, 0, 0, sumWeight/2);

        System.out.println("数组组合一：" + resultList);

        // 两个数组和的绝对值，其中一个数组的质量和为 sumWeight - maxSum；另外一个数组的质量和为maxSum;
        return sumWeight - maxSum - maxSum;
    }

    /**
     * 最大值
     */
    public static int maxSum = 0;

    /**
     * 用于存储回溯元素
     */
    public static LinkedList<Integer> path = new LinkedList<>();

    /**
     * 组合结果
     */
    public static List<Integer> resultList = new ArrayList<>();

    /**
     * 回溯方法体
     * @param arr   数组
     * @param currSum 当前和
     * @param currIndex 当前遍历下标
     * @param target    最大组合总和
     */
    public static void back_trace_body(int[] arr, int currSum, int currIndex, int target){
        for (int i = currIndex; i < arr.length; i++) {
            // 如果组合质量超过 target，则跳过
            if (currSum + arr[i] > target){
                continue;
            }else{
                currSum += arr[i];
                path.addLast(arr[i]);
                // 回溯
                back_trace_body(arr, currSum, i+1, target);

                if(currSum > maxSum){
                    maxSum = currSum;
                    resultList = new ArrayList<>(path);
                }

                currSum -= arr[i];
                path.removeLast();
            }
        }
    }

}
