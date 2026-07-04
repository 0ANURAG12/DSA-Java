/*
 * LeetCode: 2492. Minimum Score of a Path Between Two Cities
 * https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/
 *
 * ------------------------------------------------------------
 * Approach 1: Depth First Search (DFS)
 * ------------------------------------------------------------
 * Intuition:
 * - Build an adjacency list for the undirected weighted graph.
 * - Start a DFS from city 1 to visit every city in its connected component.
 * - While traversing, keep updating the minimum edge weight encountered.
 * - Since we can revisit cities and roads any number of times, the answer
 *   is simply the minimum edge weight in the connected component containing
 *   city 1.
 *
 * Time Complexity: O(N + M)
 * Space Complexity: O(N + M)
 *
 * where:
 * N = Number of cities
 * M = Number of roads
 */

class Solution {

    public int minScore(int n, int[][] roads) {
        List<int[]>[] adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            adj[u].add(new int[]{v, d});
            adj[v].add(new int[]{u, d});
        }

        boolean[] visited = new boolean[n + 1];
        int[] ans = {Integer.MAX_VALUE};

        dfs(1, adj, visited, ans);

        return ans[0];
    }

    private void dfs(int node, List<int[]>[] adj, boolean[] visited, int[] ans) {
        visited[node] = true;

        for (int[] edge : adj[node]) {
            int next = edge[0];
            int dist = edge[1];

            ans[0] = Math.min(ans[0], dist);

            if (!visited[next]) {
                dfs(next, adj, visited, ans);
            }
        }
    }
}
/*
 * ------------------------------------------------------------
 * Approach 2: Breadth First Search (BFS)
 * ------------------------------------------------------------
 * Intuition:
 * - Build an adjacency list for the undirected weighted graph.
 * - Perform a BFS starting from city 1.
 * - Visit every city in the connected component while keeping track
 *   of the minimum edge weight encountered.
 * - Since every city in the component is reachable and revisiting
 *   roads is allowed, the minimum edge in the component is the answer.
 *
 * Time Complexity: O(N + M)
 * Space Complexity: O(N + M)
 *
 * where:
 * N = Number of cities
 * M = Number of roads
 */

class Solution {

    public int minScore(int n, int[][] roads) {
        List<int[]>[] adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            adj[u].add(new int[]{v, d});
            adj[v].add(new int[]{u, d});
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;

        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int[] edge : adj[node]) {
                int next = edge[0];
                int dist = edge[1];

                ans = Math.min(ans, dist);

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return ans;
    }
}
