import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SSAD {

    static void printArray(String[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    static Graph parseGraph(RandomAccessFile inputFile) throws IOException {
        String currLine;
        // TODO: implement while loop to skip linebreaks

        // parse number of vertices from line 1
        currLine = inputFile.readLine();
        int numVertices = Integer.parseInt(currLine.split(":")[1].trim());

        // parse start vertex from line 2
        currLine = inputFile.readLine();
        int startVertexIndex = Integer.parseInt(currLine.split(":")[1].trim());

        // skip linebreak
        inputFile.readLine();

        // skip index headers
        inputFile.readLine();

        // skip dashed line separator
        inputFile.readLine();

        // set line pointer to first row in table
        currLine = inputFile.readLine();
        int vertexCounter = 0;

        // instantiate graph
        Graph graph = new Graph(numVertices, startVertexIndex);

        // begin reading lines
        while (currLine != null) {

            // TODO: use row beginner as index rather than order (low priority)
            // System.out.println("line (len " + currLine.length() + ") :" +
            // currLine);

            String[] adjacencyList = currLine.split("\\|")[1].trim().split(
                " {1,}|	{1,}|\n");

            for (int i = 0; i < adjacencyList.length; i++) {
                int distance = Integer.parseInt(adjacencyList[i]);
                if (distance > 0) {
                    graph.addEdge(vertexCounter, i, distance);
                }
            }

            vertexCounter++;
            currLine = inputFile.readLine();
        }

        return graph;
    }


    static void writeGraph(RandomAccessFile outputFile, Graph graph)
        throws IOException {
        outputFile.writeBytes(graph.toString());
    }


    public static void main(String[] args) throws IOException {
        String inputFileName = args[0];
        String outputFileName = args[1];

        RandomAccessFile inputFile = new RandomAccessFile(inputFileName, "rw");
        RandomAccessFile outputFile = new RandomAccessFile(outputFileName,
            "rw");

        Graph graph = parseGraph(inputFile);

        writeGraph(outputFile, graph);

        inputFile.close();
        outputFile.close();
    }

}