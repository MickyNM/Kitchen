package com.example.kuhinja;

import com.example.kuhinja.podaci.ListaJela_2;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Optional;

public class KontrolerDodavanje_3 {
    @FXML
    private TextField nazivJelaField;
    //    @FXML
//    private TextField idKuhinjeField;
    @FXML
    private TextArea sastojciJelaArea;
    @FXML
    private TextField velicinaPorcijeField;
//    private ObradaPodataka obradaPodataka = new ObradaPodataka();

    @FXML
    public ListaJela_2 ubaciJelo() {
        String naziv = nazivJelaField.getText().trim();
//        int id_kuhinje = Integer.parseInt(idKuhinjeField.getText().trim());
        String sastojci = sastojciJelaArea.getText().trim();
        int velicina_porcije;
        if (velicinaPorcijeField.getText().trim().isEmpty()) {
            velicina_porcije = 0;
        } else {
            velicina_porcije = Integer.parseInt(velicinaPorcijeField.getText().trim());
        }
        ListaJela_2 listaJela_2 = new ListaJela_2(naziv, sastojci, velicina_porcije);
        ObradaPodataka.getInstance().otvoriKonekciju();

        if (!naziv.isEmpty() && !sastojci.isEmpty() && velicina_porcije != 0) {
            ObradaPodataka.getInstance().dodajJela(listaJela_2);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Greska!");
            alert.setContentText("Morate uneti sve stavke");
            Optional<ButtonType> rezultat1 = alert.showAndWait();
        }
        return listaJela_2;
    }
}

