
import javax.swing.JFrame;

public class ClientRun {

		static Client bot1;
		
		public static void main(String[] args) {
			bot1 = new Client("127.0.0.1");
			bot1.startRunning();
		}
}
