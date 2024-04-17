package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tracker.Helpers.Helpers;
import tracker.Models.UserInput;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HelperTest {

    Helpers helper;

    private static Stream<Arguments> validateArgumentsGood(){
        return Stream.of(
                Arguments.of("1 0 0 0 5", UserInput.Message.PASS),
                Arguments.of("1 0 5 6 5", UserInput.Message.PASS),
                Arguments.of("1 1 0 0 5", UserInput.Message.PASS),
                Arguments.of("1 0 0 0 0", UserInput.Message.PASS)
        );
    }

    private static Stream<Arguments> validateArgumentsBadSize(){
        return Stream.of(
                Arguments.of("5", UserInput.Message.SIZE),
                Arguments.of("1 0", UserInput.Message.SIZE),
                Arguments.of("1 1 0 0", UserInput.Message.SIZE),
                Arguments.of("1 0 0 0 0 8", UserInput.Message.SIZE)
        );
    }

    private static Stream<Arguments> validateArgumentsBadId(){
        return Stream.of(
                Arguments.of("1ew 0 0 0 5", UserInput.Message.ID),
                Arguments.of("pen 0 5 6 5", UserInput.Message.ID),
                Arguments.of("-9i 1 0 0 5", UserInput.Message.ID),
                Arguments.of("89- 0 0 0 0", UserInput.Message.ID)
        );
    }

    @BeforeEach
    void setUp(){
        helper = new Helpers();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "0", "25", "100"})
    void testIsIntegerGood(String id){
        assertTrue(helper.isInteger(id));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2r4", "rgww", "twenty", "-1rf"})
    void testIsIntegerBad(String id){
        assertFalse(helper.isInteger(id));
    }

    @ParameterizedTest
    @MethodSource("validateArgumentsGood")
    void testValidateGood(String input, Enum expected){
        UserInput goodUser = helper.validate(input);
        assertEquals( expected, goodUser.getMessage());
    }

   @ParameterizedTest
   @MethodSource("validateArgumentsBadSize")
    void testValidateBadSize(String input, Enum expected){
        UserInput goodUser = helper.validate(input);
        assertEquals( expected, goodUser.getMessage());
    }

    @ParameterizedTest
    @MethodSource("validateArgumentsBadId")
    void testValidateBadId(String input, Enum expected){
        UserInput goodUser = helper.validate(input);
        assertEquals( expected, goodUser.getMessage());
    }

//    need tests for:
//     - convertStringToInts
//    - addUsersScores

}
