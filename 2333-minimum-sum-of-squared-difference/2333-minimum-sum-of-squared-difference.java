class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long)k1 + (long)k2;

        long[] count = new long[100005];
        long maxDiff = 0;

        for (int i = 0; i < n; i++){
            long diff = Math.abs((long) nums1[i] - nums2[i]);
            count[(int) diff]++;
            if (diff > maxDiff){
                maxDiff = diff;
            }
        }

        for (long i = maxDiff; i > 0 && totalOps > 0; i--){
            if (count[(int) i] > 0){
                long opsToUse = Math.min(totalOps, count[(int) i]);
                count[(int) i] -= opsToUse;
                count[(int) i - 1] += opsToUse;
                totalOps -= opsToUse;
            }
        }

        long minSum  = 0;
        for (int i = 1; i <= maxDiff; i++){
            if (count[i] > 0){
                minSum += count[i]*(long)i * (long)i;
            }
        }

        return minSum;
    }
}