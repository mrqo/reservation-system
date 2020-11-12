package booking;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Endpoint implements Comparable<Endpoint> {
    public enum EndpointType {
        START,
        END
    }

    @Getter
    private EndpointType type;

    @Getter
    private Date date;
    
    @Getter
    private Reservation reservation;

    public Endpoint(EndpointType type, Date date, Reservation reservation) {
        this.type = type;
        this.date = date;
        this.reservation = reservation;
    }

    public boolean isStart() {
        return type == EndpointType.START;
    }

    public boolean isPast(Date date) {
        return this.date.compareTo(date) == 1;
    }

    public static Endpoint start(Date date, Reservation res) {
        return new Endpoint(EndpointType.START, date, res);
    }

    public static Endpoint end(Date date, Reservation res) {
        return new Endpoint(EndpointType.END, date, res);
    }

    @Override
    public int compareTo(Endpoint that) {
        return getDate().compareTo(that.getDate());
    }
}
