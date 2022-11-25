package models;

import controllers.ConexionMYSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Usuarios {
    private int id_usuario;
    private String apodo;
    private String clave;
    
    public boolean login(String apodo, String clave){
        try {
            ConexionMYSQL c = new ConexionMYSQL();
            ResultSet r = c.consulta("SELECT * FROM usuarios WHERE apodo = '"+apodo+"' AND clave ='"+clave+"';");
            return r.next();        
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean create(String apodo, String clave){
        String query = "INSERT INTO usuarios (apodo, clave) ";
        query += " VALUES ('"+apodo+"', '"+clave+"');";
        ConexionMYSQL c = new ConexionMYSQL();
        ResultSet r = c.consulta(query);
        try {
            return r.next();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public ResultSet result(){
        String query = "SELECT * FROM usuarios;";
        ConexionMYSQL c = new ConexionMYSQL();
        ResultSet r = c.consulta(query);
        return r;
    }
    
    public boolean update(int id_usuario, String apodo, String clave){
        String query = "UPDATE usuarios SET apodo = '"+apodo+"', ";
        query += " clave = '"+clave+"' WHERE id_usuario ="+id_usuario+";);";
        ConexionMYSQL c = new ConexionMYSQL();
        ResultSet r = c.consulta(query);
        try {
            return r.next();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean delete(int id_usuario){
        String query = "DELETE FROM usuarios ";
        query += " WHERE id_usuario ="+id_usuario+";);";
        ConexionMYSQL c = new ConexionMYSQL();
        ResultSet r = c.consulta(query);
        try {
            return r.next();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    
    
    
}
