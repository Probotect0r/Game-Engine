import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GameMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		Game game = new Game();		
		frame.add(game, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		
		game.start();
	}

}
