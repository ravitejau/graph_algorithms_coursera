import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StronglyConnected {
	
	private static int counter=0;
	private static int[] preOrder;
	private static int[] postOrder;
	
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
    	int result = 0;
    	ArrayList<Integer>[] revAdj = (ArrayList<Integer>[])new ArrayList[adj.length];
    	preOrder = new int[adj.length];
    	postOrder = new int[adj.length];
    	for (int i = 0; i < adj.length; i++) {
    		revAdj[i] = new ArrayList<Integer>();
        }
    	for (int index=0; index<adj.length; index++) {
    		for (int source:adj[index]) {
    			revAdj[source].add(index);
    		}
    	}
    	
    	// Run DFS on reverse graph
    	boolean visited[] = new boolean[adj.length];
    	for (int index=0; index<adj.length; index++) {
    		if (!visited[index])
    			explore(revAdj, index, visited);
    	}
    	Map<Integer, Integer> treeMap = new TreeMap<>(Collections.reverseOrder());
    	for (int vertex=0; vertex<adj.length; vertex++) {
    		treeMap.put(postOrder[vertex], vertex);
    	}
    	
    	boolean sccVisited[] = new boolean[adj.length];
    	for (int key:treeMap.keySet()) {
    		int vertex = treeMap.get(key);
    		if (!sccVisited[vertex]) {
    			explore(adj, vertex, sccVisited);
    			result++;
    		}
    	}
        return result;
    }

    private static void explore(ArrayList<Integer>[] revAdj, int index, boolean[] visited) {
    	visited[index] = true;
    	preOrder[index] = ++counter;
    	for (int ele:revAdj[index]) {
    		if (!visited[ele])
    			explore(revAdj, ele, visited);
    	}
    	postOrder[index] = ++counter;
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
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

