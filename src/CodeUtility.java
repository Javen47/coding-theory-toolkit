public class CodeUtility {

    public static void probabilityOfCorrectDecoding(int codeLength, double singleBitErrorProbability)
    {
        double calculation = Math.pow( 1 - singleBitErrorProbability, codeLength) +
                codeLength * singleBitErrorProbability * Math.pow(1 - singleBitErrorProbability, codeLength - 1);
        System.out.println(calculation);
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
