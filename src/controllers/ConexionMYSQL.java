package controllers;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionMYSQL {
    private Connection _conn;
    private String _url;
    private String _user;
    private String _pass;
    private int _port;
    private Statement _stmt;
    private String _databaseName;
    
    public ConexionMYSQL(){
        this._user = "root";
        this._pass = "";
        this._port = 3306;
        this._databaseName = "terminal_ventas";
        this._url = "jdbc:mysql://localhost:"+this._port+"/"+this._databaseName;
        this._url += "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this._conn = DriverManager.getConnection(this._url, this._user, this._pass);
            this._stmt = this._conn.createStatement();
        }catch(ClassNotFoundException|SQLException ex){
            System.out.println("Error al inicializar Conexion: "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error:"+ex.getMessage());
        }        
    }
    
    public ResultSet consulta(String tabla, String[] campos, String filtro){
        
        ResultSet resultado = null;
        String listaCampos = "";
        for(int i = 0; i< campos.length; i++){
            listaCampos += campos[i]+", ";
        }
        listaCampos = listaCampos.substring(0, listaCampos.length()-2);
        String query = "SELECT "+listaCampos+" FROM "+tabla;
        query += filtro.length() > 0 ? " WHERE "+filtro : "";
        try{
            resultado = this._stmt.executeQuery(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Se ha generado un error:\n"+ex.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consulta(String query){
        
        ResultSet resultado = null;        
        try{
            resultado = this._stmt.executeQuery(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Se ha generado un error:\n"+ex.getMessage());
        }
        return resultado;
    }
    
    public int ejecutar(String comandoSQL){
        int lastID = 0;
        try {
            lastID = this._stmt.executeUpdate(comandoSQL, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionMYSQL.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        return lastID;
    }
}