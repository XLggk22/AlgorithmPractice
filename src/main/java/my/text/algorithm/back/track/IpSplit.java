package my.text.algorithm.back.track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 此题比原作者题解简单
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * https://programmercarl.com/0093.%E5%A4%8D%E5%8E%9FIP%E5%9C%B0%E5%9D%80.html#%E5%9B%9E%E6%BA%AF%E4%B8%89%E9%83%A8%E6%9B%B2
 * @Title: IpSplit
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2023/2/2 17:17
 * @Version 1.0
 */
public class IpSplit {

    // 全局结果集合
    private static List<String> result = new ArrayList<>();

    // 回溯处理队列
    private static List<String> segmentList = new ArrayList<>();

    public static void main(String[] args) {
        // 结果应该是 ["255.255.11.135","255.255.111.35"]
        String str = "25525511135";
        backTrack(str, 0);
        System.out.println(result);
    }

    private static void backTrack(String str, int startIndex){
        if (segmentList.size() == 4){
            if (startIndex < str.length()){
                // 说明已经截取了4段ip后还有剩余字符串，终止
                return;
            }else{
                result.add(segmentList.stream().collect(Collectors.joining(".")));
            }
        }

        // 剪枝，因为每段最多只能3位，最低1位，所以剩余字符的长度必须：
        // 1.大于等于 段余额（4 - segment.size()）
        // 2.小于等于 3倍段余额
        int resLen = str.length() - startIndex;
        if (resLen < (4 - segmentList.size()) || resLen > 3 * (4 - segmentList.size())){
            return;
        }

        for (int i = startIndex; i < str.length(); i++) {
            String substring = str.substring(startIndex, i + 1);
            if (validIpSegmentFormat(substring)){
                segmentList.add(substring);
            }else{
                // 剪枝，当前段不符合的直接跳过改分支
                continue;
            }
            backTrack(str,i + 1);
            segmentList.remove(segmentList.size() - 1);
        }
    }

    /**
     * 验证ip段是否有效
     * ip段取值[0,255]
     * @param segment   当前段
     * @return
     */
    private static boolean validIpSegmentFormat(String segment){
        if (null == segment || segment.length() == 0){
            return false;
        }
        if (segment.length() > 4){
            return false;
        }
        if (Integer.valueOf(segment) > 255){
            return false;
        }
        if (segment.startsWith("0")){
            return false;
        }
        return true;
    }
}
