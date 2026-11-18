class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to group duplicates together and enable early pruning
        Arrays.sort(candidates);
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] candidates, int target, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicate elements at the same recursion depth to avoid duplicate combinations
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Early pruning: since the array is sorted, no subsequent number can fit
            if (candidates[i] > target) {
                break;
            }

            currentCombination.add(candidates[i]);
            // Pass i + 1 because each element in candidates can only be used once
            backtrack(i + 1, candidates, target - candidates[i], currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}