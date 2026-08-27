class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[][] countAfter = new int[n + 1][26];
        for (char ch : s.toCharArray()) countAfter[0][ch - 'a']++;

        int maxL = 0;
        for (int L = 0; L < n; L++) {
            int c = target.charAt(L) - 'a';
            if (countAfter[L][c] > 0) {
                countAfter[L + 1] = countAfter[L].clone();
                countAfter[L + 1][c]--;
                maxL = L + 1;
            } else {
                break;
            }
        }

        int upper = Math.min(maxL, n - 1);
        for (int p = upper; p >= 0; p--) {
            int[] cnt = countAfter[p];
            int tc = target.charAt(p) - 'a';
            int found = -1;
            for (int c = tc + 1; c < 26; c++) {
                if (cnt[c] > 0) { found = c; break; }
            }
            if (found != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, p);
                sb.append((char) ('a' + found));
                int[] rem = cnt.clone();
                rem[found]--;
                for (int c = 0; c < 26; c++) {
                    for (int k = 0; k < rem[c]; k++) sb.append((char) ('a' + c));
                }
                return sb.toString();
            }
        }
        return "";
    }
}