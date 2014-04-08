import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{
	
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	private CBnoUI cb;
	
	public Client(String host){
		super("Client");
		serverIP = host;
		cb = new CBnoUI();
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						sendM(event.getActionCommand());
						userText.setText("");
					}
				}
			);
		add(userText, BorderLayout.SOUTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(450,300);
		setVisible(true);
	}
	
	//connect to server
	public void startRunning(){
		try{
			connectToServer();
			setupStreams();
			whileChatting();
		}catch(EOFException eofException){
			dispM("\n CONNECTION TERMINATED");
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally{
			//closeUp();
		}
	}
	
	//connect to server
	private void connectToServer() throws IOException{
		dispM("Trying connection.. \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		dispM("Connected to: "+connection.getInetAddress().getHostName());
	}
	
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		dispM("\nStreams Active");
	}
	
	private void whileChatting() throws IOException{
		canWrite(true);
		do{
			try{
				message = (String)input.readObject();
				cb.respond(message);
				dispM("\nCODEBOT1:   "+message);
				sendM(cb.out);
			}catch(ClassNotFoundException classnotfoundException){
				dispM("\n CLASS NOT FOUND");
			}
			
		}while(!message.equals("END"));
	}
	
	private void closeUp(){
		dispM("\n closing crap down...");
		canWrite(false);
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	private void sendM(String message){
		try{
			output.writeObject(message);
			output.flush();
			dispM("\n CODEBOT2:   " + message);
		}catch(IOException ioException){
			chatWindow.append("ERROR SENDING");
		}
	}
	
	private void dispM(final String mes){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						chatWindow.append(mes);
					}
				}
			);
	}
	
	private void canWrite(final boolean tof){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						userText.setEditable(tof);
					}
				}
			);
	}
}
