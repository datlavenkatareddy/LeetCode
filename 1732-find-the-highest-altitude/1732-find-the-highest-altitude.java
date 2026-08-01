class Solution {
    public int largestAltitude(int[] gain) {
        int start = 0;
        int max = 0;
        int n = gain.length;
        for (int i = 0; i < n; i++){
            start += gain[i];
            max = Math.max(max, start);
        }

        return max;
    }
}