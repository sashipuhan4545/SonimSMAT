package application;

import java.io.IOException;

import com.sun.javafx.application.LauncherImpl;

import AllGMSTC.SingleInstance;
import javafx.application.Application;
import javafx.application.Preloader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("restriction")
public class Main extends Application  {

	public static Parent parentroot;
	private static final int COUNT_LIMIT = 10;
	public static Stage primaryStage;  



	public static void main(String[] args) throws InterruptedException  {

		LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);

		LauncherImpl.launchApplication(Main.class, args);

	}


	@Override
	public void init() throws Exception {       

		for (int i = 1; i <= COUNT_LIMIT; i++) {
			double progress =(double) i/10;

			LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));

			Thread.sleep(500);
		}

	}

	@Override
	public void start(Stage primaryStage) throws IOException, InterruptedException {

		

		  singleInstacne();


		parentroot = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(parentroot, 800, 438);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.resizableProperty().setValue(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image("/application/newLogo.png"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("S-MAT");
		primaryStage.show();

	}


	public static void singleInstacne() {

		Thread t=new Thread(new Runnable() {



			@SuppressWarnings("static-access")
			public void run() {

				SingleInstance st=new SingleInstance();
				st.main(null);

			}
		});
		t.start();
	}


}

