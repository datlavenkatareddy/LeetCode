import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // Store interval data: {l, r, weight, original_index}
        int[][] sortedIntervals = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sortedIntervals[i][0] = interval.get(0);
            sortedIntervals[i][1] = interval.get(1);
            sortedIntervals[i][2] = interval.get(2);
            sortedIntervals[i][3] = i;
        }
        
        // Sort intervals by their start time
        Arrays.sort(sortedIntervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // suffixDp[i][k] stores the optimal outcome using at most k intervals from index i to n-1
        // Format: {maxWeight, idx1, idx2, ...} (indices padded with -1 up to count k)
        long[][][] suffixDp = new long[n + 1][5][];
        
        // Base case: 0 intervals selected yields weight 0 and empty index list
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                suffixDp[i][k] = new long[]{0};
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            int l = sortedIntervals[i][0];
            int r = sortedIntervals[i][1];
            long weight = sortedIntervals[i][2];
            int originalIdx = sortedIntervals[i][3];
            
            // Binary search for the first interval starting strictly after current interval's right boundary
            int nextIdx = binarySearchNext(sortedIntervals, r + 1, i + 1, n);
            
            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip current interval
                long[] skipChoice = suffixDp[i + 1][k];
                
                // Option 2: Take current interval
                long[] prevChoice = suffixDp[nextIdx][k - 1];
                long[] takeChoice = new long[prevChoice.length + 1];
                takeChoice[0] = weight + prevChoice[0];
                takeChoice[1] = originalIdx;
                for (int j = 1; j < prevChoice.length; j++) {
                    takeChoice[j + 1] = prevChoice[j];
                }
                // Sort indices of the current choice to preserve lexicographical order comparison
                Arrays.sort(takeChoice, 1, takeChoice.length);
                
                // Compare Option 1 and Option 2 to choose max weight, breaking ties lexicographically
                suffixDp[i][k] = compareChoices(skipChoice, takeChoice) >= 0 ? skipChoice : takeChoice;
            }
        }
        
        long[] bestResult = suffixDp[0][4];
        int resultSize = bestResult.length - 1;
        
        int[] ans = new int[resultSize];
        for (int i = 0; i < resultSize; i++) {
            ans[i] = (int) bestResult[i + 1];
        }
        
        return ans;
    }
    
    private int binarySearchNext(int[][] intervals, int targetStart, int low, int high) {
        int ans = high;
        while (low <= high - 1) {
            int mid = low + (high - low) / 2;
            if (intervals[mid][0] >= targetStart) {
                ans = mid;
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    
    private int compareChoices(long[] a, long[] b) {
        if (a[0] != b[0]) {
            return Long.compare(a[0], b[0]);
        }
        
        // Equal weight: compare indices lexicographically (smaller is better)
        int lenA = a.length - 1;
        int lenB = b.length - 1;
        int minLen = Math.min(lenA, lenB);
        
        for (int i = 1; i <= minLen; i++) {
            if (a[i] != b[i]) {
                return Long.compare(b[i], a[i]); // Smaller index is preferred
            }
        }
        
        return Integer.compare(lenB, lenA); // Shorter length preferred if prefix matches
    }
}