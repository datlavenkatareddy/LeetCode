class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long start = 1000;
        long commas = 1;

        while (start <= n) {
            long end = start * 1000 - 1;
            long count = Math.min(n, end) - start + 1;
            totalCommas += count * commas;
            
            // Prevent overflow for start * 1000
            if (start > n / 1000) {
                break;
            }
            start *= 1000;
            commas++;
        }

        return totalCommas;
    }
}