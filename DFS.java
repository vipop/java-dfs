import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Iterator;

public class DFS
{

    // stack
    private static Stack<String> stack;
    // visited set
    private static HashSet<String> visited;
    // Adjacency List
    private static HashMap<String,HashSet<String>> aList;
    // array holding the labels of the vertices
    private static HashSet<String> vertices;

    public static void main(String[] args)
    {
        // INITIALIZE
        stack = new Stack<String>();
        visited = new HashSet<String>();
        aList = new HashMap<String,HashSet<String>>();
        vertices = new HashSet<String>();

        // READ INPUT FILE
        int input = readInput();
        if (input == 1)
        {
            System.out.println("");
            System.exit(0);
        }

        // TRAVERSE ALL GRAPHS
        for (String vertex : vertices)
        {
            if (!visited.contains(vertex))
            {
                traverse(vertex);
            }
        }

    }

    /**
    * Read the input file
    * return 0 if ok 1 if something went wrong
    **/
    public static int readInput()
    {

        // make a scanner for the input file
        Scanner scanner = new Scanner(System.in);
        // get number of vertices
        int numVerti = scanner.nextInt();
        if (numVerti == 0) return 1;
        // go to next line
        scanner.nextLine();
        // read the labels of the vertices
        for (int i = 0; i < numVerti; i++)
        {
            vertices.add(scanner.next());
        }
        scanner.nextLine();
        while (scanner.hasNextLine())
        {

            // save the input line
            String line = scanner.nextLine();
            // make a scanner over the saved input line
            Scanner lineScanner = new Scanner(line);
            // save the main vertex
            String vertex = lineScanner.next();
            // skip the "|"
            lineScanner.next();
            // make a set for all the vertices the main vertex is connected to
            HashSet<String> connected = new HashSet<String>();
            // fill out the set of vertices
            while(lineScanner.hasNext())
            {
                // save the vertex
                String verti = lineScanner.next();
                // add it to the set
                connected.add(verti);
            }
            // add the vertex and its set of connected vertices to the HashMap
            aList.put(vertex,connected);
        }

        return 0;
    }

    /**
    * Traverse a graph starting at a specific vertex
    * @param start the starting vertex
    **/
    public static void traverse(String start)
    {
        stack.push(start);
        // while the stack is not empty
        while(!stack.empty())
        {
            // look at the top of the stack
            String top = stack.pop();
            // if the top has not yet been visited
            if (!visited.contains(top))
            {
                // print the vertex
                System.out.println(top);
                // add it to the set of visited vertices
                visited.add(top);
                // retrieve its adjacent vertices
                HashSet<String> connected = aList.get(top);
                // find an adjacent vertex that has not been visited yet
                // and push it on the stack
                Iterator<String> iterator = connected.iterator();
                boolean pushed = false;
                while (iterator.hasNext() && !pushed)
                {
                    // retrieve adjacent vertex
                    String adjV = iterator.next();
                    // if this vertex has not been visited before
                    if (!visited.contains(adjV))
                    {
                        // push it on to the stack
                        stack.push(adjV);
                        pushed = true;
                    }
                }
            }
        }
    }

}
