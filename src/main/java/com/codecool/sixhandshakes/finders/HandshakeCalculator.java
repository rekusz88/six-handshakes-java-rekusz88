package com.codecool.sixhandshakes.finders;

import com.codecool.sixhandshakes.model.UserNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HandshakeCalculator {
    static int minDistance;


    public static Optional<Integer> getMinimumHandshakesBetween(UserNode start, UserNode end) {
        minDistance = -1;
        List<UserNode> trackingPath = new ArrayList<>();
        trackingPath.add(start);
        Optional<Integer> distance = Optional.empty();
        findPath(trackingPath, end, 0);
        if (minDistance > -1) distance = Optional.of(minDistance);
        return distance;
    }

    private static void findPath(List<UserNode> trackingPath, UserNode endNode, int depth) {
        UserNode lastNode = trackingPath.get(trackingPath.size() - 1);
        if (endNode.equals(lastNode)) {
            minDistance = depth;
        }else if (minDistance == -1 || depth + 1 < minDistance) {
            for (UserNode nextNode : lastNode.getFriends()) {
                if (!trackingPath.contains(nextNode)) {
                    trackingPath.add(nextNode);
                    findPath(trackingPath, endNode,  depth + 1);
                    trackingPath.remove(nextNode);
                }
            }
        }
    }
}
