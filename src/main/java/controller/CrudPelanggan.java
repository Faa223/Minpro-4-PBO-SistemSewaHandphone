package controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import model.Pelanggan;
/**
 *
 * @author ASUS
 */
public interface CrudPelanggan {

    Pelanggan tambahPelanggan(String nama, String noHP, String nik);

    Pelanggan cariPelanggan(String idPelanggan);

    boolean ubahPelanggan(String idPelanggan, String nama, String noHP, String nik);

    boolean hapusPelanggan(String idPelanggan);

    Pelanggan[] getDaftarPelanggan();
}