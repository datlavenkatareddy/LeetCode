class Solution {
    public int minimumPushes(String word) {
        Integer[] frequency = new Integer[26];
        Arrays.fill(frequency, 0);

        for (char c : word.toCharArray()){
            frequency[c - 'a']++;
        }

        Arrays.sort(frequency, Collections.reverseOrder());

        int totalPushes = 0;

        for (int i = 0; i < 26; i++){
            if (frequency[i] == 0) break;

            int multiplier = (i/8) + 1;
            totalPushes += frequency[i]*multiplier;
        }

        return totalPushes;
    }
}