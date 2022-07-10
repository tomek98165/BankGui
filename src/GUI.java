import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
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
	
	JTextField idNumber = new JTextField();	
	JTextField rName = new JTextField();
	
	
	
	
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
		login.setBounds(0,0,200,80);
		
		register.setText("Stwórz nowe konto");
		register.addActionListener(this);
		register.setBounds(0,80,200,80);
		
		
		
		//Login Panel
		loginPanel.setVisible(false);
		loginPanel.setBounds(300,0,600,80);
		loginPanel.setLayout(null);
		
		loginPanel.add(idNumber);
		loginPanel.add(infoID);
		
		idNumber.setVisible(true);
		idNumber.setBounds(300,20,300,40);
		idNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		
		infoID.setVisible(true);
		infoID.setBounds(0,0,300,80);
		infoID.setText("<html>Podaj swoje ID<br/>ponownie wciœnij Zaloguj siê</html>");
		infoID.setVerticalAlignment(JLabel.CENTER);
		infoID.setHorizontalAlignment(JLabel.CENTER);
		infoID.setFont(new Font("MS Boli", Font.PLAIN,23));
		
		//register Panel
		registerPanel.setVisible(false);
		registerPanel.setBounds(300,0,600,240);
		registerPanel.setLayout(null);
		
		rInfo.setVisible(true);
		rInfo.setBounds(0,0,600,80);
		rInfo.setText("<html>Po wprowadzeniu danych klikn¹æ ponownie Zarejestruj siê</html>");
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
		rIDNumber.setText("8888");
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
		
		registerPanel.add(rInfo);
		registerPanel.add(rIDNumberInfo);
		registerPanel.add(rIDNumber);
		registerPanel.add(rNameInfo);
		registerPanel.add(rName);
		
		
		
		//add to Frame
		
		this.add(header, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
		
	}
	
	/*
	 * Method create next ID number for new account
	 */
	
	public static String file() throws FileNotFoundException {
		Scanner file = new Scanner(new File("Account.txt"));
		
		ArrayList<String> daneKonta = new ArrayList<String>();
		while(file.hasNextLine()) {
			
			daneKonta.add(file.nextLine());	
		}
		file.close();
		String[] t = (daneKonta.get(daneKonta.size()-1)).split(":");
		int x = Integer.parseInt(t[0]);
		
		return String.format("%04d", x+1);
		
		
	}
	
	/*
	 * Method find data for user
	 * a is ID user
	 * if ID user doesn't exist return null
	 */
	
	public static String findID(String a) throws FileNotFoundException {
		Scanner file = new Scanner(new File("Account.txt"));
		String x;
		while(file.hasNextLine()) {
			x = file.nextLine();
			if(a.equals(x.split(":")[0])) {
				return x;
			}
		}
		
		return null;
		
	}
	/*
	 * Method add new user to database
	 */
	public static void createUser(String a, String b) throws IOException {
		
		
		Scanner accounts = new Scanner(new File("Account.txt"));
		ArrayList<String> acc = new ArrayList<String>();
		while(accounts.hasNextLine()) {
			acc.add(accounts.nextLine());
		}
		accounts.close();
		String result = a + ":" + b + ":0";
		
		PrintWriter save = new PrintWriter("Account.txt");
		
		for(int i=0; i< acc.size(); i++) {
			save.println(acc.get(i));
		}
		save.println(result);
		save.close();
		
	}
	
	
	/*
	 * Method checks a String a is a number
	 */
	public static boolean isNumber(String a) {
		try {
			Integer.parseInt(a);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login) {
			if(loginPanel.isVisible()) {
				if(idNumber.getText().isEmpty()) {
					loginPanel.setVisible(false);
				} else {
					try {
						if(idNumber.getText().length() != 4 || !isNumber(idNumber.getText())) {
							JOptionPane.showMessageDialog(null,
									"Nieprawid³owe ID podaj 4 cyfry", 
									"Nieprawid³owe ID", 
									JOptionPane.ERROR_MESSAGE
									);
						}
						else if(findID(idNumber.getText()) == null){
							JOptionPane.showMessageDialog(null,
									"Nie ma w bazie u¿ytkownika o podanym ID", 
									"Brak danych", 
									JOptionPane.ERROR_MESSAGE
									);
							System.out.println(idNumber.getText()+isNumber(idNumber.getText()));
							idNumber.setText("");
						}else {
							JOptionPane.showMessageDialog(null,
									"Witaj "+ findID(idNumber.getText()).split(":")[1], 
									"Hello", 
									JOptionPane.INFORMATION_MESSAGE
									);
							this.dispose();
							new userInterface(findID(idNumber.getText()).split(":")[0],findID(idNumber.getText()).split(":")[1],findID(idNumber.getText()).split(":")[2]);
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				loginPanel.setVisible(true);
				registerPanel.setVisible(false);
				
			}
		}
		if(e.getSource()==register) {
			if(registerPanel.isVisible()) {
				if(rName.getText().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Pole nazwa nie mo¿e byæ puste", 
							"Puste pole", 
							JOptionPane.ERROR_MESSAGE
							);
				else {
					if(
							JOptionPane.showConfirmDialog(null,
							"Czy na pewno chcesz utworzyæ u¿ytkownika "+rName.getText(), 
							"Potwierdzenie", 
							JOptionPane.YES_NO_OPTION
							) == 0
						) {
					
							try {
								createUser(rIDNumber.getText(),rName.getText());
								JOptionPane.showMessageDialog(null,
										"Stworzono u¿ytkownika "+rName.getText(), 
										"Stworzono U¿ytkownika", 
										JOptionPane.INFORMATION_MESSAGE
										);
								rName.setText("");
								registerPanel.setVisible(false);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
				
			}else {
				registerPanel.setVisible(true);
				
				try {
					rIDNumber.setText(file());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				loginPanel.setVisible(false);
			}
		}
		
	}
	
	
	
}
