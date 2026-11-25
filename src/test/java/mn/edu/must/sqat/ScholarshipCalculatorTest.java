package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void gradesAnExcellentScore() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void gradesAnAverageScore() {
        assertEquals("C", calculator.letterGrade(74));
    }

    @Test
    void averagesTwoGrades() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}));
    }

    @Test
    void awardsFullScholarship() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void awardsSeventyPercent() {
        assertEquals(70, calculator.scholarship(3.5, 13, false));
    }
}
