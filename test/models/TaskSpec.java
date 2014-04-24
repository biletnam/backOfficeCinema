package models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class TaskSpec {

    @Before
    public void initialize() {
        Movie.removeAll();
    }

    @Test
    public void beAddable() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                String testLabel = "Movie label";
                Movie.create(testLabel);
                    List<Movie> movies = Movie.all();

                assertThat(movies.size()).isEqualTo(1);
                assertThat(movies.get(0).label).isEqualTo(testLabel);
            }
        });
    }

    @Test
    public void beDeletable(){
        String testLabel = "Movie label";
        Movie.create(testLabel);

        List<Movie> movies = Movie.all();
        Movie.delete(movies.get(0).id);

        movies = Movie.all();
        assertThat(movies.size()).isEqualTo(0);
    }
}
