class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origAdj = new ArrayList[n];
        List<Integer>[] newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        buildNewTree(0, parent, s, origAdj, newAdj, lastSeen);

        int[] answer = new int[n];
        dfsSize(0, newAdj, answer);
        return answer;
    }

    private void buildNewTree(
            int u,
            int[] parent,
            String s,
            List<Integer>[] origAdj,
            List<Integer>[] newAdj,
            int[] lastSeen
    ) {
        int idx = s.charAt(u) - 'a';
        int oldCharNode = lastSeen[idx];

        if (u != 0) {
            int targetParent = (oldCharNode != -1) ? oldCharNode : parent[u];
            newAdj[targetParent].add(u);
        }

        lastSeen[idx] = u;
        for (int v : origAdj[u]) {
            buildNewTree(v, parent, s, origAdj, newAdj, lastSeen);
        }
        lastSeen[idx] = oldCharNode;
    }

    private int dfsSize(int u, List<Integer>[] newAdj, int[] answer) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += dfsSize(v, newAdj, answer);
        }
        answer[u] = size;
        return size;
    }
}