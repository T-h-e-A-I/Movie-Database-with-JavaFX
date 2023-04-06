package com.example.javafxdemo2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TransferController implements Initializable{

    @FXML
    public  ListView<String> myListView;
    public static String currentMovie;
    public Label transferLabel;
    public Button transfer;
    public TextField receiverCompany;

    public ListView<String> getMyListView() {
        return myListView;
    }

    public void setMyListView(ListView<String> myListView) {
        this.myListView = myListView;
    }

    static Stage stage;
    Client myClient = new Client();

    public Stage getStage() {
        return stage;
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
    String receiver;
    public void onTransferClick(ActionEvent actionEvent) throws InterruptedException {
        receiver = receiverCompany.getText();
        System.out.println(currentMovie);
        myClient.transfer(currentMovie,receiver);
        myListView.getItems().remove(currentMovie);
    }



        public String[] MakeList () {

        List<Movie> myMovies = new ArrayList<>();
        myMovies = myClient.getReceivedList().getMovieList();


        String[] nameMovie = new String[myMovies.size()];
        for (int i = 0; i < myMovies.size(); i++) {
            nameMovie[i] = myMovies.get(i).getTitle();
        }
        return nameMovie;
    }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){


        myListView.getItems().addAll(MakeList());
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                currentMovie = myListView.getSelectionModel().getSelectedItem();
                transferLabel.setText(currentMovie);
            }
        });

    }
    public void addItem(String movie){
        myListView.getItems().add(movie);
    }
}
