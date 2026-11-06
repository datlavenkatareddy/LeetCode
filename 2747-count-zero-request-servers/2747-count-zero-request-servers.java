import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        Integer[] indices = new Integer[m];

        for (int i = 0; i < m; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) ->
            Integer.compare(queries[a], queries[b])
        );

        int[] ans = new int[m];
        int[] count = new int[n + 1];

        int left = 0;
        int right = 0;
        int active = 0;

        for (int idx : indices) {
            int q = queries[idx];
            int start = q - x;

            // Add logs inside the right boundary
            while (right < logs.length && logs[right][1] <= q) {
                int id = logs[right][0];

                if (count[id] == 0) {
                    active++;
                }

                count[id]++;
                right++;
            }

            // Remove logs outside the left boundary
            while (left < right && logs[left][1] < start) {
                int id = logs[left][0];

                count[id]--;

                if (count[id] == 0) {
                    active--;
                }

                left++;
            }

            ans[idx] = n - active;
        }

        return ans;
    }
}