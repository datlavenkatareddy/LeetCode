class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()){
            count[c -'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char middleChar = 0;

        for (int i = 0; i < 26; i++){
            if (count[i] > 0){
                int half = count[i]/2;
                for (int j = 0; j < half; j++){
                    firstHalf.append((char) ('a' + i));
                }

                if (count[i] % 2 != 0){
                    middleChar = (char)('a' + i);
                }
            }
        }

        StringBuilder result = new StringBuilder(firstHalf);
        if(middleChar != 0){
            result.append(middleChar);
        }
        result.append(firstHalf.reverse());

        return result.toString();
    }
}