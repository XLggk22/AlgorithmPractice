package my.text.algorithm.back.track;

import java.util.*;

/**
 * 给定一个 有重复 数字的序列，返回其所有可能的全排列。
 * https://programmercarl.com/0046.%E5%85%A8%E6%8E%92%E5%88%97.html#%E6%80%9D%E8%B7%AF
 * 示例:
 * 输入：nums = [1,1,2]
 * 输出： [[1,1,2], [1,2,1], [2,1,1]]
 * @Title: AllArrangeMent
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/27 16:27
 * @Version 1.0
 */
public class AllArrangeMentContainRepeatEle {

    /**
     * 存放子集结果
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 用于回溯处理子集
     */
    private static Deque<Integer> path = new LinkedList<>();

    public static void main(String[] args) {

        int[] nums = new int[] {1,1,2};
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        //必须排序
        Arrays.sort(nums);
        backTrack(nums, used);
        System.out.println(result);
    }

    /**
     * 回溯方法
     * @param nums      数组
     */
    private static void backTrack(int[] nums, boolean[] used){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 和前一个元素相同，并且前一个used为false，说明在这一层该元素被使用过
            if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false){
                continue;
            }
            if (used[i] == false){
                used[i] = true;
                path.add(num);
                backTrack(nums, used);
                path.removeLast();
                used[i] = false;
            }
        }
    }
}
