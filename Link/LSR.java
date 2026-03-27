import java.util.Scanner;

public class LSR {
    static final int MAX = 20;
    static final int INF = 9999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] graph = new int[MAX][MAX];
        int[] dist = new int[MAX];
        boolean[] visited = new boolean[MAX];

        System.out.print("Enter number of routers (vertices): ");
        int v = sc.nextInt();

        System.out.print("Enter number of links (edges): ");
        int e = sc.nextInt();

        // Step 4: Initialize adjacency matrix
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = INF;
            }
        }

        // Step 5: Read edges
        System.out.println("Enter source destination cost for each link:");
        for (int i = 0; i < e; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int cost = sc.nextInt();
            // Added bounds check to prevent crash if input exceeds v
            if (s < v && d < v) {
                graph[s][d] = cost;
                graph[d][s] = cost; 
            }
        }

        // Step 6: Source router
        System.out.print("Enter source router: ");
        int src = sc.nextInt();

        // Step 7: Initialize distance and visited arrays
        for (int i = 0; i < v; i++) {
            dist[i] = INF;
            visited[i] = false;
        }
        dist[src] = 0;

        // Step 8: Dijkstra’s Algorithm
        for (int count = 0; count < v; count++) { // Changed v-1 to v to ensure all nodes checked

            int min = INF, u = -1;

            // Find unvisited router with minimum distance
            for (int i = 0; i < v; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            // --- CRITICAL FIX ---
            // If u is still -1, it means remaining nodes are unreachable.
            // We must break to avoid ArrayIndexOutOfBoundsException.
            if (u == -1) break;

            visited[u] = true;

            // Update distances of neighboring routers
            for (int j = 0; j < v; j++) {
                if (!visited[j] && graph[u][j] != INF &&
                        dist[u] + graph[u][j] < dist[j]) {
                    dist[j] = dist[u] + graph[u][j];
                }
            }
        }

        // Step 9: Display routing table
        System.out.println("\nRouting Table for Router " + src);
        System.out.println("Destination\tCost");
        for (int i = 0; i < v; i++) {
            String output = (dist[i] == INF) ? "INF" : String.valueOf(dist[i]);
            System.out.println(i + "\t\t" + output);
        }

        sc.close();
    }
}