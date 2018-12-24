import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {

	/*static class Point {
		int xCordinate;
		int yCordinate;
		Point (int xCordinate, int yCordinate) {
			this.xCordinate = xCordinate;
			this.yCordinate = yCordinate;
		}
	};*/
	
	private static class Priority implements Comparable<Priority> {
		Double dist;
		int vertex;
		public Priority(Double dist, int vertex) {
			this.dist = dist;
			this.vertex = vertex;
		}
		@Override
		public int compareTo(Priority arg0) {
			return Double.compare(this.dist, arg0.dist);
		}
	}

	private static double minimumDistance(int[] x, int[] y) {
		double result = 0.;
		ArrayList<Integer>[] adjList = (ArrayList<Integer>[])new ArrayList[x.length];
		ArrayList<Double>[] cost = (ArrayList<Double>[])new ArrayList[x.length];
		int length = x.length;
		int count=0;
		for (int index=0; index<length; index++) {
			adjList[index] = new ArrayList<Integer>();
			cost[index] = new ArrayList<Double>();
		}
		for (int row=0; row<length; row++) {
			for (int col=0; col<length; col++) {
				if (row != col) {
					adjList[row].add(col);
					cost[row].add(computeDist(x[row], x[col], y[row], y[col]));
				}
			}  
		}
		
		// Prim's algorithm for finding the minimum spanning tree
		PriorityQueue<Priority> pqueue = new PriorityQueue<>();
    	Priority[] dist = new Priority[length];
    	boolean[] visited = new boolean[length];
    	for (int index=0; index<length; index++) {
    		dist[index] = new Priority(Double.MAX_VALUE, index);
    	}
    	dist[0].dist = (double)0;
    	pqueue.add(dist[0]);
    	while (!pqueue.isEmpty()) {
    		Priority temp = pqueue.poll();
    		if (visited[temp.vertex])
    			continue;
    		result += temp.dist;
    		visited[temp.vertex] = true;
    		for (int index=0; index<adjList[temp.vertex].size(); index++) {
				if (!visited[adjList[temp.vertex].get(index)]
						&& dist[adjList[temp.vertex].get(index)].dist > cost[temp.vertex].get(index)) {
					dist[adjList[temp.vertex].get(index)].dist = cost[temp.vertex].get(index);
					pqueue.add(new Priority(dist[adjList[temp.vertex].get(index)].dist, adjList[temp.vertex].get(index)));
				}
    		}
    	}
		return result;
	}

	private static double computeDist (int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2)+ Math.pow(y1 - y2, 2));
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = scanner.nextInt();
			y[i] = scanner.nextInt();
		}
		System.out.println(minimumDistance(x, y));
	}
	
}

