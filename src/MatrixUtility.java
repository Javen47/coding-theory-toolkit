public class MatrixUtility {

    public static int[][] transpose(int[][] matrix)
    {
       int trans[][] = new int[matrix.length][matrix[0].length];
        for( int i = 0; i < matrix[0].length; i++) {
            for( int j = 0; j < matrix.length; j++) {
                matrix[i][j] = trans[j][i];
            }
        }
        return trans;
    }

    public static int[][] multiply(int[][] a, int[][] b)
    {
       int product[][] = new int[a[0].length][a.length];
        for( int i = 0; i < a[0].length; i++) {
            for( int j = 0; j < a.length; j++) {
                product[i][j] = a[i][j] * b[i][j];
            }
        }
        return product;
    }

    public static int[] combineBinaryVectors(int[] a, int[] b)
    {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++)
        {
            result[i] = a[i] ^ b[i];
        }
        return result;
    }

    public static void printMatrix(int[][] matrix)
    {
        System.out.println();
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printVector(int[] vector)
    {
        System.out.println();
        for (int i = 0 ; i < vector.length; i++)
        {
            System.out.print(vector[i] + " ");
        }
    }

    public static boolean determineIfVectorsAreEqual(int[] a, int[] b)
    {
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

}
