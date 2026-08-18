class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0;
        Integer elem1 = null, elem2 = null;

        for (int num : nums){
            if (elem1 != null && num == elem1){
                count1++;
            } else if (elem2 != null && num == elem2){
                count2++;
            } else if (count1 == 0){
                elem1 = num;
                count1 = 1;
            } else if (count2 == 0){
                elem2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums){
            if (elem1 != null && num == elem1){
                count1++;
            } else if (elem2 != null && num == elem2){
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length/3;

        if (elem1 != null && count1 > threshold){
            result.add(elem1);
        }
        if (elem2 != null && count2 > threshold){
            result.add(elem2);
        }
        return result;
    }
}