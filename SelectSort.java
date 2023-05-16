// Importing
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The program uses selection sort.
 *
 * @author Adrijan Vranjkovic
 * @version 1.0
 * @since 2023-05-16
 */

public final class SelectSort {

    /**
     * For style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private SelectSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused
     */
    public static void main(String[] args) {

        // Create a list to hold the input arrays
        final ArrayList<int[]> inputList = new ArrayList<>();

        try {
            // Create input/output file and scanner
            final File input = new File("input.txt");
            final Scanner scanInput = new Scanner(input);
            final FileWriter output = new FileWriter("output.txt");

            int lineNumber = 1;

            while (scanInput.hasNextLine()) {

                // Cut off any trailing white spaces
                final String line = scanInput.nextLine().trim();

                // Check for blank line
                if (line.isEmpty()) {
                    output.write("Invalid input on line " + lineNumber + "\n");
                } else {

                    // Parse the input line and store the array
                    final int[] arrOfInt = parseInputLine(line);

                    if (arrOfInt != null) {
                        inputList.add(arrOfInt);
                    } else {
                        // Display there is invalid input.
                        output.write("Invalid input on line "
                            + lineNumber + "\n");
                    }
                }

                lineNumber++;
            }

            // Call selection sort function
            for (int[] arrOfInt : inputList) {
                selectionSort(arrOfInt);
                output.write(Arrays.toString(arrOfInt) + "\n");
            }

            // Close output
            output.close();

        } catch (IOException err) {
            // Display error
            System.err.println("Error: " + err.getMessage());
        }
    }

    /**
     * This function uses selection sort.
     *
     * @param array *
     */
    private static void selectionSort(int[] array) {
        final int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                final int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    /**
     * This function prepares the input for the sorting.
     *
     * @param line *
     * @return arrOfInt
     */
    private static int[] parseInputLine(String line) {
        final String[] numStrings = line.split(" ");
        final int[] arrOfInt = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            try {
                arrOfInt[i] = Integer.parseInt(numStrings[i]);
            } catch (NumberFormatException fnfex) {
                // Return null if it's not an integer
                return null;
            }
        }
        return arrOfInt;
    }
}
