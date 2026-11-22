class Solution {
    public boolean checkString(String s) {
        boolean seenB = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'b') {
                seenB = true;
            } else if (seenB) {
                
                return false;
            }
        }

        return true;
    }
}