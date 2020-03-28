import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GraphTester {

    //print colour
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    static Scanner input = new Scanner(System.in);

    static int source ;
    static int destination ;
    static String k = "k12.txt";
    static String[] arrayIndexStringEquivalents = {""};	//map human readable names to each vertex, not just array indexes
    static int vertices = 0;
    static int[][] graph;

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(BLUE + "\n          ####  MAXIMUM FLOW PROBLEM  ####" + RESET);
        consoleApp();

    }

    private static void consoleApp() throws FileNotFoundException {


        int grab;
        do{

            System.out.println("\n****************************************************");
            System.out.println("\nTo proceed select: ");
            System.out.println("  1) Compute Maxflow of given graph");
            System.out.println("  2) Edit a graph");
            System.out.println("  3) Exit ");
            System.out.print(">");
            grab = intInputValidator();

            switch (grab) {
                case 1:
                    System.out.print("\n");
                    readGraph();
                    break;

                case 2:
                    editGraph();
                    break;

                case 3:
                    System.out.println("End of the program. Good Bye!!");
                    System.exit(0);

                default:
                    System.out.println("The selected response is incorrect! Re-Enter!!");

            }
        }while (grab < 1 || grab > 3);
    }

    public static void readGraph() throws FileNotFoundException {

        int selectG;
        do{
            System.out.println("\n****************************************************");
            System.out.println("Choose the type of graph to have the maxflow: ");
            System.out.println("  1.Graph 1 [ 6 Nodes ]");
            System.out.println("  2.Graph 2 [ 12 Nodes ]");
            System.out.println("  3.Graph 3 [ 24 Nodes ]");
            System.out.println("  4.Graph 4 [ 48 Nodes ]");
            System.out.print(">");
            selectG = intInputValidator();

        }while (selectG<1 || selectG>4);



        if (selectG == 1){

            arrayIndexStringEquivalents = new String[]{"S", "1", "2", "3", "4", "T"};
            vertices = 6;
            source = 0 ;
            destination = 5 ;
            System.out.println("\n");
            k = "k1.txt";

        }else if (selectG == 2){

            arrayIndexStringEquivalents = new String[]{"S", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "T"};
            source = 0 ;
            destination = 11 ;
            vertices = 12;
            System.out.println("\n");
            k = "k2.txt";

        }else if (selectG ==3){

            arrayIndexStringEquivalents = new String[]{"S", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
                    "17", "18", "19", "20", "21", "22", "T"};
            source = 0 ;
            destination = 23 ;
            vertices = 24;
            System.out.println("\n");
            k = "k3.txt";

        }else {

            arrayIndexStringEquivalents = new String[]{"S", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
                    "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                    "40", "41", "42", "43", "44", "45", "46", "T"};
            source = 0 ;
            destination = 47 ;
            vertices = 48;
            System.out.println("\n");
            k = "k4.txt";

        }

        File file =  new File("C:\\Users\\dinis\\IdeaProjects\\FindingMaxFlow\\src\\"+k);
        Scanner scan = new Scanner(file);
        int row = scan.nextInt();
        int col = scan.nextInt();
        graph = new int[row][col];
        for(int r=0; r<row; r++) {
            for(int c=0; c<col; c++) {
                graph[r][c] = scan.nextInt();

            }
        }

        Graph g = new Graph(vertices, graph, arrayIndexStringEquivalents);

        //Watch Starts
        Stopwatch stopwatch=new Stopwatch();
        int max_flow = g.findMaxFlow(source,destination);

        //Watch Ends
        double time=stopwatch.elapsedTime();
        System.out.println("Elapsed Time : "+time);

        System.out.println(YELLOW + "Maximum flow from source S to destination T is: " + max_flow + RESET);

        consoleApp();
    }

    private static void editGraph() throws FileNotFoundException {

        int secondC;
        do{
            System.out.println("\n****************************************************");
            System.out.println("Choose your preference: ");
            System.out.println("  1) Delete an edge (link) of the graph");
            System.out.println("  2) Modify the capacity of an edge (link)");
            System.out.println("  3) Add a new graph (OPTIONAL)");
            System.out.print(">");
            secondC = intInputValidator();

        }while (secondC<1 || secondC>3);

        if (secondC == 1) {

            System.out.println("\nEnter the first edge of the corresponding link :");
            int E1 = input.nextInt();

            System.out.println("Enter the second edge of the corresponding link :");
            int E2 = input.nextInt();
            System.out.println("\n");

            Graph g = new Graph(vertices, graph, arrayIndexStringEquivalents);
            g.removeLink(E1,E2);

            //Watch Starts
            Stopwatch stopwatch=new Stopwatch();

            int max_flow = g.findMaxFlow(source,destination);

            //Watch Ends
            double time=stopwatch.elapsedTime();
            System.out.println("Elapsed Time : "+time);

            System.out.println(YELLOW + "Maximum flow from source S to destination T is: " + max_flow + RESET);

        }else if (secondC == 2){

            System.out.println("\nEnter the first edge of the corresponding link :");
            int E1 = input.nextInt();

            System.out.println("Enter the second edge of the corresponding link :");
            int E2 = input.nextInt();

            System.out.println("Enter the new capacity of link between "+ E1 + " and " + E2 + " edges :" );
            int capacity = input.nextInt();
            System.out.println("\n");

            Graph g = new Graph(vertices, graph, arrayIndexStringEquivalents);
            g.modifyLink(E1, E2, capacity);

            //Watch Starts
            Stopwatch stopwatch=new Stopwatch();

            int max_flow = g.findMaxFlow(source,destination);

            //Watch Ends
            double time=stopwatch.elapsedTime();
            System.out.println("Elapsed Time : "+time);

            System.out.println(YELLOW + "Maximum flow from source S to destination T is: " + max_flow + RESET);

        }else {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter the required number of vertices :");

            int matrixRow = scan.nextInt();
            int matrixCol = matrixRow;
            int totalRows = matrixCol * matrixRow;

            //defining 2D array to hold matrix data
            int[][] matrix = new int[matrixRow][matrixCol];

            System.out.println("Enter Matrix Data ( You should enter data " + totalRows + " times ) :");

            for (int i = 0; i < matrixRow; i++) {
                for (int j = 0; j < matrixCol; j++) {
                    matrix[i][j] = scan.nextInt();
                }
            }

            System.out.println("Your Matrix is : ");

            for (int i = 0; i < matrixRow; i++) {
                for (int j = 0; j < matrixCol; j++) {
                    System.out.print(matrix[i][j] + "\t");
                }

                System.out.println();
            }
        }
        consoleApp();
    }


    private static int intInputValidator() {  //validating the input in the main menu
        while (!input.hasNextInt()) {
            System.out.println("Please Enter a numeric response from the given options!!");
            System.out.print(">");
            input.next();
        }
        return input.nextInt();
    }
}
