package string;
//"you"与"oyu"相等
public class Equals {
    public static void main(String[] args) {
        String s1 = "you";
        String s2 = "oyu";
        System.out.println(isOk(s1, s2));
    }

    public static boolean isOk(String s1, String s2) {
        int[] arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i)-'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            arr[s2.charAt(i)-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
