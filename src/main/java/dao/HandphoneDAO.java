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

/**
 *
 * @author ASUS
 */
public class HandphoneDAO {

    private Handphone mapRow(ResultSet rs) throws SQLException {
        Handphone hp = new Handphone(
                rs.getString("kode_hp"),
                rs.getString("merk"),
                rs.getString("tipe"),
                rs.getDouble("harga_sewa"));
        hp.setStatus(rs.getString("status"));
        return hp;
    }

    public boolean insert(Handphone hp) {
        String sql = "INSERT INTO handphone (kode_hp, merk, tipe, harga_sewa, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, hp.getKodeHP());
            ps.setString(2, hp.getMerk());
            ps.setString(3, hp.getTipe());
            ps.setDouble(4, hp.getHargaSewa());
            ps.setString(5, hp.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal insert handphone: " + e.getMessage());
            return false;
        }
    }

    public Handphone findByKode(String kode) {
        if (kode == null) return null;
        String sql = "SELECT * FROM handphone WHERE kode_hp = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, kode.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari handphone: " + e.getMessage());
        }
        return null;
    }

    public Handphone[] findAll() {
        String sql = "SELECT * FROM handphone ORDER BY kode_hp";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {
            rs.last();
            Handphone[] hasil = new Handphone[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                hasil[i++] = mapRow(rs);
            }
            return hasil;
        } catch (SQLException e) {
            System.out.println("Gagal membaca handphone: " + e.getMessage());
            return new Handphone[0];
        }
    }

    public boolean update(Handphone hp) {
        String sql = "UPDATE handphone SET merk = ?, tipe = ?, harga_sewa = ? WHERE kode_hp = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, hp.getMerk());
            ps.setString(2, hp.getTipe());
            ps.setDouble(3, hp.getHargaSewa());
            ps.setString(4, hp.getKodeHP());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal update handphone: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String kode) {
        String sql = "DELETE FROM handphone WHERE kode_hp = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, kode);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal hapus handphone: " + e.getMessage());
            return false;
        }
    }

    public String generateKode() {
        String sql = "SELECT kode_hp FROM handphone ORDER BY kode_hp DESC LIMIT 1";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int n = Integer.parseInt(rs.getString(1).substring(2));
                return String.format("HP%03d", n + 1);
            }
        } catch (SQLException e) {
            System.out.println("Gagal membuat kode: " + e.getMessage());
        }
        return "HP001";
    }
}
