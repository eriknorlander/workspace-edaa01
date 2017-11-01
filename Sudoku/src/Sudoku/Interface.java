package Sudoku;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class Interface {

	private Sudoku sudoku;
	private TilePane tpboard;
	private BorderPane root;

	public Interface(Sudoku sudoku) {
		this.sudoku = sudoku;
		this.tpboard = makeTP();
		this.root = new BorderPane();
		HBox control = makeBox();

		root.setCenter(tpboard);
		root.setBottom(control);
		root.autosize();
	}
	/** Returnerar roten. */
	public Parent getRoot() {
		return root;
	}

	private TilePane makeTP() {
		TilePane tile = new TilePane();
	
		tile.setMaxSize(500, 500);
		tile.setMinSize(500, 500);
	
		tile.setHgap(4);
		tile.setVgap(4);
		tile.setPadding(new Insets(0, 10, 0, 10));
		tile.setPrefColumns(9);
		tile.setPrefRows(9);
		tile.setAlignment(Pos.CENTER);
	
		tile.getChildren().clear();
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				tile.getChildren().add(makeTF(col, row));
			}
		}
		return tile;
	}

	private TextField makeTF(int col, int row) {
		OneLetterTextField tf = new OneLetterTextField();
		int val = sudoku.get(col, row);		
		String text = val != 0 ? Integer.toString(val) : "0";
	
		tf.replaceSelection(text);
		tf.setPrefWidth(45);
		tf.setPrefHeight(45);
		tf.setAlignment(Pos.CENTER);
		tf.setStyle("-fx-font-weight: bold;");
	
		if (!((row / 3 == 0 && col / 3 == 1) || (row / 3 == 1 && col / 3 == 0) || (row / 3 == 1 && col / 3 == 2)
				|| (row / 3 == 2 && col / 3 == 1))) {
			tf.setStyle("-fx-background-color: rgba(167, 100, 255, 1.0);" + "-fx-font-weight: bold;");
		}
		return tf;
	}

	private HBox makeBox() {
		HBox hbox = new HBox();
	
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
	
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.BOTTOM_CENTER);
	
		solve.setPrefSize(100, 20);
		solve.setOnAction(e -> solve());
		clear.setPrefSize(100, 20);
		clear.setOnAction(e -> clear());
		
		hbox.getChildren().addAll(solve, clear);
	
		return hbox;
	}

	private void update(TilePane tile) {
		tile.getChildren().clear();
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				tile.getChildren().add(makeTF(col, row));
			}
		}
	}

	private void solve() {
		getBoardVal();
		
		if (sudoku.solve()) {
			update(tpboard);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fel!");
			alert.setHeaderText(null);
			alert.setContentText("Gör om gör rätt!");
			alert.showAndWait();
		}
	}

	private void getBoardVal() {
		int temp[][] = new int[9][9];
		int counter = 0;
		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				TextField tftemp = (TextField) tpboard.getChildren().get(counter);
				temp[row][col] = tftemp.getText().isEmpty() ? 0 : Integer.parseInt(tftemp.getText());
				counter++;
			}
		}
		sudoku.setBoard(temp);
		update(tpboard);
	}

	private void clear() {
		sudoku.clear();
		update(tpboard);
	}

}
