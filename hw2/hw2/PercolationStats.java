package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

import java.util.Arrays;

public class PercolationStats {
    double[] OpenNumbers;
    int T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.T = T;
        if (N < 0 || T < 0) throw new IllegalArgumentException();
        OpenNumbers = new double[T];
        for (int count = 0; count < T; count ++) {
            Percolation p = pf.make(N);
            for (int i = 0; i < N * N; i ++) {
                int row = (int)(1 + N * StdRandom.uniform());
                int col = (int)(1 + N * StdRandom.uniform());
                while (p.isOpen(row, col)) {
                    row = (int)(1 + N * StdRandom.uniform());
                    col = (int)(1 + N * StdRandom.uniform());
                }
                p.open(row, col);
                if (p.percolates()) {
                    OpenNumbers[count] = i;
                    break;
                 }
                }
            }
        }// perform T independent experiments on an N-by-N grid
    public double mean() {
         return StdStats.mean(OpenNumbers);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(OpenNumbers);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                                  // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }                                 // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(100,1,new PercolationFactory());
        System.out.println(p.mean());
    }
}
