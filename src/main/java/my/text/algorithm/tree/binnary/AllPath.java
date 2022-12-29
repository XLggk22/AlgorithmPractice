package my.text.algorithm.tree.binnary;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印出树的所有路径
 * @Title: AllPath
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/29 18:55
 * @Version 1.0
 */
public class AllPath {

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

        List<String> list = byRecursice(root, new ArrayList<>(), new ArrayList<>());
        for (String s : list) {
            System.out.println(s);
        }

    }

    public static List<String> byRecursice(Node curr, List<Integer> paths, List<String> res){
        if (null == curr){
            return res;
        }

        paths.add(curr.val);

        if (null == curr.getLeft() && null == curr.getRight()){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size(); i++) {
                sb.append(paths.get(i));
                //最后一次不拼接 ->
                if (i != paths.size()-1){
                    sb.append(" -> ");
                }
            }
            res.add(sb.toString());
            return res;
        }

        if (null != curr.getLeft()){
            byRecursice(curr.getLeft(), paths, res);
            //回溯
            paths.remove(paths.size() - 1);
        }

        if (null != curr.getRight()){
            byRecursice(curr.getRight(), paths, res);
            //回溯
            paths.remove(paths.size() - 1);
        }

        return res;
    }
}
