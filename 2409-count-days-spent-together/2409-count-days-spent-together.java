class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceArrival = calculateDayOfYear(arriveAlice);
        int aliceLeave = calculateDayOfYear(leaveAlice);
        int bobArrival = calculateDayOfYear(arriveBob);
        int bobLeave = calculateDayOfYear(leaveBob);

        int overlapStart = Math.max(aliceArrival, bobArrival);
        int overlapEnd = Math.min(aliceLeave, bobLeave);

        return Math.max(0, overlapEnd - overlapStart +1);
    }

    private int calculateDayOfYear(String date){
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month= Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++){
            dayOfYear += daysInMonths[i];
        }
        return dayOfYear;
    }
}