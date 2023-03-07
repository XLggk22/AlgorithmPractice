package my.text.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Title: 合并区间
 * @Description:给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 *      输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *      输出: [[1,6],[8,10],[15,18]]
 *      解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * https://programmercarl.com/0056.%E5%90%88%E5%B9%B6%E5%8C%BA%E9%97%B4.html
 * @Author deepexi-raobinghua
 * @Date 2023/3/7 19:07
 * @Version 1.0
 */
public class MergeRange {
    public static void main(String[] args) {
        List rangeNodes = new ArrayList(){{
            add(new RangeNode(1,3));
            add(new RangeNode(2,6));
            add(new RangeNode(8,10));
            add(new RangeNode(15,18));
        }};
        System.out.println(mergeRange(rangeNodes));
    }


    /**
     * 思路：
     *  1.先按照开始位置从小到大排序
     *  2.迭代比较，合并节点逻辑
     * @param rangeNodes
     * @return
     */
    private static List<RangeNode> mergeRange(List<RangeNode> rangeNodes){

        if (Objects.isNull(rangeNodes) || rangeNodes.size() < 2){
            return rangeNodes;
        }

        // 按照开始位置从小到大排序
        Collections.sort(rangeNodes, (a,b) -> a.getStart() - b.getStart());

        List<RangeNode> mergedNodes = new ArrayList<>();
        int start = rangeNodes.get(0).getStart();
        int end = rangeNodes.get(0).getEnd();
        for (int i = 1; i < rangeNodes.size(); i++) {
            RangeNode rangeNode = rangeNodes.get(i);
            // 如果当前node的start <= 上一个的end, 则说明可以合并，因此把end设置为目前最大的end值。
            // 如果当前node的start >  上一个的end, 则说明不可以合并，需要另起一个节点
            if (rangeNode.getStart() <= end){
                end = Math.max(end, rangeNode.getEnd());
            }else{
                mergedNodes.add(new RangeNode(start, end));
                start = rangeNode.getStart();
                end = rangeNode.getEnd();
            }
        }
        // 把最后一个加进去
        mergedNodes.add(new RangeNode(start, end));
        return mergedNodes;
    }

    /**
     * RangeNode
     */
    static class RangeNode{
        /**
         * 开始位置
         */
        private int start;

        /**
         * 结束位置
         */
        private int end;

        public RangeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "RangeNode{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
