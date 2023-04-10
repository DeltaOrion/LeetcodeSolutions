package me.jacob.assign.algorithms.dp;

public class Problem673 {

    public int findNumberOfLIS(int[] nums) {
        int[] DP = new int[nums.length];
        int[] R = new int[nums.length];
        DP[0] = 1;
        R[0] = 1;
        int maxDP = 1;
        for(int i=1;i<nums.length;i++) {
            int max = 0;
            int maxCount = 1;
            for(int j=0;j<i;j++) {
                if(nums[j] < nums[i]) {
                    if(DP[j] > max) {
                        max = DP[j];
                        maxCount = R[j];
                    } else if(max == DP[j]) {
                        maxCount+= R[j];
                    }
                }
            }
            DP[i] = max+1;
            R[i] = maxCount;
            if(DP[i]>maxDP)
                maxDP = DP[i];
        }

        int count = 0;
        for(int i=0;i<DP.length;i++) {
            if(DP[i]==maxDP) {
                count+=R[i];
            }
        }

        return count;
    }
}
