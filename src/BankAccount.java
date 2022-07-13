import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
   The BankAccount class simulates a bank account.
*/

public class BankAccount
{
   private double balance;// Account balance
   private int ID;
   private String name;
   


   public int getID() {
	   return ID;
   }

   public void setID(int iD) {
	   ID = iD;
   }

   public String getName() {
	   return name;
   }

   public void setName(String name) {
	   this.name = name;
   }

/**
      This constructor sets the starting balance
      at 0.0.
   */

   public BankAccount()
   {
      balance = 0.0;
   }
   
   /**
      This constructor sets the starting balance
      to the value passed as an argument.
      @param startBalance The starting balance.
   */

   public BankAccount(double startBalance, int ID, String name)
   {
      balance = startBalance;
      this.ID = ID;
      this.name = name;
   }

   /**
      This constructor sets the starting balance
      to the value in the String argument.
      @param str The starting balance, as a String.
   */

   public BankAccount(String str)
   {
      balance = Double.parseDouble(str);
   }

   /**
      The deposit method makes a deposit into
      the account.
      @param amount The amount to add to the
                    balance field.
   */

   public void deposit(double amount)
   {
      balance += amount;
   }

   /**
      The deposit method makes a deposit into
      the account.
      @param str The amount to add to the
                 balance field, as a String.
   */

   public void deposit(String str)
   {
	   if(isNumber(str))
		   balance += Double.parseDouble(str);
   }

   /**
      The withdraw method withdraws an amount
      from the account.
      @param amount The amount to subtract from
                    the balance field.
   */

   public void withdraw(double amount)
   {
	   if(amount<=balance)
		   balance -= amount;
	   else 
		   JOptionPane.showMessageDialog(null, "Brak œrodków na koncie", "Brak œrodków", JOptionPane.ERROR_MESSAGE);
   }

   /**
      The withdraw method withdraws an amount
      from the account.
      @param str The amount to subtract from
                 the balance field, as a String.
   */

   public void withdraw(String str)
   {
	   if(Double.parseDouble(str)<=balance && isNumber(str))
		   balance -= Double.parseDouble(str);
	   else 
		   JOptionPane.showMessageDialog(null, "Brak œrodków na koncie", "Brak œrodków", JOptionPane.ERROR_MESSAGE);
	   System.out.println(Double.parseDouble(str));
   }

   /**
      The setBalance method sets the account balance.
      @param b The value to store in the balance field.
   */

   public void setBalance(double b)
   {
      balance = b;
   }

   /**
      The setBalance method sets the account balance.
      @param str The value, as a String, to store in
                 the balance field.
   */

   public void setBalance(String str)
   {
      balance = Double.parseDouble(str);
   }
   
   /**
      The getBalance method returns the
      account balance.
      @return The value in the balance field.
   */

   public double getBalance()
   {
      return balance;
   }
   
   /*
	 * Method create List for all account
	 */
	
	public static ArrayList<BankAccount> file() throws FileNotFoundException {
		Scanner file = new Scanner(new File("Account.txt"));
		
		ArrayList<BankAccount> daneKonta = new ArrayList<BankAccount>();
		String acc;
		while(file.hasNextLine()) {
			acc= file.nextLine();
			daneKonta.add(new BankAccount(
										  	Double.parseDouble(acc.split(":")[2].replace(',', '.')),
										  	Integer.parseInt(acc.split(":")[0]),
										  	acc.split(":")[1])
										  );
			
		}
		file.close();
		return daneKonta;
	}
	
	
	/*
	 * Method add new user to database
	 */
	public static void createUser(String name) throws IOException {
		if(JOptionPane.showConfirmDialog(null, 
										 "Czy na pewno chcesz Stworzyæ u¿ytkownika o nazwie " + name,
										 "Powtwierdzenie",
										 JOptionPane.YES_NO_OPTION
										 )==0) { 
			
				
				ArrayList<BankAccount> acc = file();
				acc.add(new BankAccount(0.00,Integer.parseInt(newID()),name));
				PrintWriter save = new PrintWriter("Account.txt");
				for(int i=0; i< acc.size(); i++) {
					save.println(acc.get(i).toString());
				}
				save.close();
				JOptionPane.showMessageDialog(null,
						  "Stworzono u¿ytkownika. Witaj " + name, 
						  "Witaj", 
						  JOptionPane.INFORMATION_MESSAGE
						  );
			
		}
	}
	
	
	/*
	 * Method find next freeID for new User
	 */
	
	public static String newID() throws FileNotFoundException {
		return String.format("%04d",file().get(file().size()-1).getID()+1);
		
	}
	
	/*
	 * Method to check id exist and give data for account
	 */
	
	public static BankAccount findId(String ID) throws NumberFormatException {
		try {
			if(isNumber(ID)) {
			ArrayList<BankAccount> acc = file();
				for(int i=0; i<acc.size();i++) {
					if(Integer.parseInt(ID) == acc.get(i).getID()) {
						return acc.get(i);
					}
				}

			
			}else {
			JOptionPane.showMessageDialog(null,
										  "Wprowadz 4 cyfrowy kod ID",
										  "Nieprawid³owy kod",
										  JOptionPane.ERROR_MESSAGE
										  );
			}
		}catch(Exception e){
	}
		
		
		return null;
	}
	/*
	 * Method checks a String a is a number or Double
	 */
	public static boolean isNumber(String a) {
		try {
			Double.parseDouble(a);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public void transfer(String addressID, String amount) throws FileNotFoundException {
		
		if(isNumber(addressID) && isNumber(amount)) {
			ArrayList<BankAccount> acc = file();
			BankAccount accountAddress;
			if(findId(addressID) != null) {
				for(int i = 0; i< acc.size(); i++) {
					if(findId(addressID).equals(acc.get(i))) {
						accountAddress = acc.get(i);
						accountAddress.deposit(amount);
						saveChanges(accountAddress);
						break;
					}
				}
				
			}
		}
	}
	
	
	public static void saveChanges(BankAccount acc) throws FileNotFoundException {
		ArrayList<BankAccount> accounts = file();
		PrintWriter save = new PrintWriter("Account.txt");
		
		accounts.remove(acc.getID()-1);
		accounts.add(acc.getID()-1, acc);
		
		for(int i=0; i< accounts.size(); i++) {
			save.println(accounts.get(i).toString());
		}
		save.close();
		
	}
	@Override
	public String toString() {
		return String.format("%04d:%s:%.2f", getID(), getName(), getBalance());
	}
	@Override
	public int hashCode() {
		return Objects.hash(ID, balance, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return ID == other.ID && Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(name, other.name);
	}
	
}
