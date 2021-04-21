package com.codecool.sixhandshakes;

import com.codecool.sixhandshakes.GraphPlotter;
import com.codecool.sixhandshakes.finders.FriendChainCalculator;
import com.codecool.sixhandshakes.finders.FriendsOfFriendsFinder;
import com.codecool.sixhandshakes.finders.HandshakeCalculator;
import com.codecool.sixhandshakes.model.UserNode;

import java.util.*;

public class SixHandshakes {
    private static List<UserNode> users;
    private static GraphPlotter graphPlotter;

    public static void main(String[] args) {
        initSocialGraph();
        graphPlotter = new GraphPlotter(users);

        System.out.println("Done!");


        mainMenu();
    }


    public static List<UserNode> getUsers() {
        return users;
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

    private static void printMenu(Map<Integer, String> menuItems) {
        for (Integer key: menuItems.keySet()) {
            System.out.println(key + ") " + menuItems.get(key));
        }
        System.out.println();
    }


    private static void mainMenu() {
        Map<Integer, String> menuItems = new HashMap<>();
        menuItems.put(1, "Minimum handshakes");
        menuItems.put(2, "Friends of friends");
        menuItems.put(3, "Shortest routes");
        printMenu(menuItems);
        Integer choose = chooseMenuItem(menuItems.keySet());
        switch (choose) {
            case 1: minimumHandshakes();
                break;
            case 2: friendsOfFriends();
                break;
            case 3: shortestRoutes();
                break;
        }
    }

    private static void shortestRoutes() {
        UserNode startNode = getUserByGivenName();
        UserNode endNode =  getUserByGivenName();
        List<List<UserNode>> shortestPaths = FriendChainCalculator.getShortestRoutesBetween(startNode, endNode);
        printRoutes(shortestPaths);
        for (List<UserNode> path : shortestPaths) {
            graphPlotter.highlightRoute(path);
        }
    }

    private static void minimumHandshakes() {
        UserNode startNode = getUserByGivenName();
        UserNode endNode = getUserByGivenName();
        Optional<Integer> distance = HandshakeCalculator.getMinimumHandshakesBetween(startNode, endNode);
        distance.ifPresent(System.out :: println);
    }

    private static void friendsOfFriends() {
        UserNode searchForFriends = getUserByGivenName();
        int distance = getIntegerAsInput();
        Set<UserNode> friendOfFriends = FriendsOfFriendsFinder.getFriendsOfFriends(searchForFriends, distance);
        visualizeFriendCircle(friendOfFriends, searchForFriends);
    }

    private static UserNode getUserByGivenName() {
        String name;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Give a name: ");
            name = scanner.nextLine();
            for (UserNode user: users) {
                if (user.getFullName().equals(name)) {
                    return user;
                }
            }
            System.out.println("The person doesn't exist. Pls give another name.");
        }
    }

    private static int getIntegerAsInput() {
        Integer choose = null;
        while (choose == null) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Choose from the menu: ");
                choose = scanner.nextInt();

            }catch (Exception e) {
                System.out.println("Not valid form.");
                choose = null;
            }
        }
        return choose;
    }

    private static int chooseMenuItem(Set<Integer> selectable) {
        Integer choose = null;
        while (true) {
            choose = getIntegerAsInput();
            if (selectable.contains(choose)) return choose;
            System.out.println("Not a valid option.");
            choose = null;

        }
    }
}
