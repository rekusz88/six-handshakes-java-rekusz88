package com.codecool.sixhandshakes.finders;

import com.codecool.sixhandshakes.model.UserNode;

import java.util.*;

public class FriendsOfFriendsFinder {

    static Map<UserNode, Integer> distanceStore;
    static Queue<UserNode> nodeToVisit;
    static Set<UserNode> nodeVisited;


    public static Set<UserNode> getFriendsOfFriends(UserNode user, int distance) {
        distanceStore = new HashMap<>();
        nodeToVisit = new LinkedList<>();
        nodeVisited = new HashSet<>();

        distanceStore.put(user, 0);
        appendNodeToVisit(user, distance);

        while (nodeToVisit.size() > 0) {
            UserNode userNode =  nodeToVisit.poll();
            nodeVisited.add(userNode);
            appendNodeToVisit(userNode, distance);
        }
        return nodeVisited;
    }

    private static void appendNodeToVisit(UserNode userNode, int distance) {
        int currentDistance = distanceStore.get(userNode);
        if (currentDistance < distance) {
            Set<UserNode> friends = userNode.getFriends();
            for (UserNode friendNode: friends) {
                if (isNotExaminedYet(friendNode)) {
                    addToExamineQueue(currentDistance, friendNode);
                }
            }
        }

    }

    private static void addToExamineQueue(int currentDistance, UserNode friendNode) {
        nodeToVisit.add(friendNode);
        distanceStore.put(friendNode, currentDistance + 1);
    }

    private static boolean isNotExaminedYet(UserNode friendNode) {
        return !(nodeVisited.contains(friendNode) || nodeToVisit.contains(friendNode));
    }
}
