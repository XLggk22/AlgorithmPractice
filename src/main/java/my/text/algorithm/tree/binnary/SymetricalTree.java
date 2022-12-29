package my.text.algorithm.tree.binnary;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 判断二叉树是否左右对称（即树左右两边内存节点相同，外侧节点也相同）
 * 思路：
 *  左侧遍历方式：左右中
 *  右边遍历方式：右左中
 *  两边遍历出来的结果应该相同
 * @Title: SymetricalTree
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/20 10:24
 * @Version 1.0
 */
public class SymetricalTree {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2l = new Node(2);
        Node node2r = new Node(2);
        Node node3l = new Node(3);
        Node node3r = new Node(3);
        Node node4l = new Node(4);
        Node node4r = new Node(4);
        Node node5l = new Node(5);
        Node node5r = new Node(5);
        Node node6l = new Node(6);
        Node node6r = new Node(6);

        node1.setLeft(node2l);
        node1.setRight(node2r);

        // root左树
        node2l.setLeft(node3l);
        node2l.setRight(node4l);
        node3l.setLeft(node5l);
        node3l.setRight(node6l);

        // root右树
        node2r.setLeft(node4r);
        node2r.setRight(node3r);
        node3r.setLeft(node6r);
        node3r.setRight(node5r);
//        node3r.setLeft(node5r);
//        node3r.setRight(node6r);


        Node root = node1;

        System.out.println(byRecursive(root.getLeft(), root.getRight()));
        System.out.println(byIterate(root.getLeft(), root.getRight()));

    }

    /**
     * 递归方式
     * @param left  left of root
     * @param right right of root
     */
    private static boolean byRecursive(Node left, Node right){
        // 左右两边都为null—对称
        if (null == left && null == right){
            return true;
        }
        // 任意有一边为空或值不同—不对称
        if (null == left || null == right || !left.getVal().equals(right.getVal())){
            return false;
        }

        //对比外侧
        boolean outside = byRecursive(left.getLeft(), right.getRight());

        //对比内测
        boolean inside = byRecursive(left.getRight(), right.getLeft());

        return outside && inside;
    }


    /**
     * 迭代方式 + 双队列/双端队列
     * @param left  left of root
     * @param right right of root
     */
    private static boolean byIterate(Node left, Node right){
        Queue<Node> lQueue = new LinkedBlockingQueue<Node>(){{offer(left);}};
        Queue<Node> rQueue = new LinkedBlockingQueue<Node>(){{offer(right);}};
        while (!lQueue.isEmpty() || !rQueue.isEmpty()){
            Node lNode = lQueue.poll();
            Node rNode = rQueue.poll();

            // 左边入队顺序：左-右
            if (null != lNode.getLeft()){
                lQueue.offer(lNode.getLeft());
            }
            if (null != lNode.getRight()){
                lQueue.offer(lNode.getRight());
            }

            // 右边入队顺序：右-左
            if (null != rNode.getRight()){
                rQueue.offer(rNode.getRight());
            }
            if (null != rNode.getLeft()){
                rQueue.offer(rNode.getLeft());
            }

            // 左右两边都为null—对称
            if (null == lNode && null == rNode){
                continue ;
            }

            // 任意有一边为空或值不同—不对称
            if (null == lNode || null == rNode || !lNode.getVal().equals(rNode.getVal())){
                return false;
            }
        }
        return true;
    }
}
