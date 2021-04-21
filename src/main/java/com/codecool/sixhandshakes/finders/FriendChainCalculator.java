package com.codecool.sixhandshakes.finders;

import com.codecool.sixhandshakes.model.UserNode;

import java.util.ArrayList;
import java.util.List;

public class FriendChainCalculator {
    static int minDistance;
    static List<List<UserNode>> shortestPaths;


    public static List<List<UserNode>> getShortestRoutesBetween(UserNode start, UserNode end) {
        minDistance = -1;
        shortestPaths = new ArrayList<>();
        List<UserNode> trackingPath = new ArrayList<>();
        trackingPath.add(start);
        findPath(trackingPath, end, 0);
        return shortestPaths;
    }

    private static void findPath(List<UserNode> trackingPath, UserNode endNode, int depth) {
        UserNode lastNode = trackingPath.get(trackingPath.size() -1);
        if (endNode.equals(lastNode)) {
            if (depth < minDistance) shortestPaths = new ArrayList<>();
            minDistance = depth;
            shortestPaths.add(List.copyOf(trackingPath));
        }else if (minDistance == -1 || depth < minDistance) {
            for (UserNode nextNode : lastNode.getFriends()) {
                if (!trackingPath.contains(nextNode)) {
                    trackingPath.add(nextNode);
                    findPath(trackingPath, endNode, depth + 1);
                    trackingPath.remove(nextNode);
                }
            }
        }
    }
}
