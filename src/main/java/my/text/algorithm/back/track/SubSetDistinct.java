package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * https://programmercarl.com/0090.%E5%AD%90%E9%9B%86II.html#_90-%E5%AD%90%E9%9B%86ii
 * @Title: SubSetDistinct
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/3 17:39
 * @Version 1.0
 */
public class SubSetDistinct {
    /**
     * 存放子集结果
     */
    private static List<List<Integer>> result = new ArrayList<>();

     /**
     * 用于回溯处理子集
     */
     private static Deque<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        backTrack2(nums, 0);
        System.out.println(result);
    }

    /**
     * 方法一
     * 回溯处理方法
     * 思路：要使子集不重合，那当前层使用过的数字不能再使用，下一层（下一次递归）可以
     * @param nums  要处理的元素集合
     * @param startIndex    开始元素下标
     */
    private static void backTrack(int[] nums, int startIndex){
        //当前层使用过的数字
        List<Integer> currLevelUsedNums = new ArrayList<>();

        result.add(new ArrayList<>(queue));

        for (int i = startIndex; i < nums.length; i++) {
            int num = nums[i];
            //当前层使用过的数字包含当前元素值，则表名重复，跳过
            if (currLevelUsedNums.contains(num)){
                continue;
            }
            queue.add(num);
            currLevelUsedNums.add(num);
            backTrack(nums, i + 1);
            queue.removeLast();
        }
    }

    /**
     * 方法二
     * 思路: 同方法一，在判断时候被使用过的时候逻辑不一样；区别：nums必须排序过
     * @param nums  排序过的要处理的元素集合
     * @param startIndex    开始元素下标
     */
    private static void backTrack2(int[] nums, int startIndex){

        result.add(new ArrayList<>(queue));

        for (int i = startIndex; i < nums.length; i++) {
            // 判断当前层时候使用过改元素逻辑
            if (i > startIndex && nums[i] == nums[i - 1]){
                continue;
            }
            queue.add(nums[i]);
            backTrack2(nums, i + 1);
            queue.removeLast();
        }
    }
}
