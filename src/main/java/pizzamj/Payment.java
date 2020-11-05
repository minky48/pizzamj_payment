package pizzamj;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String paymentStatus;

    @PostPersist
    public void onPostPersist(){
        Paid paid = new Paid();
        BeanUtils.copyProperties(this, paid);
        paid.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        PaymentCanceled paymentCanceled = new PaymentCanceled();
        BeanUtils.copyProperties(this, paymentCanceled);
        paymentCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }




}
