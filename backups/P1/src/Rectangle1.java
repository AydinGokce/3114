
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rectangle1 {

    public static void main(String[] args) throws FileNotFoundException {

        // instantiate file and scanner
        File file = new File(args[1]);
        Scanner scanner = new Scanner(file);

        // instantiate skip list
        SkipList<String, Coordinates> skipList = new SkipList();

        // iterate over every line in the file
        while (scanner.hasNext()) {

            // parse the command and arguments to an array of strings
            String[] command = parseCommand(scanner.next());
            Rectangle rect = new Rectangle(command[1], new Coordinates(
                Integer.parseInt(command[2]), Integer.parseInt(command[3]),
                Integer.parseInt(command[4]), Integer.parseInt(
                    command[5])));
            if (command[0] == "insert") {
                if (skipList.insert(rect)) {
                        System.out.print("Rectangle inserted: ");
                }
                else {
                    System.out.print("Rectangle rejected: ");
                }
                System.out.print(rect.toString());
            }
            else if (command[0] == "dump") {

            }
            else if (command[0] == "remove" && command.length == 2) {

            }
            else if (command[0] == "remove" && command.length == 5) {

            }
            else if (command[0] == "regionsearch") {

            }
            else if (command[0] == "intersections") {

            }
            else if (command[0] == "search") {

            }
            else {
                throw new IllegalArgumentException("Illegal command: "
                    + command[0] + " (length " + command.length + ")");
            }
        }

        scanner.close();
    }


    // given string, return array of commands
    private static String[] parseCommand(String command) {

        // trim and split against whitespaces
        return command.trim().split("\\s+");
    }

}
