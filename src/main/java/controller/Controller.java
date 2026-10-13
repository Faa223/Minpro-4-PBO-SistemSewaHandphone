/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.HandphoneDAO;
import dao.PelangganDAO;
import dao.SewaDAO;
import model.Handphone;
import model.Pelanggan;
import model.Sewa;
import model.SewaHarian;
import model.SewaMingguan;
/**
 *
 * @author ASUS
 */
public class Controller implements CrudHandphone, CrudPelanggan {
    private final HandphoneDAO hpDAO = new HandphoneDAO();
    private final PelangganDAO pelangganDAO = new PelangganDAO();
    private final SewaDAO sewaDAO = new SewaDAO();

    // ================= HANDPHONE =================
    @Override
    public Handphone[] getDaftarHandphone() {
        return hpDAO.findAll();
    }

    @Override
    public Handphone tambahHandphone(String merk, String tipe, double harga) {
        if (!Handphone.validasiMerk(merk) || !Handphone.validasiTipe(tipe)
                || !Handphone.validasiHargaSewa(harga)) {
            return null;
        }
        Handphone hp = new Handphone(hpDAO.generateKode(), merk, tipe, harga);
        return hpDAO.insert(hp) ? hp : null;
    }

    @Override
    public Handphone cariHandphone(String kodeHP) {
        return hpDAO.findByKode(kodeHP);
    }

    @Override
    public boolean ubahHandphone(String kodeHP, String merk, String tipe, double harga) {
        Handphone hp = cariHandphone(kodeHP);
        if (hp == null || hp.getStatus().equals("DISEWA")) return false;
        if (!Handphone.validasiMerk(merk) || !Handphone.validasiTipe(tipe)
                || !Handphone.validasiHargaSewa(harga)) {
            return false;
        }
        hp.setMerk(merk);
        hp.setTipe(tipe);
        hp.setHargaSewa(harga);
        return hpDAO.update(hp);
    }

    public boolean handphonePunyaRiwayat(String kodeHP) {
        return sewaDAO.adaRiwayatHP(kodeHP);
    }

    @Override
    public boolean hapusHandphone(String kodeHP) {
        if (cariHandphone(kodeHP) == null || handphonePunyaRiwayat(kodeHP)) return false;
        return hpDAO.delete(kodeHP.trim());
    }

    // ================= PELANGGAN =================
    @Override
    public Pelanggan[] getDaftarPelanggan() {
        return pelangganDAO.findAll();
    }

    @Override
    public Pelanggan tambahPelanggan(String nama, String noHP, String nik) {
        if (!Pelanggan.validasiNama(nama) || !Pelanggan.validasiNoHP(noHP)
                || !Pelanggan.validasiNIK(nik)) {
            return null;
        }
        if (noHPSudahAda(noHP) || nikSudahAda(nik)) return null;

        Pelanggan p = new Pelanggan(pelangganDAO.generateId(), nama, noHP, nik);
        return pelangganDAO.insert(p) ? p : null;
    }

    @Override
    public Pelanggan cariPelanggan(String idPelanggan) {
        return pelangganDAO.findById(idPelanggan);
    }

    @Override
    public boolean ubahPelanggan(String idPelanggan, String nama, String noHP, String nik) {
        Pelanggan p = cariPelanggan(idPelanggan);
        if (p == null) return false;
        if (!Pelanggan.validasiNama(nama) || !Pelanggan.validasiNoHP(noHP)
                || !Pelanggan.validasiNIK(nik)) {
            return false;
        }
        if (noHPSudahAdaSelain(noHP, idPelanggan) || nikSudahAdaSelain(nik, idPelanggan)) {
            return false;
        }
        p.setNama(nama);
        p.setNoHP(noHP);
        p.setNik(nik);
        return pelangganDAO.update(p);
    }

    public boolean noHPSudahAda(String noHP) {
        return pelangganDAO.noHPSudahAda(noHP, "");
    }

    public boolean noHPSudahAdaSelain(String noHP, String idPelanggan) {
        return pelangganDAO.noHPSudahAda(noHP, idPelanggan);
    }

    public boolean nikSudahAda(String nik) {
        return pelangganDAO.nikSudahAda(nik, "");
    }

    public boolean nikSudahAdaSelain(String nik, String idPelanggan) {
        return pelangganDAO.nikSudahAda(nik, idPelanggan);
    }

    public boolean pelangganPunyaRiwayat(String idPelanggan) {
        return sewaDAO.adaRiwayatPelanggan(idPelanggan);
    }

    @Override
    public boolean hapusPelanggan(String idPelanggan) {
        if (cariPelanggan(idPelanggan) == null || pelangganPunyaRiwayat(idPelanggan)) return false;
        return pelangganDAO.delete(idPelanggan.trim());
    }

    // ================= SEWA =================
    public Sewa[] getDaftarSewa() {
        return sewaDAO.findAll();
    }

    public Sewa buatSewaHarian(String idPelanggan, String kodeHP, int jumlahHari) {
        Pelanggan pelanggan = cariPelanggan(idPelanggan);
        Handphone hp = cariHandphone(kodeHP);

        if (pelanggan == null || hp == null) return null;
        if (!hp.getStatus().equals("TERSEDIA")) return null;
        if (!SewaHarian.validasiJumlahHari(jumlahHari)) return null;

        SewaHarian sewa = new SewaHarian(sewaDAO.generateId(), pelanggan, hp, jumlahHari);
        return sewaDAO.insert(sewa) ? sewa : null;
    }

    public Sewa buatSewaMingguan(String idPelanggan, String kodeHP, int jumlahMinggu) {
        Pelanggan pelanggan = cariPelanggan(idPelanggan);
        Handphone hp = cariHandphone(kodeHP);

        if (pelanggan == null || hp == null) return null;
        if (!hp.getStatus().equals("TERSEDIA")) return null;
        if (!SewaMingguan.validasiJumlahMinggu(jumlahMinggu)) return null;

        SewaMingguan sewa = new SewaMingguan(sewaDAO.generateId(), pelanggan, hp, jumlahMinggu);
        return sewaDAO.insert(sewa) ? sewa : null;
    }

    public Sewa cariSewa(String idSewa) {
        return sewaDAO.findById(idSewa);
    }

    public boolean kembalikanHandphone(String idSewa) {
        return sewaDAO.selesai(idSewa);
    }

    // ================= STATISTIK DASHBOARD =================
    public int hitungHPTersedia() {
        int jumlah = 0;
        for (Handphone hp : hpDAO.findAll()) {
            if (hp.getStatus().equals("TERSEDIA")) jumlah++;
        }
        return jumlah;
    }

    public int hitungHPDisewa() {
        int jumlah = 0;
        for (Handphone hp : hpDAO.findAll()) {
            if (hp.getStatus().equals("DISEWA")) jumlah++;
        }
        return jumlah;
    }

    public int hitungSewaAktif() {
        int jumlah = 0;
        for (Sewa s : sewaDAO.findAll()) {
            if (s.getStatus().equals("AKTIF")) jumlah++;
        }
        return jumlah;
    }
}
