package com.example.kuhinja;

import com.example.kuhinja.podaci.ListaJela_2;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class KontrolerListeJela_2 {
    @FXML
    private ListView<ListaJela_2> jela_2ListView;
    @FXML
    private TextArea sastojciTextArea;
    @FXML
    private BorderPane mainPane;
    @FXML
    private ContextMenu contextMenu;
    //    private ObradaPodataka obradaPodataka = new ObradaPodataka();
    @FXML
    private ImageView slika;


    public KontrolerListeJela_2() {
    }

    public void initialize() {
//        ObradaPodataka.getInstance().otvoriKonekciju();
        contextMenu = new ContextMenu();
        MenuItem obrisiJelo = new MenuItem("Delete");
        obrisiJelo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ListaJela_2 listaJela_2 = jela_2ListView.getSelectionModel().getSelectedItem();
                obrisiJelo(listaJela_2);

            }
        });


        MenuItem azuriraj = new MenuItem("Azuriraj");
        azuriraj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ListaJela_2 listaJela_2 = jela_2ListView.getSelectionModel().getSelectedItem();

                azurirajJelo(listaJela_2);
                otvoriDijalogAzuriranja(listaJela_2.getId());

            }
        });

        contextMenu.getItems().addAll(obrisiJelo, azuriraj);


        jela_2ListView.setCellFactory(new Callback<ListView<ListaJela_2>, ListCell<ListaJela_2>>() {
            @Override
            public ListCell<ListaJela_2> call(ListView<ListaJela_2> jela_2ListView) {
                ListCell<ListaJela_2> celija = new ListCell<ListaJela_2>() {
                    @Override
                    protected void updateItem(ListaJela_2 listaJela_2, boolean empty) {
                        super.updateItem(listaJela_2, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(listaJela_2.getNaziv());
                            setFont(Font.font(14));

                        }
                    }
                };

                celija.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                celija.setContextMenu(null);
                            } else {
                                celija.setContextMenu(contextMenu);
                            }
                        }
                );
                return celija;
            }
        });
        jela_2ListView.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<ListaJela_2>() {
                    @Override
                    public void changed(ObservableValue<? extends ListaJela_2> observableValue, ListaJela_2 stara, ListaJela_2 nova) {
                        if (nova != null) {
                            ListaJela_2 selektovanaListaJela_2 = jela_2ListView.getSelectionModel().getSelectedItem();
                            sastojciTextArea.setText(selektovanaListaJela_2.getSastojci() + " / " + selektovanaListaJela_2.getvelicina_porcije() + " g");
                            slika.setImage(new Image("domaca3.jpg"));
                        }
                    }
                });

        jela_2ListView.setItems(ObradaPodataka.getInstance().listaJelaIdKuhinje(ObradaPodataka.ID_KUHINJE));
        jela_2ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        jela_2ListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void otvoriDijalog() {
//        obradaPodataka.otvoriKonekciju();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Dodavanje_3.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Ne moze da otvori dijalog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> rezultat = dialog.showAndWait();
        if (rezultat.isPresent() && rezultat.get() == ButtonType.OK) {
            KontrolerDodavanje_3 kontroler = fxmlLoader.getController();
            ListaJela_2 listaJela_2 = kontroler.ubaciJelo();
            jela_2ListView.setItems(ObradaPodataka.getInstance().listaJelaIdKuhinje(ObradaPodataka.ID_KUHINJE));
            jela_2ListView.getSelectionModel().select(listaJela_2);
            jela_2ListView.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void otvoriDijalogAzuriranja(int id_jela) {
//        ObradaPodataka.getInstance().otvoriKonekciju();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Azuriranje_4.fxml"));
        try {
            ObradaPodataka.ID_JELA = id_jela;
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Ne moze da otvori dijalog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> rezultat = dialog.showAndWait();
        if (rezultat.isPresent() && rezultat.get() == ButtonType.OK) {
            KontrolerAzuriranje_4 kontroler = fxmlLoader.getController();
            ListaJela_2 listaJela_2 = kontroler.azurirajJelo();
            jela_2ListView.setItems(ObradaPodataka.getInstance().listaJelaIdKuhinje(ObradaPodataka.ID_KUHINJE));
            jela_2ListView.getSelectionModel().select(listaJela_2);
            jela_2ListView.getSelectionModel().selectFirst();

        }
    }

    public void obrisiJelo(ListaJela_2 listaJela_2) {
//        obradaPodataka.otvoriKonekciju();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Brisanje jela sa menu-a");
        alert.setHeaderText("Brisanje jela " + listaJela_2.getNaziv());
        alert.setContentText("Dali ste sigurni?");
        Optional<ButtonType> rezultat = alert.showAndWait();
        if (rezultat.isPresent() && rezultat.get() == ButtonType.OK) {
            ObradaPodataka.getInstance().obrisiJela(listaJela_2);
            jela_2ListView.setItems(ObradaPodataka.getInstance().listaJelaIdKuhinje(ObradaPodataka.ID_KUHINJE));
            jela_2ListView.getSelectionModel().selectFirst();
        }
    }

    public void azurirajJelo(ListaJela_2 listaJela_2) {
//        obradaPodataka.otvoriKonekciju();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Azuriranje jela sa menu-a");
        alert.setHeaderText("Azuriranje jela " + listaJela_2.getNaziv());
        alert.setContentText("Dali ste sigurni?");
        Optional<ButtonType> rezultat = alert.showAndWait();

        if (rezultat.isPresent() && rezultat.get() == ButtonType.OK) {
            ObradaPodataka.getInstance().azurirajJela(listaJela_2);

        }else {

        }



        }
    }



