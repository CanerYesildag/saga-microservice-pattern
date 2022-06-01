package com.payment.paymentservice.application;

import com.payment.paymentservice.application.event.BilledOrderEvent;
import com.payment.paymentservice.application.event.CancelCouponUseEvent;
import com.payment.paymentservice.domain.Order;
import com.payment.paymentservice.domain.Payment;
import com.payment.paymentservice.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final BankPosService bankPosService;

    public void pay(Order order, String transactionId) {
        /*
         * Any payment business logic
         */

        int bankPosPaymentResponse = bankPosService.pay(order.getPrice());
        if (bankPosPaymentResponse !=  HttpServletResponse.SC_OK) {
            eventPublisher.publishEvent(new CancelCouponUseEvent(transactionId, order));
        }

        Payment payment = createPayment(order);
        paymentRepository.save(payment);
        eventPublisher.publishEvent(new BilledOrderEvent(transactionId, order));
    }

    private Payment createPayment(Order order) {
        return Payment.builder()
                .paymentStatus(PaymentStatus.SUCCESS)
                .price(order.getPrice())
                .usedOrderId(order.getId())
                .build();
    }

    public void refund(Long orderId) {
        paymentRepository.findByOrderId(orderId)
                .ifPresentOrElse(payment -> update(payment, PaymentStatus.REFUND),
                        () -> log.error("Payment cannot find by orderId: {} to refund", orderId));
    }

    public void update(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
