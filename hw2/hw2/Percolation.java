package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF ds;
    int[] helper;
    int N;
    int OpenNumber;
    public Percolation(int N) {
        if (N < 0) throw new IllegalArgumentException();
        this.N = N;
        ds = new WeightedQuickUnionUF(N * N);
        helper =  new int[N * N];
    }               // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        int index = (row - 1) * N + col - 1;
        if (helper[index] == 1) return;
        helper[index] = 1;
        if (col > 1 && helper[index - 1] == 1) ds.union(index, index - 1);
        if (col < N - 1 && helper[index + 1] == 1) ds.union(index, index + 1);
        if (row < N - 1 && helper[index + N] == 1) ds.union(index, index + N);
        if (row > 1 && index - N > 0 && helper[index - N] == 1) ds.union(index, index - N);
        OpenNumber ++;
    }      // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col)  {
        return helper[(row - 1) * N + col - 1] == 1;
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < N; i ++) {
            int index = (row - 1) * N + col - 1;
            if (ds.connected(i,index)) return true;
        }
        return false;
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return OpenNumber;
    }          // number of open sites
    public boolean percolates() {
        for (int i = 0; i < N; i ++) {
            for (int j = N * (N - 1); j < N * N; j ++) {
                if (ds.connected(i, j)) return true;
            }
        }
        return false;
    }             // does the system percolate?
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1,2);
        System.out.println(p.percolates());
        p.open(2,2);
        System.out.println(p.percolates());
        p.open(3,2);
        System.out.println(p.percolates());
        p.open(4,2);
        System.out.println(p.percolates());

    }   // use for unit testing (not required)
}
