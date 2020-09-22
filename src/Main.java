import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException
    {
        Main main = new Main();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        main.printOptions();
        int selectedOption = reader.read();
        switch (selectedOption)
        {
            case 1:
                break;
            case 2:
                System.out.println("For the Parity Check Matrix: \n");
                int[][] PCM = main.promptInputMatrix(reader);
                System.out.println("For the received vector: \n");
                int[][] vector = main.promptInputMatrix(reader);
                CodeUtility.determineOriginalCodeword(PCM, vector);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                main.printOptions();
                break;
        }

        //System.out.println("\nResult: ");
    }

    private void printOptions()
    {
        System.out.println("What would you like to do?\n-\n");
        System.out.println("1. Calculate probability of decoding received codeword correctly");
        System.out.println("2. Given a PCM and a received vector, determine original codeword");
    }

    private int[][] promptInputMatrix(BufferedReader reader) throws IOException
    {
        System.out.println("How many rows are in the matrix?: ");
        int rows = reader.read();

        System.out.println("How many columns are in the matrix?: ");
        int columns = reader.read();

        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.println("Value at entry[" + i + "][" + j + "]?: ");
                matrix[i][j] = reader.read();
            }
        }

        return matrix;
    }

}
