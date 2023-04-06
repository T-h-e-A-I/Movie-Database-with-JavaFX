package com.example.javafxdemo2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MovieList implements Serializable{

    //constructor
    public MovieList(List Movielist) throws IOException {
        this.MovieList = Movielist;
    }

    //File Handling

    public static int attributeNumber = 10;
    private List<Movie> MovieList = new ArrayList();

    public List<Movie> getMovieList() {
        return MovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        MovieList = movieList;
    }

    public List<Movie> searchbyName(String searchName){
        List<Movie> MovieListName = new ArrayList();
        int searchIndex = -1;
        for (int i = 0; i < this.MovieList.size(); i++) {
            Movie t = this.MovieList.get(i);
            if (t.getTitle().equalsIgnoreCase(searchName)) {
                searchIndex = i;
                MovieListName.add(t);
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such movie with this name\n");
        }

        return MovieListName;
    }
    public Movie searchbyNameMovie(String searchName){
        Movie MovieListName = new Movie();
        int searchIndex = -1;
        for (int i = 0; i < this.MovieList.size(); i++) {
            Movie t = this.MovieList.get(i);
            if (t.getTitle().equalsIgnoreCase(searchName)) {
                searchIndex = i;
                MovieListName = t;
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such movie with this name\n");
        }

        return MovieListName;
    }
    public boolean NameExists(String searchName){
        int searchIndex = -1;
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            if (t.getTitle().equalsIgnoreCase(searchName)) {
                searchIndex = i;
            }
        }
        if (searchIndex == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    public List<Movie> searchByYear(int searchYear){
        List <Movie> MovieByYear = new ArrayList<>();
        int searchIndex = -1;
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            if (t.getYearOfRelease() == searchYear) {
                searchIndex = i;
                MovieByYear.add(t);
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such movie with this release year\n");
        }
        return MovieByYear;
    }
    public List<Movie> searchByGenre(String searchGenre) {
        List<Movie> GenreSearch = new ArrayList<>();
        int searchIndex = -1;
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            String [] genres = t.getGenre();
            if (genres[0].equalsIgnoreCase(searchGenre) || genres[1].equalsIgnoreCase(searchGenre) || genres[2].equalsIgnoreCase(searchGenre)) {
                searchIndex = i;
                GenreSearch.add(t);
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such movie with this genre\n");
        }
        return GenreSearch;
    }
    public List<Movie> searchbyCompany(String searchCompany){
        List<Movie> MovieListCompany = new ArrayList();
        int searchIndex = -1;
        for (int i = 0; i < this.MovieList.size(); i++) {
            Movie t = this.MovieList.get(i);
            if (t.getProductionCompany().equalsIgnoreCase(searchCompany)) {
                searchIndex = i;
                MovieListCompany.add(t);
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such production company with this name\n");
        }
        return MovieListCompany;
    }
    private void searchByRuntime(int upper, int lower) {
        int searchIndex = -1;
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            if (t.getRunningTime() <= upper && t.getRunningTime() >= lower) {
                searchIndex = i;
                t.displayMovie();
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such movie with this running time range\n");
        }
    }

    public void transferMovie(String sender, Movie sendMovie, String receiver) {
        sendMovie.setProductionCompany(receiver);
    }




    class SortbyProfit implements Comparator<Movie>{
        public int compare(Movie a,Movie b){
            return b.findProfit() - a.findProfit();
        }
    }
    class SortbyProductionCompany implements Comparator<Movie>{
        public int compare(Movie a,Movie b){
            return a.getProductionCompany().compareTo(b.getProductionCompany());
        }
    }
    public void top3movies() {
        MovieList.sort(new SortbyProfit());
        for(int i = 0;i < 3;i++){
            MovieList.get(i).displayMovie();
        }
    }
    public Movie recentMovies(String searchCompany) {
        Movie m = new Movie();
        int searchIndex = -1;
        int maxYear = Integer.MIN_VALUE;
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            if (t.getProductionCompany().equalsIgnoreCase(searchCompany)) {
                searchIndex = i;
                if (t.getYearOfRelease() > maxYear) maxYear = t.getYearOfRelease();
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such production company with this name\n");
        } else {
            for (int i = 0; i < MovieList.size(); i++) {
                Movie t = MovieList.get(i);
                if (t.getProductionCompany().equalsIgnoreCase(searchCompany) && t.getYearOfRelease() == maxYear) {
                    m = t;
                }
            }
        }
        return m;
    }
    public Movie MaxRevenue(String searchCompany) {
        int searchIndex = -1;
        int maxRevenue = Integer.MIN_VALUE;
        Movie m = new Movie();
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            if (t.getProductionCompany().equalsIgnoreCase(searchCompany)) {
                searchIndex = i;
                if (t.getRevenue() > maxRevenue) maxRevenue = t.getRevenue();
            }
        }
        if (searchIndex == -1) {
            System.out.println("\nNo such production company with this name\n");
        } else {
            for (int i = 0; i < MovieList.size(); i++) {
                Movie t = MovieList.get(i);
                if (t.getProductionCompany().equalsIgnoreCase(searchCompany) && t.getRevenue() == maxRevenue) {
                    m = t;
                }
            }
        }
        return m;
    }
    public long totalProfit(String searchCompany){
        int searchIndex = -1;
        long totalProfit = 0;
        for (int i = 0; i < MovieList.size(); i++) {
            Movie t = MovieList.get(i);
            if (t.getProductionCompany().equalsIgnoreCase(searchCompany)) {
                searchIndex = i;
                totalProfit += t.findProfit();
            }
        }
        return totalProfit;
    }
    public  void addMovie(Movie movie){
        MovieList.add(movie);
    }
    public void totalMovies(){
        MovieList.sort(new SortbyProductionCompany());
        int movieCount = 0;
        int i;
        for(i = 0;i < MovieList.size()-1;i++){
            Movie t = MovieList.get(i);
            Movie u = MovieList.get(i+1);
            movieCount++;
            if(!(t.getProductionCompany().equalsIgnoreCase(u.getProductionCompany()))){
                System.out.println("Total Number of Movies Produced by " + t.getProductionCompany() + " is " + movieCount);
                movieCount = 0;
            }
        }
        System.out.println("Total Number of Movies Produced by " + MovieList.get(i).getProductionCompany() + " is " + ++movieCount);
    }
    public static final String OUTPUT_FILE_NAME = "movies.txt";
    /*public static  Scanner userinput = new Scanner(System.in);
    public static  Scanner userinputNAME = new Scanner(System.in);

    public static void main(String[] args) throws Exception{

        //FileHandling
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME,true));

        Movie M = new Movie();

        MovieList m = new MovieList(M.LoadMovies());
        while (true){
            System.out.println("Main Menu:");
            System.out.println("1) Search Movies");
            System.out.println("2) Search Production Companies");
            System.out.println("3) Add Movie");
            System.out.println("4) Exit System");
            int input = userinput.nextInt();
            if(input == 1){
                while (true) {
                    System.out.println("Movie Searching Options: \n1) By Movie Title \n2) By Release Year \n3) By Genre \n4) By Production Company \n5) By Running Time \n6) Top 10 Movies \n7) Back to Main Menu");
                    int input2 = userinput.nextInt();
                    if(input2 == 1){
                        System.out.println("Please Enter the Movie Name: ");
                        String searchName = userinputNAME.nextLine();
                        m.searchbyName(searchName);

                    } else if(input2 == 2){
                        System.out.println("Please Enter a Release Year: ");
                        int searchYear = userinput.nextInt();
                        m.searchByYear(searchYear);

                    } else if (input2 == 3) {
                        System.out.println("Please Enter a Genre: ");
                        String searchGenre = userinputNAME.nextLine();
                        m.searchByGenre(searchGenre);

                    } else if(input2 == 4){
                        System.out.println("Please Enter a Production Company Name: ");
                        String searchCompany = userinputNAME.nextLine();
                        m.searchbyCompany(searchCompany);


                    } else if(input2 == 5){
                        System.out.println("Please Enter the lower runtime limit and upper runtime limit in minutes: ");
                        int lower = userinput.nextInt();
                        int upper = userinput.nextInt();
                        m.searchByRuntime(upper,lower);


                    } else if(input2 == 6){
                        m.top3movies();

                    } else if(input2 == 7) break;
                    else{
                        System.out.println("\nInvalid Input\n");
                    }
                }

            } else if(input == 2){
                while (true) {
                    System.out.println("Production Company Searching Options: \n1) Most Recent Movies \n2) Movies with the Maximum Revenue \n3) Total Profit \n4) List of Production Companies and the Count of their Produced Movies \n5) Back to Main Menu");
                    int input3 = userinput.nextInt();
                    if(input3 == 1){
                        System.out.println("Please Enter a Production Company Name: ");
                        String searchCompany = userinputNAME.nextLine();
                        m.recentMovies(searchCompany);

                    } else if (input3 == 2) {
                        System.out.println("Please Enter a Production Company Name: ");
                        String searchCompany = userinputNAME.nextLine();
                        m.MaxRevenue(searchCompany);

                    } else if (input3 == 3) {
                        System.out.println("Please Enter a Production Company Name: ");
                        String searchCompany = userinputNAME.nextLine();
                        m.totalProfit(searchCompany);

                    } else if (input3 == 4) {
                        m.totalMovies();
                    } else if(input3 == 5) break;
                    else
                        System.out.println("\nInvalid Input\n");

                }
            } /*else if(input == 3){
                System.out.println("Please Enter Movie Title: ");
                String title = userinputNAME.nextLine();
                if(!m.NameExists(title)) {
                    System.out.println("Please Enter the year of release: ");
                    int year = userinput.nextInt();
                    System.out.println("How many Genres do you want to enter? (maximum 3 minimum 1): ");
                    int noOfgenre = userinput.nextInt();
                    String genre[] = new String[3];
                    genre[0] = "";
                    genre[1] = "";
                    genre[2] = "";
                    for (int i = 0; i < noOfgenre; i++) {
                        String cardinal = "";
                        if (i == 0) cardinal = "1st";
                        else if (i == 1) cardinal = "2nd";
                        else if (i == 2) cardinal = "3rd";
                        System.out.println("Please Enter the " + cardinal + " genre: ");
                        String genreInput = userinputNAME.nextLine();
                        genre[i] = genreInput;
                    }
                    System.out.println("Please Enter running time: ");
                    int runtime = userinput.nextInt();
                    System.out.println("Please Enter the production company: ");
                    String productionCompany = userinputNAME.nextLine();
                    System.out.println("Please Enter the Budget: ");
                    int budget = userinput.nextInt();
                    System.out.println("Please Enter the Revenue");
                    int revenue = userinput.nextInt();
                    String text = title + "," + year + "," + genre[0] + "," + genre[1] + "," + genre[2] + "," + runtime + "," + productionCompany + "," + budget + "," + revenue;
                    String[] MovieAttributes = text.split(",", attributeNumber);
                    Movie m2 = new Movie(MovieAttributes[0], MovieAttributes[1], MovieAttributes[2], MovieAttributes[3], MovieAttributes[4], MovieAttributes[5], MovieAttributes[6], MovieAttributes[7], MovieAttributes[8]);
                    MovieList.add(m2);
                    bw.write(text);
                    bw.write(System.lineSeparator());
                    bw.close();
                }
                else{
                    System.out.println("Movie already exists!");
                }
            } else if(input == 4){
                System.exit(0);
            }
            else
                System.out.println("\nInvalid Input\n");
        }
    }*/


}


