package com.example.javafxdemo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addMovieController {
    public TextField title;
    public TextField year;
    public TextField genre;
    public TextField runtime;
    public TextField budget;
    public TextField revenue;
    Stage stage;
    Client myClient = new Client();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addButtonClick(ActionEvent actionEvent) {
        String titleText = title.getText();
        String yearText = year.getText();
        String [] genreText = genre.getText().split(",");

        String runtimeText = runtime.getText();
        String budgetText =  budget.getText();
        String revenueText = revenue.getText();
        System.out.println(myClient.getProductionCompanyName());
        Movie newMovie = new Movie(titleText,yearText,genreText[0],genreText[1],genreText[2],runtimeText,myClient.getProductionCompanyName(),budgetText,revenueText);
        newMovie.displayMovie();
        myClient.getReceivedList().addMovie(newMovie);
        myClient.addMovie(newMovie);
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745, 450);

        MenuController controller = fxmlLoader.getController();
        controller.setStage(stage);
        String css = this.getClass().getResource("menu.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
