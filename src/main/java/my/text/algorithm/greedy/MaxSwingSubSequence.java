package my.text.algorithm.greedy;

/**
 *
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 例如，
 *  [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 *  相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * https://programmercarl.com/0376.%E6%91%86%E5%8A%A8%E5%BA%8F%E5%88%97.html#%E6%80%9D%E8%B7%AF1-%E8%B4%AA%E5%BF%83%E8%A7%A3%E6%B3%95
 *
 * 示例 1:
 *      输入: [1,7,4,9,2,5]
 *      输出: 6
 *      解释: 整个序列均为摆动序列。
 * @Title: MaxSwingSubSequence
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/6 14:57
 * @Version 1.0
 */
public class MaxSwingSubSequence {

    public static void main(String[] args) {
        int[] nums = new int[]{1,7,4,9,2,5};
        int[] nums2 = new int[]{1,7,4,9,9,9,2,5,6};

        System.out.println(maxSwingLength(nums));
        System.out.println(maxSwingLength(nums2));
    }

    private static int maxSwingLength(int[] nums){

        if (nums.length <= 1){
            return nums.length;
        }

        int preDiff = 0;
        int currDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            currDiff = nums[i] - nums[i-1];
            if ((preDiff <= 0 && currDiff > 0) || (preDiff >= 0 && currDiff < 0)){
                count ++;
                preDiff = currDiff;
            }
        }
        return count;
    }

}
