package my.text.algorithm.tree.binnary;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: BlanceTree
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/20 20:01
 * @Version 1.0
 */
public class BlanceTree {

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
        node5.setLeft(node6);

//        node3.setLeft(node6);
//        node3.setRight(node7);

        Node root = node1;

        System.out.println(byIterate(root));

    }

    /**
     * 递归模式
     * 计算最大深度、最小深度 比较
     * @param root
     * @return
     */
    private static boolean byRecursive(Node root){
        if (null == null){
            return  false;
        }

        return false;
    }

    /**
     * 循环遍历法——层序遍历
     * 求出最大深度、最小深度，最后求差值
     * @param root
     * @return
     */
    private static boolean byIterate(Node root){
        if (null ==root){
            return false;
        }
        Queue<Node> queue = new LinkedBlockingQueue<Node>(){{offer(root);}};
        int maxDepth = 0;
        int minDepth = 0;
        //已经找出来了最小深度标记
        boolean minDepthElected = false;
        while (!queue.isEmpty()){
            maxDepth ++;
            //如果找出来了最小深度则不累加
            if (!minDepthElected){
                minDepth ++;
            }
            for (int i = queue.size(); i >0 ; i--) {
                Node poll = queue.poll();
                if (null != poll.getRight()){
                    queue.offer(poll.getRight());
                }
                if (null != poll.getLeft()){
                    queue.offer(poll.getLeft());
                }
                if (!minDepthElected && null == poll.getLeft() && null == poll.getRight()){
                    minDepthElected = true;
                }
            }
        }

        System.out.println("maxDepth: " + maxDepth);
        System.out.println("minDepth: " + minDepth);

        return maxDepth - minDepth > 1;
    }
}
