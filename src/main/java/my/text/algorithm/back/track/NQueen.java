package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后问题
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 不能相互攻击定义：
 *  不能同行
 *  不能同列
 *  不能同斜线
 *
 * 例：
 *  输入：n = 4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释：如上图所示，4 皇后问题存在两个不同的解法。
 * @Title: NQueen
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/28 15:55
 * @Version 1.0
 */
public class NQueen {

    /**
     * 存放子集结果
     */
    private static List<List<String>> result = new ArrayList<>();

    public static void main(String[] args) {
        int queenNums = 5;

        char[][] chessBoard = new char[queenNums][queenNums];

        for (char[] chars : chessBoard) {
            Arrays.fill(chars, '.');
        }

        backTrack(queenNums, 0, chessBoard);

        // 打印棋盘
        for (int i = 0; i < result.size(); i++) {
            System.out.println("-----------解法：" + (i+1) + "-----------");
            List<String> list = result.get(i);
            for (String chars : list) {
                for (int j = 0; j < chars.length(); j++) {
                    System.out.print(" " + chars.charAt(j) + " ");
                }
                // 换行
                System.out.println();
            }
        }
    }

    public static void backTrack(int queenNums, int row, char[][] chessBoard){

        // 如果已经到了最后一行，则终止
        if (queenNums == row){
            result.add(Array2List(chessBoard));
            return;
        }

        for (int col = 0;  col < queenNums; col++) {
            //校验放在这里是否可以
            if (isValid(row, col, queenNums, chessBoard)){
                chessBoard[row][col] = 'Q';
                backTrack(queenNums, row + 1, chessBoard);
                chessBoard[row][col] = '.';
            }
        }
    }

    private static List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    /**
     * 8皇后规则校验
     * @param col
     * @param row
     * @param chessBoard
     * @return
     */
    private static boolean isValid(int row, int col, int queenNums, char[][] chessBoard){
        // 不能在同一列
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q'){
                return false;
            }
        }

        // 不能在45度对角
        for (int i = row-1, j = col+1; i >= 0 && j < queenNums; i--, j++) {
            if (chessBoard[i][j] == 'Q'){
                return false;
            }
        }

        // 不能在135度对角
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }
}
