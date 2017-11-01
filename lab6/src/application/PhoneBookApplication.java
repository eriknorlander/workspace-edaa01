package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import phonebook.MapPhoneBook;
//import phonebook.MapPhoneBook;
import phonebook.PhoneBook;

public class PhoneBookApplication extends Application {
	private PhoneBook phoneBook;
	private NameListView nameListView;

	/**
	 * The entry point for the Java program.
	 * @param args
	 */
	public static void main(String[] args) {	
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Dialogs dialog = new Dialogs();
		dialog.alert("Eriks PhoneBook", "Välkommen till min PhoneBook.", "Tryck på OK om du vill fortsätta.");
			
		if(dialog.confirmDialog("", "", "Vill du läsa in en textfil med informationen?")){
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
			File selectedFile = fileChooser.showOpenDialog(primaryStage);	
			read(selectedFile);
		} else {
			phoneBook = new MapPhoneBook();
			phoneBook.put("Justin", "1337");
		}
		// set default locale english 
		Locale.setDefault(Locale.ENGLISH);
		
		nameListView = new NameListView(phoneBook);
		BorderPane root = new BorderPane();
		root.setTop(new PhoneBookMenu(phoneBook, nameListView));
		root.setCenter(nameListView);		
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	@Override
	public void stop(){
		// Here you can place any action to be done when the application is closed, i.e. save phone book to file.
		Optional<String> result = Dialogs.oneInputDialog("File", "", "Skriv filnamn:");
		if(result.isPresent()){
			File file = new File("/Users/Erik/Documents/PhoneBook/" + result + ".txt");
			try {
				file.createNewFile();
				write(file);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	private void write(File file){
		try {
			ObjectOutputStream out = 
					new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(phoneBook);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	private void read(File file){
		try {
			ObjectInputStream in =
					new ObjectInputStream(new FileInputStream(file));
			phoneBook = (PhoneBook) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
