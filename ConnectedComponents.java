import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        //write your code here
    	int vertices = adj.length;
    	boolean[] visited = new boolean[vertices];
    	for (int index=0; index<vertices; index++) {
    		if (!visited[index]) {
    			result++;
    	    	explore(adj, visited, index);
    		}
    	}
        return result;
    }

    private static void explore(ArrayList<Integer>[] adj, boolean[] visited, int x) {
    	visited[x] = true;
    	for (int neighbours: adj[x]) {
    		if (!visited[neighbours])
    			explore(adj, visited, neighbours);
    	}
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

