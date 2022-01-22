package tubesplrs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import tubesplrs.helper.DBHelper;
import tubesplrs.pasien.pasien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class homeController {

    private ObservableList<pasien> listOfPasien = FXCollections.observableArrayList();

    @FXML
    private TableView<pasien> tblShowPasien;

    @FXML
    private TableColumn<pasien, String> namaCol;

    @FXML
    private TableColumn<pasien, String> alamatCol;

    @FXML
    private TableColumn<pasien, String> tglCol;

    @FXML
    private TableColumn<pasien, String> kelasCol;

    @FXML
    private TableColumn<pasien, Integer> ruanganCol;

    @FXML
    private TableColumn<pasien, String> statusCol;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper();
        Connection connection = dbHelper.getConnection();

        listOfPasien = readDB(connection);

        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<>("Alamat"));
        tglCol.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        kelasCol.setCellValueFactory(new PropertyValueFactory<>("Kelas"));
        ruanganCol.setCellValueFactory(new PropertyValueFactory<>("Ruangan"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));

        tblShowPasien.setItems(listOfPasien);
    }
    public ObservableList<pasien> readDB(Connection connection) throws SQLException {

        String query = "SELECT * FROM pasien";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<pasien> allPasien = FXCollections.observableArrayList();

        while (resultSet.next()) {
            pasien Pasien = new pasien(
                    resultSet.getInt("id"),
                    resultSet.getString("nama"),
                    resultSet.getString("alamat"),
                    resultSet.getString("tanggal"),
                    resultSet.getString("kelas"),
                    resultSet.getInt("ruangan"),
                    resultSet.getString("status")
            );
            allPasien.add(Pasien);
        }

        return allPasien;
    }

}
