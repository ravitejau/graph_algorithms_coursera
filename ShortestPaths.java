import java.util.*;

public class ShortestPaths {
	
	static private Queue<Integer> queue = new LinkedList<>();

	private static boolean isNegativeCycleBellmanFord(long[] dist, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,
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
						if (it == adj.length-1) {
							queue.add(adj[vertex].get(end));
						}
					}
				}
			}
			if (!visitedFlag)
				break;
		}
		return visitedFlag;
	}
	
	private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int source, long[] distance, int[] reachable, int[] shortest) {
    	boolean negativeFlag = isNegativeCycleBellmanFord(distance, adj, cost, source);
    	for (int vertex=0; vertex<adj.length; vertex++) {
			if (distance[vertex] != Integer.MAX_VALUE)
				reachable[vertex] = 1;
			shortest[vertex] = 1;
		}
    	if (negativeFlag)
    		bfs(adj, shortest);
    }

    private static void bfs(ArrayList<Integer>[] adj, int[] shortest) {
    	boolean visited[] = new boolean[adj.length];
    	while (!queue.isEmpty()) {
    		int vertex = queue.poll();
    		visited[vertex] = true;
    		shortest[vertex] = 0;
    		for (int node:adj[vertex]) {
    			if (!visited[node]) {
    				queue.add(node);
    			}
    		}
    	}
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        // similar boolean visited array, use '0' for not reachable and '1' for reachable
        int reachable[] = new int[n];
		// use '1' for a vertex/node if it is reachable from a negative cycle and '0' if
		// it is not reachable
		// meaning the shortest dist doesn't exist which is -infinity as it is reachable
		// from a negative cycle
		int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

