class Solution {
    public int maxVowels(String s, int k) {
        int maxVowels = 0;
        int currentVowels = 0;

        for (int i = 0; i < k; i++){
            if (isVowel(s.charAt(i))){
                currentVowels++;
            }
        }

        maxVowels = currentVowels;

        for (int i = k; i < s.length(); i++){
            if (isVowel(s.charAt(i))){
                currentVowels++;
            }

            if (isVowel(s.charAt(i - k))){
                currentVowels--;
            }

            maxVowels = Math.max(maxVowels, currentVowels);
        }

        return maxVowels;
    }

    private boolean isVowel(char c){
        return c =='a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}