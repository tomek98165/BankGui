import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
public class GUI extends JFrame implements ActionListener {
		
	
	JPanel main = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel registerPanel = new JPanel();
	
	
	JLabel header = new JLabel();
	JLabel footer = new JLabel();
	JLabel iDInfo = new JLabel();
	
	JLabel infoID = new JLabel();
	
	JLabel rInfo = new JLabel();
	JLabel rIDNumberInfo = new JLabel();
	JLabel rIDNumber = new JLabel();
	JLabel rNameInfo = new JLabel();
	
	
	
	JButton login = new JButton();
	JButton register = new JButton();
	JButton lConfirm = new JButton();
	JButton rConfirm = new JButton();
	
	JTextField idNumber = new JTextField();	
	JTextField rName = new JTextField();
	
	
	/*
	 * Constructor create a frame
	 */
	
	GUI(){
		
		
		//Frame settings
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,800);
		this.setTitle("BankAccount");
		this.setLayout(new BorderLayout());						   
		
		
		
		
		
		
		//Main Panels
		header.setPreferredSize(new Dimension(100,100));
		header.setText("Bank Account");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.BOTTOM);
		header.setFont(new Font("Ink Free", Font.BOLD, 50));
		
		
		footer.setPreferredSize(new Dimension(100,100));
		
		
		main.setLayout(null);
		main.setPreferredSize(new Dimension(100,100));
		
		main.add(login);
		main.add(loginPanel);
		main.add(registerPanel);
		main.add(register);
		
		//Buttons in main Panel
		login.setText("Zaloguj siê");
		login.addActionListener(this);
		login.setFocusable(false);
		login.setBounds(0,0,200,80);
		
		register.setText("Stwórz nowe konto");
		register.addActionListener(this);
		register.setBounds(0,80,200,80);
		register.setFocusable(false);
		
		
		//Login Panel
		loginPanel.setVisible(false);
		loginPanel.setBounds(300,0,600,160);
		loginPanel.setLayout(null);
		
		loginPanel.add(idNumber);
		loginPanel.add(infoID);
		loginPanel.add(lConfirm);
		
		idNumber.setVisible(true);
		idNumber.setBounds(300,20,300,40);
		idNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		
		infoID.setVisible(true);
		infoID.setBounds(0,0,300,80);
		infoID.setText("<html>Podaj swoje ID<br/>Potem wciœnij Zaloguj siê</html>");
		infoID.setVerticalAlignment(JLabel.CENTER);
		infoID.setHorizontalAlignment(JLabel.CENTER);
		infoID.setFont(new Font("MS Boli", Font.PLAIN,23));
		
		
		lConfirm.setText("Zaloguj siê");
		lConfirm.setVisible(true);
		lConfirm.setBounds(400,90,200,50);
		lConfirm.addActionListener(this);		
		
		
		//register Panel
		registerPanel.setVisible(false);
		registerPanel.setBounds(300,0,600,540);
		registerPanel.setLayout(null);
		
		rInfo.setVisible(true);
		rInfo.setBounds(0,0,600,80);
		rInfo.setText("<html>Po wprowadzeniu danych kliknij Zarejestruj siê</html>");
		rInfo.setVerticalAlignment(JLabel.CENTER);
		rInfo.setHorizontalAlignment(JLabel.CENTER);
		rInfo.setFont(new Font("MS Boli", Font.PLAIN,23));
		
		rIDNumberInfo.setVisible(true);
		rIDNumberInfo.setBounds(0,80,300,80);
		rIDNumberInfo.setText("<html>Twoje ID</html>");
		rIDNumberInfo.setVerticalAlignment(JLabel.CENTER);
		rIDNumberInfo.setHorizontalAlignment(JLabel.CENTER);
		rIDNumberInfo.setFont(new Font("MS Boli", Font.PLAIN,23));
		
		rIDNumber.setVisible(true);
		rIDNumber.setBounds(300,80,300,80);
		rIDNumber.setText("####");
		rIDNumber.setVerticalAlignment(JLabel.CENTER);
		rIDNumber.setHorizontalAlignment(JLabel.LEFT);
		rIDNumber.setFont(new Font("MS Boli", Font.BOLD,50));
		
		rNameInfo.setVisible(true);
		rNameInfo.setBounds(0,160,300,80);
		rNameInfo.setText("<html>Podaj swoje imiê</html>");
		rNameInfo.setVerticalAlignment(JLabel.CENTER);
		rNameInfo.setHorizontalAlignment(JLabel.LEFT);
		rNameInfo.setFont(new Font("MS Boli", Font.PLAIN,23));
		
		rName.setVisible(true);
		rName.setBounds(300,180,300,40);
		rName.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		
		rConfirm.setText("Stwórz konto");
		rConfirm.setVisible(true);
		rConfirm.setBounds(400,250,200,50);
		rConfirm.addActionListener(this);
		
		registerPanel.add(rInfo);
		registerPanel.add(rIDNumberInfo);
		registerPanel.add(rIDNumber);
		registerPanel.add(rNameInfo);
		registerPanel.add(rName);
		registerPanel.add(rConfirm);
		
		//add to Frame
		
		this.add(header, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login) {

				loginPanel.setVisible(true);
				registerPanel.setVisible(false);
				
		}
		if(e.getSource()==register) {
			loginPanel.setVisible(false);
			registerPanel.setVisible(true);
			try {
				rIDNumber.setText(BankAccount.newID());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
				
			
		}
		
		if(e.getSource()==lConfirm) {
			if(idNumber.getText().length()==4) {
				if(BankAccount.findId(idNumber.getText()) != null) {	
					BankAccount acc = BankAccount.findId(idNumber.getText());
					new userInterface(acc.getID(),acc.getName(),acc.getBalance());
					this.dispose();
				}
			}else {
				JOptionPane.showMessageDialog(null,
											  "Wprowadz 4 cyfrowy kod ID",
											  "Nieprawid³owy kod",
											  JOptionPane.ERROR_MESSAGE
											  );
			}
			
		}
		if(e.getSource()==rConfirm) {
			
			if(rName.getText().isEmpty())
				JOptionPane.showMessageDialog(main, "Musisz Podaæ Swoje Imie", "Puste Pole", JOptionPane.ERROR_MESSAGE);
			else {
				try {
					BankAccount.createUser(rName.getText());
					rName.setText("");
					registerPanel.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
}
