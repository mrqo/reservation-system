package com.dotissoft.booking;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Reservation implements Comparable<Reservation> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Instant dateFrom;
    
    @Getter
    @Setter
    private Instant dateTo;

    @OneToMany(mappedBy = "reservation")
    private Set<Endpoint> endpoints;

    public Reservation() {

    }
    
    public Reservation(Instant dateFrom, Instant dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public boolean intersects(Instant from, Instant to) {
        return this.dateFrom.compareTo(from) <= 0 && this.dateTo.compareTo(from) >= 0
            || this.dateFrom.compareTo(to) <= 0 && this.dateTo.compareTo(to) >= 0
            || this.dateFrom.compareTo(from) == 1 && this.dateTo.compareTo(to) == -1;
    }

    public boolean intersects(Reservation that) {
        return intersects(that.getDateFrom(), that.getDateTo());
    }
    
    @Override
    public int compareTo(Reservation that) {
        var dateFromComp = dateFrom.compareTo(that.getDateFrom());
        
        return dateFromComp == 0
            ? dateTo.compareTo(that.getDateTo())
            : dateFromComp;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Reservation)) {
            return false;
        }

        var reservation = (Reservation)that;
        return id.equals(reservation.getId());
    }
}
