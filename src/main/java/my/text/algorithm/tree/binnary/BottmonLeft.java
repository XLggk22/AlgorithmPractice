package my.text.algorithm.tree.binnary;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 获取二叉树左下角值
 * @Title: BottmonLeft
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/2 16:22
 * @Version 1.0
 */
public class BottmonLeft {

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

        System.out.println(byLevelIterate(root));
    }

    /**
     * 层序遍历，下层最左的一个
     * @param root
     * @return
     */
    private static Integer byLevelIterate(Node root){
        if (null == root){
            return null;
        }
        Queue<Node> queue = new LinkedBlockingQueue<Node>(){{offer(root);}};
        // 上一层的最左值
        Integer preLevelLeft = null;
        while (!queue.isEmpty()){
            Integer currLevelLeft = null;
            for (int i = queue.size(); i > 0; i--) {
                Node poll = queue.poll();
                if (null == currLevelLeft){
                    currLevelLeft = poll.getVal();
                    preLevelLeft = currLevelLeft;
                }
                if (null != poll.getLeft()){
                    queue.offer(poll.getLeft());
                }
                if (null != poll.getRight()){
                    queue.offer(poll.getRight());
                }
            }
        }
        return  preLevelLeft;
    }

    /**
     * 递归方式
     * 思路：找到最大深度的第一个（先递归左子节点，右子节点只要不出现比他深的话，它就是最左叶子节点）
     * @param curr
     * @return
     */
    private static Integer byRecursive(Node curr){
        // todo
        return null;
    }
}
