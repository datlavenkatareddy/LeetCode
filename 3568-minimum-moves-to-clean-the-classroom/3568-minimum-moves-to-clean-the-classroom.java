import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = 0, startY = 0;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }
        
        // If there are no litters to collect, 0 moves are needed.
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;
        
        // Queue for BFS storing {row, col, current_energy, collected_mask}
        Queue<int[]> queue = new LinkedList<>();
        
        // 3D array storing max remaining energy for (row, col, mask) to prune sub-optimal paths
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }

        // Initialize BFS
        queue.offer(new int[]{startX, startY, energy, 0});
        maxEnergy[startX][startY][0] = energy;
        
        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int e = curr[2];
                int mask = curr[3];

                // If all litter items are collected
                if (mask == targetMask) {
                    return moves;
                }

                // Out of energy, cannot move further
                if (e == 0) {
                    continue;
                }

                // Explore 4-directional moves
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        char cell = classroom[nr].charAt(nc);
                        int nextEnergy = (cell == 'R') ? energy : e - 1;
                        int nextMask = mask;

                        if (cell == 'L') {
                            nextMask |= (1 << litterIndex[nr][nc]);
                        }

                        // Only proceed if this state provides strictly more remaining energy
                        if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                            maxEnergy[nr][nc][nextMask] = nextEnergy;
                            queue.offer(new int[]{nr, nc, nextEnergy, nextMask});
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}