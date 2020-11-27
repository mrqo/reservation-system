package com.dotissoft.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class ReservationQueryReceiver {
    private final static Logger logger = LoggerFactory.getLogger(ReservationQueryReceiver.class);

    public void receiveMessage(String message) {
        logger.info(message);
    }
}
