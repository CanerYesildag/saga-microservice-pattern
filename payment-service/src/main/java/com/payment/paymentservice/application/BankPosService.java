package com.payment.paymentservice.application;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Service
public class BankPosService {

    public int pay(BigDecimal price) {
        return HttpServletResponse.SC_OK;
    }
}
