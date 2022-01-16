/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fernan
 */
public class Conexion {
    public static Connection conectar(){
        try{
           Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/usuarios", "root", "");
           return conexion;
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "error en la conexion a base de datos");
            
        }
        return (null);
    }
}
