package my.text.algorithm.greedy;

import com.sun.javafx.binding.StringFormatter;

/**
 *
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *      每个孩子至少分配到 1 个糖果。
 *      相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *      那么这样下来，老师至少需要准备多少颗糖果呢？
 *  https://programmercarl.com/0135.%E5%88%86%E5%8F%91%E7%B3%96%E6%9E%9C.html#%E6%80%9D%E8%B7%AF
 *
 *  思路：
 *      这道题目一定是要确定一边之后，再确定另一边，例如比较每一个孩子的左边，然后再比较右边，如果两边一起考虑一定会顾此失彼。
 * @Title: DispatchCandy
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/3/7 14:33
 * @Version 1.0
 */
public class DispatchCandy {

    public static void main(String[] args) {

//        int[] ratings = new int[]{1,0,2};
//        int[] ratings = new int[]{1,2,2};
        int[] ratings = new int[]{1,2,2,5,4,3,2};

        System.out.println("最少需要糖果数: " + solution(ratings));
    }

    /**
     * 思路：两遍遍历
     * 1. 第一遍：从左到右，如果右边的大于左边的则糖果数目+1
     * 2. 第二遍：从右到做，如果左边的大于右边的则糖果数目+1
     * @param ratings
     */
    private static int solution(int[] ratings){
        // 空判断
        if (ratings.length == 0){
            return 0;
        }

        int[] candys = new int[ratings.length];
        candys[0] = 1;
        // iteration 1
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]){
                candys[i] = candys[i-1] + 1;
            }else{
                // 相同分数或者更低分数情况下只给最低数 1个。
                candys[i] = 1;
            }
        }

        // iteration 2
        for (int i = ratings.length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]){
                candys[i] = candys[i+1] + 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < candys.length; i++) {
            System.out.println(String.format("第%s个孩子分到的糖果数: %s", i, candys[i]));
            sum += candys[i];
        }

        return sum;
    }
}
