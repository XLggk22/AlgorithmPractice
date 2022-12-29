package my.text.algorithm.tree.binnary;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 层序遍历
 * @Title: LevelTraverse
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/19 18:22
 * @Version 1.0
 */
public class LevelTraverse {

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

        System.out.println(byIterate(root));

    }

    /**
     * 循环遍历方式
     * @param root
     * @return
     */
    public static List<Integer> byIterate(Node root){
        if (null == root){
            return null;
        }
        List<Integer> resultList = new ArrayList<>();
        Queue<Node> queue = new LinkedBlockingDeque<Node>(){{offer(root);}};
        while (!queue.isEmpty()){
            for (int i = queue.size(); i > 0 ; i--) {
                Node poll = queue.poll();
                resultList.add(poll.getVal());
                if (null != poll.getLeft()){
                    queue.offer(poll.getLeft());
                }
                if (null != poll.getRight()){
                    queue.offer(poll.getRight());
                }
            }
        }
        return resultList;
    }
}
