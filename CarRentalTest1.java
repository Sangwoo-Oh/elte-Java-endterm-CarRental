package rental.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import rental.CarRental;

public class CarRentalTest1 {
	@BeforeAll
	public static void set() {
		CarRentalTestSuite.setHuLocale();
		assertTrue(new File("cars.txt").isFile(), "Missing file: cars.txt");
	}

	@Test
    public void test01() throws FileNotFoundException {
        CarRental cr = new CarRental("nonexistent_filename.txt");
        assertEquals(0, cr.numberOfCars(), "CarRental konstruktora: hibas filenev eseten nem ures listaval inicilaizalta az autokolcsonzot.");
    }

    @Test
    public void test02() throws FileNotFoundException {
        CarRental cr = new CarRental("cars.txt");
        assertEquals(4, cr.numberOfCars(), "CarRental constructor: read an inadequate amount of data.");
    }

    @Test
    public void test03()throws FileNotFoundException {
        CarRental cr = new CarRental("nonexistent_filename.txt");
        assertEquals("", cr + "", "CarRental: rosszul jeleniti meg az objektumot, ha nincsenek autok.");
    }

    @Test
    public void test04() throws FileNotFoundException {
        CarRental cr = new CarRental("cars.txt");
        String expected = """
			BMW (ABC 123)  50.0 EUR
			Alfa Romeo (DEF 234)   9.0 EUR
			Toyota (GHI 456) 500.0 EUR
			Volvo (JSD 856) 500.0 EUR
        	""";
        assertEquals(expected, cr + "",
                     "CarRental: either the constructor takes wrong data, or the text method displays them incorrectly.");
    }
}
