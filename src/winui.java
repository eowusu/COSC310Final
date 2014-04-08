import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.text.html.HTMLEditorKit;

import com.gtranslate.Translator;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;


public class winui {

	public JFrame frmCodebot;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	private HTMLEditorKit kit;
	private myAct eAct;
	private Translator t;
	private JCheckBox chckbxNewCheckBox;
	
	public winui() {
		initialize();
        eAct = new myAct();
        this.textArea.getInputMap().put( KeyStroke.getKeyStroke( "ENTER" ),
                "doEnterAction" );
        this.textArea.getActionMap().put( "doEnterAction", eAct );
	}
	
	class myAct extends AbstractAction {
	    public myAct() {
	        
	    }
	    public void actionPerformed(ActionEvent e) {
			Codebot.respond(textArea.getText());
			textArea.setText("");
	    }
	}
	
	class CheckBoxListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == chckbxNewCheckBox){
				if(chckbxNewCheckBox.isSelected()){
					Codebot.lang = 1;
				}
				else{
					Codebot.lang=0;
				}
			}
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		System.out.println("initializing window");
		frmCodebot = new JFrame();
		frmCodebot.setTitle("CodeBot");
		frmCodebot.setForeground(Color.BLACK);
		frmCodebot.getContentPane().setForeground(Color.BLACK);
		frmCodebot.setBounds(100, 100, 494, 326);
		frmCodebot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCodebot.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBounds(6, 212, 376, 60);
		frmCodebot.getContentPane().add(textArea);
		
		btnNewButton = new JButton("Enter");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Codebot.respond(textArea.getText());
				textArea.setText("");
			}
		} );
		
		btnNewButton.setBounds(394, 212, 94, 60);
		frmCodebot.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 482, 194);
		frmCodebot.getContentPane().add(scrollPane);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		scrollPane.setViewportView(editorPane);
		
		chckbxNewCheckBox = new JCheckBox("Lithuanian?");
		chckbxNewCheckBox.setBounds(6, 275, 128, 23);
		frmCodebot.getContentPane().add(chckbxNewCheckBox);
		
		chckbxNewCheckBox.addItemListener(new CheckBoxListener());
		
		System.out.println("window initialized");
		frmCodebot.setVisible(true);
	}
	
	public void upCon(String str){
		//System.out.println("in upCon");}
		String cap1 = editorPane.getText().substring(0,38);
		String cap2 = editorPane.getText().substring(editorPane.getText().length()-16);
		String text = editorPane.getText().substring(39, editorPane.getText().length()-16) +"<br>" + str;
		//System.out.println("updated window");
		text = cap1+text+cap2;
		//System.out.println(text);
		editorPane.setText(text);
		//System.out.println("finished upCon");
	}
}
