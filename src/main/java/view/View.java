/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.util.Scanner;
import model.Handphone;
import model.Pelanggan;
import model.Sewa;
import model.SewaHarian;
import model.SewaMingguan;

/**
 *
 * @author ASUS
 */
public class View {
    private final Scanner input;
    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void jalankan() {
        int pilihan;

        do {
            tampilkanDashboard();
            
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Kelola Handphone");
            System.out.println("2. Kelola Pelanggan");
            System.out.println("3. Penyewaan Handphone");
            System.out.println("4. Pengembalian Handphone");
            System.out.println("5. Riwayat Penyewaan");
            System.out.println("0. Keluar");

            pilihan = inputAngka("Pilih menu: ", 0, 5);

            switch (pilihan) {
                case 1:
                    menuHandphone();
                    break;

                case 2:
                    menuPelanggan();
                    break;

                case 3:
                    menuPenyewaan();
                    break;

                case 4:
                    menuPengembalian();
                    break;

                case 5:
                    tampilkanSemuaSewa();
                    break;

                case 0:
                    System.out.println("\nTerima kasih.");
                    System.out.println("Program selesai.");
                    break;
            }

        } while (pilihan != 0);
    }

    private void tampilkanDashboard() {
        System.out.println("\n========================================");
        System.out.println("     SISTEM SEWA HANDPHONE");
        System.out.println("========================================");
        System.out.println("Total Handphone : " + controller.getDaftarHandphone().length);
        System.out.println("HP Tersedia     : " + controller.hitungHPTersedia());
        System.out.println("Sedang Disewa   : " + controller.hitungHPDisewa());
        System.out.println("Total Pelanggan : " + controller.getDaftarPelanggan().length);
        System.out.println("Sewa Aktif      : " + controller.hitungSewaAktif());
    }

    // =========================================================
    // MENU HANDPHONE
    // =========================================================

    private void menuHandphone() {
        int pilihan;

        do {
            System.out.println("\n=== KELOLA HANDPHONE ===");
            System.out.println("1. Tambah Handphone");
            System.out.println("2. Lihat Handphone");
            System.out.println("3. Ubah Handphone");
            System.out.println("4. Hapus Handphone");
            System.out.println("0. Kembali");

            pilihan = inputAngka("Pilih menu: ", 0, 4);

            switch (pilihan) {
                case 1:
                    tambahHandphone();
                    break;

                case 2:
                    tampilkanHandphone();
                    break;

                case 3:
                    ubahHandphone();
                    break;

                case 4:
                    hapusHandphone();
                    break;
            }

        } while (pilihan != 0);
    }

    private void tambahHandphone() {
        System.out.println("\n=== TAMBAH HANDPHONE ===");

        String merk;

        while (true) {
            System.out.print("Merk: ");
            merk = input.nextLine().trim();

            if (Handphone.validasiMerk(merk)) {
                break;
            }

            System.out.println("Merk harus berisi 2-30 karakter.");
        }

        String tipe;

        while (true) {
            System.out.print("Tipe: ");
            tipe = input.nextLine().trim();

            if (Handphone.validasiTipe(tipe)) {
                break;
            }

            System.out.println("Tipe harus berisi 2-50 karakter.");
        }

        double harga;

        while (true) {
            harga = inputDouble("Harga sewa per hari: Rp");

            if (Handphone.validasiHargaSewa(harga)) {
                break;
            }

            System.out.println("Harga harus antara Rp1.000 sampai Rp10.000.000.");
        }

        System.out.println("\n=== KONFIRMASI DATA ===");
        System.out.println("Merk       : " + merk);
        System.out.println("Tipe       : " + tipe);
        System.out.println("Harga/Hari : Rp. " + harga);

        if (!konfirmasi("Simpan handphone?")) {
            System.out.println("Penambahan dibatalkan.");
            return;
        }

        Handphone hp = controller.tambahHandphone(merk, tipe, harga);

        if (hp == null) {
            System.out.println("Data handphone tidak valid.");
            return;
        }

        System.out.println("Handphone berhasil ditambahkan.");
        System.out.println("Kode HP: " + hp.getKodeHP());
    }

    private void tampilkanHandphone() {
        System.out.println("\n=== DAFTAR HANDPHONE ===");

        if (controller.getDaftarHandphone().length == 0) {
            System.out.println("Belum ada data handphone.");
            return;
        }

        for (Handphone hp : controller.getDaftarHandphone()) {
            tampilkanSatuHandphone(hp);
        }
    }

    private void tampilkanSatuHandphone(Handphone hp) {
        System.out.println(
                hp.getKodeHP()
                + " | "
                + hp.getMerk()
                + " "
                + hp.getTipe()
                + " | Rp. "
                + hp.getHargaSewa()
                + "/hari"
                + " | "
                + hp.getStatus()
        );
    }

    private void ubahHandphone() {
        tampilkanHandphone();

        if (controller.getDaftarHandphone().length == 0) {
            return;
        }

        System.out.print("\nMasukkan kode HP: ");
        String kode = input.nextLine().trim();

        Handphone hp = controller.cariHandphone(kode);

        if (hp == null) {
            System.out.println("Handphone tidak ditemukan.");
            return;
        }

        if (hp.getStatus().equals("DISEWA")) {
            System.out.println("Handphone sedang disewa.");
            System.out.println("Data tidak dapat diubah.");
            return;
        }

        System.out.println("\nData saat ini:");
        tampilkanSatuHandphone(hp);

        String merk;

        while (true) {
            System.out.print("Merk baru: ");
            merk = input.nextLine().trim();

            if (Handphone.validasiMerk(merk)) {
                break;
            }

            System.out.println("Merk harus berisi 2-30 karakter.");
        }

        String tipe;

        while (true) {
            System.out.print("Tipe baru: ");
            tipe = input.nextLine().trim();

            if (Handphone.validasiTipe(tipe)) {
                break;
            }

            System.out.println("Tipe harus berisi 2-50 karakter.");
        }

        double harga;

        while (true) {
            harga = inputDouble("Harga sewa baru: Rp");

            if (Handphone.validasiHargaSewa(harga)) {
                break;
            }

            System.out.println("Harga harus antara Rp1.000 sampai Rp10.000.000.");
        }

        if (!konfirmasi("Simpan perubahan?")) {
            System.out.println("Perubahan dibatalkan.");
            return;
        }

        // Menggunakan method CRUD dari Controller
        if (controller.ubahHandphone(kode, merk, tipe, harga)) {
            System.out.println("Data handphone berhasil diubah.");
        } else {
            System.out.println("Data handphone gagal diubah.");
        }
    }

    private void hapusHandphone() {
        tampilkanHandphone();

        if (controller.getDaftarHandphone().length == 0) {
            return;
        }

        System.out.print("\nMasukkan kode HP: ");
        String kode = input.nextLine().trim();

        Handphone hp = controller.cariHandphone(kode);

        if (hp == null) {
            System.out.println("Handphone tidak ditemukan.");
            return;
        }

        if (controller.handphonePunyaRiwayat(kode)) {
            System.out.println("Handphone tidak dapat dihapus.");
            System.out.println("Handphone sudah memiliki riwayat penyewaan.");
            return;
        }

        if (!konfirmasi(
                "Yakin ingin menghapus "
                + hp.getMerk()
                + " "
                + hp.getTipe()
                + "?"
        )) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        // Menggunakan method CRUD dari Controller
        if (controller.hapusHandphone(kode)) {
            System.out.println("Handphone berhasil dihapus.");
        } else {
            System.out.println("Handphone gagal dihapus.");
        }
    }

    // =========================================================
    // MENU PELANGGAN
    // =========================================================

    private void menuPelanggan() {
        int pilihan;

        do {
            System.out.println("\n=== KELOLA PELANGGAN ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Pelanggan");
            System.out.println("3. Ubah Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("0. Kembali");

            pilihan = inputAngka("Pilih menu: ", 0, 4);

            switch (pilihan) {
                case 1:
                    tambahPelanggan();
                    break;

                case 2:
                    tampilkanPelanggan();
                    break;

                case 3:
                    ubahPelanggan();
                    break;

                case 4:
                    hapusPelanggan();
                    break;
            }

        } while (pilihan != 0);
    }

    private void tambahPelanggan() {
        System.out.println("\n=== TAMBAH PELANGGAN ===");

        String nama;

        while (true) {
            System.out.print("Nama: ");
            nama = input.nextLine().trim();

            if (Pelanggan.validasiNama(nama)) {
                break;
            }

            System.out.println("Nama harus 3-50 karakter dan hanya berisi huruf.");
        }

        String noHP;

        while (true) {
            System.out.print("No HP: ");
            noHP = input.nextLine().trim();

            if (!Pelanggan.validasiNoHP(noHP)) {
                System.out.println(
                        "No HP harus diawali 08 dan terdiri dari 10-15 digit."
                );
                continue;
            }

            if (controller.noHPSudahAda(noHP)) {
                System.out.println("No HP sudah terdaftar.");
                continue;
            }

            break;
        }

        String nik;

        while (true) {
            System.out.print("NIK: ");
            nik = input.nextLine().trim();

            if (!Pelanggan.validasiNIK(nik)) {
                System.out.println("NIK harus terdiri dari tepat 16 digit.");
                continue;
            }

            if (controller.nikSudahAda(nik)) {
                System.out.println("NIK sudah terdaftar.");
                continue;
            }

            break;
        }

        System.out.println("\n=== KONFIRMASI DATA ===");
        System.out.println("Nama  : " + nama);
        System.out.println("No HP : " + noHP);
        System.out.println("NIK   : " + nik);

        if (!konfirmasi("Simpan pelanggan?")) {
            System.out.println("Penambahan dibatalkan.");
            return;
        }

        Pelanggan pelanggan = controller.tambahPelanggan(nama, noHP, nik);

        if (pelanggan == null) {
            System.out.println("Data pelanggan tidak valid.");
            return;
        }

        System.out.println("Pelanggan berhasil ditambahkan.");
        System.out.println("ID Pelanggan: " + pelanggan.getIdPelanggan());
    }

    private void tampilkanPelanggan() {
        System.out.println("\n=== DAFTAR PELANGGAN ===");

        if (controller.getDaftarPelanggan().length == 0) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }

        for (Pelanggan pelanggan : controller.getDaftarPelanggan()) {
            tampilkanSatuPelanggan(pelanggan);
        }
    }

    private void tampilkanSatuPelanggan(Pelanggan pelanggan) {
        System.out.println(
                pelanggan.getIdPelanggan()
                + " | "
                + pelanggan.getNama()
                + " | "
                + pelanggan.getNoHP()
                + " | NIK: "
                + pelanggan.getNik()
        );
    }

    private void ubahPelanggan() {
        tampilkanPelanggan();

        if (controller.getDaftarPelanggan().length == 0) {
            return;
        }

        System.out.print("\nMasukkan ID Pelanggan: ");
        String id = input.nextLine().trim();

        Pelanggan pelanggan = controller.cariPelanggan(id);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        System.out.println("\nData saat ini:");
        tampilkanSatuPelanggan(pelanggan);

        String nama;

        while (true) {
            System.out.print("Nama baru: ");
            nama = input.nextLine().trim();

            if (Pelanggan.validasiNama(nama)) {
                break;
            }

            System.out.println(
                    "Nama harus 3-50 karakter dan hanya berisi huruf."
            );
        }

        String noHP;

        while (true) {
            System.out.print("No HP baru: ");
            noHP = input.nextLine().trim();

            if (!Pelanggan.validasiNoHP(noHP)) {
                System.out.println(
                        "No HP harus diawali 08 dan terdiri dari 10-15 digit."
                );
                continue;
            }

            if (controller.noHPSudahAdaSelain(noHP, id)) {
                System.out.println("No HP sudah digunakan pelanggan lain.");
                continue;
            }

            break;
        }

        String nik;

        while (true) {
            System.out.print("NIK baru: ");
            nik = input.nextLine().trim();

            if (!Pelanggan.validasiNIK(nik)) {
                System.out.println("NIK harus terdiri dari tepat 16 digit.");
                continue;
            }

            if (controller.nikSudahAdaSelain(nik, id)) {
                System.out.println("NIK sudah digunakan pelanggan lain.");
                continue;
            }

            break;
        }

        if (!konfirmasi("Simpan perubahan?")) {
            System.out.println("Perubahan dibatalkan.");
            return;
        }

        // Menggunakan method CRUD dari Controller
        if (controller.ubahPelanggan(id, nama, noHP, nik)) {
            System.out.println("Data pelanggan berhasil diubah.");
        } else {
            System.out.println("Data pelanggan gagal diubah.");
        }
    }

    private void hapusPelanggan() {
        tampilkanPelanggan();

        if (controller.getDaftarPelanggan().length == 0) {
            return;
        }

        System.out.print("\nMasukkan ID Pelanggan: ");
        String id = input.nextLine().trim();

        Pelanggan pelanggan = controller.cariPelanggan(id);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        if (controller.pelangganPunyaRiwayat(id)) {
            System.out.println("Pelanggan tidak dapat dihapus.");
            System.out.println("Pelanggan sudah memiliki riwayat penyewaan.");
            return;
        }

        if (!konfirmasi("Yakin ingin menghapus " + pelanggan.getNama() + "?")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        if (controller.hapusPelanggan(id)) {
            System.out.println("Pelanggan berhasil dihapus.");
        } else {
            System.out.println("Pelanggan gagal dihapus.");
        }
    }

    // =========================================================
    // PENYEWAAN
    // =========================================================

    private void menuPenyewaan() {
        System.out.println("\n=== PENYEWAAN HANDPHONE ===");

        if (controller.getDaftarPelanggan().length == 0) {
            System.out.println("Belum ada pelanggan.");
            return;
        }

        if (controller.hitungHPTersedia() == 0) {
            System.out.println("Tidak ada handphone tersedia.");
            return;
        }

        tampilkanPelanggan();

        System.out.print("\nMasukkan ID Pelanggan: ");
        String idPelanggan = input.nextLine().trim();

        Pelanggan pelanggan = controller.cariPelanggan(idPelanggan);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        tampilkanHPTersedia();

        System.out.print("\nMasukkan kode HP: ");
        String kodeHP = input.nextLine().trim();

        Handphone hp = controller.cariHandphone(kodeHP);

        if (hp == null) {
            System.out.println("Handphone tidak ditemukan.");
            return;
        }

        if (!hp.getStatus().equals("TERSEDIA")) {
            System.out.println("Handphone sedang disewa.");
            return;
        }

        System.out.println("\n=== JENIS SEWA ===");
        System.out.println("1. Sewa Harian");
        System.out.println("2. Sewa Mingguan");
        System.out.println("Sewa mingguan mendapat diskon 10%.");

        int jenis = inputAngka("Pilih jenis sewa: ", 1, 2);
        int durasi;
        double total;

        if (jenis == 1) {
            while (true) {
                durasi = inputAngkaBebas("Jumlah hari: ");

                if (SewaHarian.validasiJumlahHari(durasi)) {
                    break;
                }

                System.out.println("Sewa harian hanya 1-6 hari.");
            }

            total = hp.getHargaSewa() * durasi;

        } else {
            while (true) {
                durasi = inputAngkaBebas("Jumlah minggu: ");

                if (SewaMingguan.validasiJumlahMinggu(durasi)) {
                    break;
                }

                System.out.println("Sewa mingguan hanya 1-4 minggu.");
            }

            double hargaNormal = hp.getHargaSewa() * 7 * durasi;
            total = hargaNormal
                    - (hargaNormal * SewaMingguan.getDiskonMingguan());
        }

        System.out.println("\n=== KONFIRMASI PENYEWAAN ===");
        System.out.println(
                "Pelanggan : "
                + pelanggan.getIdPelanggan()
                + " - "
                + pelanggan.getNama()
        );

        System.out.println(
                "Handphone : "
                + hp.getKodeHP()
                + " - "
                + hp.getMerk()
                + " "
                + hp.getTipe()
        );

        System.out.println(
                "Jenis     : "
                + (jenis == 1 ? "Harian" : "Mingguan")
        );

        System.out.println(
                "Durasi    : "
                + durasi
                + (jenis == 1 ? " hari" : " minggu")
        );

        if (jenis == 2) {
            System.out.println("Diskon    : 10%");
        }

        System.out.println("Total     : Rp. " + total);

        if (!konfirmasi("Konfirmasi penyewaan?")) {
            System.out.println("Penyewaan dibatalkan.");
            return;
        }

        Sewa sewa;

        if (jenis == 1) {
            sewa = controller.buatSewaHarian(
                    idPelanggan,
                    kodeHP,
                    durasi
            );
        } else {
            sewa = controller.buatSewaMingguan(
                    idPelanggan,
                    kodeHP,
                    durasi
            );
        }

        if (sewa == null) {
            System.out.println("Penyewaan gagal.");
            return;
        }

        System.out.println("\nPenyewaan berhasil.");
        System.out.println("ID Sewa : " + sewa.getIdSewa());
        System.out.println("Total   : Rp. " + sewa.getTotalBiaya());
        System.out.println("Status HP berubah menjadi DISEWA.");
    }

    private void tampilkanHPTersedia() {
        System.out.println("\n=== HANDPHONE TERSEDIA ===");

        boolean ditemukan = false;

        for (Handphone hp : controller.getDaftarHandphone()) {
            if (hp.getStatus().equals("TERSEDIA")) {
                tampilkanSatuHandphone(hp);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada handphone tersedia.");
        }
    }

    // =========================================================
    // PENGEMBALIAN
    // =========================================================

    private void menuPengembalian() {
        System.out.println("\n=== PENGEMBALIAN HANDPHONE ===");

        if (controller.hitungSewaAktif() == 0) {
            System.out.println("Tidak ada transaksi sewa aktif.");
            return;
        }

        tampilkanSewaAktif();

        System.out.print("\nMasukkan ID Sewa: ");
        String idSewa = input.nextLine().trim();

        Sewa sewa = controller.cariSewa(idSewa);

        if (sewa == null) {
            System.out.println("Transaksi sewa tidak ditemukan.");
            return;
        }

        if (!sewa.getStatus().equals("AKTIF")) {
            System.out.println("Transaksi sudah selesai.");
            return;
        }

        System.out.println("\n=== DETAIL PENGEMBALIAN ===");
        System.out.println("ID Sewa   : " + sewa.getIdSewa());
        System.out.println("Pelanggan : " + sewa.getPelanggan().getNama());
        System.out.println(
                "Handphone : "
                + sewa.getHandphone().getMerk()
                + " "
                + sewa.getHandphone().getTipe()
        );
        System.out.println("Jenis     : " + sewa.getJenisSewa());
        System.out.println("Durasi    : " + sewa.getDurasi());
        System.out.println("Total     : Rp. " + sewa.getTotalBiaya());

        if (!konfirmasi("Konfirmasi pengembalian?")) {
            System.out.println("Pengembalian dibatalkan.");
            return;
        }

        if (controller.kembalikanHandphone(idSewa)) {
            System.out.println("\nHandphone berhasil dikembalikan.");
            System.out.println("Status sewa menjadi SELESAI.");
            System.out.println("Status HP menjadi TERSEDIA.");
        } else {
            System.out.println("Pengembalian gagal.");
        }
    }

    private void tampilkanSewaAktif() {
        System.out.println("\n=== SEWA AKTIF ===");

        boolean ditemukan = false;

        for (Sewa sewa : controller.getDaftarSewa()) {
            if (sewa.getStatus().equals("AKTIF")) {
                tampilkanSatuSewa(sewa);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada sewa aktif.");
        }
    }

    private void tampilkanSemuaSewa() {
        System.out.println("\n=== RIWAYAT PENYEWAAN ===");

        if (controller.getDaftarSewa().length == 0) {
            System.out.println("Belum ada transaksi penyewaan.");
            return;
        }

        for (Sewa sewa : controller.getDaftarSewa()) {
            tampilkanSatuSewa(sewa);
        }
    }

    private void tampilkanSatuSewa(Sewa sewa) {
        System.out.println("\n----------------------------------------");
        System.out.println("ID Sewa   : " + sewa.getIdSewa());
        System.out.println(
                "Pelanggan : "
                + sewa.getPelanggan().getIdPelanggan()
                + " - "
                + sewa.getPelanggan().getNama()
        );
        System.out.println(
                "Handphone : "
                + sewa.getHandphone().getKodeHP()
                + " - "
                + sewa.getHandphone().getMerk()
                + " "
                + sewa.getHandphone().getTipe()
        );
        System.out.println("Jenis     : " + sewa.getJenisSewa());
        System.out.println("Durasi    : " + sewa.getDurasi());
        System.out.println("Total     : Rp. " + sewa.getTotalBiaya());
        System.out.println("Status    : " + sewa.getStatus());
    }

    // =========================================================
    // INPUT
    // =========================================================

    private int inputAngka(String pesan, int minimum, int maksimum) {
        while (true) {
            int angka = inputAngkaBebas(pesan);

            if (angka < minimum || angka > maksimum) {
                System.out.println(
                        "Pilihan harus antara "
                        + minimum
                        + " sampai "
                        + maksimum
                        + "."
                );
                continue;
            }

            return angka;
        }
    }

    private int inputAngkaBebas(String pesan) {
        while (true) {
            System.out.print(pesan);

            String nilai = input.nextLine().trim();

            if (!nilai.matches("[0-9]+")) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            if (nilai.length() > 9) {
                System.out.println("Angka terlalu besar.");
                continue;
            }

            return Integer.parseInt(nilai);
        }
    }

    private double inputDouble(String pesan) {
        while (true) {
            System.out.print(pesan);

            String nilai = input.nextLine().trim();

            if (!nilai.matches("[0-9]+")) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            if (nilai.length() > 12) {
                System.out.println("Angka terlalu besar.");
                continue;
            }

            return Double.parseDouble(nilai);
        }
    }

    private boolean konfirmasi(String pesan) {
        while (true) {
            System.out.print(pesan + " (Y/N): ");

            String jawaban = input.nextLine().trim();

            if (jawaban.equalsIgnoreCase("Y")) {
                return true;
            }

            if (jawaban.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Input hanya boleh Y atau N.");
        }
    }
}