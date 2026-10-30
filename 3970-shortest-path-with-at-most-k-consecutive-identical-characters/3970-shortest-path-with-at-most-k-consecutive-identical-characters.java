import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        int[][] mavorqeli = edges;

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : mavorqeli) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long d = cur[0];
            int u = (int) cur[1];
            int count = (int) cur[2];

            if (d != dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return (int) d;
            }

            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];

                int nextCount = labels.charAt(u) == labels.charAt(v)
                              ? count + 1 : 1;

                if (nextCount > k) {
                    continue;
                }

                long nd = d + w;

                if (nd < dist[v][nextCount]) {
                    dist[v][nextCount] = nd;
                    pq.offer(new long[]{nd, v, nextCount});
                }
            }
        }

        return -1;
    }
}