import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    OffByOne offByOne = new OffByOne();
    @Test
    public void testOffByone() {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('r','q'));

    }
}
