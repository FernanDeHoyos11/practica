/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.awt.Color;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fernan
 */
public class FXMLRegistrarController implements Initializable {

    @FXML
    private TextField txtcedula;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtapellido;
    @FXML
    private TextField txtedad;
    @FXML
    private TextField txtcorreo;
    @FXML
    private TextField txtusuario;
    @FXML
    private TextField txtcontraseña;
    @FXML
    private Button btnguardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void guarfar(ActionEvent event) {
        int validacion = 0;
        String cedula, nombre, apellido, correo, username, pass, Nivel, Estado;
        int edad;

        cedula = txtcedula.getText().trim();
        nombre = txtnombre.getText().trim();
        apellido = txtapellido.getText().trim();
        edad = Integer.parseInt(txtedad.getText().trim());
        correo = txtcorreo.getText().trim();
        username = txtusuario.getText().trim();
        pass = txtcontraseña.getText().trim();
        Nivel = "Usuario";
        Estado= "Activo";

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select usuario from registros where usuario = '" + username + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Ojo!!");
                alert.setTitle("Tenga en cuenta");
                alert.setContentText("nombre de usuario ya en uso");
                alert.showAndWait();
                cn.close();
            } else {
                cn.close();

                if (validacion == 0) {

                    try {

                        Connection cn2 = Conexion.conectar();
                        PreparedStatement pst2 = cn2.prepareStatement("insert into registros values(?,?,?,?,?,?,?,?,?)");

                        pst2.setString(1, cedula);
                        pst2.setString(2, nombre);
                        pst2.setString(3, apellido);
                        pst2.setInt(4, edad);
                        pst2.setString(5, correo);
                        pst2.setString(6, pass);
                        pst2.setString(7, username);
                        pst2.setString(8, Nivel);
                        pst2.setString(9, Estado);
                        pst2.executeUpdate();
                        cn2.close();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Congratulations");
                        alert.setContentText("Registro exitoso");
                        alert.showAndWait();
                        
                    } catch (SQLException ex) {
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setTitle("Error de conexion!!\\ncontacte al administrador");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    }

                } else {
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Error");
                    alert.setContentText("debe llenar todos los campos");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");

        }
    }

}
