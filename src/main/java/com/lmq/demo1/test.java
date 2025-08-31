package com.lmq.demo1;

// 常规动态规划
public class test {
    /**
     * 计算字符串s中有多少个子序列等于字符串t（动态规划解法）
     * @param str 源字符串s
     * @param target 目标字符串t
     * @return 不同的子序列数量
     */
    public static int numDistinct(String str, String target) {
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int n = s.length;
        int m = t.length;

        // dp[i][j] 表示s的前i个字符中有多少个子序列等于t的前j个字符
        int[][] dp = new int[n + 1][m + 1];

        // 初始化：空字符串是任何字符串的子序列（出现1次）
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // 动态规划填表
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 默认情况：不使用s[i-1]字符，继承前一个结果
                dp[i][j] = dp[i - 1][j];

                // 当字符匹配时，增加使用s[i-1]字符的情况
                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
