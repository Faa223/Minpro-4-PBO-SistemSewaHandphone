/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class SewaHarian extends Sewa {
    private final int jumlahHari;

    public SewaHarian(String idSewa, Pelanggan pelanggan, Handphone handphone, int jumlahHari) {
        super(idSewa, pelanggan, handphone);
        this.jumlahHari = jumlahHari;
        setTotalBiaya(hitungTotal());
    }

    public SewaHarian(String idSewa, Pelanggan pelanggan, Handphone handphone,
                      int jumlahHari, String status, double totalBiaya) {
        super(idSewa, pelanggan, handphone, status, totalBiaya);
        this.jumlahHari = jumlahHari;
    }

    public int getJumlahHari() {
        return jumlahHari;
    }

    @Override
    public double hitungTotal() {
        return getHandphone().getHargaSewa() * jumlahHari;
    }

    @Override
    public String getJenisSewa() {
        return "Harian";
    }

    @Override
    public String getDurasi() {
        return jumlahHari + " hari";
    }
    
    public static boolean validasiJumlahHari(int jumlahHari) {
        return jumlahHari >= 1 && jumlahHari <= 6;
    }
}
