import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
    	
    	//Initialization of Queue, visited boolean array and dist array 
    	boolean visited[] = new boolean[adj.length];
    	int dist[] = new int[adj.length];
    	Queue<Integer> queue = new LinkedList<>();
    	for (int index=0; index<dist.length; index++) {
    		dist[index] = -1;
    	}
    	dist[s] = 0;
    	visited[s] = true;
    	queue.add(s);
    	
    	while (!queue.isEmpty()) {
    		int vertex = queue.poll();
    		for (int ele:adj[vertex]) {
    			if (!visited[ele]) {
    				queue.add(ele);
    				visited[ele] = true;
    				dist[ele] = dist[vertex] + 1;
    			}
    		}
    	}
        return dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

