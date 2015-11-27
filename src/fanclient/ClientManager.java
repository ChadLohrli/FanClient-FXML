/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanclient;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author chad
 */
public class ClientManager extends Application {
    
    
    private Stage primaryStage;
    private AnchorPane fanClientLayout;
    private BorderPane rootLayout;
    
    private void init(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Client Manager");
        
        initRootLayout();
        showClientManagerLayout();
    }
  
    @Override
    public void start(Stage primaryStage){
        init(primaryStage);
        primaryStage.show();
    }
    
    public void initRootLayout(){
        
               try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClientManager.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            primaryStage.show();
        } catch (IOException e) {
                       System.out.println("IOException in initRootLayout");

            e.printStackTrace();
        }
        
    }
    
    public void  showClientManagerLayout(){
        
        try{
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClientManager.class.getResource("view/FanPane.fxml"));
            AnchorPane clientManagerLayout = (AnchorPane) loader.load();
          
            rootLayout.setCenter(clientManagerLayout);  
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
}

