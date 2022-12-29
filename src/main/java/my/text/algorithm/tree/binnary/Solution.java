package my.text.algorithm.tree.binnary;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Solution
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/29 19:28
 * @Version 1.0
 */
//解法一
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
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
        List<String> list = solution.binaryTreePaths(root);
        for (String s : list) {
            System.out.println(s);
        }
    }
    /**
     * 递归法
     */
    public List<String> binaryTreePaths(Node root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, res);
        return res;
    }

    private void traversal(Node root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        // 叶子结点
        if (root.getLeft() == null && root.getRight() == null) {
            // 输出
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.getLeft() != null) {
            traversal(root.getLeft(), paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
        if (root.getRight() != null) {
            traversal(root.getRight(), paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
    }
}
