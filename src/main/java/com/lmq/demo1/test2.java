package com.lmq.demo1;

class test2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        /**
         * 定义状态方程函数 0<=i<n,1<=j<=k
         * dp[i][2*(j-1)]:第i天第j次持有股票的现金
         * dp[i][2*j-1]:第i天第j次不持有股票的现金
         */
        int[][] dp = new int[n][2 * k];
        /**
         * 初始化
         * 第0天只要持有股票，都要花费prices[0],即-prices[0]
         */
        for (int j = 0; j < k; j++) {
            dp[0][2 * j] = -prices[0];
        }
        /**
         * 递推公式
         * 1 第i天第1次持有股票，因为是第1次持有股票，
         * 要么是第i天第1次购买：-prices[i]
         * 要么第i-1天就已经第1次持有，第i天无操作：dp[i-1][0]
         * 2 第i天第1次不持有股票，因为是第1次不持有股票，
         * 要么是第i-1天第1次不持有：dp[i-1][1]
         * 要么第i-1天就已经第1次持有，第i天卖掉了：dp[i-1][0]+price[i]
         * 3 第i天第j次持有股票（j>1)
         * 要么是第i-1天第j-1次不持有，第i天第j次购买：dp[i-1][2*j-3]-prices[i]
         * 要么是第i-1天第j次持有，第i天无操作：dp[i-1][2*(j-1)]
         * 4 第i天第j次不持有股票（j>1)
         * 要么是第i-1天第j次不持有，第i天无操作：dp[i-1][2*j-1]
         * 要么是第i-1天第j次持有，第i天卖掉了：dp[i-1][2*(j-1)]+prices[i]
         *
         * 总结：
         * 2和4情况类似
         * 1和3情况类似，但是1是第1次持有比较特殊，不会由第0次持有的状态推导而来
         * （相悖，如果上一次状态有持有，本次就不会是第1次持有了）
         */
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 第j次持有情况
                dp[i][2 * (j - 1)] = Math.max(j == 1 ? -prices[i] : dp[i - 1][2 * j - 3] - prices[i],
                        dp[i - 1][2 * (j - 1)]);
                // 第j次不持有情况
                dp[i][2 * j - 1] = Math.max(j == 1 ? dp[i - 1][1] : dp[i - 1][2 * j - 1],
                        dp[i - 1][2 * (j - 1)] + prices[i]);
            }
            // System.out.println("i="+i+",dp="+ print(dp[i]));
        }
        return dp[n-1][2 * k - 1];

    }
    private String print(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for(int n:nums) {
            sb.append(n+",");
        }
        return sb.toString();
    }
}