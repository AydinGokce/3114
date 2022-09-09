
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rectangle1 {

    public static void main(String[] args) throws FileNotFoundException {

        // instantiate file and scanner
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);

        // instantiate skip list
        SkipList<String, Coordinates> skipList = new SkipList<String, Coordinates>();

        // iterate over every line in the file
        while (scanner.hasNext()) {

            // parse the command and arguments to an array of strings
            String nextLine = scanner.nextLine();
            String[] command = parseCommand(nextLine);

            if (command.length > 0 && !command[0].equals("")) {

                if (command[0].equals("insert")) {
                    try {
                        Rectangle rect = new Rectangle(command[1],
                            new Coordinates(intify(command[2]), intify(command[3]),
                                intify(command[4]), intify(command[5])));
                        skipList.insert(rect);
                        System.out.println("Rectangle inserted: (" + rect
                            .toString() + ")");

                    }
                    catch (Exception e) {
                        Rectangle rect = new Rectangle(command[1],
                            new Coordinates(intify(command[2]), intify(command[3]),
                                intify(command[4]), intify(command[5]),
                                "override"));
                        System.out.println("Rectangle rejected: (" + rect
                            .toString() + ")");
                    }

                }
                else if (command[0].equals("dump")) {
                    skipList.dump();
                }
                else if (command[0].equals("remove") && command.length == 2) {
                    Rectangle result = (Rectangle)skipList.removeByKey(command[1]);
                    if(result != null) {
                        System.out.println("Rectangle removed: (" + result.toString() + ")");
                    }
                    else {
                        System.out.println("Rectangle not removed: (" + command[1] + ")");
                    }
                }
                else if (command[0].equals("remove") && command.length == 5) {
                    Coordinates coords = null;
                    try {
                        coords = new Coordinates(intify(command[1]), intify(command[2]), intify(command[3]), intify(command[4]));
                    }
                    catch(Exception e) {
                        System.out.println("rectangle rejected: (" + command[1] + ", " + command[2]  + ", " + command[3]  + ", " + command[4] + ")");
                        continue;
                    }
                    Rectangle result = (Rectangle)skipList.removeByCoordinates(coords);
                    if(result != null) {
                        System.out.println();
                    }
                    else {
                        System.out.println("Rectangle not removed: (" + command[1] + ", " + command[2]  + ", " + command[3]  + ", " + command[4]+ ")");
                    }
                    
                }
                else if (command[0].equals("regionsearch")) {
                    Coordinates coords;
                    try {
                        coords = new Coordinates(intify(command[1]), intify(command[2]), intify(command[3]), intify(command[4]), "regionSearch");
                        
                    }
                    catch (Exception e) {
                        coords = new Coordinates(intify(command[1]), intify(command[2]), intify(command[3]), intify(command[4]), "override");
                        System.out.println("Rectange rejected: (" + coords.toString() + ")");
                        continue;
                    }
                    String regionResults = skipList.regionSearch(coords);
                    System.out.println("Rectangles intersecting region (" + coords.toString() + "):");
                    if(regionResults.length() != 0) {
                        System.out.println(regionResults);
                    }
                }
                else if (command[0].equals("intersections")) {
                    System.out.println("Intersections pairs:");
                    String results = skipList.intersection();
                    if(results.length() > 0) {
                        System.out.println(results);
                    }
                }
                else if (command[0].equals("search")) {
                    KVPair[] result = skipList.search(command[1]);
                    if(result.length == 0 || result[0] == null) {
                        System.out.println("Rectangle not found: " + command[1]);
                    }
                    else {
                        System.out.println("Rectangles found: ");
                        int arrIndex = 0;
                        while(result[arrIndex] != null) {
                            System.out.println(result[arrIndex].toString());
                            arrIndex++;
                        }
                    }
                }
                else {
                    throw new IllegalArgumentException("Illegal command: "
                        + command[0] + " (length " + command.length + ")");
                }
            }
        }

        scanner.close();
    }


    // given string, return array of commands
    private static String[] parseCommand(String command) {

        // trim and split against whitespaces
        return command.trim().split("\\s+");
    }


    // to make the code neater
    private static int intify(String s) {
        return Integer.parseInt(s);
    }

}
