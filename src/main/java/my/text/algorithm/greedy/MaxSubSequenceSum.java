package my.text.algorithm.greedy;

/**
 * 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *      输入: [-2,1,-3,4,-1,2,1,-5,4]
 *      输出: 6
 *      解释: 连续子数组 [4,-1,2,1] 的和最大，为 6
 * https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C.html#%E6%9A%B4%E5%8A%9B%E8%A7%A3%E6%B3%95
 * @Title: MaxSubSequenceSum
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/6 15:24
 * @Version 1.0
 */
public class MaxSubSequenceSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubSequenceSum(nums));
    }

    /**
     * 思路：
     *  1.使用一个变量记录最大值
     *  2.从头开始累加，累加到为负数的时候丢弃前面所有的元素从头开始计算。
     * @param nums
     */
    private static int maxSubSequenceSum(int[] nums){
        if (nums.length == 0){
            return  0;
        }
        int maxSum = nums[0];
        int currSum = 0;
        for (int i = 0; i <nums.length; i++) {
            currSum += nums[i];
            maxSum = Math.max(currSum, maxSum);
            if (currSum < 0){
                currSum = 0;
            }
        }
        return maxSum;
    }
}
