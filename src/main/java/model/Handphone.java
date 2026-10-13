/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Handphone {
    private final String kodeHP;
    private String merk;
    private String tipe;
    private double hargaSewa;
    private String status;

    public Handphone(String kodeHP, String merk, String tipe, double hargaSewa) {
        this.kodeHP = kodeHP;
        setMerk(merk);
        setTipe(tipe);
        setHargaSewa(hargaSewa);
        this.status = "TERSEDIA";
    }

    public String getKodeHP() {
        return kodeHP;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        if (!validasiMerk(merk)) {
            throw new IllegalArgumentException("Merk harus terdiri dari 2-30 karakter.");
        }
        
        this.merk = merk.trim();
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        if (!validasiTipe(tipe)) {
            throw new IllegalArgumentException("Tipe harus terdiri dari 2-50 karakter.");
        }
        this.tipe = tipe.trim();
    }
    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        if (!validasiHargaSewa(hargaSewa)) {
            throw new IllegalArgumentException("Harga sewa harus antara Rp1.000 sampai Rp10.000.000.");
        }

        this.hargaSewa = hargaSewa;
    }

    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        if (!validasiStatus(status)) {
            throw new IllegalArgumentException("Status handphone tidak valid.");
        }

        this.status = status.toUpperCase();
    }
    
    public static boolean validasiMerk(String merk) {
    if (merk == null) {
        return false;
    }

    String nilai = merk.trim();

    return nilai.length() >= 2
            && nilai.length() <= 30
            && nilai.matches("[a-zA-Z ]+");
}

    public static boolean validasiTipe(String tipe) {
    if (tipe == null) {
        return false;
    }

    String nilai = tipe.trim();

    return nilai.length() >= 2
            && nilai.length() <= 50
            && nilai.matches(".*[a-zA-Z].*")
            && nilai.matches("[a-zA-Z0-9 ]+");
}
    public static boolean validasiHargaSewa(double harga) {
        return harga >= 1000 && harga <= 10000000;
    }

    public static boolean validasiStatus(String status) {
        if (status == null) {
            return false;
        }

        return status.equalsIgnoreCase("TERSEDIA") || status.equalsIgnoreCase("DISEWA");
    }
}
