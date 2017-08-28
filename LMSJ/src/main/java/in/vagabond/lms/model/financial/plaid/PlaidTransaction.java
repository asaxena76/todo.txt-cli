package in.vagabond.lms.model.financial.plaid;

import com.plaid.client.response.TransactionsGetResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asaxena on 8/2/17.
 */
@Data
@Entity
@NoArgsConstructor
public class PlaidTransaction {

    @Transient
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    private String transactionId;


    @ManyToOne
    private PlaidAccount plaidAccount;

    private String accountOwner;

    private Long amount;

    @OneToOne
    private PlaidCategory plaidCategory;


    @Temporal(TemporalType.DATE)
    private Date txdate;

    private String txname;

    @ManyToOne
    private PlaidLocation plaidLocation;

    // Payment_meta

    private String byOrderOf;

    private String payee;

    private String payer;

    private String paymentMethod;

    private String paymentProcessor;

    private String ppdId;

    private String reason;

    private String referenceNumber;

    private Boolean pending;

    private String pendingTransactionId;



    private String transactionType;

    public  PlaidCategory getPlaidCategory() {
        if ( plaidCategory == null) {
            return PlaidCategory.NONE;
        } else return plaidCategory;
    }

    public PlaidTransaction(TransactionsGetResponse.Transaction transaction) {
     this.transactionId = transaction.getTransactionId();
        this.pendingTransactionId = transaction.getPendingTransactionId();
        this.pending = transaction.getPending();
        this.referenceNumber = transaction.getPaymentMeta().getReferenceNumber();
        this.byOrderOf = transaction.getPaymentMeta().getByOrderOf();
        this.payee=transaction.getPaymentMeta().getPayee();
        this.payer=transaction.getPaymentMeta().getPayer();
        this.paymentMethod=transaction.getPaymentMeta().getPaymentMethod();

        this.paymentProcessor=transaction.getPaymentMeta().getPaymentProcessor();
        this.ppdId=transaction.getPaymentMeta().getPpdId();
        this.reason = transaction.getPaymentMeta().getReason();
        this.txname=transaction.getName();
        try {
            this.txdate=format.parse(transaction.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new RuntimeException("Could not parse date " , e);
        }
        this.accountOwner=transaction.getAccountOwner();
        this.amount=(long)(transaction.getAmount().doubleValue() * 100);

    }
}
