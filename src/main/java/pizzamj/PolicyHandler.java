package pizzamj;

import pizzamj.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_CancelPayment(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            Iterator<Payment> iterator = paymentRepository.findAll().iterator();
            while(iterator.hasNext()){
                Payment paymenttmp = iterator.next();
                if(paymenttmp.getOrderId() == orderCanceled.getId()){
                    Optional<Payment> PaymentOptional = paymentRepository.findById(paymenttmp.getId());
                    Payment payment = PaymentOptional.get();
                    payment.setPaymentStatus(orderCanceled.getOrderStatus());
                    paymentRepository.save(payment);
                }
            }



            System.out.println("##### listener CancelPayment : " + orderCanceled.toJson());
        }
    }

}
