package org.pluralsight;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * This class provides data access methods for interacting with the Sakila database.
 */
public class SakilaDataManager {
    private DataSource dataSource;

    /**
     * Constructs a SakilaDataManager with the specified DataSource.
     *
     * @param dataSource The DataSource to be used for database connections.
     */
    public SakilaDataManager(DataSource dataSource){
        this.dataSource = dataSource;
    }

    /**
     * Retrieves a list of actors based on the last name.
     *
     * @param name The last name of the actors to search for.
     * @return A list of Actor objects matching the last name, or null if an error occurs.
     */
    public List<Actor> getActorByLast(String name){
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select * from actor where last_name = ?")){
            statement.setString(1, name);
            List<Actor> actors = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    actors.add(new Actor(results.getInt(1), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error occurred during the search");
            }
            return actors;
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred during the search");
        }
        return null;
    }

    /**
     * Retrieves a list of actors based on the first name.
     *
     * @param name The first name of the actors to search for.
     * @return A list of Actor objects matching the first name, or null if an error occurs.
     */
    public List<Actor> getActorByFirst(String name){
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select * from actor where first_name = ?")){
            statement.setString(1, name);
            List<Actor> actors = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    actors.add(new Actor(results.getInt(1), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error occurred during the search");
            }
            return actors;
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred during the search");
        }
        return null;
    }

    /**
     * Retrieves a list of movies in which an actor has participated based on the full name.
     *
     * @param name The full name of the actor.
     * @return A list of Film objects, or null if an error occurs.
     */
    public List<Film> getMovieFromActor(String name){
        String[] names = name.split(" ");
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select film.* from film " +
                "join film_actor on film.film_id = film_actor.film_id " +
                "join actor on film_actor.actor_id = actor.actor_id " +
                "where first_name = ? AND last_name = ?")){
            statement.setString(1, names[0]);
            statement.setString(2, names[1]);
            List<Film> movies = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    movies.add(new Film(results.getInt(1), results.getInt(4), results.getInt(9), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                System.out.println("Error occurred during the search");
            }
            return movies;
        }
        catch(SQLException e){
            System.out.println("Error occurred during the search");
        }
        return null;
    }

    /**
     * Retrieves a list of movies in which an actor has participated based on the actor's ID.
     *
     * @param id The ID of the actor.
     * @return A list of Film objects, or null if an error occurs.
     */
    public List<Film> getMovieFromActorID(String id){
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select film.* from film " +
                "join film_actor on film.film_id = film_actor.film_id " +
                "join actor on film_actor.actor_id = actor.actor_id " +
                "where actor.actor_id = ?")){
            statement.setInt(1, Integer.parseInt(id));
            List<Film> movies = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    movies.add(new Film(results.getInt(1), results.getInt(4), results.getInt(9), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                System.out.println("Error occurred during the search");
            }
            return movies;
        }
        catch(SQLException e){
            System.out.println("Error occurred during the search");
        }
        return null;
    }
}
