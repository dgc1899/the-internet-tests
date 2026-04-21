package api.utils;

import api.pojos.Booking;
import api.pojos.BookingDate;
import org.testng.annotations.DataProvider;

public class ApiDataProvider {

    @DataProvider(name = "Booking data provider")
    public static Booking[][] createData() {
        BookingDate date = new BookingDate("2026-01-01", "2026-05-02");
        Booking b1  = new Booking(
                "Daniel",
                "Garcia",
                250,
                true,
                date,
                "sody pops"

        );
        Booking b2  = new Booking(
                "Daniel",
                "Garcia",
                250,
                true,
                date,
                "sody pops"

        );
        Booking b3 = new Booking(
                "Daniel",
                "Garcia",
                250,
                true,
                date,
                "sody pops"

        );
        return new Booking[][] {
                {b1},
                {b2},
                {b3}
        };
    }
}
