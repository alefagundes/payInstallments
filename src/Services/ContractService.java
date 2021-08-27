package Services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService=onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months;
	    for(int i=1;i<=months;i++) {
	    	double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
	    	double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
	    	Date dueDate = addMonths(contract.getDate(), i);
	    	contract.getIntallments().add(new Installment(dueDate, fullQuota));
	    }
	}
	
	
	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

}
