package com.example.javafxdemo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class TotalProfitController {
    public Label mostRecent;
    public Label HighestProfit;
    public Label profit11;
    public Label profit1;
    Client myClient = new Client();
    Stage stage;
    LoginController myLogin = new LoginController();

    public Stage getStage() {
        return stage;
    }
    public Label profit;

    public Label getProfit() {
        return profit;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
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
    Long Profit;
    MovieList MyMovie;
    public void onClickToSee() {
        try {
            MyMovie = myClient.getReceivedList();
            Profit = MyMovie.totalProfit(myClient.getProductionCompanyName());
            String label = "" + Profit;
            profit.setText(label);
            HighestProfit.setText(MyMovie.MaxRevenue(myClient.getProductionCompanyName()).getTitle());
            mostRecent.setText(MyMovie.recentMovies(myClient.getProductionCompanyName()).getTitle());

        }
        catch (Exception e){
            if(MyMovie == null) System.out.println("List Khali");
            profit.setText("Cannot show profit");
        }
    }
}
