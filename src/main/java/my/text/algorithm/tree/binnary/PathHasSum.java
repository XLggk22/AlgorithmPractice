package my.text.algorithm.tree.binnary;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径包含和为某个数的路径
 * @Title: PathHasSum
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/1/3 10:50
 * @Version 1.0
 */
public class PathHasSum {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        Node node5_1 = new Node(5);
        Node node6_1 = new Node(6);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        node4.setLeft(node6_1);
        node5.setLeft(node5_1);
        node5.setRight(node5_1);

        Node root = node1;

        List<String> list = byRecursice(13, root, new ArrayList<>(), new ArrayList<>());
        for (String s : list) {
            System.out.println(s);
        }

    }

    /**
     * 递归方式
     * @param sum 路径和
     * @param root  root节点
     * @param path  用于存放路径节点的集合
     * @param pathStr   用于存放路径结果的String集合，内容格式 1 -> 2 -> 4
     * @return
     */
    private static List<String> byRecursice(Integer sum,Node root, List<Integer> path, List<String> pathStr){
        if (null == root || null == sum){
            return pathStr;
        }

        path.add(root.getVal());
        sum -= root.getVal();

        if (null == root.getLeft() && null == root.getRight() && sum == 0){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i != path.size() - 1){
                    sb.append(" -> ");
                }
            }
            pathStr.add(sb.toString());
        }

        if (null != root.getLeft()){
            byRecursice(sum, root.getLeft(), path, pathStr);
            path.remove(path.size() - 1);
        }

        if (null != root.getRight()){
            byRecursice(sum, root.getRight(), path, pathStr);
            path.remove(path.size() - 1);
        }

        return pathStr;
    }
}
