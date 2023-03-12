package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    HomeController controller = new HomeController();

    @Test
    void movie_list_is_sorted_ascending_if_button_displays_desc(){
        // GIVEN
        HomeController controller = new HomeController();
        //create list that is going to be tested:
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        ObservableList<Movie> actual = controller.sortMovies(exampleMovies, "Sort (asc)"); //actual

        // THEN
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie1);
        expected.add(movie3);
        expected.add(movie2);
        //test
        assertEquals(expected, actual);
    }

    @Test
    void movie_list_is_sorted_descending_if_button_displays_asc(){
        // GIVEN
        HomeController controller = new HomeController();
        //create list that is going to be tested:
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        ObservableList<Movie> actual = controller.sortMovies(exampleMovies, "Sort (desc)"); //actual

        // THEN
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie1);
        //test
        assertEquals(expected, actual);
    }
    /*@Test
    void display_only_movies_with_selected_genre(){

        //we need multiple Lists
        //one for the example input movies
        List<Movie> example = new ArrayList<>();
        //another one for the movies we expect
        List<Movie> expected = new ArrayList<>();
        //and one for the actual movies we get
        List<Movie> actual = new ArrayList<>();

        //Given
        Movie movie1 = new Movie("Film-1", "Description of film-1", Arrays.asList(Genres.ACTION, Genres.ROMANCE, Genres.COMEDY));
        Movie movie2 = new Movie("Film-2", "Description of film-2", Arrays.asList(Genres.DRAMA));
        Movie movie3 = new Movie("Film-3", "Description of film-3", Arrays.asList(Genres.DRAMA, Genres.SPORT, Genres.ADVENTURE));
        Movie movie4 = new Movie("Film-4", "Description of film-4", Arrays.asList(Genres.BIOGRAPHY, Genres.DRAMA));
        example.add(movie1);
        example.add(movie2);
        example.add(movie3);
        example.add(movie4);


        //When
        actual = controller.filterMovies();

        //Then
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie4);

        //checking if true
       assertEquals(expected, actual);


        }*/


}
