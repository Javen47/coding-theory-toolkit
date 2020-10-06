public class MatrixUtility {

    public static int[][] transpose(int[][] matrix)
    {
        int n = matrix[0].length;
        int m = matrix.length;
        int[][] trans = new int[n][m];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                trans[i][j] = matrix[j][i];
            }
        }

        return trans;
    }

    public static int[][] transposeVector(int[] vector)
    {
        int[][] trans = new int[vector.length][1];
        for (int i = 0; i < trans.length; i++)
        {
            trans[i][0] = vector[i];
        }
        return trans;
    }

    public static int[][] multiply(int[][] a, int[][] b)
    {
        int rowA = a.length, rowB = b.length, colA = a[0].length, colB = b[0].length;

        if (rowB != colA) return null;

        int[][] c = new int[rowA][colB];

        for (int i = 0; i < rowA; i++)
        {
            for (int j = 0; j < colB; j++)
            {
                for (int k = 0; k < rowB; k++)
                {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }

    public static int[] differenceOfVectors(int[] a, int[] b)
    {
        return null;
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
