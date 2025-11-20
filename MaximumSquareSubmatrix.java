public class MaximumSquareSubmatrix {

    public static int size(int[][] a) {
        int n = a.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            dp[i][0] = a[i][0];
            if (dp[i][0] > max) max = dp[i][0];
            dp[0][i] = a[0][i];
            if (dp[0][i] > max) max = dp[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][j] == 1) {
                    int m1 = dp[i - 1][j];
                    int m2 = dp[i][j - 1];
                    int m3 = dp[i - 1][j - 1];
                    int min = m1 < m2 ? m1 : m2;
                    if (m3 < min) min = m3;
                    dp[i][j] = 1 + min;
                    if (dp[i][j] > max) max = dp[i][j];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readInt();
            }
        }
        System.out.println(size(a));
    }
}