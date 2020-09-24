public class CodeUtility {

    public static void probabilityOfCorrectDecoding(int codeLength, double singleBitErrorProbability)
    {
        double calculation = Math.pow( 1 - singleBitErrorProbability, codeLength) +
                codeLength * singleBitErrorProbability * Math.pow(1 - singleBitErrorProbability, codeLength - 1);
        System.out.println(calculation);
    }

    public static int calculateVectorWeight(int[] vector)
    {
        int count = 0;
        for (int i : vector) {
            if (i == 1) count++;
        }
        return count;
    }

    //TODO: current implementation assumes there will be 3 rows in input matrix -> 8 rows in coset
    public static int[][] generateCoset(int[][] matrix, int[] cosetRep)
    {
        int[][] coset = new int[7][cosetRep.length];

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

    public static int calculateNumberOfCosets(int[][] matrix)
    {
        return (int) Math.pow(2, matrix[0].length - matrix.length);
    }

    public static void determineOriginalCodeword(int[][] parityCheckMatrix, int[][] vector)
    {
        int[][] syndrome = calculateSyndrome(parityCheckMatrix, vector);

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
                vector[0][incorrectBitPosition] = vector[0][incorrectBitPosition] == 1 ? 0 : 1;
                MatrixUtility.printMatrix(vector);
                break;
            }

        }
    }

    private static int[][] calculateSyndrome(int[][] parityCheckMatrix, int[][] vector)
    {
        return MatrixUtility.multiply(
                parityCheckMatrix, MatrixUtility.transpose(vector));
    }

}
