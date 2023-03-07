package my.text.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用最少数量的箭引爆气球
 * 有一组气球，提供这组气球的横坐标开始，结束坐标（因为气球有宽度）。如果沿着纵坐标方向向上射爆这些气球，最多需要多少支箭。
 *
 * 示例 1：
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * https://programmercarl.com/0452.%E7%94%A8%E6%9C%80%E5%B0%91%E6%95%B0%E9%87%8F%E7%9A%84%E7%AE%AD%E5%BC%95%E7%88%86%E6%B0%94%E7%90%83.html
 * @Title: MinArrowShots
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/7 16:30
 * @Version 1.0
 */
public class MinArrowShots {

    public static void main(String[] args) {
        List ballbooms = new ArrayList(){{
            add(new BallBoom(10,16));
            add(new BallBoom(2,8));
            add(new BallBoom(1,6));
            add(new BallBoom(7,12));
        }};
        System.out.println(minArrowShots(ballbooms));
    }

    /**
     * 求出最少用的箭数
     * @param ballbooms
     * @return
     */
    private static int minArrowShots(List<BallBoom> ballbooms){
        if (null == ballbooms || ballbooms.isEmpty()){
            return 0;
        }

        //按开始坐标大小排序(前提条件：结束坐标肯定大于开始坐标)
        Collections.sort(ballbooms,(a,b) -> a.getStartIndex() - b.getStartIndex());

        int count = 1;
        // 前一支箭能射爆气球的结束坐标
        int preArrowEndIndex = ballbooms.get(0).getEndIndex();
        for (int i = 1; i < ballbooms.size(); i++) {
            // 当前的气球开始位置坐标 大于 前一支箭能射爆气球的结束坐标，则说明需要另起一支箭
            if(ballbooms.get(i).getStartIndex() > preArrowEndIndex){
                count ++;
                preArrowEndIndex = ballbooms.get(i).getEndIndex();
            }
        }
        return count;
    }

    /**
     * 气球类
     */
    static class BallBoom{
        /**
         * 横坐标开始位置
         */
        private int startIndex;

        /**
         * 横坐标结束位置
         */
        private int endIndex;

        public BallBoom(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }
    }
}
