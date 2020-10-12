public class CodeUtility {

    static int[][] cosetReps8 = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,1,0},
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,1,0},
            {0,0,0,1,0,0,1,0}
    };

   private static int[][] cosetReps6 = {
            {0,0,0,0,0,0},
            {0,0,0,0,0,1},
            {0,0,0,0,1,0},
            {0,0,0,1,0,0},
            {0,0,1,0,0,0},
            {0,1,0,0,0,0},
            {0,0,1,0,1,0},
            {0,1,0,0,1,0}
    };

    private static int[][] cosetReps5 = {
            {0,0,0,0,0},
            {0,0,0,0,1},
            {0,0,0,1,0},
            {0,0,1,0,0},
            {0,1,0,0,0},
            {1,0,0,0,0},
            {0,1,0,1,0},
            {1,0,0,1,0}
    };

    static double calculateProbabilityOfCorrectHammingCodeDecoding(int codeLength, double singleBitErrorProbability)
    {
        return Math.pow( 1 - singleBitErrorProbability, codeLength) +
                codeLength * singleBitErrorProbability * Math.pow(1 - singleBitErrorProbability, codeLength - 1);
    }

    //p = single bit error probability
   static double calculateProbabilityOfCorrectSyndromeDecoding(int[][] PCM, double p)
    {
        double x = 0.0;
        int n = PCM[0].length;

        for (int i = 0; i <= n; i++)
        {
            int temp = calculateNumberOfCosetsWithGivenWeight(PCM, i);
            x +=  temp
                    * Math.pow(p, i)
                    * Math.pow(1 - p, n - i);
        }

        return x;
    }

    private static int calculateNumberOfCosetsWithGivenWeight(int[][] matrix, int weight)
    {
        int numOfCosets = calculateNumberOfCosets(matrix);
        //int numOfCosets = 8;
        int count = 0;

        for (int i = 0 ; i < numOfCosets; i++)
        {
            if (calculateVectorWeight(cosetReps6[i]) == weight) count++;
        }

        return count;
    }

    public static int calculateCosetWeight(int[][] coset)
    {
        return calculateVectorWeight(findCosetLeader(coset));
    }

    static int calculateVectorWeight(int[] vector)
    {
        int count = 0;
        for (int i : vector) {
            if (i == 1) count++;
        }
        return count;
    }

    static int[][][] generateCosets(int[][] matrix)
    {
        int columns = matrix[0].length;
        int numOfCosets = calculateNumberOfCosets(matrix);
        int[][][] cosets = new int[numOfCosets][][];

        for (int i = 0; i < numOfCosets; i++)
        {
            if (columns == 6)
            {
                cosets[i] = generateCoset(matrix, cosetReps6[i]);
            }
            else if (columns == 5)
            {
                cosets[i] = generateCoset(matrix, cosetReps5[i]);
            }
            else if (columns == 8)
            {
                cosets[i] = generateCoset(matrix, cosetReps8[i]);
            }
        }

        return cosets;
    }

    static int[][] generateCoset(int[][] matrix, int[] cosetRep)
    {
        int numOfCosetRows = 0;
        if (matrix.length == 3)
        {
            numOfCosetRows = 7;
        }
        else if (matrix.length == 4)
        {
            numOfCosetRows = 13;
        }

        int[][] coset = new int[numOfCosetRows][cosetRep.length];

        for (int i = 0 ; i < matrix.length; i++)
        {
            coset[i] = MatrixUtility.combineBinaryVectors(matrix[i], cosetRep);
        }

        if (matrix.length == 3) // 3 rows
        {
            coset[3] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[1]), cosetRep);
            coset[4] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[2]), cosetRep);
            coset[5] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[1], matrix[2]), cosetRep);
            coset[6] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[1]), matrix[2]), cosetRep);
        }
        else if (matrix.length  == 4) // 4 rows
        {
            coset[4] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[1]), cosetRep);
            coset[5] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[2]), cosetRep);
            coset[6] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[3]), cosetRep);
            coset[7] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[1], matrix[2]), cosetRep);
            coset[8] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[1], matrix[3]), cosetRep);
            coset[9] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[2], matrix[3]), cosetRep);
            coset[10] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[0], matrix[1]), matrix[2]), cosetRep);
            coset[11] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryVectors(matrix[1], matrix[2]), matrix[3]), cosetRep);
            coset[12] = MatrixUtility.combineBinaryVectors(MatrixUtility.combineBinaryMatrix(new int[][] {matrix[0], matrix[1], matrix[2], matrix[3]}), cosetRep);
        }

        return coset;
    }

    static int[][] findCosetLeaders(int[][] matrix)
    {
        int[][][] cosets = generateCosets(matrix);
        int[][] cosetLeaders = new int[cosets.length][matrix[0].length];
        for (int i = 0; i < cosets.length; i++)
        {
            cosetLeaders[i] = findCosetLeader(cosets[i]);
        }

        return cosetLeaders;
    }

    static int[] findCosetLeader(int[][] coset)
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

    static int findWeightOfHeaviestCosetLeader(int[][] cosetLeaders)
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

    static int[][] createGeneratorMatrixOfRMCode(int r, int m, boolean isFirstCall)
    {
        if (r == 0)
        {
            //G(0, m) = (111...1)
            int[][] matrix = new int[1][m];
            for (int i = 0; i < m; i++)
            {
                matrix[0][i] = 1;
            }
            return matrix;
        }
        else if (r == m)
        {
            //G(m, m) = I-2^m
            int w = (int) Math.pow(2, m);
            int[][] matrix = new int[w][w];
            for (int i = 0; i < w; i++)
            {
                matrix[i][i] = 1;
            }
            return matrix;
        }

        //recursive call
        int[][][] matrix = {
                createGeneratorMatrixOfRMCode(r, m - 1, false),
                createGeneratorMatrixOfRMCode(r, m - 1, false),
                new int[1][m],
                createGeneratorMatrixOfRMCode(r - 1, m - 1, false)
        };

        if (isFirstCall)
        {
            for (int[][] i : matrix)
            {
                MatrixUtility.printMatrix(i);
            }
        }

        return new int[1][1];
    }

    //TODO fix
    static int calculateNumberOfCosets(int[][] matrix)
    {
        return (int) Math.pow(2, matrix[0].length - matrix.length);
    }

    static boolean determineIfTernaryPerfectCode(int[][] PCM)
    {
        int[][] result = MatrixUtility.multiply(PCM, MatrixUtility.transpose(PCM));
        return determineIfMatrixIsGivenMod(result, 3);
    }

    static boolean determineIfCodeExistsWithinSPBound(int n, int k, int d, int q)
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

    static boolean determineIfParametersAreOfMDSCode(int n, int k, int d)
    {
        return d == n - k + 1;
    }

    static boolean determineIfParametersAreOfReedMullerCode(int n, int k, int d)
    {
        int[] code = calculateRMCode(n, d);
        return k == calculateRMCodeKParameter(code[0], code[1]);
    }

    static boolean determineIfParametersAreOfDualRMCode(int n, int k, int d, int r, int m)
    {
        int[] dual = calculateRMDuelCode(r, m); //adjusted r, m

        int[] test = calculateRMCode(n, d);

        return test[0] == dual[0]
                && test[1] == dual[1]
                && k == calculateRMCodeKParameter(dual[0], dual[1]);
    }

    static boolean determineIfParametersAreOfSelfDualRMCode(int n, int k, int d)
    {
//        int x = log2(n);
//        if (x % 2 != 0) return false;

        int[] code = calculateRMCode(n, d);

        return determineIfRMCodeIsSelfDual(code[0], code[1]);
    }

    static boolean determineIfParametersAreOfROrderRMCode(int n, int k, int d, int order, int length)
    {
        int[] code = calculateRMCode(n, d);

        return code[0] == order
                && n == length
                && k == calculateRMCodeKParameter(code[0], code[1]);
    }

    private static boolean determineIfRMCodeIsSelfDual(int r, int m)
    {
        return r == m - r - 1;
        //return m == (2 * r) - 1;
    }

    private static boolean determineIfRMCodeIsSelfOrthogonal(int r, int m)
    {
        return r <= m - r - 1;
        //return m > (2 * r) + 1;
    }

    static boolean determineIfCodeExistsWithinVGBound(int n, int k, int d, int q)
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

    static boolean determineIfVectorBelongsToBLC(int[][] PCM, int[] vector)
    {
        int[][] form = { vector };
        int[][] syndrome = calculateSyndrome(PCM, form);
        return determineIfMatrixIsGivenMod(syndrome, 2);
    }

    static boolean determineIfSelfOrthogonal(int[][] generator)
    {
        int[][] result = MatrixUtility.multiply(generator, MatrixUtility.transpose(generator));
        return determineIfMatrixIsGivenMod(result, 2);
    }

    static boolean determineIfDoublyEven(int[][] matrix)
    {
        for (int[] i : matrix)
        {
            int weight = calculateVectorWeight(i);
            if (weight % 4 != 0) return false;
        }
        return true;
    }

    private static boolean determineIfMatrixIsGivenMod(int[][] matrix, int mod)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                if (matrix[i][j] % mod != 0) return false;
            }
        }
        return true;
    }

    static boolean determineIfGeneratorMatrixIsOfRMCode(int[][] matrix)
    {
        int[][] coset = generateCoset(matrix, cosetReps8[0]);
        for (int[] i : coset)
        {
            if (calculateVectorWeight(i) % 2 != 0) return false;
        }
        return true;
    }

    static int calculateDistanceBetweenVectors(int[] a, int[] b)
    {
        int difference = 0;
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] != b[i]) difference++;
        }
        return difference;
    }

    static double calculatePackingRadius(int d)
    {
        return Math.floor( (d - 1) / 2 );
    }

    //radius = largest weight of coset leaders
    static int calculateCoveringRadius(int[][] PCM)
    {
        int numOfCosets = calculateNumberOfCosets(PCM);
        int largest_weight = 0;

        for (int i = 0; i < numOfCosets; i++)
        {
            int weight = calculateVectorWeight(cosetReps6[i]);
            if (weight > largest_weight)
            {
                largest_weight = weight;
            }
        }

        return largest_weight;
    }

    private static int[] calculateRMCode(int n, int d)
    {
        int m = log2(n);
        int r = m - log2(d);

        return new int[] { r, m };
    }

    private static int[] calculateRMDuelCode(int r, int m)
    {
        return new int[] { m - r - 1, m };
    }

    static int[] calculateMinimalRMCode(int infoSymbols, int errorCorrection)
    {
        int d = 2;
        int adj_d = (d - 1) / 2;
        while ( adj_d < errorCorrection)
        {
            d = d << 1;
            adj_d = (d - 1) / 2;
        }

        int m = -1, n = 0;
        while (n < infoSymbols)
        {
            m++;
            n = (int) Math.pow(2, m);
        }

        int k = 0;
        int r = 0;
        while (k < infoSymbols)
        {
            r = m - log2(d);
            k = calculateRMCodeKParameter(r, m);
            m++;
        }
        m--;

        return new int[] { r, m };
    }

    private static int calculateRMCodeKParameter(int r, int m)
    {
        int k = 0;
        for (int i = 0; i <= r; i++)
        {
            k += combination(m, i);
        }
        return k;
    }

    private static int calculateRMCodeNParameter(int m)
    {
        return (int) Math.pow(2, m);
    }

    private static int calculateRMCodeDParameter(int r, int m)
    {
        return (int) Math.pow(2, m - r);
    }

    /*
        ( n )
        ( r ) == n C r
     */
    static long combination(int n, int r)
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

    private static int log2(int x)
    {
        return (int) (Math.log(x) / Math.log(2));
    }

    static int[] determineOriginalCodewordUsingSyndromeDecoding(int[][] parityCheckMatrix, int[] vector)
    {
        int[][] syndrome = calculateSyndrome(parityCheckMatrix, vector);

        //compare syndrome to each column of PCM
        for (int i = 0; i < parityCheckMatrix[0].length; i++)
        {
            boolean columnsMatch = true;
            for (int j = 0; j < parityCheckMatrix.length; j++)
            {
                if (parityCheckMatrix[j][i] != syndrome[j][0] % 2)
                {
                    columnsMatch = false;
                }
            }

            if (columnsMatch)
            {
                vector[i] = vector[i] == 1 ? 0 : 1;
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

    static int[][] calculateSyndrome(int[][] parityCheckMatrix, int[] vector)
    {
        return MatrixUtility.multiply(
                parityCheckMatrix, MatrixUtility.transposeVector(vector));
    }

}
