/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanclient.view;

import fanclient.model.FanPane;
import fanclient.model.FanGroup;
import fanclient.model.Fan;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
  

/**
 *
 * @author chad
 */
public class ClientLayoutController implements Initializable{
    
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML TilePane fanPane;
    
    private final int FANCOUNT = 6;
    private FanGroup fanGroup;
    private Fan [] fanArray = new Fan[FANCOUNT];
    FanPane[] fanPanes = new FanPane[FANCOUNT];
    
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private Socket socket;
    private RootLayoutController rlc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
            for (int i = 0; i < FANCOUNT; i++) {
            fanPanes[i] = new FanPane();
            fanArray[i] = fanPanes[i].getFan();
        }
        
        // Create fan group from fan Array with arbritrary values
        fanGroup = new FanGroup(fanArray,fanArray[0].getTemperature(),40,50);
        
        
                // Create tile pane for fan panes
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(3);
        tilePane.setHgap(-20);

        // Add fan panes to tile pane
        for (FanPane fanPane : fanPanes) {
            tilePane.getChildren().add(fanPane);
        }
        
        scrollPane.setContent(tilePane);
        
        try {
            // Create a socket to connect to the server
       	    socket = new Socket("localhost", 8000);

            // Create an output stream to send data to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.flush();

            // Create an input stream to receive data from the server
            fromServer = new ObjectInputStream(socket.getInputStream());
            
            
            Thread t1 = new Thread(new sendData());
            t1.start();
            
        }catch (ConnectException ce){
           System.out.println("Bad Connection");
        }  		 
        catch(Exception ex){
        	System.out.println("Bad");
        }
        
        
    }
    
    public class sendData implements Runnable{
    	
    	public void run() {
    		
    		try{
    			while(true){
    				
    				//toServer.writeObject(fanGroup);
    				toServer.writeUnshared(fanGroup);
    				toServer.reset();
    				//toServer.writeUnshared(fanPanes[0].getFan());
    				toServer.flush();
    				
    				//Here we see that the speed is updated as the slider is moved
    				//System.out.println(fanGroup.getFans().get(0).getSpeed());
    				
    				Thread.sleep(1000);
    			}
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}finally {
    			
    			try{
    				socket.close();
    				fromServer.close();
    				toServer.close();
    			}catch(SocketException ex){
    				System.err.println(ex);
    				System.out.println("Fan Server Error: Finally Block");
    			} catch (IOException ex) {
					System.err.println(ex);	
				}
    		}
    	}
    }
}
    
    
    


