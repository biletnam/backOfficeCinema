package models;

import java.util.*;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class Movie {
    
  @Id
  @ObjectId
  public String id;

  public String label;

  private static JacksonDBCollection<Movie, String> coll = MongoDB.getCollection("movies", Movie.class, String.class);

  public Movie(){

  }

  public Movie(String label) {
    this.label = label;
  }

  public static List<Movie> all() {
    return Movie.coll.find().toArray();
  }

  public static void create(Movie movie) {
    Movie.coll.save(movie);
  }

  public static void create(String label){
      create(new Movie(label));
  }

  public static void delete(String id) {
    Movie movie = Movie.coll.findOneById(id);
    if (movie != null)
        Movie.coll.remove(movie);
  }

  public static void removeAll(){
    Movie.coll.drop();
  }

}
