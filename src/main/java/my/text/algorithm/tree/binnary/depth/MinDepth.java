package my.text.algorithm.tree.binnary.depth;

import my.text.algorithm.tree.binnary.Node;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: minDepth
 * @Description: 求二叉树的最小深度（根节点到 叶子节点 的的最小高度（节点数））
 * @Author deepexi-raobinghua
 * @Date 2022/12/20 11:39
 * @Version 1.0
 */
public class MinDepth {

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

        System.out.println(byRecursive(root, 1));
        System.out.println(byIterate(root));

    }

    /**
     * 递归方式
     * 深度优先，时间复杂度O(n)
     * @param curr  当前节点
     * @param parentDepth   父节点深度
     * @return
     */
    private static int byRecursive(Node curr, int parentDepth){
        if (null == curr){
            return parentDepth;
        }
        Node currLeft = curr.getLeft();
        Node currRight = curr.getRight();
        if (null == currLeft && null == currRight){
            return parentDepth;
        }

        parentDepth ++;

        int leftDepth = byRecursive(currLeft, parentDepth);
        int rightDepth = byRecursive(currRight, parentDepth);

        // 一边为空，需要查找另一边
        if (null == currLeft){
            return rightDepth;
        }
        if (null == currRight){
            return leftDepth;
        }

        return  Math.min(leftDepth, rightDepth);
    }

    /**
     * 循环遍历—层序遍历方式
     * 广度优先
     * 时间复杂度 <= O(n)，优于递归方式，从上往下，只要遇到有子节点为空的节点，就返回结果
     * @param root
     * @return
     */
    private static int byIterate(Node root){
        Queue<Node> queue = new LinkedBlockingQueue<Node>(){{offer(root);}};
        int cnt = 0;
        while (!queue.isEmpty()){
            cnt ++;
            for (int i = queue.size(); i > 0; i--) {
                Node poll = queue.poll();
                // 左右子节点都为空，直接返回
                if (null == poll.getLeft() && null == poll.getRight()){
                    return cnt;
                }
                if (null != poll.getLeft()){
                    queue.offer(poll.getLeft());
                }
                if (null != poll.getRight()){
                    queue.offer(poll.getRight());
                }
            }
        }

        return cnt;
    }
}
