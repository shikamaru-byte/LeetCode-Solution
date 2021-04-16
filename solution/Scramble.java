public class Scramble {

    public static void main(String[] args) {
        System.out.println(new Scramble().isScramble("great","rgeat"));
    }

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        boolean[][][] dp = new boolean[s1.length()][s1.length()][s1.length() + 1];
        for (int i = 0;i < s1.length();i ++) {
            for (int j = 0;j < s1.length();j ++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int len = 2;len <= s1.length();len++) {
            for (int i = 0;i <= s1.length() - len;i ++) {
                for (int j = 0;j <= s2.length() - len;j ++) {
                    for (int k = 1;k < len;k ++) {
                        boolean can1 = dp[i][j][k] && dp[i + k][j + k][len - k];
                        if (can1) {
                            dp[i][j][len] = true;
                            break;
                        }
                        boolean can2 = dp[i + len - k][j][k] && dp[i][j + k][len - k];
                        if (can2) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][s1.length()];
    }
}
