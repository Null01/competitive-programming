package edu.algorithms.competitive.mathematics;


public class Sum {

    public int findMaxSum(int[][] matrix) {
        int maxSum = 0;
        for (int left = 0; left < matrix[0].length; left++) {
            int temp[] = new int[matrix.length];
            for (int right = left; right < matrix[0].length; right++) {
                for (int i = 0; i < matrix.length; ++i)
                    temp[i] += matrix[i][right];
                int sum = kadane(temp, temp.length);
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    public int kadane(int array[], int n) {
        int max_so_far = 0, max_ending_here = 0;
        for (int i = 0; i < n; i++) {
            max_ending_here = max_ending_here + array[i];
            if (max_ending_here < 0)
                max_ending_here = 0;

            else if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
        }
        return max_so_far;
    }

}
