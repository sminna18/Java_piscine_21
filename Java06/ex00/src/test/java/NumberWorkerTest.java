import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    public NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource (ints = {2, 3 ,7, 5})
    void isPrimeForPris (int num) {
        assertTrue(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource (ints = {4, 6 , 9998, 123})
    void isPrimeForNotPris (int num) {
        assertFalse(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource (ints = {0, 1 , -777})
    void isPrimeForWrongNums (int num) {
        assertThrows(IllegalNumberException.class, () -> {
            numberWorker.isPrime(num);
        }, "Not thrown exception");
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/data_1.csv")
    void isPrimeForEqualSum (int num, int sum) {
        assertEquals(numberWorker.digitsSum(num), sum);
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/data_2.csv")
    void isPrimeForNotEqualSum (int num, int sum) {
        assertNotEquals(numberWorker.digitsSum(num), sum);
    }
    
}
