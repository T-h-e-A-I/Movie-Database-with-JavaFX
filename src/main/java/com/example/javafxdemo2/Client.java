package com.example.javafxdemo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import server.Datawrapper;
import server.SocketWrapper;


import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client{
    Stage stage;
    public Thread t;
    boolean transferSwitch = false;
    static String productionCompanyName = new String();
    static MovieList receivedList;
    boolean noMovie = false;
    MenuController menu;

    Client(){

    }

    public MovieList getReceivedList() {
        return receivedList;
    }

    public boolean isNoMovie() {
        return noMovie;
    }

    public String getProductionCompanyName() {return productionCompanyName;}

    public void setProductionCompanyName(String productionCompanyName) {this.productionCompanyName = productionCompanyName;}

    public static SocketWrapper server;

    public void call() throws IOException {

                server = new SocketWrapper("127.0.0.1",3000);
                Scanner sc = new Scanner(System.in);

                System.out.println(productionCompanyName);

                server.write(productionCompanyName);
                try {
                    Object data = server.read();
                    receivedList = (MovieList) data;
                    if(receivedList.getMovieList().isEmpty()) noMovie = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


                new Thread(() -> {
                    while (true) {
                        try {
                            Object data = server.read();
                            Datawrapper dw = (Datawrapper) data;
                            if (dw.command.equalsIgnoreCase("MovieList")) {
                                receivedList = (MovieList) dw.data;
                                if (receivedList.getMovieList().isEmpty()) System.out.println("Movie Nai");
                                else System.out.println("Movie Paisi");
                            }
                            if(dw.command.equalsIgnoreCase("MoviePathaisi")) {
                                String movie = (String) dw.data;
                                menu.loading = true;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();

        }
        public void save(){
            Datawrapper dw = new Datawrapper("save",null);
            try {
                server.write(dw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void transfer(String movie, String receiver) {
            new Thread(() -> {
                transferSwitch = true;
                String transferMessage = getProductionCompanyName() + "->" + movie + "->" + receiver;
                Datawrapper dw = new Datawrapper("Transfer", transferMessage);
                try {
                    server.write(dw);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                /*try {
                    Object data = server.read();
                    receivedList = (MovieList) data;
                    TransferController transferController = new TransferController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }*/
            }
            ).start();
        }

    public void addMovie(Movie newMovie) {
        Datawrapper dw = new Datawrapper("add",newMovie);
        try {
            server.write(dw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
