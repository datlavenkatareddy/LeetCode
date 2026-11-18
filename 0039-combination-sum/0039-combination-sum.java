class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] candidates, int target, List<Integer> currentCombination, List<List<Integer>> result) {
        // Base case: if target is 0, we found a valid combination
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        
        // Base case: if target becomes negative, this path is invalid
        if (target < 0) {
            return;
        }

        // Iterate through candidates starting from 'start' to avoid duplicate combinations
        for (int i = start; i < candidates.length; i++) {
            // Choose the candidate
            currentCombination.add(candidates[i]);
            
            // Explore: pass 'i' again because the same number can be chosen multiple times
            backtrack(i, candidates, target - candidates[i], currentCombination, result);
            
            // Un-choose (backtrack) to explore other branches
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}