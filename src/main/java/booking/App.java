package booking;

import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        ReservationManager reservationManager = new ReservationManager();

        var start = new Date();
        var end = new Date();

        var res1 = reservationManager.reserve(
            start,
            end
        );

        System.out.println(res1);

        var res2 = reservationManager.reserve(
            start,
            end
        );

        System.out.println(res2);
    }
}
