import javax.swing.JFrame;

public class ServerRun {
		static Server bot2;
		//bot2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		public static void main(String[] args) {
			bot2 = new Server();
			bot2.startRunning();
		}
	}
