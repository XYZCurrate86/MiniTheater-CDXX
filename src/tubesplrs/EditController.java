/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesplrs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tubesplrs.helper.DBHelper;
import tubesplrs.pasien.pasien;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Prayogi
 */
public class EditController{

    private final Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    private final Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    private homeController tblController;
    private Connection connection;

    private ObservableList<pasien> listOfPasien;
    private PreparedStatement preparedStatement;
    private pasien Pasien;


    @FXML
    private TableView<pasien> tblShowPasien;

    @FXML
    private TableColumn<pasien, Integer> idCol;

    @FXML
    private TableColumn<pasien, String> namaCol;

    @FXML
    private TableColumn<pasien, String> kelasCol;

    @FXML
    private TableColumn<pasien, Integer> ruanganCol;

    @FXML
    private TableColumn<pasien, String> statusCol;

    @FXML
    private TextField namaTxt;

    @FXML
    private TextField kelasTxt;

    @FXML
    private TextField ruanganTxt;

    @FXML
    private TextField statusTxt;

    @FXML
    private Button buttonUpdate;

    @FXML
    void onButtonUpdateClicked(ActionEvent event) throws SQLException {
        boolean resultTrue = isTextFieldCorrect(namaTxt, kelasTxt,
                ruanganTxt, statusTxt);

        if (resultTrue){
            alertWarning.setTitle("!!Warning");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Textfield tidak boleh kosong!!");

            alertWarning.showAndWait();
        }else{
            String query = "UPDATE pasien set nama = ?, kelas = ?, ruangan= ?, status = ? where id = ?";


            int idPasien = Pasien.getId();
            String nama = namaTxt.getText();
            String kelas = kelasTxt.getText();
            int ruangan = Integer.valueOf(ruanganTxt.getText());
            String status = statusTxt.getText();

            alertInformation.setTitle("!!!Update Sukses");
            alertInformation.setHeaderText(null);
            alertInformation.setContentText("Data berhasil diubah!!");

            alertInformation.showAndWait();

            tblShowPasien.refresh();
            tblShowPasien.getSelectionModel().select(null);

            namaTxt.clear();
            kelasTxt.clear();
            ruanganTxt.clear();
            statusTxt.clear();

            namaTxt.setDisable(true);
            kelasTxt.setDisable(true);
            ruanganTxt.setDisable(true);
            statusTxt.setDisable(true);
            buttonUpdate.setDisable(true);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, kelas);
            preparedStatement.setInt(3, ruangan);
            preparedStatement.setString(4, status);
            preparedStatement.setInt(5, idPasien);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            listOfPasien = tblController.readDB(connection);
            tblShowPasien.setItems(listOfPasien);
        }
    }

    @FXML
    void onTableClicked(MouseEvent event) {
        Pasien = tblShowPasien.getSelectionModel().getSelectedItem();

        namaTxt.setDisable(false);
        kelasTxt.setDisable(false);
        ruanganTxt.setDisable(false);
        statusTxt.setDisable(false);
        buttonUpdate.setDisable(false);

        namaTxt.setText(String.valueOf(Pasien.getNama()));
        kelasTxt.setText(String.valueOf(Pasien.getKelas()));
        ruanganTxt.setText(String.valueOf(Pasien.getRuangan()));
        statusTxt.setText(String.valueOf(Pasien.getStatus()));

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        DBHelper dbHelper = new DBHelper();
        connection = dbHelper.getConnection();

        namaTxt.setDisable(true);
        kelasTxt.setDisable(true);
        ruanganTxt.setDisable(true);
        statusTxt.setDisable(true);
        buttonUpdate.setDisable(true);

        tblController = new homeController();
        listOfPasien = tblController.readDB(connection);

        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        kelasCol.setCellValueFactory(new PropertyValueFactory<>("Kelas"));
        ruanganCol.setCellValueFactory(new PropertyValueFactory<>("Ruangan"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));


        tblShowPasien.setItems(listOfPasien);
    }

    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().equals("");
    }

    private boolean isTextFieldCorrect(TextField namaTxt, TextField kelasTxt, TextField ruanganTxt, TextField statusTxt) {

        return isTextFieldEmpty(namaTxt) || isTextFieldEmpty(kelasTxt) || isTextFieldEmpty(ruanganTxt)
                || isTextFieldEmpty(statusTxt);
    }
}
