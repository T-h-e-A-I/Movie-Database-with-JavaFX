package com.example.javafxdemo2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LOGIN_APPLICATION extends Application {
    static LoginController controller;
    static LOGIN_APPLICATION app;
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745, 450);

        controller = fxmlLoader.getController();
        controller.setStage(stage);
        String css = this.getClass().getResource("login-application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onsearchReload() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("search-movie-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745, 450);
        SearchMovieViewController controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.load();
        String css = this.getClass().getResource("search-movie.css").toExternalForm();
        scene.getStylesheets().add(css);


        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }
    /*public void transferPageLoad() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("transfer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600.0 ,400.0);

        TransferController transferController = fxmlLoader.getController();
        transferController.setStage(stage);
        stage.setTitle("Sort");
        stage.setScene(scene);
        stage.show();
    }*/
    public static void main(String[] args) {
        launch();
    }
}