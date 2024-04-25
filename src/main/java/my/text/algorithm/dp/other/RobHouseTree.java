package my.text.algorithm.dp.other;

import my.text.algorithm.tree.binnary.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 打家劫舍 — 树形房屋
 * 同打家劫舍1{@link my.text.algorithm.dp.other.RobHouse}，只是房屋是一个树形状，不能偷树中相连的两户
 *
 * https://programmercarl.com/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 *
 * 示例 1：
 *  输入：nums = [2,3,2]
 *  输出：3
 *
 * 示例 2：
 *  输入：nums = [1,2,3,1]
 *  输出：4
 * @Title: RobHouse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2024/4/16 15:56
 * @Version 1.0
 */
public class RobHouseTree {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        Node root = node1;

        System.out.println("最大金额：" + method02_levelTraversal(root));
    }



    /**
     * 方法一：二叉树层序遍历
     * 思路：不能偷相连的两个，其实就是跳层的偷, 奇数层和偶数层分别计算出综合，再取大的即最大金额
     * @param root 根房屋
     * @return  最大金额
     */
    public static int method02_levelTraversal(Node root){
        if (null == root){
            return 0;
        }

        // 遍历队列
        Queue<Node> queue = new LinkedBlockingQueue<Node>(){{offer(root);}};
        // 存放各层的的和
        List<Integer> levelSumList = new ArrayList<>();

        while (!queue.isEmpty()){
            // 统计当前层的sum
            int currLevelSum = 0;
            for (int i = queue.size(); i > 0 ; i--) {
                Node poll = queue.poll();
                currLevelSum += poll.getVal();
                if (null != poll.getLeft()){
                    queue.offer(poll.getLeft());
                }
                if (null != poll.getRight()){
                    queue.offer(poll.getRight());
                }
            }
            levelSumList.add(currLevelSum);
        }

        // 求偶数层的和
        int sum1 = 0;
        for (int i = 0; i < levelSumList.size(); i += 2) {
            sum1 += levelSumList.get(i);
        }

        // 求偶数层的和
        int sum2 = 0;
        for (int i = 1; i < levelSumList.size(); i += 2) {
            sum2 += levelSumList.get(i);
        }

        return Math.max(sum1, sum2);
    }

    /**
     * 方法二：递归方式
     * 思路：
     *  其实和方法一层序遍历是一样的，分别递归获取奇数层和偶数层的和，然后做比较。
     *   奇数层元素：root.left、root.left
     *   偶数层元素：root.left.left、root.left.right、root.right.left、root.right.right
     * @param root
     * @return
     */
    public static int method02_recursion(Node root){
        return 0;
    }

    /**
     * 方法一：动态规划
     * // todo 不太好理解，具体见教程
     * @param root 根房屋
     * @return  最大金额
     */
    public static int method03_dp(Node root){
        return 0;
    }

}
