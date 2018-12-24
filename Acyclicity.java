import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
    	boolean visited[] = new boolean[adj.length];
    	for (int index=0; index<adj.length; index++) {
    		Set<Integer> set = new HashSet<>();
    		if (!visited[index] && explore(adj, visited, index, set))
    			return 1;
    	}
        return 0;
    }

    private static boolean explore(ArrayList<Integer>[] adj, boolean visited[], int index, Set<Integer> set) {
    	visited[index] = true;
    	set.add(index);
    	for (int ele:adj[index]) {
    		if (set.contains(ele))
    			return true;
    		if (!visited[ele])
    			if (explore(adj, visited, ele, set))
    				return true;
    	}
    	set.remove(index);
    	return false;
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
        System.out.println(acyclic(adj));
    }
}

