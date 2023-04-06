package com.example.javafxdemo2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchMovieViewController{

    Client myClient = new Client();
    public TextField SearchBox;
    Stage stage;
    LoginController myLogin = new LoginController();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
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
    int choice = 0;
    public void onTitleSearchClick(ActionEvent actionEvent) throws Exception {
        choice = 1;
        try{
            MovieToSearch = SearchBox.getText();
        }
        catch (Exception e){
            choice = 4;
        }
        this.load();

    }

    public void onGenreSearchClick(ActionEvent actionEvent) throws Exception {
        choice = 2;
        try {
            MovieToSearch = SearchBox.getText();
        }
       catch (Exception e){
            choice = 4;
       }
        this.load();
    }

    public void onYearSearchClick(ActionEvent actionEvent) throws Exception {
        choice = 3;
        try {
            YearToSearch = Integer.valueOf(SearchBox.getText());
        }
        catch (Exception e){
            choice = 4;
        }
        this.load();
    }
    public void onFullListClick(ActionEvent actionEvent) throws Exception {
        choice = 0;
        this.load();
    }
    //Table
    @FXML
    private TableView tableView;

    ObservableList<Movie> data;

    @FXML
    private Button button;

    private boolean init = true;

    public void initializeColumns() {
        TableColumn<Movie, String> firstNameCol = new TableColumn<>("Title");
        firstNameCol.setMinWidth(80);
        firstNameCol.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getTitle()));

        TableColumn<Movie, String> firstNameCol1 = new TableColumn<>("YearOfRelease");
        firstNameCol1.setMinWidth(80);
        firstNameCol1.setCellValueFactory(cellData-> new SimpleStringProperty(""+ cellData.getValue().getYearOfRelease()));

        TableColumn<Movie, String> firstNameCol2 = new TableColumn<>("Genre1");
        firstNameCol2.setMinWidth(80);
        firstNameCol2.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getGenre1()));

        TableColumn<Movie, String> firstNameCol3 = new TableColumn<>("Genre2");
        firstNameCol3.setMinWidth(80);
        firstNameCol3.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getGenre2()));

        TableColumn<Movie, String> firstNameCol4 = new TableColumn<>("Genre3");
        firstNameCol4.setMinWidth(80);
        firstNameCol4.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getGenre3()));

        TableColumn<Movie, String> firstNameCol5 = new TableColumn<>("RunningTime");
        firstNameCol5.setMinWidth(80);
        firstNameCol5.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().getRunningTime()));


        TableColumn<Movie, String> firstNameCol7 = new TableColumn<>("Budget");
        firstNameCol7.setMinWidth(80);
        firstNameCol7.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().getBudget()));

        TableColumn<Movie, String> firstNameCol8 = new TableColumn<>("Revenue");
        firstNameCol8.setMinWidth(80);
        firstNameCol8.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().getRevenue()));


        tableView.getColumns().addAll(firstNameCol,firstNameCol1,firstNameCol2,firstNameCol3,firstNameCol4,firstNameCol5,firstNameCol7,firstNameCol8);
    }

    Movie m = new Movie();
    List<Movie> Mylist = new ArrayList<>();
    List<Movie> search = new ArrayList<>();
    String MovieToSearch;
    int YearToSearch;
    public void load() throws Exception{
        MovieList searchMovies = myClient.getReceivedList();
        search = searchMovies.getMovieList();
        if(choice == 1) search = searchMovies.searchbyName(MovieToSearch);
        if(choice == 2) search = searchMovies.searchByGenre(MovieToSearch);
        if(choice == 3) search = searchMovies.searchByYear(YearToSearch);
        if(choice == 4) search = new ArrayList<>();
        data = FXCollections.observableArrayList(
                search
        );
        tableView.setEditable(true);
        tableView.setItems(data);
        if (init) {
            initializeColumns();
            init = false;
        }


    }
}
