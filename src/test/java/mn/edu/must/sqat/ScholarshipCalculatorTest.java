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

    @Test
    void rejectsScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-0.01));
    }

    @Test
    void rejectsScoreAboveOneHundred() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(100.01));
    }

    @Test
    void rejectsEmptyGpaArray() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[0]));
    }

    @Test
    void rejectsNullGpaArray() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void averagesEveryGradeBand() {
        assertEquals(2.0, calculator.gpa(new double[]{96, 84, 73, 62, 42}));
    }

    @Test
    void rejectsNegativeGpa() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.01, 15, false));
    }

    @Test
    void rejectsGpaAboveFour() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.01, 15, false));
    }

    @Test
    void rejectsNegativeCredits() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.8, -1, false));
    }

    @Test
    void violationBlocksScholarship() {
        assertEquals(0, calculator.scholarship(4.0, 18, true));
    }

    @Test
    void fullTierWithoutCreditsFallsToSeventy() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void seventyTierWithoutCreditsFallsToFifty() {
        assertEquals(50, calculator.scholarship(3.6, 11, false));
    }

    @Test
    void lowGpaGetsNoScholarship() {
        assertEquals(0, calculator.scholarship(2.9, 18, false));
    }

    @Test
    void averagesASingleGrade() {
        assertEquals(4.0, calculator.gpa(new double[]{95}));
    }

    @Test
    void averagesFourGrades() {
        assertEquals(2.5, calculator.gpa(new double[]{95, 85, 75, 65}));
    }

    @Test
    void exactlySixtyIsD() {
        assertEquals("D", calculator.letterGrade(60));
    }

    @ParameterizedTest
    @CsvSource({"59.99,F", "69.99,D", "0,F", "100,A", "89.99,B", "90,A"})
    void gradesOtherBoundaryScores(double score, String expected) {
        assertEquals(expected, calculator.letterGrade(score));
    }
}
