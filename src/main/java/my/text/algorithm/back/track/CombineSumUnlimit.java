package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * @Title: CombineSum
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/1 15:01
 * @Version 1.0
 */
public class CombineSumUnlimit {

    /**
     * 结果集合
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 递归存放处理路径
     */
    private static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {

        backTrack(5, 2, 1, 0);

        System.out.println(result);
    }

    /**
     * 回溯获取组合
     * @param targetSum
     * @param k
     * @param currSum
     */
    private static void backTrack(int targetSum, int k ,int startIndex, int currSum){
        // 组合的sum已经大于了目标值，剪枝
        if (currSum > targetSum){
            return;
        }

        // 符合的组合加入到result集合
        if (targetSum == currSum && k == path.size()){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            currSum += i;
            backTrack(targetSum, k , i + 1, currSum);
            path.removeLast();
            currSum -= i;
        }

    }
}
