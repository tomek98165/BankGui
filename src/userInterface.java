import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
public class userInterface extends JFrame implements ActionListener{

	JLabel header = new JLabel();
	JLabel footer = new JLabel();	
	JLabel mainHeader = new JLabel();
	JLabel textfieldHeader = new JLabel();
	JLabel infoTextfield = new JLabel();
	JLabel addressIDInfo = new JLabel();
	
	JPanel main = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel textfieldPanel = new JPanel();
	
	JButton deposit = new JButton();
	JButton withdraw = new JButton();
	JButton transfer = new JButton();
	JButton confirm = new JButton();
	
	JTextField amount = new JTextField();
	JTextField addressID = new JTextField();
	
	BankAccount acc;
	userInterface(int ID, String Name, double balance){

		acc = new BankAccount(balance,ID, Name);
	//Frame
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1420,1000);
		this.setTitle("BankAccount");
		//this.setIconImage(popCat.getImage());
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
		this.setResizable(false);
		
		
		
	//Header	
		header.setPreferredSize(new Dimension(100,100));
		header.setText("Bank Account");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.BOTTOM);
		header.setFont(new Font("Ink Free", Font.BOLD, 50));
		
		
		
	//footer
		footer.setPreferredSize(new Dimension(100,100));
		
	//Main panel	
		main.setLayout(new BorderLayout(100,10));
		main.setPreferredSize(new Dimension(100,100));
		main.add(mainHeader, BorderLayout.NORTH);
		main.add(buttonPanel, BorderLayout.WEST);
		main.add(textfieldPanel, BorderLayout.CENTER);
		
		mainHeader.setText(String.format("Witaj %s twój aktualny stan konta wynosi: %.2f", acc.getName(), acc.getBalance()));
		mainHeader.setHorizontalAlignment(JLabel.CENTER);
		mainHeader.setVerticalAlignment(JLabel.CENTER);
		mainHeader.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		mainHeader.setPreferredSize(new Dimension(100,100));
		
		buttonPanel.setPreferredSize(new Dimension(300,100));
		buttonPanel.setLayout(new GridLayout(3,1,10,10));
		buttonPanel.add(deposit);
		buttonPanel.add(withdraw);
		buttonPanel.add(transfer);
		
		
		deposit.setText("Wp³aæ");
		deposit.addActionListener(this);
		
		withdraw.setText("Wyp³aæ");
		withdraw.addActionListener(this);
		
		transfer.setText("Przelew");
		transfer.addActionListener(this);
		
		textfieldPanel.setVisible(false);
		textfieldPanel.setLayout(null);
		textfieldPanel.setPreferredSize(new Dimension(100,100));
		textfieldPanel.add(textfieldHeader);
		textfieldPanel.add(amount);
		textfieldPanel.add(confirm);
		textfieldPanel.add(addressIDInfo);
		textfieldPanel.add(addressID);
		
		textfieldHeader.setBounds(0,0,647,100);
		textfieldHeader.setVisible(true);
		textfieldHeader.setVerticalAlignment(JLabel.CENTER);
		textfieldHeader.setHorizontalAlignment(JLabel.CENTER);
		textfieldHeader.setFont(new Font("Arial", Font.PLAIN, 28));
		

		
		amount.setVisible(true);
		amount.setBounds(0,100,647,100);
		amount.setFont(new Font("Arial", Font.PLAIN, 23));
		
		
		
		addressIDInfo.setBounds(0,200,647,100);
		addressIDInfo.setVisible(false);
		addressIDInfo.setVerticalAlignment(JLabel.CENTER);
		addressIDInfo.setHorizontalAlignment(JLabel.CENTER);
		addressIDInfo.setFont(new Font("Arial", Font.PLAIN, 28));
		addressIDInfo.setText("Podaj ID Odbiorcy");
		
		
		addressID.setVisible(false);
		addressID.setBounds(0,300,647,100);
		addressID.setFont(new Font("Arial", Font.PLAIN, 23));
		
		
		
		confirm.setText("ZatwierdŸ");
		confirm.addActionListener(this);
		
		
		
		
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==deposit) {
			
			textfieldPanel.setVisible(true);
			textfieldHeader.setText("Ile Chcesz Wp³aciæ");
			addressID.setVisible(false);
			addressIDInfo.setVisible(false);
			confirm.setBounds(447,250,200,50);
			amount.setName("deposit");
			
			
		}
		if(e.getSource()==withdraw) {
			
			textfieldPanel.setVisible(true);
			textfieldHeader.setText("Ile Chcesz Wyp³aciæ");
			addressID.setVisible(false);
			addressIDInfo.setVisible(false);
			confirm.setBounds(447,250,200,50);
			amount.setName("withdraw");
			

		}
		if(e.getSource()==transfer) {
			textfieldPanel.setVisible(true);
			textfieldHeader.setText("Ile Chcesz Przelaæ");
			addressID.setVisible(true);
			addressIDInfo.setVisible(true);
			confirm.setBounds(447,450,200,50);
			amount.setName("transfer");
		}
		
		if(e.getSource()==confirm) {
			switch(amount.getName()) {
				
			case "withdraw":
				acc.withdraw(amount.getText());
				mainHeader.setText(String.format("Witaj %s twój aktualny stan konta wynosi: %.2f", acc.getName(), acc.getBalance()));
				break;
			case "deposit":
				acc.deposit(amount.getText());
				mainHeader.setText(String.format("Witaj %s twój aktualny stan konta wynosi: %.2f", acc.getName(), acc.getBalance()));
				break;
			case "transfer":
				acc.withdraw(Double.parseDouble(amount.getText()));
				try {
					acc.transfer(addressID.getText(), amount.getText());
					mainHeader.setText(String.format("Witaj %s twój aktualny stan konta wynosi: %.2f", acc.getName(), acc.getBalance()));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				mainHeader.setText(String.format("Witaj %s twój aktualny stan konta wynosi: %.2f", acc.getName(), acc.getBalance()));
				break;
			}
	
		}
		try {
			BankAccount.saveChanges(acc);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}

}
