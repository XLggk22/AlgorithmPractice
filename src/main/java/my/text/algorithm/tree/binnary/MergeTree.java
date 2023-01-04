package my.text.algorithm.tree.binnary;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 合并二叉树：
 * 如果同一个位置有重叠，则该节点的值为重叠的两节点值相加，否则值为非空的那个节点值。
 * @Title: MergeTree
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/4 17:19
 * @Version 1.0
 */
public class MergeTree {

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

        Node root1 = node1;

        Node root2 = node1;

//        System.out.println(LevelTraverse.byIterate(byRecursive(root1, root2)));
        System.out.println(LevelTraverse.byIterate(byIterate(root1, root2)));

    }

    /**
     * 递归方式
     * 以root1为基
     * @param root1 根节点1
     * @param root2 根节点2
     * @return
     */
    private static Node byRecursive(Node root1, Node root2){
        if (null == root1) {
            return root2;
        }
        if (null == root2){
            return root1;
        }

        root1.setVal(root1.getVal() + root2.getVal());
        root1.setLeft(byRecursive(root1.getLeft(), root2.getLeft()));
        root1.setRight(byRecursive(root1.getRight(), root2.getRight()));
        return root1;
    }

    /**
     * 迭代方式
     * 以root1为基
     * @param root1 根节点1
     * @param root2 根节点2
     * @return
     */
    private static Node byIterate(Node root1, Node root2){
        if (null == root1){
            return root2;
        }
        if (null == root2){
            return  root1;
        }

        //用于存放遍历的root1、root2的节点，入列、出列都一次操作两个
        Queue<Node> queue= new LinkedBlockingQueue<Node>(){{offer(root1);offer(root2);}};
        while (!queue.isEmpty()){
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            node1.setVal(node1.getVal() + node2.getVal());

            // 两棵树的左节点都不为空，同时入列
            if (null != node1.getLeft() && null != node2.getLeft()){
                queue.offer(node1.getLeft());
                queue.offer(node2.getLeft());
            }
            // 两棵树的右节点都不为空，同时入列
            if (null != node1.getRight() && null != node2.getRight()){
                queue.offer(node1.getRight());
                queue.offer(node2.getRight());
            }
            // node1左节点为空，则直接赋值node2的左节点
            if (null == node1.getLeft()){
                node1.setLeft(node2.getLeft());
            }
            // node1右节点为空，则直接赋值node2的右节点
            if (null == node1.getRight()){
                node1.setRight(node2.getRight());
            }
        }
        return root1;
    }
}
