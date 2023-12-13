package org.pluralsight;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class SakilaDataManager {
    private DataSource dataSource;
    public SakilaDataManager(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public List<Actor> getActorByLast(String name){
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select * from actor where last_name = ?")){
            statement.setString(1, name);
            List<Actor> a = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    a.add(new Actor(results.getInt(1), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error occurred during the search");
            }
            return a;
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred during the search");
        }
        return null;
    }
    public List<Actor> getActorByFirst(String name){
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select * from actor where first_name = ?")){
            statement.setString(1, name);
            List<Actor> a = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    a.add(new Actor(results.getInt(1), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error occurred during the search");
            }
            return a;
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred during the search");
        }
        return null;
    }
    public List<Film> getMovieFromActor(String name){
        String[] names = name.split(" ");
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select film.* from film " +
                "join film_actor on film.film_id = film_actor.film_id " +
                "join actor on film_actor.actor_id = actor.actor_id " +
                "where first_name = ? AND last_name = ?")){
            statement.setString(1, names[0]);
            statement.setString(2, names[1]);
            List<Film> m = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    m.add(new Film(results.getInt(1), results.getInt(4), results.getInt(9), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                System.out.println("Error occurred during the search");
            }
            return m;
        }
        catch(SQLException e){
            System.out.println("Error occurred during the search");
        }
        return null;
    }
    public List<Film> getMovieFromActorID(String id){
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("select film.* from film " +
                "join film_actor on film.film_id = film_actor.film_id " +
                "join actor on film_actor.actor_id = actor.actor_id " +
                "where actor.actor_id = ?")){
            statement.setInt(1, Integer.parseInt(id));
            List<Film> m = new ArrayList<>();
            try(ResultSet results = statement.executeQuery()){
                while(results.next()){
                    m.add(new Film(results.getInt(1), results.getInt(4), results.getInt(9), results.getString(2), results.getString(3)));
                }
            }
            catch(SQLException e){
                System.out.println("Error occurred during the search");
            }
            return m;
        }
        catch(SQLException e){
            System.out.println("Error occurred during the search");
        }
        return null;
    }
}
