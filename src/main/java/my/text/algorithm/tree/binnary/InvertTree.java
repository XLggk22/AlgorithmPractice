package my.text.algorithm.tree.binnary;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 反转二叉树
 * @Title: InvertTree
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/19 18:44
 * @Version 1.0
 */
public class InvertTree {

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

//        byRecursive(root);
        byIterate(root);
        System.out.println(LevelTraverse.byIterate(root));

    }

    /**
     * 深度优先(DFS)
     * 递归方式翻转二叉树
     * @param curr
     */
    private static void byRecursive(Node curr){
        if (null == curr){
            return;
        }

        //左右两边节点对调
        Node temp = curr.getLeft();
        curr.setLeft(curr.getRight());
        curr.setRight(temp);

        //左右两笔节点递归
        byRecursive(curr.getLeft());
        byRecursive(curr.getRight());
    }

    /**
     * 广度优先(BSF)
     * 层序遍历——循环迭代方式
     * @param root
     */
    private static void byIterate(Node root){
        Queue<Node> queue = new LinkedBlockingQueue<Node>(){{offer(root);}};
        while (!queue.isEmpty()){
            for (int i = queue.size(); i > 0; i--) {
                //左右两边节点对调
                Node poll = queue.poll();
                Node temp = poll.getLeft();
                poll.setLeft(poll.getRight());
                poll.setRight(temp);

                //继续迭代下一级
                if (poll.getLeft() != null){
                    queue.offer(poll.getLeft());
                }
                if (poll.getRight() != null){
                    queue.offer(poll.getRight());
                }
            }
        }
    }
}
