package trie_prefixtree;
/*实现一个 MapSum 类，支持两个方法，insert 和 sum：

        MapSum() 初始化 MapSum 对象
        void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
        int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
         
        输入：
        ["MapSum", "insert", "sum", "insert", "sum"]
        [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
        输出：
        [null, null, 3, null, 5]

        解释：
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.sum("ap");           // return 3 (apple = 3)
        mapSum.insert("app", 2);
        mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)

        提示:
        1 <= key.length, prefix.length <= 50
        key 和 prefix 仅由小写英文字母组成
        1 <= val <= 1000
        最多调用 50 次 insert 和 sum

        思路：用map存储key-value,可以用map存储prefix所对应的value总和，或者前缀树
        */

import java.util.HashMap;
import java.util.Map;

class MapSum {
    class TireNode{
        int val = 0;
        TireNode[] childNodes = new TireNode[26];
    }
    TireNode root;
    Map<String, Integer> map;
    public MapSum() {
        root = new TireNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        TireNode tireNode = root;
        int temp = val - map.getOrDefault(key, 0);
        map.put(key, val);
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if(tireNode.childNodes[index] == null){
                tireNode.childNodes[index] = new TireNode();
            }
            tireNode.childNodes[index].val += temp;
            tireNode = tireNode.childNodes[index];
        }
    }

    public int sum(String prefix) {
        TireNode tireNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if(tireNode.childNodes[index] == null){
                return 0;
            }
            tireNode = tireNode.childNodes[index];
        }
        return tireNode.val;
    }
}
