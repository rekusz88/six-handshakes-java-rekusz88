package com.codecool.sixhandshakes.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserNode {
    private static long idCounter = 0;
    private long id = idCounter++;
    private String firstName;
    private String lastName;

    private Set<UserNode> friends = new HashSet<>();

    public UserNode(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public Set<UserNode> getFriends() {
        return friends;
    }

    public void addFriend(UserNode friend) {
        friends.add(friend);
        friend.friends.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return firstName + "_" + lastName + " (" + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNode userNode = (UserNode) o;
        return id == userNode.id &&
                firstName.equals(userNode.firstName) &&
                lastName.equals(userNode.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
