package com.codecool.sixhandshakes;

import com.codecool.sixhandshakes.finders.FriendChainCalculator;
import com.codecool.sixhandshakes.finders.FriendsOfFriendsFinder;
import com.codecool.sixhandshakes.finders.HandshakeCalculator;
import com.codecool.sixhandshakes.model.UserNode;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SixHandshakes {
    private static List<UserNode> users;
    private static GraphPlotter graphPlotter;

    public static void main(String[] args) {
        initSocialGraph();
        graphPlotter = new GraphPlotter(users);

        System.out.println("Done!");
    }

    private static void initSocialGraph() {
        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();
    }

    private static void visualizeFriendCircle(Set<UserNode> friendCircle, UserNode user) {
        graphPlotter.highlightNodes(friendCircle, user);
    }

    private static void printRoutes(List<List<UserNode>> routes) {
        for (List<UserNode> nodes : routes) {
            System.out.print("\nRoute with " + nodes.size() + " steps:");
            for (int i = 0, nodesSize = nodes.size(); i < nodesSize; i++) {
                UserNode node = nodes.get(i);
                System.out.print(" " + node.getId());
                if (i < nodesSize - 1) {
                    System.out.print(" ->");
                }
            }
        }
        System.out.println();
    }
}
