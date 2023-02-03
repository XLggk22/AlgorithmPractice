package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例: 输入: nums = [1,2,3] 输出: [ [3],   [1],   [2],   [1,2,3],   [1,3],   [2,3],   [1,2],   [] ]
 * @Title: SubSet
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/3 13:47
 * @Version 1.0
 */
public class SubSet {

    /**
     * 存放子集结果
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 用于回溯处理子集
     */
    private static Deque<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        backTrack(nums,0);
        System.out.println(result);
    }

    /**
     * 回溯方法
     * @param nums  给定的父集合
     * @param startIndex    取下一个元素的开始下标
     */
    private static void backTrack(int[] nums, int startIndex){
        result.add(new ArrayList<>(queue));

        if (startIndex >= nums.length){
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            queue.add(nums[i]);
            backTrack(nums, i + 1);
            queue.removeLast();
        }
    }
}
