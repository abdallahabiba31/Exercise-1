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

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

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
        /*sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
                observableMovies.sort(Comparator.comparing(Movie::getTitle));
                //sortState = SortState.ASCENDING;

            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });
        searchBtn.setOnAction(actionEvent -> {
            Genres selectedGenre = (Genres) genreComboBox.getValue();


            if (genreComboBox.getValue().equals(selectedGenre)) {
                observableMovies.removeIf(movie -> !movie.getGenre().contains(selectedGenre));
            }

        });*/

    }

    public void sortMovies(ActionEvent actionEvent) {
        if (sortBtn.getText().equals("Sort (asc)")) {
            // TODO sort observableMovies ascending
            sortBtn.setText("Sort (desc)");
            observableMovies.sort(Comparator.comparing(Movie::getTitle));

        } else {
            // TODO sort observableMovies descending
            sortBtn.setText("Sort (desc)");
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText("Sort (asc)");

        }
    }

    public void filterMovies(ActionEvent actionEvent) {

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

}
}