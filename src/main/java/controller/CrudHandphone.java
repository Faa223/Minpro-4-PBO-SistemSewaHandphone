    package controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import model.Handphone;

/**
 *
 * @author ASUS
 */

public interface CrudHandphone {

    Handphone tambahHandphone(String merk, String tipe, double harga);

    Handphone cariHandphone(String kodeHP);

    boolean ubahHandphone(String kodeHP, String merk, String tipe, double harga);

    boolean hapusHandphone(String kodeHP);

    Handphone[] getDaftarHandphone();
}
