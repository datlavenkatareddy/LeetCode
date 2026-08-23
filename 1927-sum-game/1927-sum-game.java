class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        double sum1 = 0, sum2 = 0;
        double q1 = 0, q2 = 0;
        
        // Calculate sums and question mark counts for the left half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                q1++;
            } else {
                sum1 += num.charAt(i) - '0';
            }
        }
        
        // Calculate sums and question mark counts for the right half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                q2++;
            } else {
                sum2 += num.charAt(i) - '0';
            }
        }
        
        // If total number of '?' is odd, Alice always gets the last move and wins
        if ((q1 + q2) % 2 != 0) {
            return true; 
        }
        
        // If the number of '?' is even, Bob can only win if the difference 
        // in sums can be exactly balanced by the difference in '?' counts.
        // For every 2 questions marks, Bob can force a sum difference of 9 (averaging 4.5 each).
        return (sum1 - sum2) != (q2 - q1) * 4.5;
    }
}