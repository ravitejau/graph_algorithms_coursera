import java.util.*;

public class Dijkstra {
	
	private static class Priority implements Comparable<Priority> {
		int dist;
		int vertex;
		public Priority(int dist, int vertex) {
			this.dist = dist;
			this.vertex = vertex;
		}
		@Override
		public int compareTo(Priority arg0) {
			return Integer.compare(this.dist, arg0.dist);
		}
	} 
	
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	PriorityQueue<Priority> pqueue = new PriorityQueue<>();
    	Priority[] dist = new Priority[adj.length];
    	int[] prev = new int[adj.length];
    	boolean[] visited = new boolean[adj.length];
    	for (int index=0; index<adj.length; index++) {
    		dist[index] = new Priority(Integer.MAX_VALUE, index);
    		prev[index] = -1;
    	}
    	dist[s].dist = 0;
    	pqueue.add(dist[s]);
    	while (!pqueue.isEmpty()) {
    		Priority temp = pqueue.poll();
    		if (visited[temp.vertex])
    			continue;
    		visited[temp.vertex] = true;
    		for (int index=0; index<adj[temp.vertex].size(); index++) {
				if (!visited[adj[temp.vertex].get(index)]
						&& dist[adj[temp.vertex].get(index)].dist > temp.dist + cost[temp.vertex].get(index)) {
					dist[adj[temp.vertex].get(index)].dist = temp.dist + cost[temp.vertex].get(index);
					pqueue.add(new Priority(dist[adj[temp.vertex].get(index)].dist, adj[temp.vertex].get(index)));
					prev[adj[temp.vertex].get(index)] = temp.vertex;
				}
    		}
    	}
    	List<Integer> list = new ArrayList<>();
    	int temp = t;
    	list.add(temp+1);
    	while (prev[temp] != -1) {
    		temp = prev[temp];
    		list.add(temp+1);
    	}
    	Collections.reverse(list);
//    	System.out.println(list.toString());
    	if (dist[t].dist == Integer.MAX_VALUE)
    		return -1;
        return dist[t].dist;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    	/*Priority p1 = new Priority(7, 1);
    	Priority p2 = new Priority(2,2);
    	PriorityQueue<Priority> pqueue = new PriorityQueue<>();
    	pqueue.add(p1);
    	pqueue.add(p2);
    	System.out.println("first:" + pqueue.poll().vertex);
    	pqueue.add(new Priority(4, 0));
    	System.out.println("second:" + pqueue.poll().vertex);*/
    }
}

