import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
    	boolean visited[] = new boolean[adj.length];
    	// int 1 for white and 2 for black
    	int color[] = new int[adj.length];
    	Queue<Integer> queue = new LinkedList<>();
    	if (adj == null || adj.length == 0)
    		return 0;
    	visited[0] = true;
    	color[0] = 1;
    	queue.add(0);
    	while (!queue.isEmpty()) {
    		int vertex = queue.poll();
    		for (int ele:adj[vertex]) {
    			if (visited[ele]) {
    				if (color[vertex] == color[ele])
    					return 0;
    			}
    			else {
    				visited[ele] = true;
    				queue.add(ele);
    				color[ele] = (color[vertex] == 1) ? 2 : 1;
    			}
    		}
    	}
        return 1;
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
        System.out.println(bipartite(adj));
    }
}

