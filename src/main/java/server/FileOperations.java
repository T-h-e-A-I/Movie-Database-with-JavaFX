package server;

import com.example.javafxdemo2.Movie;
import com.example.javafxdemo2.MovieList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileOperations {

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";


    public static List<Movie>  read() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        List<Movie> MovieList = new ArrayList();


        while (true) {

            String line = br.readLine();
            if (line == null) break;
            String[] MovieAttributes = line.split(",", 10);
            Movie m1 = new Movie(MovieAttributes[0],MovieAttributes[1], MovieAttributes[2], MovieAttributes[3], MovieAttributes[4],MovieAttributes[5], MovieAttributes[6], MovieAttributes[7], MovieAttributes[8]);

            MovieList.add(m1);
        }
        return MovieList;
    }

    public void write(MovieList saveList) throws IOException {
        Movie m = new Movie();
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(int i = 0;i < saveList.getMovieList().size();i++) {
            m = saveList.getMovieList().get(i) ;
            String text = m.getTitle()+","+m.getYearOfRelease()+","+m.getGenre1()+","+m.getGenre2()+","+m.getGenre3()+","+m.getRunningTime()+","+m.getProductionCompany()+","+m.getBudget()+","+m.getRevenue();
            //The Shawshank Redemption,1994,Drama,Crime,,142,Castle Rock Entertainment,25000000,28341469;
            bw.write(text);
            bw.write(System.lineSeparator());
        }
        bw.close();
    }


}