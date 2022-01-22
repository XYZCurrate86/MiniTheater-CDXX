/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesplrs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Prayogi
 */
public class FXMLDocumentController implements Initializable {

    Parent root;
    private Label label;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnInputData;
    @FXML
    private Button btnEditData;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleHomeButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        root.getStylesheets().add("file:src/tubesplrs/Style.css");
        borderPane.setCenter(root);
    }

    @FXML
    private void handleInputButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Input.fxml"));
        root.getStylesheets().add("file:src/tubesplrs/Style.css");
        borderPane.setCenter(root);
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("update_data.fxml"));
        root.getStylesheets().add("file:src/tubesplrs/Style.css");
        borderPane.setCenter(root);
    }

    @FXML
    private void handleHomeButtonClickedAction(MouseEvent event) {
        root.getStylesheets().add("file:src/tubesplrs/Style.css");
        btnHome.setStyle("-fx-background-color:#008093");
        btnInputData.setStyle("-fx-background-color:#005E7A");
        btnEditData.setStyle("-fx-background-color:#005E7A");

    }

    @FXML
    private void handleInputClickedButtonAction(MouseEvent event) {
        root.getStylesheets().add("file:src/tubesplrs/Style.css");
        btnHome.setStyle("-fx-background-color:#005E7A");
        btnInputData.setStyle("-fx-background-color:#008093");
        btnEditData.setStyle("-fx-background-color:#005E7A");
    }

    @FXML
    private void handleEditClickedButtonAction(MouseEvent event) {
        root.getStylesheets().add("file:src/tubesplrs/Style.css");
        btnHome.setStyle("-fx-background-color:#005E7A");
        btnInputData.setStyle("-fx-background-color:#005E7A");
        btnEditData.setStyle("-fx-background-color:#008093");
    }

}
