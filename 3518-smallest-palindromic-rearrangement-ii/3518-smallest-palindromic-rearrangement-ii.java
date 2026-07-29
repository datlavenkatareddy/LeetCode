class Solution {
    private static final int INF = 1000001;
    private static final int N = 24;
    private static int[][] C = new int[N][N];
    private static boolean pascalComputed = false;

    private static void pascal() {
        if (pascalComputed) return;
        pascalComputed = true;
        C[0][0] = 1;
        for (int i = 1; i < N; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j <= i / 2; j++) {
                C[i][j] = C[i][i - j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
    }

    private static int comb(int n, int k) {
        if (n < N) return C[n][k];
        if (2 * k > n) k = n - k;
        long ans = 1;
        for (int i = 1; i <= k; i++) {
            ans = ans * (n - i + 1) / i;
            if (ans >= INF) return INF;
        }
        return (int) ans;
    }

    private static int perm(int[] freq, int sz) {
        long ans = 1;
        for (int f : freq) {
            if (f == 0) continue;
            ans *= comb(sz, f);
            if (ans >= INF) return INF;
            sz -= f;
        }
        return (int) ans;
    }

    public String smallestPalindrome(String s, int k) {
        pascal();
        int n = s.length();
        int n0 = n / 2;
        int[] freq = new int[26];
        
        for (int i = 0; i < n0; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int total = perm(freq, n0);
        if (k > total) return "";

        StringBuilder left = new StringBuilder();
        int sz = n0;

        for (int i = 0; i < n0; i++) {
            for (int c = 0; c < 26; c++) {
                if (freq[c] == 0) continue;
                freq[c]--;
                int cnt = perm(freq, --sz);
                if (cnt >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= cnt;
                    freq[c]++;
                    sz++;
                }
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();
        if (n % 2 == 1) {
            left.append(s.charAt(n0));
        }
        left.append(right);

        return left.toString();
    }
}