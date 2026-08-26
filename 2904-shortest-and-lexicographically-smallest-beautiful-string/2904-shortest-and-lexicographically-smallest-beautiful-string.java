class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";

        for (int i = 0; i < s.length(); i++) {
            int ones = 0;

            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    ones++;
                }

                if (ones == k) {
                    String curr = s.substring(i, j + 1);

                    if (ans.isEmpty() ||
                        curr.length() < ans.length() ||
                        (curr.length() == ans.length() &&
                         curr.compareTo(ans) < 0)) {
                        ans = curr;
                    }
                    break;
                }
            }
        }

        return ans;
    }
}