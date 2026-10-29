import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[][] up;
    int[] depth, tin, tout;
    int timer, LOG;
    Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        buildEuler(n);

        bit = new Fenwick(n + 1);
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            bit.rangeXor(tin[i], tout[i] + 1, mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                bit.rangeXor(tin[u], tout[u] + 1, delta);
                chars[u] = c;
            } else {
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);

                int mask = bit.pointQuery(tin[u])
                         ^ bit.pointQuery(tin[v])
                         ^ (1 << (chars[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    void buildEuler(int n) {
        int[] parent = new int[n];
        int[] next = new int[n];
        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0;
        stack[0] = 0;
        parent[0] = 0;
        timer = 0;
        tin[0] = timer++;

        for (int k = 0; k < LOG; k++)
            up[k][0] = 0;

        while (top >= 0) {
            int u = stack[top];

            if (next[u] < adj[u].size()) {
                int v = adj[u].get(next[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                for (int k = 1; k < LOG; k++)
                    up[k][v] = up[k - 1][up[k - 1][v]];

                tin[v] = timer++;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++)
            if ((diff & (1 << k)) != 0)
                u = up[k][u];

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void add(int i, int val) {
            for (i++; i <= n; i += i & -i)
                tree[i] ^= val;
        }

        void rangeXor(int l, int r, int val) {
            add(l, val);
            add(r, val);
        }

        int pointQuery(int i) {
            int res = 0;

            for (i++; i > 0; i -= i & -i)
                res ^= tree[i];

            return res;
        }
    }
}