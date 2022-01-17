/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author fernan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Button btnEntrar;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void entrar(ActionEvent event) {
        String user = "";
        String pass = "";

        user = txtUsuario.getText().trim();
        pass = txtContraseña.getText().trim();

        if (!user.equals("") || !pass.equals("")) {
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(
                        "select  Nivel, Estado from registros where usuario = '" + user
                        + "' and contraseña = '" + pass + "'");

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    String Nivel = rs.getString("Nivel");
                    String Estado = rs.getString("Estado");

                    if (Nivel.equalsIgnoreCase("Administrador") && Estado.equalsIgnoreCase("Activo")) {

                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLAdministrador.fxml"));

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();

                        } catch (IOException e) {
                        }

                    } else if (Nivel.equalsIgnoreCase("Usuario") && Estado.equalsIgnoreCase("Activo")) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLMenu.fxml"));

                            Scene scene = new Scene(root);

                            Stage stage = new Stage();
                            stage.setTitle("Bienvenido " + user);
                            stage.setScene(scene);
                            stage.showAndWait();

                        } catch (IOException e) {
                        }
                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    //alert.setTitle("Error de conexion!!\\ncontacte al administrador");
                    alert.setContentText("Datos de acceso incorrectos");
                    alert.showAndWait();
                    txtUsuario.setText("");
                    txtContraseña.setText("");

                }
            } catch (SQLException ex) {
                //System.err.println(ex);
                //JOptionPane.showMessageDialog(null, "error de conexion!!\ncontacte al administrador");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setTitle("Error de conexion!!\\ncontacte al administrador");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error");
            alert.setHeight(500);
            //alert.setTitle("Error de conexion!!\\ncontacte al administrador");
            alert.setContentText("Debe llenar todos los campos");
            alert.showAndWait();
        }
    }

    @FXML
    private void Registrarse(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLRegistrar.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
        }

    }

}
