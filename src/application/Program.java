package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException{
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		String carModel;
		Date start, finish;
		double pricePerHour, pricePerDay;
		
		System.out.println("Entra com a data do aluguel");
		System.out.print("Modelo do carro: ");
		carModel = sc.nextLine();
		System.out.print("Retirada: ");
		start = sdf.parse(sc.nextLine());
		System.out.print("Entrga: ");
		finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
			
		System.out.print("Entre com o preço por hora: ");
		pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		System.out.println("Fatura");
		System.out.println("Pagamento basico: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa: "+ String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: "+ String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		
		sc.close();
	}

}
