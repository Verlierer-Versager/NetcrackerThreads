public class Main {

    public static int[][] multiply(int[][] a, int[][] b) {
        int threadsNumber = Runtime.getRuntime().availableProcessors();
        int length = a.length;
        int[][] result = new int[length][b[0].length];
        if (threadsNumber > length) {
            threadsNumber = length;
        }
        int count = length / threadsNumber;
        int extra = length % threadsNumber;
        Thread[] threads = new Thread[threadsNumber];
        int start = 0;
        for (int i = 0; i < threadsNumber; i++) {
            int end = start + count;
            if (i == 0) {
                end += extra;
            }
            threads[i] = new MultiplyThread(a, b, result, start, end - 1);
            start = end;
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

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] b = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] c = multiply(a, b);

        /*
         * result:
         * 38 44 50 56
         * 83 98 113 128
         * 128 152 176 200
         * */
        printMatrix(c);

    }
}
