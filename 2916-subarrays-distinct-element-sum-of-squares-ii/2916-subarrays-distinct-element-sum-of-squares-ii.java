class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegTree seg = new SegTree(n);
        long ans = 0;
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int p = last[x] + 1;

            seg.update(p, i, 1);
            last[x] = i;

            ans = (ans + seg.query(0, i)) % MOD;
        }

        return (int) ans;
    }

    static class SegTree {
        int n;
        long[] sum, sq, lazy;

        SegTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int l, int r, long v) {
            long len = r - l + 1;
            sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + len % MOD * v % MOD * v % MOD) % MOD;
            sum[node] = (sum[node] + len * v) % MOD;
            lazy[node] = (lazy[node] + v) % MOD;
        }

        void push(int node, int l, int r) {
            if (lazy[node] == 0 || l == r) return;

            int mid = (l + r) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }

        void update(int ql, int qr, int v) {
            if (ql > qr) return;
            update(1, 0, n - 1, ql, qr, v);
        }

        void update(int node, int l, int r,
                    int ql, int qr, int v) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, v);
                return;
            }

            push(node, l, r);
            int mid = (l + r) / 2;

            if (ql <= mid)
                update(node * 2, l, mid, ql, qr, v);
            if (qr > mid)
                update(node * 2 + 1, mid + 1, r, ql, qr, v);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }

        long query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        long query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return sq[node];

            push(node, l, r);
            int mid = (l + r) / 2;
            long res = 0;

            if (ql <= mid)
                res += query(node * 2, l, mid, ql, qr);
            if (qr > mid)
                res += query(node * 2 + 1, mid + 1, r, ql, qr);

            return res % MOD;
        }
    }
}