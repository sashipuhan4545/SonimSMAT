package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class MainController implements Initializable{
	

	@FXML
	private Label errorMessage;

	@FXML
	private TextField userName;

	@FXML
	private TextField password;

	@FXML
	private Button LoginBtn;
	
	@FXML
	private CheckBox rememberMe;

	@FXML
	private Hyperlink smatSupport;

	public static String TEAM_BASED_LOGIN;
	public static Stage secondaryStage1;
	
	Preferences prefs;

	public static Scene scene;
	
	
	
	

	public void login(ActionEvent event) throws IOException {


		try {


			if(userName.getText().trim().equals("qateam1") && password.getText().trim().equals("$Sonim#Tech#bLr")) {
				
				
				
				errorMessage.setTextFill(Color.GREEN);
				errorMessage.setFont(Font.font ("Verdana", 12));
				errorMessage.setText("Logged in Successfully");

				Parent child = FXMLLoader.load(getClass().getResource("/application/AllQA.fxml"));
				scene = new Scene(child,1350,685);
				secondaryStage1 = new Stage();
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				secondaryStage1.getIcons().add(new Image("/application/newLogo.png"));
				secondaryStage1.resizableProperty().setValue(Boolean.TRUE);
				secondaryStage1.setMaximized(true);
				
				secondaryStage1.initStyle(StageStyle.DECORATED);
				secondaryStage1.setScene(scene);
				secondaryStage1.setTitle("S-MAT");
				secondaryStage1.show();

				Stage stage=(Stage)LoginBtn.getScene().getWindow();
				stage.close();

				
				secondaryStage1.setOnCloseRequest(e-> {
                    e.consume();
					closePrograme();

				});
				
					
				if(rememberMe.isSelected()) {
					
					
					
					prefs.put("username", userName.getText());
					prefs.put("password", password.getText());
					
					
					}else {
						
					

						prefs.put("username", "");
						prefs.put("password", "");
					}
			


			}else if (userName.getText().trim().equals("pmteam1") && password.getText().trim().equals("$123#tech#Sonim") || userName.getText().trim().equals("opsteam") && password.getText().trim().equals("tech123$0n1m") || userName.getText().trim().equals("devqateam") && password.getText().trim().equals("#$0n1mTech123") || userName.getText().trim().equals("csteam") && password.getText().trim().equals("$0n1m#tech") || userName.getText().trim().equals("pdmteam1") && password.getText().trim().equals("#Sonim$tech#123") || userName.getText().trim().equals("stteam") && password.getText().trim().equals("Sonim@tech$123")) {

				
				
				
				errorMessage.setTextFill(Color.GREEN);
				errorMessage.setFont(Font.font ("Verdana", 12));
				errorMessage.setText("Logged in Successfully");


				Parent child = FXMLLoader.load(getClass().getResource("/application/NonQA.fxml"));
				Scene scene = new Scene(child,1350,685);
				secondaryStage1 = new Stage();
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				secondaryStage1.getIcons().add(new Image("/application/newLogo.png"));
				secondaryStage1.resizableProperty().setValue(Boolean.FALSE);
				secondaryStage1.setMaximized(false);
				secondaryStage1.initStyle(StageStyle.DECORATED);
				secondaryStage1.setScene(scene);
				secondaryStage1.setTitle("S-MAT");
				secondaryStage1.show();


				Stage stage=(Stage)LoginBtn.getScene().getWindow();
				stage.close();

				secondaryStage1.setOnCloseRequest(e-> {

					e.consume();
					closePrograme();

				});


				
				if(rememberMe.isSelected()) {
					
					prefs.put("username", userName.getText());
					prefs.put("password", password.getText());
					
					
					}else {
						
						prefs.put("username", "");
						prefs.put("password", "");
					}
			


			}else if (userName.getText().equals("") || password.getText().equals("")) {



				errorMessage.setTextFill(Color.RED);
				//	errorMessage.setFont(Font.font ("Verdana", 12));
				errorMessage.setText("Please Enter Username & Password");

			}
			else {

				errorMessage.setTextFill(Color.RED);
				//errorMessage.setFont(Font.font ("Verdana", 12));
				errorMessage.setText("Invalid Username or Password");
			}


			/*
			 * This section is used to check which team is currently logged in like sales team,Qa tema etc 
			 */
			String name=userName.getText().trim();
			switch(name) {

			case "stteam":TEAM_BASED_LOGIN="Logged in as: Sales Team";break;
			case "qateam1":TEAM_BASED_LOGIN="Logged in as: QA Team";break;
			case "pdmteam1":TEAM_BASED_LOGIN="Logged in as: Production Team";break;
			case "csteam":TEAM_BASED_LOGIN="Logged in as: CS Team";break;
			case "opsteam":TEAM_BASED_LOGIN="Logged in as: OPS Team";break;
			case "devqateam":TEAM_BASED_LOGIN="Logged in as: DevQa Team";break;
			case "pmteam1":TEAM_BASED_LOGIN="Logged in as: PM Team";break;

			}

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("Problem is found in MainController class");
		}



	}




	public void closePrograme() {



		try {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			//alert.setTitle("S-MAT");
			alert.initStyle(StageStyle.UNDECORATED);
			alert.initOwner(secondaryStage1);

			alert.setContentText("Do you want to Exit ?");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogPane.getStyleClass().add("LoginPageCloseIconPopup");
			ButtonType yesButton = new ButtonType("Yes",ButtonData.YES);
			ButtonType noButton = new ButtonType("No",ButtonData.NO);
			alert.getButtonTypes().setAll(yesButton, noButton);

			Optional<ButtonType> action = alert.showAndWait();
			if(action.get()==yesButton) {
				System.exit(0);
			}

		} catch (Exception e) {

			System.out.println("Exeption occurs in closing the Main stage");
		}



	}  

	public void closeIcon(ActionEvent event) {

		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			//alert.setTitle("S-MAT");
			alert.initStyle(StageStyle.UNDECORATED);


			alert.setContentText("Do you want to Exit ?");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogPane.getStyleClass().add("LoginPageCloseIconPopup");
			Optional<ButtonType> action = alert.showAndWait();
			if(action.get()==ButtonType.OK)
				System.exit(0);
		} catch (Exception e) {

		}
	}


	@FXML
	public void smatSupport() throws URISyntaxException, IOException {

		try {

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.MAIL)) {
					URI mailto = new URI("mailto:smat@sonimtech.com?subject=S-MAT%20queries");
					desktop.mail(mailto);
				}
			}
			
		}catch (Exception e) {
			
			AllQA hc=new AllQA();
			Platform.runLater(()->hc.AlertDialog("Please configure the Windows mail in your System"));
			
		}


	}




	public void minimize(ActionEvent event) {
		try {
			Stage primaryStage = (Stage)errorMessage.getScene().getWindow();
			primaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
			primaryStage.setIconified(true);
		} catch (Exception e) {
		}
	}	
	

	
//4.jan.2018 This method is used to Remember the username and password if Remember username and password is checkbox is enabled 

public void initialize(URL location, ResourceBundle resources) {
	
  prefs= Preferences.userRoot().node(this.getClass().getName());
	 
	 if(prefs!=null) {
		 
		 if(prefs.get("username", null)!=null  &&  !prefs.get("password", null).isEmpty()) {
			 
			 userName.setText(prefs.get("username", null));
			 password.setText(prefs.get("password",null));
			 rememberMe.setSelected(true);
			 
			 
			
			 
		 }
		 
		 
		 
		 
	 }
	
	
	
	
}





}







