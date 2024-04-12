package my.text.algorithm.dp;

/**
 * 完全背包：
 *  有N件物品和一个最多能背重量为W的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。
 *  每件物品都有无限个（也就是可以放入背包多次），求解将哪些物品装入背包里物品价值总和最大。
 * 示例：
 *  物品      重量      价值
 *  物品0     1         15
 *  物品1     3         20
 *  物品2     4         30
 *
 *  背包容量：4
 *  结果为：45
 *
 *https://programmercarl.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80%E5%AE%8C%E5%85%A8%E8%83%8C%E5%8C%85.html#%E6%80%9D%E8%B7%AF
 * @Title: CompleteBag
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/10 15:54
 * @Version 1.0
 */
public class CompleteBag {

    public static void main(String[] args) {

        int[] weights = new int[]{1, 2, 3};
        int[] values = new int[]{15, 20, 30};
        int bagSize = 3;
        System.out.println("max value payload: " + method01_dp(weights, values, bagSize));
    }

    /**
     * 方法1：动态规划
     * 思路：
     *  多重背包，区别与01背包的是，每个物品都可以放任意多次。
     *  在实现上，区别与01背包的是，遍历背包的时候采用正序遍历（因为这样才会有累加效应，可以自行遍历推演）。
     *  多重背包可以理解为：
     *      一个背包拆分成2个，那么要使得总体价值最大，则需要求出前一个物品放进背包里时候的最大值，然后再根据当前物品的价值和重量，选择装或者不装。
     *      然而背包遍历循序的累加效应再第一个物品就能实现初始的最大，后面的物品自然就能是实现最大了。
     *      递推公式：
     *          dp[j] = Math.max(dp[j], dp[i-weights[i]] + values[i])
     * @param weights   goods weight array
     * @param values    goods value array
     * @param bagSize   bag size
     * @return  max value payload of bag
     */
    public static int method01_dp(int[] weights, int[] values, int bagSize){
        //1. means of array: dp[j] present max value payload of j capacity bag
        //2. array dp recursion formula: dp[j] = Math.max(dp[j], dp[j-weights[i]] + values[i]);
        //3. array dp init: no need
        //4. recursion order
        //5. iterate goods and deduce
        int[] dp = new int[bagSize+1];

        for (int i = 0; i < weights.length; i++) {
            for (int j = weights[i]; j <= bagSize; j++) {
                dp[j] = Math.max(dp[j], dp[j-weights[i]] + values[i]);
            }
        }

        return dp[bagSize];
    }
}
