import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void testNameHorseNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    public void testTextMassageNullException() {
        try {
            new Horse(null, 1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.",
                    e.getMessage());
        }

    }


    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n", " "})
    public void testNameBlankException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n", " "})
    public void testTextMassageBlankException(String name) {
        try {
            new Horse(name, 1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.",
                    e.getMessage());
        }

    }


    @Test
    public void testSpeedHorseException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("nameHorse", -0.1, 1.0));
    }

    @Test
    public void testTextMassageSpeedException() {
        try {
            new Horse("nameHorse", -0.1, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.",
                    e.getMessage());
        }
    }


    @Test
    public void testDistanceHorseException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("nameHorse", 1.0, -0.1));
    }

    @Test
    public void testTextMassageDistanceException() {
        try {
            new Horse("nameHorse", 1.0, -0.1);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.",
                    e.getMessage());
        }
    }

    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("nameHorse", 1.0, 1.0);

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("nameHorse", nameValue);
    }

    @Test
    public void testGetSpeed(){
        double expectedSpeed = 999.0;
        Horse horse = new Horse("nameHorse", expectedSpeed, 1.0);

        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void testGetDistance(){
        Horse horse = new Horse("nameHorse", 1.0);

        assertEquals(0, horse.getDistance());
    }

    @Test
    void testMoveUsesGetRandom(){
       try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
           new Horse("nameHorse", 35, 953).move();

           mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
       }
    }
}
