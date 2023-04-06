package com.example.javafxdemo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public TextField companyName;
    public TextField Pass;
    public Label noMovie;
    Stage stage;
    static String productionCompany;
    List<Movie> prodCompany;

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }


    Movie m = new Movie();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws Exception{


        try {
            productionCompany = companyName.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(LOGIN_APPLICATION.class.getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 745, 450);

            MenuController controller = fxmlLoader.getController();

            //controller.setStage(stage);
            Client app = new Client();
            app.setProductionCompanyName(productionCompany);
            app.call();
            if(app.isNoMovie()){
                noMovie.setText("Invalid Login");
            }else {
                controller.setStage(stage);
                String css = this.getClass().getResource("menu.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){
            noMovie.setText("Invalid Login");
        }

    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}