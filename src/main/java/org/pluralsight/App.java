package org.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.*;
public class App {
    public static Scanner scan = new Scanner(System.in);
    public static String userInput = "";

    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/Sakila");
        dataSource.setUsername(System.getenv("MY_DB_USERNAME"));
        dataSource.setPassword(System.getenv("MY_DB_PASSWORD"));
        SakilaDataManager dataManager = new SakilaDataManager(dataSource);
        System.out.println("Welcome to Sakila Movies!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1) Search by Actor
                    2) Search by Film
                    0) Exit
                    Please enter your choice as the corresponding number\t""");
            userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    searchByActor(dataManager);
                    break;
                case "2":
                    searchFilmByActor(dataManager);
                    break;
                case "0":
                    scan.close();
                    try {
                        dataSource.close();
                    } catch (SQLException e) {
                        System.out.println("Error occurred while closing the dataSource");
                    }
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("ERROR: please choose a valid input");
                    break;
            }
        }
    }

    public static void searchByActor(SakilaDataManager dataManager) {
        do {
            System.out.println("""
                    How would you like to search?
                    1) Actor's Last Name
                    2) Actor's First Name
                    0) Return to Home
                    Please enter your choice as the corresponding number\t""");
            userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    System.out.print("Enter the Last Name of the actor:");
                    userInput = scan.nextLine();
                    List<Actor> actors = dataManager.getActorByLast(userInput);
                    if (actors != null) {
                        for (Actor a : actors) {
                            System.out.println(a.toString());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Enter the First Name of the actor:");
                    userInput = scan.nextLine();
                    List<Actor> actors1 = dataManager.getActorByFirst(userInput);
                    if (actors1 != null) {
                        for (Actor a : actors1) {
                            System.out.println(a.toString());
                        }
                    }
                    break;
                case "0":
                    System.out.println("Returning to Home");
                    break;
                default:
                    System.out.println("ERROR: please choose a valid input");
                    break;
            }
        } while (!userInput.equalsIgnoreCase("0"));
    }
    public static void searchFilmByActor(SakilaDataManager dataManager) {
        do {
        System.out.println("""
                How would you like to search?
                1) Actor's Full Name
                2) Actor's ID
                0) Return to Home
                Please enter your choice as the corresponding number""");
        userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    System.out.print("Enter the First Name and Last Name of the actor: ");
                    userInput = scan.nextLine();
                    List<Film> filmList = dataManager.getMovieFromActor(userInput);
                    if (filmList != null) {
                        for (Film film : filmList) {
                            System.out.println(film.toString());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Enter the ID of the actor: ");
                    userInput = scan.nextLine();
                    List<Film> filmList1 = dataManager.getMovieFromActorID(userInput);
                    if (filmList1 != null) {
                        for (Film film : filmList1) {
                            System.out.println(film.toString());
                        }
                    }
                    break;
                case "0":
                    System.out.println("Returning to Home");
                    break;
                default:
                    System.out.println("ERROR: please choose a valid input");
                    break;
            }
        } while (!userInput.equalsIgnoreCase("0"));
    }
}