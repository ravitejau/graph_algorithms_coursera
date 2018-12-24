import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NegativeCycle {

	private static boolean isNegativeCycleBellmanFord(int[] dist, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,
			int source) {
		for (int t=0; t<adj.length; t++) {
			dist[t] = Integer.MAX_VALUE;
		}
		dist[source] = 0;
		boolean visitedFlag=false;
		for (int it=0; it<adj.length; it++) {
			visitedFlag = false;
			for (int vertex=0; vertex<adj.length; vertex++) {
				if (dist[vertex] == Integer.MAX_VALUE) {
					continue;
				}
				for (int end=0; end<adj[vertex].size(); end++) {
					if (dist[adj[vertex].get(end)] > dist[vertex]+cost[vertex].get(end)) {
						dist[adj[vertex].get(end)] = dist[vertex]+cost[vertex].get(end);
						visitedFlag = true;
					}
				}
			}
			if (!visitedFlag)
				break;
		}
		return visitedFlag;
	}
	
	private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
		// write your code here
		int[] dist = new int[adj.length];
		for (int index=0; index<adj.length; index++) {
			dist[index] = Integer.MAX_VALUE;
		}
		boolean visited[] = new boolean[adj.length];
		for (int node=0; node<adj.length; node++) {
			if (!visited[node]) {
				if (isNegativeCycleBellmanFord(dist, adj, cost, node))
					return 1;
				for (int vertex=0; vertex<adj.length; vertex++) {
					if (dist[vertex] != Integer.MAX_VALUE)
						visited[vertex] = true;
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
		ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			cost[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1].add(w);
		}
		System.out.println(negativeCycle(adj, cost));
	}
}

