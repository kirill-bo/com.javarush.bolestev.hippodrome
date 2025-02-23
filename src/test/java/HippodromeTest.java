import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void testNullHippodromeException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void testEmptyHippodromeException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, 1.0+i, 1.0+i));
        }

        Hippodrome test = new Hippodrome(horses);

        assertEquals(horses, test.getHorses());
    }

    @Test
    public void testMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
      Horse horse1 = new Horse("Horse 1", 1.0+1, 1.0+1);
      Horse horse2 = new Horse("Horse 2", 1.0+2, 1.0+2);
      Horse horse3 = new Horse("Horse 3", 1.0+3, 1.0+3);
      Horse horse4 = new Horse("Horse 4", 1.0+4, 1.0+4);
      Horse horse5 = new Horse("Horse 5", 1.0+5, 1.0+5);

      Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));
      assertSame(horse5, hippodrome.getWinner());
    }
}
