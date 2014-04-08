
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame{
	
	private JTextField userText;
	private JTextArea chatWindow;
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	
	private Socket connection;
	private CBnoUI cb;
	
	//constructor
	public Server(){
		super("CodeBot IM");
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
		add(new JScrollPane(chatWindow));
		setSize(450, 300);
		setVisible(true);
	}
	
	//set up and run the server
	public void startRunning(){
		try{
			server = new ServerSocket(6789, 100);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					whileChatting();
				}catch(EOFException eofException){
					dispM("\n Server ended the connection ");
				}finally{
					//closeUp();
				}
			}
			
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	//wait for connection, then display connection information
	private void waitForConnection() throws IOException{
		dispM("Waiting for someone to connect...\n");
		connection = server.accept();
		dispM("Connected to " + connection.getInetAddress().getHostName());
	}
	
	//get stream to send and receive data
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		dispM("\n Streams active \n");
	}
	
	//in conversation
	private void whileChatting() throws IOException{
		String message = cb.out;
		sendM(message);
		canWrite(true);
		do{
			//have conversation
			try{
				message = (String) input.readObject();
				cb.respond(message);
				dispM("\nCODEBOT2:   " + message);
				sendM(cb.out);
			}catch(ClassNotFoundException classNotFoundException){
				dispM("\n idk wtf");
			}
		}while(!message.equals("END"));
	}
	//close streams and sockets after you are done chatting
	private void closeUp(){
		dispM("\n Closing connections... \n");
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
			dispM("\nCODEBOT1:    " + message);
		}catch(IOException ioException){
			chatWindow.append("\n ERROR SENDING MESSAGE");
		}
	}
	
	//updates chatWindow
	private void dispM(final String text){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					chatWindow.append(text);
				}
			}
		);
	}
	
	//let user type
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
