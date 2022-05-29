package regular_expression;

/**
 * @author WSL
 * @version 1.0.0
 * @ClassName ValidIPAddress.java
 * @Description TODO
 * @createTime 2022年05月29日 10:04:00
 */
/*
力扣：468. 验证IP地址
给定一个字符串queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。

        有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中0 <= xi<= 255且xi不能包含 前导零。例如:“192.168.1.1”、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。

        一个有效的IPv6地址是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:

        1 <= xi.length <= 4
        xi是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
        在xi中允许前导零。
        例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。



        示例 1：

        输入：queryIP = "172.16.254.1"
        输出："IPv4"
        解释：有效的 IPv4 地址，返回 "IPv4"
        示例 2：

        输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
        输出："IPv6"
        解释：有效的 IPv6 地址，返回 "IPv6"
        示例 3：

        输入：queryIP = "256.256.256.256"
        输出："Neither"
        解释：既不是 IPv4 地址，又不是 IPv6 地址


        提示：

        queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。*/

public class ValidIPAddress {
    public static void main(String[] args) {
        //System.out.println("0".matches("((2(5[0-5]|[0-4]\\d))|(1([0-9][0-9]))|[1-9][0-9]?|[0-9])"));
        System.out.println(new ValidIPAddress().validIPAddress("0.0.0.1"));
    }
   /**
    * @methodName validIPAddress
    * @Author WSL
    * @Description  使用正则表达式验证是否是合法的ipv4或者ipv6地址
    * @Date 10:09 2022/5/29
    * @Param queryIP
    * @return java.lang.String
    **/
    public String validIPAddress(String queryIP) {
        /*
        * 1、ipv4可以表示为(127) + (.127.127.127)前半段：((2(5[0-5]|[0-4]\d))|(1([0-9][0-9]))|[1-9][0-9]?|[0-9])
        * 后半段：(.((2(5[0-5]|[0-4]\d))|(1([0-9][0-9]))|[1-9][0-9]?|[0-9])){3}
        *
        * 2、解析前半段前：((2(5[0-5]|[0-4]\d))|(1([0-9][0-9]))|[1-9][0-9]?|[0-9])其中：(2(5[0-5]|[0-4]\d))表示的是200~255
        * (1([0-9][0-9]))表示的是100~199，[1-9][0-9]?表示的是1~99
        *
        * */
        String ipv4 = "((2(5[0-5]|[0-4]\\d))|(1([0-9][0-9]))|[1-9][0-9]?)(.((2(5[0-5]|[0-4]\\d))|(1([0-9][0-9]))|[1-9][0-9]?|[0-9])){3}";
        String ipvv6 = "([0-9a-fA-F]{1,4})(:[0-9a-fA-F]{1,4}){7}";
        if(queryIP == null || "".equals(queryIP.trim())){
            return "Neither";
        }
        if(queryIP.matches(ipv4)){
            return "IPv4";
        }
        if(queryIP.matches(ipvv6)){
            return "IPv6";
        }
        return "Neither";
    }
}
