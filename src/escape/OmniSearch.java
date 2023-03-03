package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.required.LocationType;

import java.util.*;

public class OmniSearch {
    /**
     * finds the shortest path between two coordinates
     *
     * @param from            starting coordinate
     * @param to              ending coordinate
     * @param gameInitializer gameInitializer
     * @return distance between two coordinates
     */
    public static int omniBFS(CoordinateImpl from, CoordinateImpl to, EscapeGameInitializer gameInitializer) {
        Map<CoordinateImpl, Integer> distances = new HashMap<>();
        Queue<CoordinateImpl> queue = new LinkedList<>();
        queue.add(from);
        distances.put(from, 0);

        while (!queue.isEmpty()) {
            CoordinateImpl current = queue.remove();

            if (current.equals(to)) {
                return distances.get(current);
            }

            for (CoordinateImpl neighbor : getNeighbors(current, gameInitializer)) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, distances.get(current) + 1);
                    queue.add(neighbor);
                }
            }
        }

        return -1;
    }

    /**
     * gets neighbors of a coordinate
     *
     * @param node            coordinate
     * @param gameInitializer gameInitializer
     * @return list of neighbors
     */
    private static List<CoordinateImpl> getNeighbors(CoordinateImpl node, EscapeGameInitializer gameInitializer) {
        List<CoordinateImpl> neighbors = new ArrayList<>();
        int x = node.getRow();
        int y = node.getColumn();

        if (y > 0) {
            CoordinateImpl neighbor = new CoordinateImpl(x, y - 1);
            neighbors.add(neighbor);

        }
        if (y < gameInitializer.getyMax() - 1) {
            CoordinateImpl neighbor = new CoordinateImpl(x, y + 1);
            neighbors.add(neighbor);

        }
        if (x > 0) {
            CoordinateImpl neighbor = new CoordinateImpl(x - 1, y);
            neighbors.add(neighbor);

        }
        if (x < gameInitializer.getxMax() - 1) {
            CoordinateImpl neighbor = new CoordinateImpl(x + 1, y);
            neighbors.add(neighbor);

        }

        return neighbors;
    }

}
