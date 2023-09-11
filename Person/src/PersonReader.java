import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class PersonReader {

    public static void main(String[] args) {

        // Use JFileChooser to choose the record
        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(null);

        boolean done = false;

        Scanner in = new Scanner(System.in);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try {
                Scanner input = new Scanner(file);
                System.out.println("ID#\tFirstname\tLastname\tTitle\tYOB");
                System.out.println("======================================");

                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] information = line.split(",");

                    if (information.length == 5) { // Check if there are 5 elements
                        System.out.printf("%s\t%s\t%s\t%s\t%s\n", information[0], information[1], information[2], information[3], information[4]);
                    }

                    else {
                        System.out.println("Invalid record: " + line);
                    }

                }

                input.close();
            }

            catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }

        }

        else {
            System.out.println("No file selected.");
        }

        if (SafeInput.getYNConfirm(in, "Are you finished?")) done = true;
        else done = false;

    }

}
