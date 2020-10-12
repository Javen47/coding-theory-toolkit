import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException
    {
        Main main = new Main();
        Scanner reader = new Scanner(System.in);

        main.printOptions();
        main.promptInputOptions(reader);
    }

    private void promptInputOptions(Scanner reader) throws IOException
    {
        Main main = new Main();
        int selectedOption = reader.nextInt();

        switch (selectedOption)
        {
            case 1:
                optionOne(reader);
                break;
            case 2:
                optionTwo(reader);
                break;
            case 3:
                optionThree(reader);
                break;
            case 4:
                optionFour(reader);
                break;
            case 5:
                optionFive(reader);
                break;
            case 6:
                optionSix(reader);
                break;
            case 7:
                optionSeven(reader);
                break;
            case 8:
                optionEight(reader);
                break;
            case 9:
                optionNine(reader);
                break;
            case 10:
                optionTen(reader);
                break;
            case 11:
                optionEleven(reader);
                break;
            case 12:
                optionTwelve(reader);
                break;
            case 13:
                optionThirteen(reader);
                break;
            case 14:
                optionFourteen(reader);
                break;
            case 15:
                optionFifteen(reader);
                break;
            case 16:
                optionSixteen(reader);
                break;
            case 17:
                optionSeventeen(reader);
                break;
            case 18:
                optionEighteen(reader);
                break;
            case 19:
                optionNineteen(reader);
                break;
            case 20:
                optionTwenty(reader);
                break;
            case 21:
                optionTwentyOne(reader);
                break;
            case 22:
                optionTwentyTwo(reader);
                break;
            case 23:
                optionTwentyThree(reader);
                break;
            case 24:
                optionTwentyFour(reader);
                break;
            case 25:
                optionTwentyFive(reader);
                break;
            case 26:
                optionTwentySix(reader);
                break;
            case 27:
                optionTwentySeven(reader);
                break;
            case 28:
                optionTwentyEight(reader);
                break;
            default:
                main.printOptions();
                main.promptInputOptions(reader);
                break;
        }
    }

    private void printOptions()
    {
        System.out.println("\n\nWhat would you like to do?\n-");

        System.out.println("1. Calculate the probability of decoding a received hamming codeword correctly");
        System.out.println("2. Given a PCM and a received vector, determine original codeword using syndrome decoding");
        System.out.println("3. Determine if the given parameters [n, k, d] are the parameters of an MDS code");
        System.out.println("4. Determine if the given parameters [n, k, d]q imply the existence of a code by satisfying the VG bound");
        System.out.println("5. Determine if the given parameters [n, k, d] correspond to a given packing radius");
        System.out.println("6. Calculate the covering radius of a binary PCM");
        System.out.println("7. Generate the cosets for a PCM");
        System.out.println("8. Calculate combination");
        System.out.println("9. Determine if the given parameters [n, k, d]q violate the SP bound");
        System.out.println("10. Determine if a given vector can be the coset leader of a PCM");
        System.out.println("11. Calculate the probability of correctly using syndrome decoding on a received codeword");
        System.out.println("12. Determine if given vectors belong to a binary linear code");
        System.out.println("13. Determine which of the given vectors belong to the sphere with a given radius and center");
        System.out.println("14. Find the minimum weight of the BLC with a generator matrix");
        System.out.println("15. Find the number of codewords of a given weight in a BLC generator matrix");
        System.out.println("16. Determine if a given matrix is a generator matrix of a binary doubly-even self-orthogonal code");
        System.out.println("17. Transpose a matrix");
        System.out.println("18. Calculate syndrome");
        System.out.println("19. Determine which of the given pairs of vectors belong to the same coset given a PCM");
        System.out.println("20. Determine which of the given pairs of vectors have the same syndrome with respect to a PCM");
        System.out.println("21. Determine if a given parity check matrix is of a ternary perfect code");
        System.out.println("22. Determine if the given parameters are of a Reed-Miller code");
        System.out.println("23. Suppose that you have to choose a Reed-Muller code R(r,m) of smallest possible length that encodes a given number of info symbols and corrects a given number of errors");
        System.out.println("24. Determine the parameters [n, k, d] of a given order RM code with a given length");
        System.out.println("25. Determine the parameters [n, k, d] of the dual code of a given RM code");
        System.out.println("26. Determine if the given parameters are of a self-dual RM code");
        System.out.println("27. Create a generator matrix for a given RM code");
        System.out.println("28. Determine if a matrix is a generator matrix of a RM code");
    }

    private void optionOne(Scanner reader)
    {
        System.out.println("What is the length of the codeword?");
        int length = reader.nextInt();

        System.out.println("What is the single bit error probability?");
        double p = reader.nextDouble();

        double result = CodeUtility.calculateProbabilityOfCorrectHammingCodeDecoding(length, p);
        System.out.println("Result: " + result);
    }

    private void optionTwo(Scanner reader) throws IOException
    {
        determineOriginalCodewordUsingSyndromeDecoding(reader);
    }

    private void optionThree(Scanner reader) throws IOException
    {
        int[][] parameters = promptInputNKDParameters(reader);
        for (int[] i : parameters)
        {
            if (CodeUtility.determineIfParametersAreOfMDSCode(i[0], i[1], i[2]))
            {
                System.out.format("\n[%d, %d, %d] are the parameters of an MDS code", i[0], i[1], i[2]);
            }
        }
    }

    private void optionFour(Scanner reader) throws IOException
    {
        int[][] parameters = promptInputNKDQParameters(reader);
        for (int[] i : parameters)
        {
            if (CodeUtility.determineIfCodeExistsWithinVGBound(i[0], i[1], i[2], i[3]))
            {
                System.out.format("\n[%d, %d, %d]%d implies the existence of a code", i[0], i[1], i[2], i[3]);
            }
        }
    }

    private void optionFive(Scanner reader) throws IOException
    {
        System.out.println("What is the packing radius?");
        int packingRadius = reader.nextInt();

        int[][] parameters = promptInputNKDParameters(reader);
        for (int[] i : parameters)
        {
            if (CodeUtility.calculatePackingRadius(i[2]) == packingRadius)
            {
                System.out.format("\n[%d, %d, %d] correspond to the packing radius", i[0], i[1], i[2]);
            }
        }
    }

    private void optionSix(Scanner reader) throws IOException
    {
        System.out.println("For the Parity Check Matrix: ");
        int[][] PCM = promptInputMatrix(reader);
        int coveringRadius = CodeUtility.calculateCoveringRadius(PCM);
        System.out.println("The covering radius is: " + coveringRadius);
    }

    private void optionSeven(Scanner reader) throws IOException
    {
        System.out.println("For the Parity Check Matrix: ");
        int[][] PCM = promptInputMatrix(reader);
        for (int[] cosetRep : CodeUtility.cosetReps8)
        {
            MatrixUtility.printVector(cosetRep);
            System.out.println("---");
            int[][] coset = CodeUtility.generateCoset(PCM, cosetRep);
            MatrixUtility.printMatrix(coset);
            MatrixUtility.printVector(CodeUtility.findCosetLeader(coset));
            System.out.println(" - Leader");
        }
    }

    private void optionEight(Scanner reader)
    {
        System.out.println("What is your n?");
        int n = reader.nextInt();

        System.out.println("What is your r?");
        int r = reader.nextInt();

        long result = CodeUtility.combination(n, r);
        System.out.println("Result: " + result);
    }

    private void optionNine(Scanner reader) throws IOException
    {
        int[][] parameters = promptInputNKDQParameters(reader);
        for (int[] i : parameters)
        {
            if (!CodeUtility.determineIfCodeExistsWithinSPBound(i[0], i[1], i[2], i[3]))
            {
                System.out.format("\n[%d, %d, %d]%d violates the SP bound", i[0], i[1], i[2], i[3]);
            }
        }
    }

    private void optionTen(Scanner reader) throws IOException
    {
        System.out.println("Current believed solution: if the syndrome of a vector doesn't match any columns of the PCM, then it is the coset leader");
        calculateSyndromes(reader);
    }

    private void optionEleven(Scanner reader) throws IOException
    {
        System.out.println("For the parity check matrix");
        int[][] PCM = promptInputMatrix(reader);

        System.out.println("What is the single bit error probability?");
        double p = reader.nextDouble();

        double result = CodeUtility.calculateProbabilityOfCorrectSyndromeDecoding(PCM, p);
        System.out.println("Result: " + result);
    }

    private void optionTwelve(Scanner reader) throws IOException
    {
        System.out.println("For the parity check matrix");
        int[][] PCM = promptInputMatrix(reader);

        int[][] vectors = promptInputVectors(reader);

        for (int i = 0; i < vectors.length; i++)
        {
            if (CodeUtility.determineIfVectorBelongsToBLC(PCM, vectors[i]))
            {
                MatrixUtility.printVector(vectors[i]);
                System.out.print(" belongs to the binary linear code");
            }
        }
    }

    private void optionThirteen(Scanner reader) throws IOException
    {
        System.out.println("What is the radius?");
        int radius = reader.nextInt();

        System.out.println("For the center vector");
        int[] center = promptInputVector(reader);

        System.out.println("For the vector options");
        int[][] options = promptInputVectors(reader);

        for(int[] i : options)
        {
            if (CodeUtility.calculateDistanceBetweenVectors(i, center) <= radius)
            {
                MatrixUtility.printVector(i);
                System.out.print(" belongs to the sphere");
            }
        }
    }

    private void optionFourteen(Scanner reader) throws IOException
    {
        System.out.println("For the generator matrix");
        int[][] generator = promptInputMatrix(reader);

        int[][] coset = CodeUtility.generateCoset(generator, CodeUtility.cosetReps8[0]);
        int minWeight = CodeUtility.calculateVectorWeight(CodeUtility.findCosetLeader(coset));

        System.out.println("Minimum weight: " + minWeight);
    }

    private void optionFifteen(Scanner reader) throws IOException
    {
        System.out.println("What is the weight?");
        int weight = reader.nextInt();

        System.out.println("For the generator matrix");
        int[][] generator = promptInputMatrix(reader);

        int[][] coset = CodeUtility.generateCoset(generator, CodeUtility.cosetReps8[0]);
        int count = 0;

        for (int[] i : coset)
        {
            if (CodeUtility.calculateVectorWeight(i) == weight) count++;
        }

        System.out.println("Number of codewords with given weight: " + count);
    }

    private void optionSixteen(Scanner reader) throws IOException
    {
        System.out.println("For the generator matrix");
        int[][] generator = promptInputMatrix(reader);

        boolean selfOrthogonal = CodeUtility.determineIfSelfOrthogonal(generator);
        boolean doublyEven = CodeUtility.determineIfDoublyEven(generator);

        if (selfOrthogonal && doublyEven)
        {
            System.out.println("Matrix is of a binary doubly-even self-orthogonal code");
        } else
        {
            System.out.println("Matrix is NOT");
        }
    }

    private void optionSeventeen(Scanner reader) throws IOException
    {
        int[][] matrix = promptInputMatrix(reader);
        MatrixUtility.printMatrix(MatrixUtility.transpose(matrix));
    }

    private void optionEighteen(Scanner reader) throws IOException
    {
        System.out.println("For the matrix");
        int[][] matrix = promptInputMatrix(reader);

        System.out.println("For the vector");
        int[] vector = promptInputVector(reader);

        MatrixUtility.printMatrix(CodeUtility.calculateSyndrome(matrix, vector));
    }

    private void optionNineteen(Scanner reader) throws IOException
    {
        System.out.println("Current believed solution: if two syndromes match, then the respective vectors are in the same coset");
        calculateSyndromes(reader);
    }

    private void optionTwenty(Scanner reader) throws IOException
    {
        calculateSyndromes(reader);
    }

    private void optionTwentyOne(Scanner reader) throws IOException
    {
        System.out.println("For the parity check matrix");
        int[][] PCM = promptInputMatrix(reader);

        boolean result = CodeUtility.determineIfTernaryPerfectCode(PCM);
        System.out.println("Is ternary perfect code: " + result);
    }

    private void optionTwentyTwo(Scanner reader) throws IOException
    {
        int[][] parameters = promptInputNKDParameters(reader);
        for (int[] i : parameters)
        {
            if (CodeUtility.determineIfParametersAreOfReedMullerCode(i[0], i[1], i[2]))
            {
                MatrixUtility.printVector(i);
                System.out.println(" are of a RM code");
            }
        }
    }

    private void optionTwentyThree(Scanner reader) throws IOException
    {
        System.out.println("How many info symbols does it encode?");
        int infoSymbols = reader.nextInt();

        System.out.println("How many errors can it correct?");
        int numOfCorrectingErrors = reader.nextInt();

        MatrixUtility.printVector(CodeUtility.calculateMinimalRMCode(infoSymbols, numOfCorrectingErrors));
    }

    private void optionTwentyFour(Scanner reader) throws IOException
    {
        System.out.println("What is the order of the RM code?");
        int order = reader.nextInt(); //r

        System.out.println("What is the length of the RM code?");
        int length = reader.nextInt(); //n

        int[][] options = promptInputNKDParameters(reader);

        for (int[] i : options)
        {
            if (CodeUtility.determineIfParametersAreOfROrderRMCode(
                    i[0], i[1], i[2], order, length
            ))
            {
                MatrixUtility.printVector(i);
                System.out.println("is of the given order RM code");
            }
        }
    }

    private void optionTwentyFive(Scanner reader) throws IOException
    {
        System.out.println("What is r ?");
        int r = reader.nextInt();

        System.out.println("What is m ?");
        int m = reader.nextInt();

        int[][] options = promptInputNKDParameters(reader);

        for (int[] i : options)
        {
            if (CodeUtility.determineIfParametersAreOfDualRMCode(
                    i[0], i[1], i[2], r, m
            ))
            {
                MatrixUtility.printVector(i);
                System.out.println("is of the dual RM code");
            }
        }
    }

    private void optionTwentySix(Scanner reader) throws IOException
    {
        int[][] options = promptInputNKDParameters(reader);

        for (int[] i : options)
        {
            if (CodeUtility.determineIfParametersAreOfSelfDualRMCode(
                    i[0], i[1], i[2]
            ))
            {
                MatrixUtility.printVector(i);
                System.out.println("is of a self-dual RM code");
            }
        }
    }

    private void optionTwentySeven(Scanner reader) throws IOException
    {
        System.out.println("What is r ?");
        int r = reader.nextInt();

        System.out.println("What is m ?");
        int m = reader.nextInt();

        CodeUtility.createGeneratorMatrixOfRMCode(r, m, true);
    }

    private void optionTwentyEight(Scanner reader) throws IOException
    {
        int[][] matrix = promptInputMatrix(reader);

        System.out.println(CodeUtility.determineIfGeneratorMatrixIsOfRMCode(matrix));
    }

    private void calculateSyndromes(Scanner reader) throws IOException
    {
        System.out.println("For the parity check matrix");
        int[][] PCM = promptInputMatrix(reader);

        int[][] vectors = promptInputVectors(reader);

        for (int[] i : vectors)
        {
            int[][] syndrome = CodeUtility.calculateSyndrome(PCM, i);
            MatrixUtility.printVector(i);
            System.out.println(": ");
            MatrixUtility.printMatrix(syndrome);
        }
    }

    private void determineOriginalCodewordUsingSyndromeDecoding(Scanner reader) throws IOException
    {
        System.out.println("For the Parity Check Matrix:");
        int[][] PCM = promptInputMatrix(reader);
        System.out.println("For the received vector:");
        int[] vector = promptInputVector(reader);

        System.out.println("Original codeword: ");
        MatrixUtility.printVector(CodeUtility.determineOriginalCodewordUsingSyndromeDecoding(PCM, vector));
    }

    private int[][] promptInputRMParameters(Scanner reader) throws IOException
    {
        System.out.println("How many [r, m] parameter sets are there?");
        int num = reader.nextInt();

        int[][] parameters = new int[num][2];

        for (int i = 0; i < num; i++)
        {
            System.out.println((i + 1) + ".) r = ? : ");
            parameters[i][0] = reader.nextInt();

            System.out.println((i + 1) + ".) m = ? : ");
            parameters[i][1] = reader.nextInt();
        }

        return parameters;
    }

    private int[][] promptInputNKDParameters(Scanner reader) throws IOException
    {
        System.out.println("How many [n, k, d] parameter sets are there?");
        int numberOfParameters = reader.nextInt();

        int[][] parameters = new int[numberOfParameters][3];

        for (int i = 0; i < numberOfParameters; i++)
        {
            System.out.println((i + 1) + ".) n = ? : ");
            parameters[i][0] = reader.nextInt();

            System.out.println((i + 1) + ".) k = ? : ");
            parameters[i][1] = reader.nextInt();

            System.out.println((i + 1) + ".) d = ? : ");
            parameters[i][2] = reader.nextInt();
        }

        return parameters;
    }

    private int[][] promptInputNKDQParameters(Scanner reader) throws IOException
    {
        System.out.println("How many [n, k, d]q parameter sets are there?");
        int numberOfParameters = reader.nextInt();

        int[][] parameters = new int[numberOfParameters][4];

        for (int i = 0; i < numberOfParameters; i++)
        {
            System.out.println((i + 1) + ".) n = ? ");
            parameters[i][0] = reader.nextInt();

            System.out.println((i + 1) + ".) k = ? ");
            parameters[i][1] = reader.nextInt();

            System.out.println((i + 1) + ".) d = ? ");
            parameters[i][2] = reader.nextInt();

            System.out.println((i + 1) + ".) q = ? ");
            parameters[i][3] = reader.nextInt();
        }

        return parameters;
    }

    private int[] promptInputVector(Scanner reader) throws IOException
    {
        System.out.println("What is the length of the vector?");
        int length = reader.nextInt();

        int[] vector = new int[length];

        for (int j = 0; j < length; j++)
        {
            System.out.println("Value at index[" + j + "]? ");
            vector[j] = reader.nextInt();
        }

        return vector;
    }

    private int[][] promptInputVectors(Scanner reader) throws IOException
    {
        System.out.println("How many vectors are there?");
        int rows = reader.nextInt();

        System.out.println("What is the length of each vector?");
        int columns = reader.nextInt();

        int[][] vectors = new int[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.println((i + 1) + ".) Value at index[" + j + "]? ");
                vectors[i][j] = reader.nextInt();
            }
        }

        return vectors;
    }

    private int[][] promptInputMatrix(Scanner reader) throws IOException
    {
        System.out.println("How many rows are in the matrix?: ");
        int rows = reader.nextInt();

        System.out.println("How many columns are in the matrix?: ");
        int columns = reader.nextInt();

        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.println("Value at entry[" + i + "][" + j + "]?: ");
                matrix[i][j] = reader.nextInt();
            }
        }

        return matrix;
    }

}
