package org.pluralsight;

public class Actor {
    private int actorID;
    private String firstName, lastName;

    public Actor(int actorID, String firstName, String lastName) {
        this.actorID = actorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Actor ID: " + actorID);
        str.append("\nName: " + firstName + " " + lastName);
        str.append("\n_________________________________________________________________________________");
        return str.toString();
    }
}