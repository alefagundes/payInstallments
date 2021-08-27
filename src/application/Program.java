package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import Services.ContractService;
import Services.PaypalService;
import entities.Contract;
import entities.Installment;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		

		System.out.println("<-Enter contract data->");
		System.out.print("Number:");
		Integer number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy):");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value:");
		Double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.println("Enter number of Intallments:");
		int n = sc.nextInt();
		
		ContractService cs = new ContractService(new PaypalService());
		cs.processContract(contract, n);
		
		System.out.println();
		
		System.out.println("INSTALLMENTS:");
		for(Installment it: contract.getIntallments()) {
			System.out.println(it);
		}
		sc.close();
	}

}
