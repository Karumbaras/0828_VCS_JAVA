package lt.vs.andrius.javafx;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


// mano langelis su keliais fake metodais
public class Langelis extends Rectangle {
	
	public String manoKazkoksLaukelis = "A";
	
	public void manoKazkoksMetodas(){
		System.out.println(manoKazkoksLaukelis);
	}
	
	
	// Sito nenaudoju ir tikriausiai nereikia situo atveju keiciant spalvas
	// Spalvas galima keisti tiesiai kreipiantis i langeli setFill
	// Nebent jeigu kils multithreading problemu
	public void setColor(Paint paint) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				setFill(paint);
			}
		});
	}
}
