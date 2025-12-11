package exceptions;

public class UnderPaymentHandling extends Exception{
    public UnderPaymentHandling (){
        super("Saldo Tidak Cukup");
    }
}
