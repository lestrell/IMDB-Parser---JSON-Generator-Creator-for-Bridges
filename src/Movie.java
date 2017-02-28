import java.util.ArrayList;
/**
 * Created by Lucas Estrella on 2/27/2017.
 * Last modified by Lucas Estrella on 2/27/2017.
 */
public class Movie {
    private String movieName;
    private String movieActor;
    private String movieYear;
    private String movieRating;
    private ArrayList<String> movieReleaseDates;
    private ArrayList<String> genres;
    private ArrayList<String> locations;
    private ArrayList<String> languages;
    private ArrayList<String> countries;
    private ArrayList<String> actors;
    private ArrayList<String> actresses;

    public ArrayList<String> getActresses() {
        return actresses;
    }

    public void setActresses(ArrayList<String> actresses) {
        this.actresses = actresses;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }



    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<String> getMovieReleaseDates() {
        return movieReleaseDates;
    }

    public void setMovieReleaseDates(ArrayList<String> movieReleaseDates) {
        this.movieReleaseDates = movieReleaseDates;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }


}
