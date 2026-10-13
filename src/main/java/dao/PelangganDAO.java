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
import model.Pelanggan;


/**
 *
 * @author ASUS
 */
public class PelangganDAO {

    private Pelanggan mapRow(ResultSet rs) throws SQLException {
        return new Pelanggan(
                rs.getString("id_pelanggan"),
                rs.getString("nama"),
                rs.getString("no_hp"),
                rs.getString("nik"));
    }

    public boolean insert(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (id_pelanggan, nama, no_hp, nik) VALUES (?, ?, ?, ?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getIdPelanggan());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getNoHP());
            ps.setString(4, p.getNik());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal insert pelanggan: " + e.getMessage());
            return false;
        }
    }

    public Pelanggan findById(String id) {
        if (id == null) return null;
        String sql = "SELECT * FROM pelanggan WHERE id_pelanggan = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari pelanggan: " + e.getMessage());
        }
        return null;
    }

    public Pelanggan[] findAll() {
        String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {
            rs.last();
            Pelanggan[] hasil = new Pelanggan[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                hasil[i++] = mapRow(rs);
            }
            return hasil;
        } catch (SQLException e) {
            System.out.println("Gagal membaca pelanggan: " + e.getMessage());
            return new Pelanggan[0];
        }
    }

    public boolean update(Pelanggan p) {
        String sql = "UPDATE pelanggan SET nama = ?, no_hp = ?, nik = ? WHERE id_pelanggan = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getNoHP());
            ps.setString(3, p.getNik());
            ps.setString(4, p.getIdPelanggan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal update pelanggan: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal hapus pelanggan: " + e.getMessage());
            return false;
        }
    }

    private boolean ada(String sql, String nilai, String idKecuali) {
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nilai);
            ps.setString(2, idKecuali);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Gagal cek duplikat: " + e.getMessage());
            return true;
        }
    }

    public boolean noHPSudahAda(String noHP, String idKecuali) {
        return ada("SELECT COUNT(*) FROM pelanggan WHERE no_hp = ? AND id_pelanggan <> ?", noHP, idKecuali);
    }

    public boolean nikSudahAda(String nik, String idKecuali) {
        return ada("SELECT COUNT(*) FROM pelanggan WHERE nik = ? AND id_pelanggan <> ?", nik, idKecuali);
    }

    public String generateId() {
        String sql = "SELECT id_pelanggan FROM pelanggan ORDER BY id_pelanggan DESC LIMIT 1";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int n = Integer.parseInt(rs.getString(1).substring(1));
                return String.format("P%03d", n + 1);
            }
        } catch (SQLException e) {
            System.out.println("Gagal membuat ID: " + e.getMessage());
        }
        return "P001";
    }
}
