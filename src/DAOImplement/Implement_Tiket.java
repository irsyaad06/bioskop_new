/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;

import java.util.List;
import model.ModelTiket;


/**
 *
 * @author irsya
 */
public interface Implement_Tiket {
    
    public void insert(ModelTiket key);
    
    public void update(ModelTiket key);
    
    public void delete(int id);
    
    public List<ModelTiket> getALL();
    
    public List<ModelTiket> getCariNama(String nama);
    
}
