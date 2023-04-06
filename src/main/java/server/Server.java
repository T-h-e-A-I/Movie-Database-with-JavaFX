package server;
import com.example.javafxdemo2.Movie;
import com.example.javafxdemo2.MovieList;
import com.example.javafxdemo2.TransferMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serversocket = new ServerSocket(3000);
        FileOperations File = new FileOperations();
        List<Movie> totalMovie;
        try {
            totalMovie = File.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MovieList totalList = new MovieList(totalMovie);
        List<Movie> companyMovies = null;

        System.out.println("Server Started");

        HashMap<String,SocketWrapper> clientMap = new HashMap<>();



        while (true){
            Socket clientSocket = serversocket.accept();
            SocketWrapper client = new SocketWrapper(clientSocket);


            new Thread(()-> {




                try {
                    String productionCompany = (String) client.read();
                    boolean noMovie = true;
                    MovieList companyList = new MovieList(totalList.searchbyCompany(productionCompany));
                    client.write(companyList);
                    if(!companyList.getMovieList().isEmpty()) {
                        System.out.println(productionCompany + " Connected");
                        clientMap.put(productionCompany, client);

                    }
                    while (true){
                        Object data = client.read();
                        Datawrapper dw = (Datawrapper) data;
                        if(dw.command.equalsIgnoreCase("transfer")){
                            String transferMessage = (String) dw.data;
                            String [] tokens = transferMessage.split("->");
                            String sender = tokens[0];
                            String movie = tokens[1];
                            String receiver = tokens[2];
                            Movie sendMovie = totalList.searchbyNameMovie(movie);
                            companyList.transferMovie(sender,sendMovie,receiver);
                            MovieList afterTransfer = new MovieList(totalList.searchbyCompany(sender));
                            Datawrapper dw2 = new Datawrapper ("MovieList",afterTransfer);
                            client.write(dw2);
                            SocketWrapper receiverClient = clientMap.get(receiver);
                            MovieList afterReceive = new MovieList(totalList.searchbyCompany(receiver));
                            Datawrapper dw3 = new Datawrapper ("MovieList",afterReceive);
                            Datawrapper dw4 = new Datawrapper("MoviePathaisi",movie);
                            receiverClient.write(dw3);
                            receiverClient.write(dw4);
                        }
                        if(dw.command.equalsIgnoreCase("add")){
                            Movie newMovie = (Movie) dw.data;
                            totalList.addMovie(newMovie);
                            //totalList.searchbyNameMovie(newMovie.getTitle()).displayMovie();
                        }
                        if(dw.command.equalsIgnoreCase("save")){
                            File.write(totalList);
                        }
                    }
                } catch (IOException e) {
                    try {
                        File.write(totalList);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("Client Not Found");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        client.closeConnection();
                    } catch (IOException e) {
                        System.out.println("Disconnected");
                    }
                }


            }).start();
        }
    }
}
