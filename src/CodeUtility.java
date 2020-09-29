public class CodeUtility {

   public static int[][] cosetReps = {
            {0,0,0,0,0,0},
            {0,0,0,0,0,1},
            {0,0,0,0,1,0},
            {0,0,0,1,0,0},
            {0,0,1,0,0,0},
            {0,1,0,0,0,0},
            {0,0,1,0,1,0},
            {0,1,0,0,1,0}
    };

    public static double calculateProbabilityOfCorrectHammingCodeDecoding(int codeLength, double singleBitErrorProbability)
    {
        return Math.pow( 1 - singleBitErrorProbability, codeLength) +
                codeLength * singleBitErrorProbability * Math.pow(1 - singleBitErrorProbability, codeLength - 1);
    }

    //p = single bit error probability
    public static double calculateProbabilityOfCorrectSyndromeDecoding(int[][] PCM, double p)
    {
        double x = 0.0;
        int n = 0;

        for (int i = 0; i <= n; i++)
        {
            x += calculateNumberOfCosetsWithGivenWeight(PCM, i) * Math.pow(p, i) * Math.pow(1 - p, n - i);
        }

        return x;
    }

    public static int calculateNumberOfCosetsWithGivenWeight(int[][] matrix, int weight)
    {
        int[][][] cosets = generateCosets(matrix);
        int count = 0;

        for (int i = 0 ; i < cosets.length; i++)
        {
            if (calculateCosetWeight(cosets[i]) == weight) count++;
        }

        return count;
    }

    public static int calculateCosetWeight(int[][] coset)
    {
        return calculateVectorWeight(findCosetLeader(coset));
    }

    public static int calculateVectorWeight(int[] vector)
    {
        int count = 0;
        for (int i : vector) {
            if (i == 1) count++;
        }
        return count;
    }

    public static int[][][] generateCosets(int[][] matrix)
    {
        int numOfCosets = calculateNumberOfCosets(matrix);
        int[][][] cosets = new int[numOfCosets][][];

        for (int i = 0; i < numOfCosets; i++)
        {
            cosets[i] = generateCoset(matrix, cosetReps[i]);
        }

        return cosets;
    }

    //TODO: current implementation assumes there will be 3 rows in input matrix -> 7 rows in coset
    public static int[][] generateCoset(int[][] matrix, int[] cosetRep)
    {
        int numOfCosetRows = 7; //TODO
        int[][] coset = new int[numOfCosetRows][cosetRep.length];

        //first 3 rows are matrix row + cosetRep
        for (int i = 0 ; i < 3; i++)
        {
            coset[i] = MatrixUtility.combineBinaryVectors(matrix[i], cosetRep);
        }
        coset[3] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[1]), cosetRep);
        coset[4] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[2]), cosetRep);
        coset[5] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[1], matrix[2]), cosetRep);
        coset[6] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[1]), matrix[2]), cosetRep);

        return coset;
    }

    public static int[][] findCosetLeaders(int[][] matrix)
    {
        int[][][] cosets = generateCosets(matrix);
        int[][] cosetLeaders = new int[cosets.length][matrix[0].length];
        for (int i = 0; i < cosets.length; i++)
        {
            cosetLeaders[i] = findCosetLeader(cosets[i]);
        }

        return cosetLeaders;
    }

    public static int[] findCosetLeader(int[][] coset)
    {
        int leader_weight = Integer.MAX_VALUE;
        int leader_index = 0;

        for(int i = 0; i < coset.length; i++)
        {
            int temp_weight = calculateVectorWeight(coset[i]);
            if (temp_weight < leader_weight)
            {
                leader_weight = temp_weight;
                leader_index = i;
            }
        }

        return coset[leader_index];
    }

    public static int findWeightOfHeaviestCosetLeader(int[][] cosetLeaders)
    {
        int heaviest_weight = 0;
        for (int i = 0; i < cosetLeaders.length; i++)
        {
            int temp_weight = calculateVectorWeight(cosetLeaders[i]);
            if (temp_weight > heaviest_weight)
            {
                heaviest_weight = temp_weight;
            }
        }

        return heaviest_weight;
    }

    public static int calculateNumberOfCosets(int[][] matrix)
    {
        return (int) Math.pow(2, matrix[0].length - matrix.length);
    }

    //TODO
    public static boolean determineIfLinearCodeIsPerfect(int n, int k, int d, int q)
    {
        return false;
    }

    public static boolean determineIfCodeExistsWithinSPBound(int n, int k, int d, int q)
    {
        int x = 0;
        double bound = Math.pow(q, n - k);
        int sigBound = (d - 1) / 2;

        for (int i = 0; i <= sigBound; i++)
        {
            x += combination(n, i) * Math.pow(q - 1, i);
        }

        return x <= bound;
    }

    public static boolean determineIfParametersAreOfMDSCode(int n, int k, int d)
    {
        return d == n - k + 1;
    }

    public static boolean determineIfCodeExistsWithinVGBound(int n, int k, int d, int q)
    {
        int m = n - k;
        double bound = Math.pow(q, m) - 1;
        int x = 0;

        if (k < (n - m)) return false;

        for (int i = 1; i <= (d - 2); i++)
        {
            int term1 = (int) Math.pow(q - 1, i);
            long term2 = combination(n - 1, i);
            x += term1 * term2;
        }

        return x < bound;
    }

    public static boolean determineIfVectorBelongsToBLC(int[][] PCM, int[] vector)
    {
        int[][] form = { vector };
        int[][] syndrome = calculateSyndrome(PCM, form);
        return determineIfMatrixIsAllModTwo(syndrome);
    }

    public static boolean determineIfSelfOrthogonal(int[][] generator)
    {
        int[][] result = MatrixUtility.multiply(generator, MatrixUtility.transpose(generator));
        return determineIfMatrixIsAllModTwo(result);
    }

    public static boolean determineIfDoublyEven(int[][] matrix)
    {
        for (int[] i : matrix)
        {
            int weight = calculateVectorWeight(i);
            if (weight % 4 != 0) return false;
        }
        return true;
    }

    private static boolean determineIfMatrixIsAllModTwo(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                if (matrix[i][j] % 2 != 0) return false;
            }
        }
        return true;
    }

    public static int calculateDistanceBetweenVectors(int[] a, int[] b)
    {
        int difference = 0;
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] != b[i]) difference++;
        }
        return difference;
    }

    public static double calculatePackingRadius(int d)
    {
        return Math.floor( (d - 1) / 2 );
    }

    //radius = largest weight of coset leaders
    public static int calculateCoveringRadius(int[][] PCM)
    {
        int[][] cosetLeaders = findCosetLeaders(PCM);
        return findWeightOfHeaviestCosetLeader(cosetLeaders);
    }

    /*
        ( n )
        ( r ) == n C r
     */
    public static long combination(int n, int r)
    {
        if (0 <= r && r <= n)
        {
            return factorial(n) / (factorial(r) * factorial(n - r));
        }
        else
        {
            throw new ArithmeticException("Combination error: parameters must be 0 <= r && r <= n");
        }
    }

    private static long factorial(long n)
    {
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }

    public static int[] determineOriginalHammingCodeword(int[][] parityCheckMatrix, int[] vector)
    {
        int[][] placeHolder = {vector};
        int[][] syndrome = calculateSyndrome(parityCheckMatrix, placeHolder);

        //compare syndrome to each column of PCM
        for (int i = 0; i < parityCheckMatrix[0].length; i++)
        {
            boolean columnsMatch = true;
            for (int j = 0; j < parityCheckMatrix.length; j++)
            {
                if (parityCheckMatrix[j][i] != syndrome[j][0])
                {
                    columnsMatch = false;
                }
            }

            if (columnsMatch)
            {
                int incorrectBitPosition = i + 1;
                vector[incorrectBitPosition] = vector[incorrectBitPosition] == 1 ? 0 : 1;
                return vector;
            }
        }

        return vector;
    }

    private static int[][] calculateSyndrome(int[][] parityCheckMatrix, int[][] vector)
    {
        return MatrixUtility.multiply(
                parityCheckMatrix, MatrixUtility.transpose(vector));
    }

}
