/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public abstract class Sewa {
    private final String idSewa;
    private final Pelanggan pelanggan;
    private final Handphone handphone;
    private String status;
    private double totalBiaya;
    
    public abstract double hitungTotal();
    public abstract String getJenisSewa();
    public abstract String getDurasi();

    public Sewa(String idSewa, Pelanggan pelanggan, Handphone handphone) {
        this.idSewa = idSewa;
        this.pelanggan = pelanggan;
        this.handphone = handphone;
        this.status = "AKTIF";
    }

    public Sewa(String idSewa, Pelanggan pelanggan, Handphone handphone,
                String status, double totalBiaya) {
        this(idSewa, pelanggan, handphone);
        setStatus(status);
        this.totalBiaya = totalBiaya;
    }

    public String getIdSewa() {
        return idSewa;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public Handphone getHandphone() {
        return handphone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!validasiStatus(status)) {
            return;
        }

        this.status = status.toUpperCase();
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    protected void setTotalBiaya(double totalBiaya) {
        if (totalBiaya < 0) {
            return;
        }

        this.totalBiaya = totalBiaya;
    }
    
        public static boolean validasiStatus(String status) {
        if (status == null) {
            return false;
        }

        return status.equalsIgnoreCase("AKTIF") || status.equalsIgnoreCase("SELESAI");
    }
}