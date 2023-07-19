package com.example.kuhinja;

import com.example.kuhinja.podaci.ListaJela_2;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class KontrolerAzuriranje_4 {
    @FXML
    private TextField nazivJelaField;
    //    @FXML
//    private TextField idKuhinjeField;
    @FXML
    private TextArea sastojciJelaArea;
    @FXML
    private TextField velicinaPorcijeField;
//    private ObradaPodataka obradaPodataka = new ObradaPodataka();


    public void initialize() {
//        ObradaPodataka.getInstance().otvoriKonekciju();
        nazivJelaField.setText(ObradaPodataka.getInstance().selektovanoJelo(ObradaPodataka.ID_JELA).getNaziv());
        sastojciJelaArea.setText(ObradaPodataka.getInstance().selektovanoJelo(ObradaPodataka.ID_JELA).getSastojci());
        velicinaPorcijeField.setText(String.valueOf(ObradaPodataka.getInstance().selektovanoJelo(ObradaPodataka.ID_JELA).getvelicina_porcije()));
    }

    @FXML
    public ListaJela_2 azurirajJelo() {
        ObradaPodataka.getInstance().otvoriKonekciju();

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
        ObradaPodataka.getInstance().azurirajJela(listaJela_2);

        return listaJela_2;
    }
}

