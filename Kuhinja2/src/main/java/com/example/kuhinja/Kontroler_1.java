package com.example.kuhinja;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Kontroler_1 {
    @FXML
    public Button buttonDomaca;
    @FXML
    private Button buttonKineska;
    @FXML
    public Button buttonItalijanska;
    @FXML
    private HBox glavniBox;

//    private ObradaPodataka obradaPodataka = new ObradaPodataka();

    public void otvaranjeNovogProzora(ActionEvent ae) {
        ObradaPodataka.getInstance().otvoriKonekciju();
        Parent root = null;
        try {
            if (ae.getSource().equals(buttonDomaca)) {
                ObradaPodataka.ID_KUHINJE = 1;
            } else if (ae.getSource().equals(buttonItalijanska)) {
                ObradaPodataka.ID_KUHINJE = 2;
            } else if (ae.getSource().equals(buttonKineska)) {
                ObradaPodataka.ID_KUHINJE = 3;
            }
            root = FXMLLoader.load(getClass().getResource("ListaJela_2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Stage stage = new Stage();
        stage.setTitle("Spisak jela");
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }
}

