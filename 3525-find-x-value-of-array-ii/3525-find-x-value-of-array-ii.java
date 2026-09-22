class Solution {
    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    private Node[] tree;
    private int n, k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int q = queries.length;
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int targetX = queries[i][3];

            // 1. Update element at index idx
            update(1, 0, n - 1, idx, val);

            // 2. Query range [start, n - 1]
            Node queryRes = query(1, 0, n - 1, start, n - 1);

            // 3. Store result for targetX
            result[i] = queryRes.cnt[targetX];
        }

        return result;
    }

    private void build(int node, int start, int end, int[] nums) {
        tree[node] = new Node(k);
        if (start == end) {
            int val = nums[start] % k;
            tree[node].prod = val;
            tree[node].cnt[val] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);

        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % k;
            tree[node].prod = rem;
            for (int r = 0; r < k; r++) {
                tree[node].cnt[r] = 0;
            }
            tree[node].cnt[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        merge(tree[node], tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;

        if (r <= mid) {
            return query(2 * node, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, end, l, r);
        }

        Node leftRes = query(2 * node, start, mid, l, r);
        Node rightRes = query(2 * node + 1, mid + 1, end, l, r);

        Node res = new Node(k);
        merge(res, leftRes, rightRes);
        return res;
    }

    private void merge(Node parent, Node left, Node right) {
        parent.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; r++) {
            parent.cnt[r] = left.cnt[r];
        }

        for (int r = 0; r < k; r++) {
            if (right.cnt[r] > 0) {
                int newRem = (left.prod * r) % k;
                parent.cnt[newRem] += right.cnt[r];
            }
        }
    }
}