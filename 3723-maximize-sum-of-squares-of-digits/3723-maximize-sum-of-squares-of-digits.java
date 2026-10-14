class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if ((long) num * 9 < sum){
            return "";
        }

        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++){
            int digit = Math.min(9, sum);
            sb.append(digit);
            sum -= digit;
        }

        if (sum > 0){
            return "";
        }

        return sb.toString();
    }
}