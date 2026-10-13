/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Handphone;
import model.Pelanggan;
import model.Sewa;
import model.SewaHarian;
import model.SewaMingguan;

/**
 *
 * @author ASUS
 */
public class SewaDAO {
    private final PelangganDAO pelangganDAO = new PelangganDAO();
    private final HandphoneDAO handphoneDAO = new HandphoneDAO();

    private Sewa mapRow(ResultSet rs) throws SQLException {
        Pelanggan p = pelangganDAO.findById(rs.getString("id_pelanggan"));
        Handphone hp = handphoneDAO.findByKode(rs.getString("kode_hp"));
        String id = rs.getString("id_sewa");
        int durasi = rs.getInt("durasi");
        String status = rs.getString("status");
        double total = rs.getDouble("total_biaya");

        if ("HARIAN".equals(rs.getString("jenis"))) {
            return new SewaHarian(id, p, hp, durasi, status, total);
        }
        return new SewaMingguan(id, p, hp, durasi, status, total);
    }

    public boolean insert(Sewa sewa) {
        String sqlSewa = "INSERT INTO sewa (id_sewa, id_pelanggan, kode_hp, jenis, durasi, total_biaya, status) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlHp = "UPDATE handphone SET status = 'DISEWA' WHERE kode_hp = ? AND status = 'TERSEDIA'";

        try (Connection c = Koneksi.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps1 = c.prepareStatement(sqlSewa);
                 PreparedStatement ps2 = c.prepareStatement(sqlHp)) {

                String jenis;
                int durasi;
                if (sewa instanceof SewaHarian) {
                    jenis = "HARIAN";
                    durasi = ((SewaHarian) sewa).getJumlahHari();
                } else {
                    jenis = "MINGGUAN";
                    durasi = ((SewaMingguan) sewa).getJumlahMinggu();
                }

                ps1.setString(1, sewa.getIdSewa());
                ps1.setString(2, sewa.getPelanggan().getIdPelanggan());
                ps1.setString(3, sewa.getHandphone().getKodeHP());
                ps1.setString(4, jenis);
                ps1.setInt(5, durasi);
                ps1.setDouble(6, sewa.getTotalBiaya());
                ps1.setString(7, sewa.getStatus());
                ps1.executeUpdate();

                ps2.setString(1, sewa.getHandphone().getKodeHP());
                if (ps2.executeUpdate() == 0) {
                    c.rollback();
                    return false;
                }

                c.commit();
                return true;
            } catch (SQLException e) {
                c.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan sewa: " + e.getMessage());
            return false;
        }
    }

    public boolean selesai(String idSewa) {
        String sql1 = "UPDATE sewa SET status = 'SELESAI' WHERE id_sewa = ? AND status = 'AKTIF'";
        String sql2 = "UPDATE handphone SET status = 'TERSEDIA' "
                    + "WHERE kode_hp = (SELECT kode_hp FROM sewa WHERE id_sewa = ?)";

        try (Connection c = Koneksi.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps1 = c.prepareStatement(sql1);
                 PreparedStatement ps2 = c.prepareStatement(sql2)) {

                ps1.setString(1, idSewa);
                if (ps1.executeUpdate() == 0) {
                    c.rollback();
                    return false;
                }
                ps2.setString(1, idSewa);
                ps2.executeUpdate();

                c.commit();
                return true;
            } catch (SQLException e) {
                c.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengembalikan HP: " + e.getMessage());
            return false;
        }
    }

    public Sewa findById(String idSewa) {
        if (idSewa == null) return null;
        String sql = "SELECT * FROM sewa WHERE id_sewa = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idSewa.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari sewa: " + e.getMessage());
        }
        return null;
    }

    public Sewa[] findAll() {
        String sql = "SELECT * FROM sewa ORDER BY id_sewa";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {
            rs.last();
            Sewa[] hasil = new Sewa[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                hasil[i++] = mapRow(rs);
            }
            return hasil;
        } catch (SQLException e) {
            System.out.println("Gagal membaca sewa: " + e.getMessage());
            return new Sewa[0];
        }
    }

    private boolean ada(String sql, String nilai) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nilai);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Gagal cek riwayat: " + e.getMessage());
            return true;
        }
    }

    public boolean adaRiwayatHP(String kodeHP) {
        return ada("SELECT COUNT(*) FROM sewa WHERE kode_hp = ?", kodeHP);
    }

    public boolean adaRiwayatPelanggan(String idPelanggan) {
        return ada("SELECT COUNT(*) FROM sewa WHERE id_pelanggan = ?", idPelanggan);
    }

    public String generateId() {
        String sql = "SELECT id_sewa FROM sewa ORDER BY id_sewa DESC LIMIT 1";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int n = Integer.parseInt(rs.getString(1).substring(2));
                return String.format("SW%03d", n + 1);
            }
        } catch (SQLException e) {
            System.out.println("Gagal membuat ID sewa: " + e.getMessage());
        }
        return "SW001";
    }
}
