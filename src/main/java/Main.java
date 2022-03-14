public class Main {

    public static int[][] multiply(int[][] a, int[][] b) {
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            throw new IllegalArgumentException("a");
        }
        if (b == null || b.length == 0 || b[0] == null || b[0].length == 0) {
            throw new IllegalArgumentException("b");
        }
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("Matrices are inconsistent");
        }
        int threadsNumber = 8;
        int m = a.length;
        int[][] result = new int[m][b[0].length];
        if (threadsNumber > m) {
            threadsNumber = m;
        }
        int count = m / threadsNumber;
        int additional = m % threadsNumber;
        Thread[] threads = new Thread[threadsNumber];
        int start = 0;
        for (int i = 0; i < threadsNumber; i++) {
            int cnt = ((i == 0) ? count + additional : count);
            threads[i] = new MultiplyThread(a, b, result, start, start + cnt - 1);
            start += cnt;
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] b = {{2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1}, {2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1}};
        int[][] c = multiply(a, b);

        for (int[] ints : c) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
