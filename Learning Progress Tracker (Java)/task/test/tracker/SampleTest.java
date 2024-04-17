package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tracker.RegexChecker.RegexValidator;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    RegexValidator regexValidator;

    @BeforeEach
    void setUp() {
        regexValidator = new RegexValidator();
    }


    @Test
    void test() {
        assertEquals(5, 2 + 3);
    }

    @ParameterizedTest
    @CsvSource({
            "a, Brown, first",
            "John, Doe, passed",
            "-jha, Sam, first",
            "nam-'e, Johns, first",
            "?name, henry, first"
    })
    void testFirstName(String firstName, String lastName, String expected){
        String actual = regexValidator.nameChecker(firstName, lastName);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "Sam, surnam''e, last",
            "John, s'-urname, last",
            "John, su-'rname, last",
            "John, -surname, last",
            "John, s, last",
    })
    void testLastName(String firstName, String lastName, String expected){
        String actual = regexValidator.nameChecker(firstName, lastName);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "ann@hotmail.com, true",
            "a@hotmail.com, true",
            "emailemail.xyz, false",
            "ab@ab.ab, true",
            "email@emailxyz, false",
            "email@e@mail.xyz, false"
    })
    void testEmailName(String email, boolean expected){
        boolean actual = regexValidator.emailChecker(email);
        assertEquals(expected, actual);
    }
}
