import java.util.*;

class MedianCost {
    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    int n1 = 0, n2 = 0;
    long s1 = 0, s2 = 0;

    void addTo(TreeMap<Integer, Integer> map, int x) {
        map.merge(x, 1, Integer::sum);
    }

    void removeFrom(TreeMap<Integer, Integer> map, int x) {
        int count = map.get(x);

        if (count == 1) {
            map.remove(x);
        } else {
            map.put(x, count - 1);
        }
    }

    void adjust() {
        while (n1 - n2 > 1) {
            int x = left.lastKey();

            removeFrom(left, x);
            n1--;
            s1 -= x;

            addTo(right, x);
            n2++;
            s2 += x;
        }

        while (n2 > n1) {
            int x = right.firstKey();

            removeFrom(right, x);
            n2--;
            s2 -= x;

            addTo(left, x);
            n1++;
            s1 += x;
        }
    }

    void add(int x) {
        addTo(right, x);
        n2++;
        s2 += x;

        int y = right.firstKey();

        removeFrom(right, y);
        n2--;
        s2 -= y;

        addTo(left, y);
        n1++;
        s1 += y;

        adjust();
    }

    void remove(int x) {
        if (left.containsKey(x)) {
            removeFrom(left, x);
            n1--;
            s1 -= x;
        } else {
            removeFrom(right, x);
            n2--;
            s2 -= x;
        }

        adjust();
    }

    long query() {
        long mid = left.lastKey();

        return (long) n1 * mid - s1
             + s2 - (long) n2 * mid;
    }
}

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        MedianCost mc = new MedianCost();

        for (int i = 1; i <= n; i++) {
            mc.add(nums[i - 1]);

            long cost = mc.query();

            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j > 0 && i >= x) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost
                    );
                }
            }

            if (i >= x) {
                mc.remove(nums[i - x]);
            }
        }

        return dp[n][k];
    }
}