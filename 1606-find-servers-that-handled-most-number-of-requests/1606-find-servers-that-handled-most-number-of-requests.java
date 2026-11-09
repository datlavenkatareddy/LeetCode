import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;

        int[] count = new int[k];

        // Available servers in sorted order
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Busy servers: [finishTime, serverId]
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {

            // Release servers whose tasks have finished
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            // No available server: drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Find the preferred server or the next available one
            int preferred = i % k;

            Integer server = available.ceiling(preferred);

            // Wrap around if necessary
            if (server == null) {
                server = available.first();
            }

            // Assign request
            available.remove(server);
            count[server]++;

            busy.offer(new int[]{
                arrival[i] + load[i],
                server
            });
        }

        // Find maximum number of requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}