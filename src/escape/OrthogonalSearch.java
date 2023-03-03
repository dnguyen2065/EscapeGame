package escape;

import escape.builder.EscapeGameInitializer;

import java.util.*;

public class OrthogonalSearch {
    /**
     * finds the distance between two coordinates
     *
     * @param from            starting coordinate
     * @param to              ending coordinate
     * @param gameInitializer gameInitializer
     * @return distance between two coordinates
     */
    public static int orthoBFS(CoordinateImpl from, CoordinateImpl to, EscapeGameInitializer gameInitializer) {
        int distance = 0;
        Map<CoordinateImpl, Integer> distanceMap = new HashMap<>();
        Queue<CoordinateImpl> queue = new LinkedList<>();

        queue.add(from);
        distanceMap.put(from, 0);

        while (!queue.isEmpty()) {
            CoordinateImpl curr = queue.poll();
            if (curr.equals(to)) {
                distance = distanceMap.get(curr);
                break;
            }

            List<CoordinateImpl> neighbors = getOrthogonalNeighbors(curr, gameInitializer);
            for (CoordinateImpl neighbor : neighbors) {
                if (!distanceMap.containsKey(neighbor)) {
                    distanceMap.put(neighbor, distanceMap.get(curr) + 1);
                    queue.add(neighbor);
                }
            }
        }

        return distance;
    }

    /**
     * gets orthogonal neighbors of a coordinate
     *
     * @param coord           coordinate
     * @param gameInitializer gameInitializer
     * @return list of orthogonal neighbors
     */
    private static List<CoordinateImpl> getOrthogonalNeighbors(CoordinateImpl coord, EscapeGameInitializer gameInitializer) {
        int row = coord.getRow();
        int col = coord.getColumn();
        List<CoordinateImpl> neighbors = new ArrayList<>();
        int xMax = gameInitializer.getxMax();
        int yMax = gameInitializer.getyMax();

        if (row > 0) {
            neighbors.add(new CoordinateImpl(row - 1, col));
        }
        if (col > 0) {
            neighbors.add(new CoordinateImpl(row, col - 1));
        }
        if (row < xMax - 1) {
            neighbors.add(new CoordinateImpl(row + 1, col));
        }
        if (col < yMax - 1) {
            neighbors.add(new CoordinateImpl(row, col + 1));
        }

        return neighbors;
    }
}
