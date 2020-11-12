package booking;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import lombok.Getter;
import lombok.Setter;

public class ReservationManager {
    private List<Reservation> reservations = new LinkedList<Reservation>();
    private SortedSet<Endpoint> endpoints = new TreeSet<Endpoint>();

    private static int reservationId = 1;

    @Getter
    @Setter
    private int maxReservations;

    public ReservationManager() {
        maxReservations = 1;
    }

    public Boolean canReserve(Date from, Date to) {
        return getThroughput(from, to) > 0;
    }
    
    public Boolean reserve(Date from, Date to) {
        if (getThroughput(from, to) == 0) {
            return false;
        }

        addReservation(from, to);
        return true;
    }

    protected void addReservation(Date from, Date to) {
        Reservation reservation = new Reservation(reservationId, from, to);
 
        reservations.add(reservation);
        endpoints.add(Endpoint.start(from, reservation));
        endpoints.add(Endpoint.end(to, reservation));

        reservationId++;
    }

    protected Integer getThroughput(Date from, Date to) {
        var iterator = endpoints.iterator();

        int reservationsAtOnce = 0;

        while (iterator.hasNext()) {
            Endpoint endpoint = iterator.next();
            
            if (endpoint.isStart() && endpoint.isPast(to)) {
                break;
            }

            if (endpoint.getReservation().intersects(from, to)) {
                reservationsAtOnce += endpoint.isStart() ? 1 : -1;
            }
        }

        return maxReservations - reservationsAtOnce;
    }
}