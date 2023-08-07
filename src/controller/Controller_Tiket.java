/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DAOTiket;
import DAOImplement.Implement_Tiket;
import View.ViewView;
import View.viewAbout;
import javax.swing.JOptionPane;
import java.util.List;
import model.ModelTiket;
import model.TabelModelTiket;

/**
 *
 * @author irsya
 */
public class Controller_Tiket {

    ViewView tampilan;
    viewAbout about;
    Implement_Tiket implement_tiket;
    List<ModelTiket> data_tiket;

    public Controller_Tiket(ViewView tampilan) {
        this.tampilan = tampilan;
        implement_tiket = new DAOTiket();
        data_tiket = implement_tiket.getALL();
    }
    
    public Controller_Tiket(viewAbout about){
    this.about = about;
    }
    
    //Tomol Kembali
    public void kembali(){
        about.dispose();
    
    }
    //Tombol Reset
    public void reset() {
        tampilan.getTxtIDKode().setText("");
        tampilan.getTxtNamaPelanggan().setText("");
        tampilan.getTxtFilm().setSelectedItem("--- Pilih Film ---");
        tampilan.getTxtJamTayang().setSelectedItem("--- Pilih Jam ---");
        tampilan.getTxtKursi().setSelectedItem("--- Pilih Kursi ---");
        tampilan.getTxtCariData().setText("");
    }

    //Tampil Data Ke Tabel
    public void isiTable() {
        data_tiket = implement_tiket.getALL();
        TabelModelTiket tt = new TabelModelTiket(data_tiket);
        tampilan.getTabelDataMember().setModel(tt);
    }

    //Menampilkan Data Ke Form Ketika Data DI Klik
    public void isiField(int row) {
        tampilan.getTxtIDKode().setText(data_tiket.get(row).getId().toString());
        tampilan.getTxtNamaPelanggan().setText(data_tiket.get(row).getNama());
        tampilan.getTxtFilm().setSelectedItem(data_tiket.get(row).getJudul());
        tampilan.getTxtJamTayang().setSelectedItem(data_tiket.get(row).getJam());
        tampilan.getTxtKursi().setSelectedItem(data_tiket.get(row).getKursi());
    }

    //Insert Data Dari Form Ke Database
    public void insert() {
        if (tampilan.getTxtNamaPelanggan().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(tampilan, "Data Tidak Boleh Kosong");

        } else if (tampilan.getTxtNamaPelanggan().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(tampilan, "Nama Tidak Boleh Kosong");

        } else if (tampilan.getTxtFilm().getSelectedItem() == "--- Pilih Film ---" || tampilan.getTxtNamaPelanggan().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(tampilan, "Judul Film Tidak Boleh Kosong");

        } else if (tampilan.getTxtJamTayang().getSelectedItem() == "--- Pilih Jam ---"  || tampilan.getTxtJamTayang().getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(tampilan, "Jam Tayang Tidak Boleh Kosong");

        } else if (tampilan.getTxtKursi().getSelectedItem() == "--- Pilih Kursi ---"  || tampilan.getTxtKursi().getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(tampilan, "Kursi Tidak Boleh Kosong");

        }else {

            ModelTiket data = new ModelTiket();
            data.setNama(tampilan.getTxtNamaPelanggan().getText());
            data.setJudul(tampilan.getTxtFilm().getSelectedItem().toString());
            data.setJam(tampilan.getTxtJamTayang().getSelectedItem().toString());
            data.setKursi(tampilan.getTxtKursi().getSelectedItem().toString());
            implement_tiket.insert(data);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
        }
    }


//Update Data Dari Tabel Ke Database
public void update(){
        if(!tampilan.getTxtIDKode().getText().trim().isEmpty()){
            ModelTiket data = new ModelTiket();
            data.setNama(tampilan.getTxtNamaPelanggan().getText());
            data.setJudul(tampilan.getTxtFilm().getSelectedItem().toString());
            data.setJam(tampilan.getTxtJamTayang().getSelectedItem().toString());
            data.setKursi(tampilan.getTxtKursi().getSelectedItem().toString());
            data.setId(Integer.parseInt(tampilan.getTxtIDKode().getText()));
            
            implement_tiket.update(data);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
        } else {
            JOptionPane.showMessageDialog(tampilan, "Silahkan Pilih Data Yang Akan Di Update");
        }
    }
    
    //Hapus Data Pada Tabel
    public void delete(){
        if(!tampilan.getTxtIDKode().getText().trim().isEmpty()){
            int id = Integer.parseInt(tampilan.getTxtIDKode().getText());
            implement_tiket.delete(id);
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
        } else {
            JOptionPane.showMessageDialog(tampilan, "Silahkan Pilih Data Yang Akan Di Hapus");
        }
    }
    
    //Cari Data
    public void isiTableCariNama(){
        data_tiket = implement_tiket.getCariNama(tampilan.getTxtCariData().getText());
        TabelModelTiket tmt = new TabelModelTiket(data_tiket);
        tampilan.getTabelDataMember().setModel(tmt);
    }
    
    public void carinama(){
        if(!tampilan.getTxtCariData().getText().trim().isEmpty()){
            implement_tiket.getCariNama(tampilan.getTxtCariData().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(tampilan, "Silahkan Ketik Data Terlebih Dahulu!!!");
        }
    }
}
