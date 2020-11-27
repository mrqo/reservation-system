package com.dotissoft.booking;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
public class Endpoint implements Comparable<Endpoint> {
    public enum EndpointType {
        START,
        END
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private EndpointType type;

    @Getter
    @Setter
    private Instant date;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public Endpoint() {

    }
    
    public Endpoint(EndpointType type, Instant date, Reservation reservation) {
        this.type = type;
        this.date = date;
        this.reservation = reservation;
    }

    public boolean isStart() {
        return type == EndpointType.START;
    }

    public boolean isPast(Instant date) {
        return this.date.compareTo(date) == 1;
    }

    public static Endpoint start(Instant date, Reservation res) {
        return new Endpoint(EndpointType.START, date, res);
    }

    public static Endpoint end(Instant date, Reservation res) {
        return new Endpoint(EndpointType.END, date, res);
    }

    @Override
    public int compareTo(Endpoint that) {
        return getDate().compareTo(that.getDate());
    }
}
