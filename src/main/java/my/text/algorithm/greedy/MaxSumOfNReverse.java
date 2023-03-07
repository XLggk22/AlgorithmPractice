package my.text.algorithm.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 数组元素n次取反后的最大值
 *
 * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
 * 以这种方式修改数组后，返回数组可能的最大和。
 *
 * 示例 1：
 *      输入：A = [4,2,3], K = 1
 *      输出：5
 *      解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
 *
 * 示例 3：
 *
 *      输入：A = [2,-3,-1,5,-4], K = 2
 *      输出：13
 *      解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
 * https://programmercarl.com/1005.K%E6%AC%A1%E5%8F%96%E5%8F%8D%E5%90%8E%E6%9C%80%E5%A4%A7%E5%8C%96%E7%9A%84%E6%95%B0%E7%BB%84%E5%92%8C.html#%E6%80%9D%E8%B7%AF
 * @Title: MaxSumOfNReverse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/6 17:12
 * @Version 1.0
 */
public class MaxSumOfNReverse {

    public static void main(String[] args) {
        int[] nums = new int[]{2,-3,-1,5,-4};
        System.out.println(maxSumOfNReverse(nums, 2));
    }

    /**
     * 思路：
     *  1.先从小到大排序
     *  2.遍历数组
     *      如果下标小于k,则加绝对值；
     *      如果大于k,则加实际数值
     * @param nums
     * @param k
     * @return
     */
    private static int maxSumOfNReverse(int[] nums, int k){
        Arrays.sort(nums);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count < k){
                sum += Math.abs(nums[i]);
                count ++;
            }else{
                sum += nums[i];
            }
        }
        return sum;
    }
}

