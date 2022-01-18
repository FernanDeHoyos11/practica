/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author fernan
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private MenuItem AgrHorario;
    @FXML
    private MenuItem AgrPendiente;
    @FXML
    private MenuItem Tema;
    @FXML
    private MenuItem BtnSalir;
    @FXML
    private Tab btnpendientes;
    @FXML
    private Tab btnclases;
    @FXML
    private Button btnhorario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AgregarHorario(ActionEvent event) {
    }

    @FXML
    private void AgregarPendiente(ActionEvent event) {
    }

    @FXML
    private void AgregarTema(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            System.exit(0);
        } else {

        }

    }
}
