package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * https://programmercarl.com/0046.%E5%85%A8%E6%8E%92%E5%88%97.html#%E6%80%9D%E8%B7%AF
 * 示例:
 * 输入: [1,2,3]
 * 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 * @Title: AllArrangeMent
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/27 16:27
 * @Version 1.0
 */
public class AllArrangeMent {

    /**
     * 存放子集结果
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 用于回溯处理子集
     */
    private static Deque<Integer> path = new LinkedList<>();

    public static void main(String[] args) {

        int[] nums = new int[] {1,2,3};
        backTrack(nums);
        System.out.println(result);
    }

    /**
     * 回溯方法
     * @param nums      数组
     */
    private static void backTrack(int[] nums){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (path.contains(num)){
                continue;
            }
            path.add(num);
            backTrack(nums);
            path.removeLast();
        }
    }
}
