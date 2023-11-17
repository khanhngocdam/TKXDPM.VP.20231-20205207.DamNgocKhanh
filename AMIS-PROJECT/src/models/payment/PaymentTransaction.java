package models.payment;

public class PaymentTransaction {
    private CreditCard card;
    private String transactionContent;
    private String getTransactionId;
    private String createAt;
    private String errorCode;

    public PaymentTransaction(CreditCard card, String transactionContent, String getTransactionId, String createAt, String errorCode) {
        this.card = card;
        this.transactionContent = transactionContent;
        this.getTransactionId = getTransactionId;
        this.createAt = createAt;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
