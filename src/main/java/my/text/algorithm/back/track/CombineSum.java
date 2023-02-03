package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 1.candidates 中的数字可以无限制重复被选取
 * 2.candidates 中的数字都是不未0的正整数 —— 即不需要考虑0死循环情况
 * 3.结果不能包含的重复的组合 —— [1,2,3]、[2,1,3]、[3,1,3] 表示的是同一个组合
 * https://programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html#%E5%9B%9E%E6%BA%AF%E4%B8%89%E9%83%A8%E6%9B%B2
 * @Title: CombineSum
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/1 15:01
 * @Version 1.0
 */
public class CombineSum {

    /**
     * 结果集合
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 递归存放处理路径
     */
    private static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {

        int[] candidates = {2,3,5};

        int targetSum = 8;

        backTrack(candidates,targetSum, 0, 0);

        System.out.println(result);
    }

    /**
     * 回溯获取组合
     * @param candidates 给定数组
     * @param targetSum  目标和
     * @param startIndex 开始元素下标
     * @param currSum    当前和
     */
    private static void backTrack(int[] candidates, int targetSum, int startIndex, int currSum){
        if (currSum > targetSum){
            return;
        }

        if (currSum == targetSum){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            int currEle = candidates[i];
            currSum += currEle;
            path.add(currEle);
            backTrack(candidates, targetSum, i, currSum);
            currSum -= currEle;
            path.removeLast();
        }
    }
}
