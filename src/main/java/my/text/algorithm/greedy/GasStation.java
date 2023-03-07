package my.text.algorithm.greedy;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 示例 1: 输入:
 *      gas = [1,2,3,4,5]
 *      cost = [3,4,5,1,2]
 *
 *      输出: 3
 * @Title: GasStation
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/6 19:15
 * @Version 1.0
 */
public class GasStation {

    public static void main(String[] args) {
        int[] gas = new int[]{1,2,3,4,5};

        int[] cost = new int[]{3,4,5,1,2};

        System.out.println(solution02(gas, cost));
    }

    /**
     * 解法一
     * 思路：
     *  1.如果汽油累加数小于消耗累加数，则不管怎么样都无法绕一圈
     *  2.如果从index 0 开始累加，最小值一直都没有小于0，则说明可以从index 0 出发
     *  3.如果从index 0 开始累加，最小值小于0,则从后开始累加，直到补齐0
     *  时间复杂度 O(N)
     *  空间复杂度O(1)
     * @param gas
     * @param cost
     * @return
     */
    private static int solution01(int[] gas, int[] cost){
        int sum = 0;
        int min = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            min = Math.min(sum, min);
        }

        if (sum < 0){
            return -1;
        }

        if (min > 0){
            return 0;
        }

        for (int i = gas.length - 1; i >= 0; i--) {
            min += gas[i] - cost[i];
            if (min >= 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 解法2
     * 思路：
     *  1.如果汽油累加数小于消耗累加数，则不管怎么样都无法绕一圈
     *  2.累加供给-消耗，如果到某个下标累加值<0, 则说明断了，得重新计算出发点
     *  时间复杂度 O(N)
     *  空间复杂度 O(1)
     * @param gas
     * @param cost
     * @return
     */
    private static int solution02(int[] gas, int[] cost){
        int currSum = 0;
        int totalSum = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            totalSum += gas[i] - cost[i];
            currSum += gas[i] - cost[i];
            if (currSum < 0){
                index = i + 1;
                currSum = 0;
            }
        }
        if (totalSum < 0){
            return -1;
        }
        return index;
    }
}
