import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {

        ArrayList<String> products = new ArrayList<>();
        boolean done = false;

        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + File.separator + "src" + File.separator + "productData.txt");

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name: ");
            String description = SafeInput.getNonZeroLenString(in, "Enter a short description: ");
            double cost = SafeInput.getDouble(in, "Enter cost: ");

            String productRec = ID + ", " + name + ", " + description + ", " + cost;
            products.add(productRec);

            done = SafeInput.getYNConfirm(in, "Are you finished?");

        } while (!done);

        for (String product : products)
            System.out.println(product);

        try {
            // Open a BufferedWriter to write the file
            BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE);

            // Write each product record to the file
            for (String rec : products) {
                writer.write(rec);
                writer.newLine();  // Add a new line after each record
            }

            writer.close(); // Close the writer to flush the buffer
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the scanner
        in.close();
    }
}
