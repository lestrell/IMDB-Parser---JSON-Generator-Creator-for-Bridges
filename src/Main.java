/**
 * Created by Lucas Estrella on 2/27/2017.
 * Last modified by Lucas Estrella on 2/27/2017.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import info.debatty.java.stringsimilarity.JaroWinkler;
import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Main {

    private static final String GenresList = "data//genres.list";
    private static final String Large_IMDB = "data//large_imdb.txt";
    private static final String RatingsList = "data//ratings.list";
    private static final String CountriesList = "data//countries.list";
    private static final String LanguageList = "data//language.list";
    private static final String ReleaseDatesList = "data//release-dates.list";
    private static final String LocationsList = "data//locations.list";
    private static final String AllMovieList = "data//movies.list";
    private static final String ActorsList = "data//actors.list";
    private static final String ActressesList = "data//actresses.list";


    static ArrayList<String> genreList = new ArrayList<>(Arrays.asList(
            "Short",
            "Drama",
            "Comedy",
            "Documentary",
            "Adult",
            "Action",
            "Thriller",
            "Romance",
            "Animation",
            "Family",
            "Horror",
            "Music",
            "Crime",
            "Adventure",
            "Fantasy",
            "Sci-Fi",
            "Mystery",
            "Biography",
            "History",
            "Sport",
            "Musical",
            "War	",
            "Reality-TV",
            "Western",
            "News",
            "Talk-Show",
            "Game-Show",
            "Film-Noir",
            "Sex	",
            "Sci-fi",
            "Lifestyle",
            "Hardcore",
            "Experimental",
            "Erotica",
            "Commercial"
    ));

    static HashMap<String, Movie> moviesMap;
//    static JaroWinkler jw;

    public static void main(String[] args) {
        moviesMap = new HashMap<>();
//        jw = new JaroWinkler();

//        SplitBigText(ActorsList);

        addLargeIMDB();
        addGenres();
        addRatings();
        addCountry();
        addLanguage();
        addReleaseDate();
        addLocations();
        addYear();

//        addActors();          //FILE TOO LARGE
//        addActresses();       //FILE TOO LARGE

         writeMapIMDB();
        writeListIMDB();




        //TODO Get movies from any rating range
    }

    private static void addActors() {
        ArrayList<String> lines = getLines(ActorsList);
        for(String line : lines){
            String movieName = line.split("\t")[0].trim();
//            out.println(actorName);
            if(moviesMap.containsKey(movieName)){
                String actorName = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getActors() != null){
                    if(!moviesMap.get(movieName).getActors().contains(actorName)){
                        moviesMap.get(movieName).getActors().add(actorName);
                    }
                }
                else
                    moviesMap.get(movieName).setActors(new ArrayList<>(Arrays.asList(actorName)));
            }
        }
        lines.clear();
    }

    private static void addActresses() {
        ArrayList<String> lines = getLines(ActressesList);
        for(String line : lines){
            String movieName = line.split("\t")[0].trim();
//            out.println(actressName);
            if(moviesMap.containsKey(movieName)){
                String actressName = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getActresses() != null){
                    if(!moviesMap.get(movieName).getActresses().contains(actressName)){
                        moviesMap.get(movieName).getActresses().add(actressName);
                    }
                }
                else
                    moviesMap.get(movieName).setActresses(new ArrayList<>(Arrays.asList(actressName)));
            }
        }
        lines.clear();
    }

    private static void addYear() {
        ArrayList<String> lines = getLines(AllMovieList);
        for(String line : lines){
//            System.out.println(line.split("\t")[0]);
            String movieName = line.split("\t")[0].trim();
            if(moviesMap.containsKey(movieName)){
                String movieYear = line.replace(movieName,"").trim();
                moviesMap.get(movieName).setMovieYear(movieYear);
            }
        }
        lines.clear();
    }

    private static void addLocations() {
        ArrayList<String> lines = getLines(LocationsList);
        for(String line : lines){
            String movieName = line.split("\t")[0].trim();
//            out.println(locationName);
            if(moviesMap.containsKey(movieName)){
                String locationName = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getLocations() != null)
                    moviesMap.get(movieName).getLocations().add(locationName);
                else
                    moviesMap.get(movieName).setLocations(new ArrayList<>(Arrays.asList(locationName)));
            }
        }
        lines.clear();
    }

    private static void addReleaseDate() {
        ArrayList<String> lines = getLines(ReleaseDatesList);
        for(String line : lines){
            String movieName = line.split("\t")[0].trim();
//            out.println(releaseDate);
            if(moviesMap.containsKey(movieName)){
                String releaseDate = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getMovieReleaseDates() != null)
                    moviesMap.get(movieName).getMovieReleaseDates().add(releaseDate);
                else
                    moviesMap.get(movieName).setMovieReleaseDates(new ArrayList<>(Arrays.asList(releaseDate)));
            }
        }
        lines.clear();
    }

    private static void addLanguage() {
        ArrayList<String> lines = getLines(LanguageList);
        for(String line : lines){
            String movieName = line.split("\t")[0].trim();
//            out.println(languageName);
            if(moviesMap.containsKey(movieName)){
                String languageName = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getLanguages() != null)
                    moviesMap.get(movieName).getLanguages().add(languageName);
                else
                    moviesMap.get(movieName).setLanguages(new ArrayList<>(Arrays.asList(languageName)));
            }
        }
        lines.clear();
    }

    private static void addCountry() {
        ArrayList<String> lines = getLines(CountriesList);
        for(String line : lines){
//            System.out.println(line.split("\t")[0]);
            String movieName = line.split("\t")[0].trim();
            if(moviesMap.containsKey(movieName)){
                String countryName = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getCountries() != null)
                    moviesMap.get(movieName).getCountries().add(countryName);
                else
                    moviesMap.get(movieName).setCountries(new ArrayList<>(Arrays.asList(countryName)));
            }
        }
        lines.clear();
    }

    private static void writeMapIMDB() {
        try (Writer writer = new FileWriter("data//OutputAsMap.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(moviesMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeListIMDB() {
        try (Writer writer = new FileWriter("data//OutputAsList.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(moviesMap.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private static void addLargeIMDB() {
        ArrayList<String> lines = getLines(Large_IMDB);
//        out.println("imdb large size: " + lines.size());
        for(String line : lines){
            Movie movie = new Movie();
            String actorMovie[] = line.split(" ");
            if(actorMovie.length > 1){
                movie.setMovieActor(line.split(" ")[0].replaceAll("_"," ").trim());
                movie.setMovieName(line.split(" ")[1].replaceAll("_"," ").trim());
                moviesMap.put(movie.getMovieName(),movie);
            }
        }
        lines.clear();
//        out.println("addLargeIMDB Done.");
    }

    private static void addAllIMDBMovies() {
        ArrayList<String> lines = getLines(AllMovieList);
        for(String line : lines){
            String movieName = line.split("\t")[0].trim();
            if(!moviesMap.containsKey(movieName)){
                Movie movie = new Movie();
                movie.setMovieName(movieName);
                moviesMap.put(movieName, movie);
            }
        }
        lines.clear();
    }

    private static void addRatings() {
        ArrayList<String> lines = getLines(RatingsList);
        for(String line : lines) {
            String movieName = "";
            String rating = "empty";
            if(line.split("-_-_-").length > 3){//if there is distribution
                movieName = line.split("-_-_-")[3].trim();;
                rating = line.split("-_-_-")[2];
            }else if(line.split("-_-_-").length > 2){//if there is no distribution
//                out.println(line);
                movieName = line.split("-_-_-")[2].trim();;
                rating = line.split("-_-_-")[1];
            }
            if(moviesMap.containsKey(movieName)){
                moviesMap.get(movieName).setMovieRating(rating);
            }
        }
        lines.clear();
    }

    private static void addGenres() {
        ArrayList<String> lines = getLines(GenresList);
        int foundCount = 0;
        for(String line : lines){
//            double matchingPercent = 0.0;
//            String genreFinalName = "";
            String movieName = line.trim().split("\t")[0].trim();
//            String genreName = line.replace(movieName,"").trim();
//            if(moviesMap.containsKey(movieName)){
//                foundCount++;
////                out.println(foundCount);
//                moviesMap.get(movieName).setMovieGenre(genreName);
//            }
            if(moviesMap.containsKey(movieName)){
                String genreName = line.replace(movieName,"").trim();
                if(moviesMap.get(movieName).getGenres() != null)
                    moviesMap.get(movieName).getGenres().add(genreName);
                else
                    moviesMap.get(movieName).setGenres(new ArrayList<>(Arrays.asList(genreName)));
            }

        }
        lines.clear();
    }

//    private static void createGenresList(){
//        ArrayList<String> lines = getLines(GenresList);
//        HashMap<String, String> movieGenre = new HashMap<>();
//        for(String line : lines){
//            out.println(line.split("\\s+")[0]);
//            movieGenre.put()
//        }
//        lines.clear();
//    }


    private static ArrayList<String> getLines(String FILENAME){
        BufferedReader br = null;
        FileReader fr = null;

        ArrayList<String> lines = new ArrayList<>();

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
                lines.add(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

        return lines;
    }

    private static void SplitBigText(String FILENAME){
        BufferedReader br = null;
        FileReader fr = null;

        ArrayList<String> lines = new ArrayList<>();

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            int i = 0;
            int j = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                lines.add(sCurrentLine);
                i++;
                j++;
                if(i > 750000){
                    i = 0;
                    try (Writer writer = new FileWriter(FILENAME+"-"+j+".json")) {
//                        Gson gson = new GsonBuilder().create();
//                        gson.toJson(String.join("\n",lines), writer);
                        writer.write(String.join("\n",lines));
                        lines.clear();
                        lines = new ArrayList<>();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

}
