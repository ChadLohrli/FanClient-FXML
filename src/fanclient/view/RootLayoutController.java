/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanclient.view;

import fanclient.model.FanGroup;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author chad
 */
public class RootLayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private FanGroup fanGroup;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
    }    
    
    @FXML
    private void handleExit() throws IOException {
        System.exit(0);
    }
    
    @FXML
    private void handleAbout() {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Client");
        alert.setHeaderText(null);
        alert.setContentText("CS56 Team 02 | Fall 2015 | Santa Monica College");
        alert.setGraphic(new ImageView(this.getClass().getResource("TeamTwoLogo.png").toString()));
        alert.showAndWait();
        
    }
    
    @FXML
    private void handleLANOn() {
        
        
    }    
    
    @FXML
    private void handleLANOff() {
        
        
    }
    
   
    
}
