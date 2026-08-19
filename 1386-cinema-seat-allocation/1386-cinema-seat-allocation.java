class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map to store reserved seats for each row using a bitmask (seats 2 to 9)
        Map<Integer, Integer> rowReservations = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // We only care about seats 2 through 9
            if (col >= 2 && col <= 9) {
                rowReservations.put(row, rowReservations.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        // Rows with no reservations can fit 2 groups each
        int totalGroups = (n - rowReservations.size()) * 2;
        
        // Masks for valid four-person blocks (0-indexed relative to seat 2)
        // Seats 2,3,4,5 -> bits 0,1,2,3 -> 0x0F (15)
        // Seats 4,5,6,7 -> bits 2,3,4,5 -> 0x3C (60)
        // Seats 6,7,8,9 -> bits 4,5,6,7 -> 0xF0 (240)
        int leftGroup = 0x0F;   // binary: 00001111
        int middleGroup = 0x3C; // binary: 00111100
        int rightGroup = 0xF0;  // binary: 11110000
        
        for (int mask : rowReservations.values()) {
            boolean canFitLeft = (mask & leftGroup) == 0;
            boolean canFitMiddle = (mask & middleGroup) == 0;
            boolean canFitRight = (mask & rightGroup) == 0;
            
            if (canFitLeft && canFitRight) {
                totalGroups += 2; // Can fit both left (2-5) and right (6-9) groups
            } else if (canFitLeft || canFitRight || canFitMiddle) {
                totalGroups += 1; // Can fit at least one group
            }
        }
        
        return totalGroups;
    }
}