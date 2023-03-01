package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    /*enum Genres{
        ACTION,
        ADVENTURE,
        ANIMATION,
        BIOGRAPHY,
        COMEDY,
        CRIME,
        DRAMA,
        DOCUMENTARY,
        FAMILY,
        FANTASY,
        HISTORY,
        HORROR,
        MUSICAL,
        MYSTERY,
        ROMANCE,
        SCIENCE_FICTION,
        SPORT,
        THRILLER,
        WAR,
        WESTERN
    }*/
    private String title;
    private String description;
    // TODO add more properties here
    public static List<Genres> genre = new ArrayList<>();

    public Movie(String title, String description, List<Genres> genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Genres> getGenre() {
        return genre;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        movies.add(new Movie("Fast & Furious 10",
                "Es handelt sich um den elften Spielfilm innerhalb der Fast-&-Furious-Filmreihe und um eine Fortsetzung zu Fast & Furious 9 aus dem Jahr 2021.",
                Arrays.asList(Genres.ACTION)));
        movies.add(new Movie("Creed 3 - Rockys Legacy",
                "Dritter Teil der erfolgreichen „Creed\"-Filmreihe, in der Michael B. Jordan einem alten Freund im Ring gegenübersteht, der nichts zu verlieren hat.",
                Arrays.asList(Genres.DRAMA, Genres.SPORT)));
        movies.add(new Movie("Missing",
                "Missing: Mystery-Thriller mit Storm Reid, die versucht, ihre Mutter aufzuspüren, welche seit einem Urlaub mit ihrem neuen Freund in Kolumbien verschwunden ist.",
                Arrays.asList(Genres.DRAMA)));
        movies.add(new Movie("Barbie",
                "In ihrem ersten Live-Action-Film verwandelt sich die berühmte Plastikpuppe Barbie in einen echten Menschen - und lernt, das wahre Schönheit nur von innen kommt.",
                Arrays.asList(Genres.COMEDY)));
        movies.add(new Movie("JOHN WICK - KAPITEL 4",
                "Im vierten Teil der Reihe nimmt es John Wick mit seinen bisher tödlichsten Widersachern auf. Während das Kopfgeld auf ihn immer höher wird, zieht Wick in einen weltweiten Kampf gegen die mächtigsten Akteure der Unterwelt - von New York über Paris und Osaka bis nach Berlin.",
                Arrays.asList(Genres.ACTION, Genres.THRILLER, Genres.CRIME)));
        movies.add(new Movie("BEAU IS AFRAID",
                "Ein Jahrzehnte umfassendes Porträt von einem der mächtigsten Unternehmer aller Zeiten.",
                Arrays.asList(Genres.HORROR, Genres.MYSTERY, Genres.DRAMA, Genres.COMEDY)));
        //Vorlage, um weitere Filme zu adden
        /*movies.add(new Movie("",
                "",
                Arrays.asList(Genres.ACTION)));*/

        return movies;
    }
}
