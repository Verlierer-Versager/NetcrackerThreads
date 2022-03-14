public class MultiplyThread extends Thread {
    private final int start;
    private final int end;
    private final int[][] a;
    private final int[][] b;
    private final int[][] result;
    private final int n;

    public MultiplyThread(int[][] a, int[][] b, int[][] result, int start, int end) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.start = start;
        this.end = end;
        this.n = b.length;
    }

    @Override
    public void run() {
        System.out.println("From row number " + start + " to row number " + end + " inclusive");
        for (int row = start; row <= end; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = calculateValue(row, col);
            }
        }
    }

    private int calculateValue(int row, int col) {
        int value = 0;
        for (int i = 0; i < n; i++) {
            value += a[row][i] * b[i][col];
        }
        return value;
    }
}

