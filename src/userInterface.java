import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class userInterface extends JFrame implements ActionListener{

	JLabel header = new JLabel();
	JLabel footer = new JLabel();
	
	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	
	JPanel main = new JPanel();
	
	
	userInterface(String ID, String Name, String balance){

		BankAccount acc = new BankAccount(Double.parseDouble(balance),Integer.parseInt(ID), Name);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,800);
		this.setTitle("BankAccount");
		//this.setIconImage(popCat.getImage());
		this.setLayout(new BorderLayout());
		
		header.setPreferredSize(new Dimension(100,100));
		header.setText("Bank Account");
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.BOTTOM);
		header.setFont(new Font("Ink Free", Font.BOLD, 50));
		
		footer.setPreferredSize(new Dimension(100,100));
		
		
		main.setLayout(new GridLayout(1,3));
		main.setPreferredSize(new Dimension(100,100));
		l1.setText(acc.getName());
		l1.setPreferredSize(new Dimension(100,100));
		
		l2.setText(String.format("%04d",acc.getID()));
		l2.setPreferredSize(new Dimension(100,100));
		l3.setText(String.format("%.2f z³",acc.getBalance()));
		l3.setPreferredSize(new Dimension(100,100));
		main.add(l1);
		main.add(l2);
		main.add(l3);
		
		this.add(header, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
