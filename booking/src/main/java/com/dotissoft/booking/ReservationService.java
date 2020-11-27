package com.dotissoft.booking;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;

@Service
@Transactional
public class ReservationService {
    @Getter
    @Setter
    private int maxReservations = 1;

    @Autowired
    private EndpointRepository endpoints;

    @Autowired
    private ReservationRepository reservations;

    public Boolean canReserve(Instant from, Instant to) {
        return getThroughput(from, to) > 0;
    }

    public Boolean reserve(Instant from, Instant to) {
        if (getThroughput(from, to) == 0) {
            return false;
        }

        addReservation(from, to);
        return true;
    }

    protected void addReservation(Instant from, Instant to) {
        Reservation reservation = new Reservation(from, to);
        
        reservations.save(reservation);
        endpoints.save(Endpoint.start(from, reservation));
        endpoints.save(Endpoint.end(to, reservation));
    }

    protected Integer getThroughput(Instant from, Instant to) {
        var fromTrunc = from.truncatedTo(ChronoUnit.SECONDS);
        var toTrunc = to.truncatedTo(ChronoUnit.SECONDS);

        var iterator = endpoints.findByOrderByDateAsc().iterator();

        int reservationsAtOnce = 0;
        int maxParallelReservations = 0;

        while (iterator.hasNext()) {
            Endpoint endpoint = iterator.next();
            
            if (endpoint.isStart() && endpoint.isPast(toTrunc)) {
                break;
            }

            if (endpoint.getReservation().intersects(fromTrunc, toTrunc)) {
                reservationsAtOnce += endpoint.isStart() ? 1 : -1;
                maxParallelReservations = Math.max(reservationsAtOnce, maxParallelReservations);
            }
        }

        return maxReservations - maxParallelReservations;
    }
}