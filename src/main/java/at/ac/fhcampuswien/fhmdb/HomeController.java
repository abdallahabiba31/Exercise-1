package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;
    @FXML
    public JFXButton clearBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private final ObservableList<Movie> moviesByGenre = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genres.ACTION, Genres.DRAMA, Genres.ADVENTURE, Genres.ANIMATION, Genres.BIOGRAPHY,
                Genres.COMEDY, Genres.CRIME, Genres.DOCUMENTARY, Genres.FAMILY, Genres.FANTASY, Genres.HISTORY, Genres.HORROR, Genres.MUSICAL,
                Genres.MYSTERY, Genres.ROMANCE, Genres.SCIENCE_FICTION, Genres.WESTERN, Genres.WAR, Genres.SPORT, Genres.THRILLER);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            sortMovies(observableMovies, sortBtn.getText());
        });
        /*searchBtn.setOnAction(actionEvent -> {
            filterMoviesGenre(allMovies, (Genres) genreComboBox.getValue());
            filterMoviesSearch(moviesByGenre, searchField.getText().toLowerCase());
            //sortMovies(moviesByGenre, sortBtn.getText());

        });*/
        clearBtn.setOnAction(actionEvent -> {
            clearFilter();
        });
        //SEARCH BUTTON - ACTION EVENT
        searchBtn.setOnAction(actionEvent -> {
            filterMoviesGenre(allMovies, (Genres) genreComboBox.getValue());
            filterMoviesSearchQuery(moviesByGenre, searchField.getText().toLowerCase());
            sortMovies(moviesByGenre,sortBtn.getText());
        });

    }
    public void sortMovies(ObservableList<Movie> movies, String sortBtnText) {
        if (sortBtnText.equals("Sort (asc)")) {
            // TODO sort observableMovies ascending
            sortBtn.setText("Sort (desc)");
            movies.sort(Comparator.comparing(Movie::getTitle));

        } else {
            // TODO sort observableMovies descending
            sortBtn.setText("Sort (desc)");
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText("Sort (asc)");

        }
    }
    public void clearFilter() {
        //clear the combobox and then set the text of it
        genreComboBox.getSelectionModel().clearSelection();
        genreComboBox.setPromptText("Filter by Genre");

        //showing all the movies
        observableMovies.clear();
        observableMovies.setAll(allMovies);
        searchField.clear();
    }
    public List<Movie> filterMoviesGenre(List<Movie> allMovies, Genres genreSelection) {
        //clear list before adding the movies or else same movie will be added multiple times
        moviesByGenre.clear();

        if (genreSelection != null) {
            for (Movie movies : allMovies) {
                List<Genres> genres = movies.getGenre();

                if (genres.contains(genreSelection)) {
                    moviesByGenre.add(movies);
                }
            }
            observableMovies.setAll(moviesByGenre);
        } else {
            moviesByGenre.setAll(allMovies);// if "no filter" is selected, show all movies, moviesByGenre will be passed on to filterQuery
        }
        return moviesByGenre;
    }


    public List<Movie> filterMoviesSearchQuery(List<Movie> moviesByGenre, String searchQuery) {
        ObservableList<Movie> movieList = FXCollections.observableArrayList();

        movieList.clear();

        if(searchQuery != " ") {      //!searchQuery.isEmpty() replaced --> search field is never empty because of white space
            for (Movie movieLoop : moviesByGenre) {     // loops the already filtered movie list (by genre) and adds the movies it finds there to the new movieList
                String title = movieLoop.getTitle().toLowerCase();
                String description = movieLoop.getDescription().toLowerCase();

                // Check if the title or description of the movie  contains the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    movieList.add(movieLoop);
                }
                // Set the observableMovies list to the filtered movieList
                observableMovies.setAll(movieList);
            }
        }
        return movieList;
    }

    /*public List<Movie> filterMoviesGenre(List<Movie> allMovies, Genres genreSelection) {

        //System.out.println(genreSelection);
        //System.out.println(allMovies);
        List<Movie> rem = allMovies.stream()
                .filter(q -> q.getGenre().contains(genreSelection))
                .collect(Collectors.toList());
        //System.out.println(rem);

        //removing the not filtered elements
        //reverse system somehow
        allMovies.removeIf(q -> !rem.contains(q));
        //System.out.println(allMovies);

        return allMovies;
    }

    public List<Movie> filterMoviesSearch(List<Movie> allMovies, String searchQuery) {

        List<Movie> filteredMovies = new ArrayList<>();
        String search = searchQuery.toLowerCase();

        System.out.println(allMovies);
        if (search != null) {
            System.out.println(searchField.getText());

            //check if search query not contains in the movie(title and description) and then remove this movie from the list
            //allMovies.removeIf(x -> !x.getTitle().contains(search) && !x.getDescription().contains(search));
            for (Movie movieLoop : allMovies) {     // loops the already filtered movie list (by genre) and adds the movies it finds there to the new movieList
                String title = movieLoop.getTitle().toLowerCase();
                String description = movieLoop.getDescription().toLowerCase();

                // Check if the title or description of the movie  contains the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    filteredMovies.add(movieLoop);
                }
                // Set the observableMovies list to the filtered movieList
                observableMovies.setAll(filteredMovies);
            }
            System.out.println(filteredMovies);
            //return allMovies;
        }
        return observableMovies;

    }*/



    /*public void filterMovies(ActionEvent actionEvent) {

        String search = searchField.getText().toLowerCase();
        List<Movie> filteredMovies = new ArrayList<>();

        if (searchField.getText() != null) {
            //System.out.println(searchField.getText());

            //check if search query not contains in the movie(title and description) and then remove this movie from the list
            observableMovies.removeIf(x -> !x.getTitle().toLowerCase().contains(search) && !x.getDescription().toLowerCase().contains(search));

            //another way to remove the movies
            //for (Movie movies : allMovies) {
            // if (observableMovies.removeIf(movie -> !movie.getTitle().toLowerCase().contains(search) && !movie.getDescription().toLowerCase().contains(search))) {
            //filteredMovies.add(movies);
            // }
            // }

            //to clear the input after filtering
            searchField.clear();
        }
        if (genreComboBox.getValue() != null) {
            //Creating list with the filtered genre movies
            List<Movie> rem = observableMovies.stream()
                    .filter(q -> q.getGenre().contains(genreComboBox.getValue()))
                    .collect(Collectors.toList());

            //removing the not filtered elements
            //reverse system somehow
            observableMovies.removeIf(q -> !rem.contains(q));
        }
    }
    public void clearFilter(ActionEvent actionEvent) {

        //clear the combobox and then set the text of it
        genreComboBox.getSelectionModel().clearSelection();
        genreComboBox.setPromptText("Filter by Genre");
        //showing all the movies
        observableMovies.addAll(allMovies);
    }*/


}