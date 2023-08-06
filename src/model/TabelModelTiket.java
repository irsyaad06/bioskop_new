/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author irsya
 */
public class TabelModelTiket extends AbstractTableModel{

    List<ModelTiket> data_tiket;
    
    public TabelModelTiket(List<ModelTiket> data_tiket){
        this.data_tiket = data_tiket;
    }
    
    @Override
    public int getRowCount() {
        return data_tiket.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "ID";
            case 1:
                return "NAMA";
            case 2:
                return "JUDUL";
            case 3:
                return "JAM TAYANG";
            case 4:
                return "KURSI";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
            case 0:
                return data_tiket.get(row).getId();
            case 1:
                return data_tiket.get(row).getNama();
            case 2:
                return data_tiket.get(row).getJudul();
            case 3:
                return data_tiket.get(row).getJam();
            case 4:
                return data_tiket.get(row).getKursi();
            default:
                return null;
        }
    }
    
}
