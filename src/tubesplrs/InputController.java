/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesplrs;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import tubesplrs.helper.DBHelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InputController {

    private Connection connection;

    private Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TextField txtTgl;

    @FXML
    private TextField txtKelas;

    @FXML
    private TextField txtRuangan;

    @FXML
    private TextField txtStatus;

    @FXML
    private Button proses;

    @FXML
    void OnButtonAddClicked(ActionEvent event) throws SQLException, ClassNotFoundException {

        boolean txtEmptyResult = isInputEmpty(
                txtNama, txtAlamat, txtRuangan, txtTgl, txtKelas, txtStatus
        );

        if (txtEmptyResult) {
            alertWarning.setTitle("!!Warning");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Textfield tidak boleh kosong!!");

            alertWarning.showAndWait();
        } else {
            String nama = txtNama.getText();
            String alamat = txtAlamat.getText();
            String tanggal = txtTgl.getText();
            String kelas = txtKelas.getText();
            int ruangan = Integer.valueOf(txtRuangan.getText());
            String status = txtStatus.getText();

            boolean validValue = isValueValid(ruangan);

            if (validValue) {
                writeToDB(nama, alamat, tanggal, kelas, ruangan, status);

                alertInformation.setTitle("!!Input Sukses");
                alertInformation.setHeaderText(null);
                alertInformation.setContentText("Data berhasil ditambahkan ke Database!");

                alertInformation.showAndWait();

                txtNama.clear();
                txtAlamat.clear();
                txtTgl.clear();
                txtRuangan.clear();
                txtKelas.clear();
                txtStatus.clear();
            } else {
                alertWarning.setTitle("!!Warning");
                alertWarning.setHeaderText(null);
                alertWarning.setContentText("Value yang dimasukkan salah!");

                alertWarning.showAndWait();
                txtRuangan.clear();
            }
        }
    }


    private boolean isValueValid(int ruangan) {
        return validValue(ruangan);
    }

    private boolean validValue(int value) {
        return value >= 0 && value <= 100;
    }

    @FXML
    void initialize () throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper();
        connection = dbHelper.getConnection();
    }

    private void writeToDB(String nama, String alamat, String tanggal, String kelas, int ruangan, String status)
            throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO pasien(nama, alamat, tanggal, kelas, ruangan, status) VALUES(?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, nama);
        preparedStatement.setString(2, alamat);
        preparedStatement.setString(3, tanggal);
        preparedStatement.setString(4, kelas);
        preparedStatement.setInt(5, ruangan);
        preparedStatement.setString(6, status);


        preparedStatement.executeUpdate();
    }

    private boolean isInputEmpty(TextField nama, TextField alamat, TextField ruangan, TextField tgl, TextField kelas, TextField status) {
        return isTextFieldEmpty(nama) || isTextFieldEmpty(ruangan) || isTextFieldEmpty(alamat)
                || isTextFieldEmpty(kelas) || isTextFieldEmpty(tgl) || isTextFieldEmpty(status);
    }

    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().equals("");
    }

}

