package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 资料：https://programmercarl.com/0077.%E7%BB%84%E5%90%88.html#%E5%9B%9E%E6%BA%AF%E6%B3%95%E4%B8%89%E9%83%A8%E6%9B%B2
 *
 * @Title: combine
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/1 11:16
 * @Version 1.0
 */
public class Combine {

    /**
     * 结果集合
     */
    private static List<List<Integer>> result = new ArrayList<>();

    /**
     * 递归存放处理路径
     */
    private static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        backTrack(4, 3, 1);
        System.out.println(result);
    }

    /**
     * 获取组合方法
     * @param n 最大数
     * @param k 个数
     * @param startIndex    开始数
     */
    private static void backTrack(int n, int k, int startIndex){
        //长度符合的组合，加入到结果集合
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }

        /**
         * 此部分使用到 剪枝算法：即剩余的的元素数量肯定不满足要求时候，那就跳过后面的
         * i < n - (k - path.size()) + 1
         * 为了方便理解，设定path.size() = 0 , 代入startIndex具体数据验证该条件是否正确
         */
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backTrack(n, k, i + 1);
            path.removeLast();
        }
    }
}
