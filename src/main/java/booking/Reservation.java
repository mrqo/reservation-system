package booking;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation implements Comparable<Reservation> {
    private Integer id;
    private Date dateFrom;
    private Date dateTo;

    public Reservation(Integer id, Date dateFrom, Date dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public boolean intersects(Date from, Date to) {
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
