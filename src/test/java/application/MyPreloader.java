package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;
    
    
    public static void main(String[] args) {
		launch(args);
	}
    
    public MyPreloader() {
        
    }

    @Override
    public void init() throws Exception {               
                                         
    Parent root1 = FXMLLoader.load(getClass().getResource("/application/splashScreen.fxml")); 
    scene = new Scene(root1); 
   
                
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
       this.preloaderStage = primaryStage;

        preloaderStage.setScene(scene);  
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
        
        
      
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
      
          if (info instanceof ProgressNotification) {
            FXMLDocumentController.label.setText("Loading "+((ProgressNotification) info).getProgress()*100 + "%");
          //  System.out.println("Value@ :" + ((ProgressNotification) info).getProgress());
            FXMLDocumentController.statProgressBar.setProgress(((ProgressNotification) info).getProgress());
        }

               
        
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
      
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            
            case BEFORE_START:
              
                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }
        
        
    }
}