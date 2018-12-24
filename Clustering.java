import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Clustering {

	private static class Priority implements Comparable<Priority> {
		Double dist;
		int source;
		int dest;
		public Priority(Double dist, int source, int dest) {
			this.dist = dist;
			this.source = source;
			this.dest = dest;
		}
		@Override
		public int compareTo(Priority arg0) {
			return Double.compare(this.dist, arg0.dist);
		}
	}

	static class UnionFind {
		private int count = 0;
		private int[] parent, rank;

		public UnionFind(int n) {
			count = n;
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int p) {
			if (p != parent[p]) {
				parent[p] = find(parent[p]);    // path compression by halving
			}
			return parent[p];
		}

		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) return;
			if (rank[rootQ] > rank[rootP]) {
				parent[rootP] = rootQ;
			}
			else {
				parent[rootQ] = rootP;
				if (rank[rootP] == rank[rootQ]) {
					rank[rootP]++;
				}
			}
			count--;
		}
		
		public int count() {
            return count;
        }
	}

	// Implemented using kruskal's algorithms for finding the minimum spanning tree
	private static double clustering(int[] x, int[] y, int k) {
		//write your code here
		double result = -1;
		int length = x.length;
		List<Priority> list = new ArrayList<>();
		for (int row=0; row<length; row++) {
			for (int col=row+1; col<length; col++) {
				if (row != col) {
					list.add(new Priority(computeDist(x[row], x[col], y[row], y[col]), row, col));
				}
			}  
		}
		UnionFind unionFind = new UnionFind(length);
		Collections.sort(list);
		for (Priority path: list) {
			if (unionFind.count() == k-1)
				break;
			if (unionFind.find(path.source) != unionFind.find(path.dest)) {
				unionFind.union(path.source, path.dest);
				result = path.dist;
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
		int k = scanner.nextInt();
		System.out.println(clustering(x, y, k));
	}
}

