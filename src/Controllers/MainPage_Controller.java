package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPage_Controller implements Initializable {
    ObservableList<String> list = FXCollections.observableArrayList("Category", "Amount");
    @FXML
    AnchorPane switch_pane, PANE;
    @FXML
    VBox main_pane;
    @FXML
    HBox lib_box;
    @FXML
    HBox emp_box;
    @FXML
    HBox bor_box;
    @FXML
    HBox book_box;
    @FXML
    HBox add_client;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void handleAddBookAction(javafx.event.Event actionEvent) throws Exception {
        bor_box.setStyle("-fx-background-color: #00001f;");

        lib_box.setStyle("-fx-background-color: #00001f;");
        add_client.setStyle("-fx-background-color: #00001f;");
        book_box.setStyle("-fx-background-color: green;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/manage_book.fxml"));
        switch_pane.getChildren().setAll(pane);
    }

    @FXML
    public void handleBorrowBookAction(javafx.event.Event actionEvent) throws Exception {
        book_box.setStyle("-fx-background-color: #00001f;");

        lib_box.setStyle("-fx-background-color: #00001f;");
        add_client.setStyle("-fx-background-color: #00001f;");
        bor_box.setStyle("-fx-background-color: green;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/borrow_book.fxml"));
        switch_pane.getChildren().setAll(pane);
    }

    @FXML
    public void handleAddClientAction(javafx.event.Event actionEvent) throws Exception {
        book_box.setStyle("-fx-background-color: #00001f;");

        lib_box.setStyle("-fx-background-color: #00001f;");
        bor_box.setStyle("-fx-background-color: #00001f;");
        add_client.setStyle("-fx-background-color: green;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/manage_clients.fxml"));
        switch_pane.getChildren().setAll(pane);
    }

    @FXML
    public void btnlogoutAction(javafx.event.Event actionEvent) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/Login_Form.fxml"));
        PANE.getChildren().setAll(pane);

    }


}