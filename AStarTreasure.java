import java.util.*;

public class AStarTreasure {

    static class Node {
        int x, y;
        double g, h, f;
        Node parent;

        Node(int x, int y, double g, double h, Node parent) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }
    }

    static class CompareNode implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return Double.compare(a.f, b.f);
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static double heuristic(int x1, int y1, int x2, int y2) {
        return Math.sqrt(
            Math.pow(x1 - x2, 2) +
            Math.pow(y1 - y2, 2)
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the maze size: ");
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        System.out.print("Enter start position: ");
        int sx = sc.nextInt();
        int sy = sc.nextInt();

        System.out.print("Enter treasure position: ");
        int gx = sc.nextInt();
        int gy = sc.nextInt();

        boolean[][] blocked = new boolean[rows][cols];

        System.out.print("Enter number of danger zones: ");
        int n = sc.nextInt();

        System.out.println("Enter danger zone positions:");

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (x >= 0 && x < rows &&
                y >= 0 && y < cols) {
                blocked[x][y] = true;
            }
        }

        PriorityQueue<Node> pq =
            new PriorityQueue<>(new CompareNode());

        boolean[][] visited = new boolean[rows][cols];

        pq.add(
            new Node(
                sx,
                sy,
                0,
                heuristic(sx, sy, gx, gy),
                null
            )
        );

        Node goalNode = null;

        while (!pq.isEmpty()) {

            Node curr = pq.poll();

            if (visited[curr.x][curr.y]) {
                continue;
            }

            visited[curr.x][curr.y] = true;

            if (curr.x == gx && curr.y == gy) {
                goalNode = curr;
                break;
            }

            for (int i = 0; i < 4; i++) {

                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx >= 0 && nx < rows &&
                    ny >= 0 && ny < cols &&
                    !blocked[nx][ny] &&
                    !visited[nx][ny]) {

                    double g = curr.g + 1;
                    double h = heuristic(nx, ny, gx, gy);

                    pq.add(
                        new Node(
                            nx,
                            ny,
                            g,
                            h,
                            curr
                        )
                    );
                }
            }
        }

        if (goalNode == null) {
            System.out.println("No path exists");
            sc.close();
            return;
        }

        ArrayList<String> path = new ArrayList<>();

        Node temp = goalNode;

        while (temp != null) {
            path.add(
                "(" +
                temp.x +
                "," +
                temp.y +
                ")"
            );

            temp = temp.parent;
        }

        Collections.reverse(path);

        System.out.println(
            "The shortest path to reach the treasure is:"
        );

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));

            if (i != path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();

        System.out.println(
            "Path cost: " + (path.size() - 1)
        );

        sc.close();
    }
}