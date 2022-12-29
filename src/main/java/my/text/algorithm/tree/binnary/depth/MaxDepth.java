package my.text.algorithm.tree.binnary.depth;

import my.text.algorithm.tree.binnary.LevelTraverse;
import my.text.algorithm.tree.binnary.Node;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: MaxDepth
 * @Description: 求二叉树的最大深度（根节点到 最下层 任意一个节点的高度（节点数））
 * @Author deepexi-raobinghua
 * @Date 2022/12/20 11:39
 * @Version 1.0
 */
public class MaxDepth {

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
     * @param curr  当前节点
     * @param parentDepth   父深度
     * @return
     */
    private static int byRecursive(Node curr, int parentDepth){
        if (null == curr){
            return parentDepth;
        }

        Node currLeft = curr.getLeft();
        Node currRight = curr.getRight();
        //左右都为空，直接返回父深度
        if (null == currLeft && null == currRight){
            return parentDepth;
        }

        parentDepth ++;

        //求左边最大深度
        int leftDepth = byRecursive(currLeft, parentDepth);

        //求右边最大深度
        int rightDepth = byRecursive(currRight, parentDepth);

        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 循环遍历方式-层序遍历
     * 时间复杂度和递归方式相同，因为都要遍历所有节点
     * @param root
     * @return
     */
    private static int byIterate(Node root){
        if (null == root){
            return 0;
        }
        Queue<Node> queue = new LinkedBlockingQueue<Node>(){{offer(root);}};
        int i = 0;
        while (!queue.isEmpty()){
            i++;
            //出列掉本层所有的节点
            for (int j = queue.size(); j > 0; j--) {
                Node poll = queue.poll();
                if (null != poll.getLeft()){
                    queue.offer(poll.getLeft());
                }
                if (null != poll.getRight()){
                    queue.offer(poll.getRight());
                }
            }
        }

        return i;
    }
}
