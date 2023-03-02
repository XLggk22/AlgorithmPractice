package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * https://programmercarl.com/0491.%E9%80%92%E5%A2%9E%E5%AD%90%E5%BA%8F%E5%88%97.html#%E6%80%9D%E8%B7%AF
 * @Title: IncreaseSubSet
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/17 14:50
 * @Version 1.0
 */
public class IncreaseSubSet {

    /**
     * 存放子集结果
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 用于回溯处理子集
     */
    private static Deque<Integer> queue = new LinkedList<>();


    public static void main(String[] args) {

        int[] nums = new int[]{4, 6, 7, 7};

        backTrack(nums, 0);

        System.out.println(result);
    }

    private static void backTrack(int[] nums, int startIndex){
        // 当前层级使用过的元素
        List<Integer> currLevelUsedNums = new ArrayList<>();

        if (queue.size() > 1){
            result.add(new ArrayList<>(queue));
        }

        for (int i = startIndex; i < nums.length; i++) {
            int num = nums[i];
            if (!queue.isEmpty() && num < queue.getLast()){
                continue;
            }
            if (currLevelUsedNums.contains(num)){
                continue;
            }
            queue.add(num);
            currLevelUsedNums.add(num);
            backTrack(nums, i + 1);
            queue.removeLast();
        }
    }
}
