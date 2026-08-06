class Solution {
    public int smallestNumber(int n, int t) {
        int current = n;
        while (true){
            if (isProductDivisible(current, t)){
                return current;
            }
            current++;
        }
    }

    private boolean isProductDivisible(int num, int t){
        int product = 1;
        int temp = num;
        while (temp > 0){
            product *= (temp % 10);
            temp /= 10;
        }
        return product % t == 0;
    }
}