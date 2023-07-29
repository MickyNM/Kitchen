package com.example.kuhinja;

import com.example.kuhinja.podaci.Kuhinje;
import com.example.kuhinja.podaci.ListaJela_2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Dobrodosli u restoran internacionalne kuhinje M & M");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

    @Override
    public void stop() throws Exception {
        ObradaPodataka.getInstance().zatvoriKonekciju();
    }
    }


