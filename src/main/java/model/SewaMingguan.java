/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class SewaMingguan extends Sewa {
    private final int jumlahMinggu;
    private static final double paketMinggu = 0.10;

    public SewaMingguan(String idSewa, Pelanggan pelanggan, Handphone handphone, int jumlahMinggu) {
        super(idSewa, pelanggan, handphone);
        this.jumlahMinggu = jumlahMinggu;
        setTotalBiaya(hitungTotal());
    }

    public SewaMingguan(String idSewa, Pelanggan pelanggan, Handphone handphone,
                    int jumlahMinggu, String status, double totalBiaya) {
    super(idSewa, pelanggan, handphone, status, totalBiaya);
    this.jumlahMinggu = jumlahMinggu;
}
    
    public int getJumlahMinggu() {
        return jumlahMinggu;
    }

    public static double getDiskonMingguan() {
        return paketMinggu;
    }
    
    public static boolean validasiJumlahMinggu(int jumlahMinggu) {
        return jumlahMinggu >= 1 && jumlahMinggu <= 4;
    }

    @Override
    public double hitungTotal() {
        double hargaNormal = getHandphone().getHargaSewa() * 7 * jumlahMinggu;
        double diskon = hargaNormal * paketMinggu;
        return hargaNormal - diskon;
    }

    @Override
    public String getJenisSewa() {
        return "Mingguan";
    }

    @Override
    public String getDurasi() {
        return jumlahMinggu + " minggu";
    }
}
