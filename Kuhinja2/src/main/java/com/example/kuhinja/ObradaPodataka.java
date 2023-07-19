package com.example.kuhinja;

import com.example.kuhinja.podaci.ListaJela_2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class ObradaPodataka {
    private static ObradaPodataka instanca = new ObradaPodataka();

    public static final String BAZA_NAZIV = "restoran.db";
    public static final String KONEKCIJA_URL = "jdbc:sqlite:C:\\Users\\NN\\Documents\\" + BAZA_NAZIV;
    public static final String TABELA_JELO = "jelo";
    public static final String KOLONA_JELO_ID = "id";
    public static final String KOLONA_JELO_NAZIV = "naziv";
    public static final String KOLONA_JELO_ID_KUHINJE = "id_kuhinje";
    public static final String KOLONA_JELO_SASTOJCI = "sastojci";
    public static final String KOLONA_JELO_VELICINA_PORCIJE = "velicina_porcije";
    public static int ID_KUHINJE;
    public static int ID_JELA;

    public static final String TABELA_KUHINJA = "kuhinja";
    public static final String KOLONA_ID = "id";
    public static final String KOLONA_NAZIV = "naziv";
    public static final String KOLONA_OPIS = "opis";

    private ListaJela_2 listaJela_2;
    public Connection konekcija;
    public static ObradaPodataka getInstance(){
        return instanca;
    }

    public boolean otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection(KONEKCIJA_URL);
            return true;

        } catch (SQLException e) {
            System.out.println("Ne mozemo se konektovati: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void zatvoriKonekciju() {
        try {
            if (konekcija != null) {
                konekcija.close();
            }
        } catch (SQLException e) {
            System.out.println("Ne mozemo zatvoriti bazu: " + e.getMessage());
        }
    }

    //    public ObservableList<ListaJela_2> listaJela() {
//        String sql = "select * from " + TABELA_JELO;
//
//        try (Statement statement = konekcija.createStatement();
//             ResultSet rezultat = statement.executeQuery(sql)) {
//            if (rezultat != null) {
//                ObservableList<ListaJela_2> jelaOLista = FXCollections.observableArrayList();
//                while (rezultat.next()) {
//                    ListaJela_2 listaJela_2 = new ListaJela_2();
//                    listaJela_2.setId(rezultat.getInt(KOLONA_JELO_ID));
//                    listaJela_2.setNaziv(rezultat.getString(KOLONA_JELO_NAZIV));
//                    listaJela_2.setId_kuhinje(rezultat.getInt(KOLONA_JELO_ID_KUHINJE));
//                    listaJela_2.setSastojci(rezultat.getString(KOLONA_JELO_SASTOJCI));
//                    listaJela_2.setvelicina_porcije(rezultat.getInt(KOLONA_JELO_VELICINA_PORCIJE));
//                    jelaOLista.add(listaJela_2);
//                }
//                return jelaOLista;
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Los upit " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
    public ObservableList<ListaJela_2> listaJelaIdKuhinje(int ID_KUHINJE) {
        String sql = "select * from " + TABELA_JELO + " where " + KOLONA_JELO_ID_KUHINJE + " = " + ID_KUHINJE;
        try (Statement statement = konekcija.createStatement();
             ResultSet rezultat = statement.executeQuery(sql)) {
            if (rezultat != null) {
                ObservableList<ListaJela_2> listaJelaIdKuhinje = FXCollections.observableArrayList();
                while (rezultat.next()) {
                    ListaJela_2 listaJela_2 = new ListaJela_2();
                    listaJela_2.setId(rezultat.getInt(KOLONA_JELO_ID));
                    listaJela_2.setNaziv(rezultat.getString(KOLONA_JELO_NAZIV));
                    listaJela_2.setId_kuhinje(rezultat.getInt(KOLONA_JELO_ID_KUHINJE));
                    listaJela_2.setSastojci(rezultat.getString(KOLONA_JELO_SASTOJCI));
                    listaJela_2.setvelicina_porcije(rezultat.getInt(KOLONA_JELO_VELICINA_PORCIJE));
                    listaJelaIdKuhinje.add(listaJela_2);
                }
                return listaJelaIdKuhinje;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Los upit " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void dodajJela(ListaJela_2 listaJela_2) {

//        String sql = "insert into jelo values (11,?,?,?,?)";
        String sql = "insert into " + TABELA_JELO + " (naziv, id_kuhinje, sastojci, velicina_porcije) values (" + "'" + listaJela_2.getNaziv() + "'" + ", " + ObradaPodataka.ID_KUHINJE + ", " + "'" + listaJela_2.getSastojci() + "'" + ", " + listaJela_2.getvelicina_porcije() + ")";
//        String sql = "insert into " + TABELA_JELO + " (naziv, sastojci) values (" + "'" + listaJela_2.getNaziv() + "'" + ", " + "'" + listaJela_2.getSastojci() + "'" + ")";

        try (Statement statement = konekcija.createStatement()) {
            //obradaPodataka.otvoriKonekciju();
//        try (PreparedStatement preparedStatement= konekcija.prepareStatement(sql)) {
//            preparedStatement.setString(1,listaJela_2.getNaziv());
//            preparedStatement.setInt(2,listaJela_2.getId_kuhinje());
//            preparedStatement.setString(3,listaJela_2.getSastojci());
//            preparedStatement.setInt(4,listaJela_2.getvelicina_porcije());
//            preparedStatement.execute(sql);
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Los upit " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void obrisiJela(ListaJela_2 listaJela_2) {
        String sql = "delete from " + TABELA_JELO + " where " + KOLONA_JELO_NAZIV + "=" + "'" + listaJela_2.getNaziv() + "'";
        try (Statement statement = konekcija.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("Los upit " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void azurirajJela(ListaJela_2 listaJela_2) {
        otvoriKonekciju();
        String sql = "update " + TABELA_JELO + " set " + KOLONA_JELO_NAZIV + " = " + "'"+ listaJela_2.getNaziv() + "'" + ", sastojci = " + "'" + listaJela_2.getSastojci() + "'" + ", " + " velicina_porcije = " + listaJela_2.getvelicina_porcije() + " where " + KOLONA_JELO_ID + " = " + ID_JELA;
//        String sql = "update " + TABELA_JELO + " set id_kuhinje = " + ObradaPodataka.ID_KUHINJE + ", sastojci = " + "'" + listaJela_2.getSastojci() + "'" + ", " + " velicina_porcije = " + listaJela_2.getvelicina_porcije() + " where naziv = " + "'" + listaJela_2.getNaziv() + "'";
        try (Statement statement = konekcija.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("Los upit2 " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ListaJela_2 selektovanoJelo(int ID_JELA) {
//        otvoriKonekciju();
        String sql = "select * from " + TABELA_JELO + " where " + KOLONA_JELO_ID + " = " + ID_JELA;
        try (Statement statement = konekcija.createStatement();
             ResultSet rezultat = statement.executeQuery(sql)) {
            ListaJela_2 listaJela_2 = new ListaJela_2(rezultat.getInt("id"),rezultat.getString("naziv"), rezultat.getInt("id_kuhinje"), rezultat.getString("sastojci"), rezultat.getInt("velicina_porcije"));
            return listaJela_2;

        } catch (SQLException e) {
            System.out.println("Los upit " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}