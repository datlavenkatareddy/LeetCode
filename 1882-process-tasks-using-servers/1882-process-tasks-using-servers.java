import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Available servers: [weight, index]
        PriorityQueue<int[]> idle = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: [finishTime, weight, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);
                if (a[1] != b[1])
                    return Long.compare(a[1], b[1]);
                return Long.compare(a[2], b[2]);
            }
        );

        for (int i = 0; i < n; i++) {
            idle.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            // If all servers are busy, advance time.
            if (idle.isEmpty()) {
                time = Math.max(time, busy.peek()[0]);
            }

            // Release every server available at this time.
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                idle.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // Select the best available server.
            int[] server = idle.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            busy.offer(new long[]{
                time + tasks[i],
                weight,
                index
            });
        }

        return ans;
    }
}