package com.example.javafxdemo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class MenuController {
    Client myClient = new Client();
    Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onLogoutButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("login-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 745, 450);

        LoginController controller = fxmlLoader.getController();

        controller.setStage(stage);
        String css = this.getClass().getResource("login-application.css").toExternalForm();
        scene.getStylesheets().add(css);
        myClient.save();
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
    public static boolean loading = false;
    @FXML
    public void onSearchClick(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("search-movie-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745, 450);
        SearchMovieViewController controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.load();
        String css = this.getClass().getResource("search-movie.css").toExternalForm();
        scene.getStylesheets().add(css);
        new Thread(()-> {
            System.out.println("In thread");
            while(true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(loading == true){
                    try {
                        System.out.println("Paisi");
                        controller.load();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }finally {
                        loading = false;
                    }

                }
            }
        }
        ).start();
        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }
    public static TransferController transferController;

    public static TransferController getTransferController() {
        return transferController;
    }

    public void onTransferClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("transfer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745.0 ,450.0);

        transferController = fxmlLoader.getController();
        transferController.setStage(stage);
        String css = this.getClass().getResource("transfer-view.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Transfer");
        stage.setScene(scene);
        stage.show();
    }

    public void onTotalProfitClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("total-profit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745, 450);

        TotalProfitController controller = fxmlLoader.getController();
        controller.onClickToSee();
        controller.setStage(stage);
        String css = this.getClass().getResource("total-profit.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Movie Stats");
        stage.setScene(scene);
        stage.show();
    }

    public void addMovieClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("add-movie-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745, 450);

        addMovieController controller = fxmlLoader.getController();
        controller.setStage(stage);
        String css = this.getClass().getResource("add.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Add Movie");
        stage.setScene(scene);
        stage.show();

    }
}
