package com.example.champomatch;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GenderTest {

    @Test
    public void testSingleConstructor() {
        // Create a Single object with test values
        Single single = new Single("John", 30, Gender.Male, "profile.jpg", Gender.Female, "Hi, I'm John!", "New York", 50, 25, 40);

        // Assert that the object was created with the expected values
        assertEquals("John", single.getName());
        assertEquals(30, single.getAge());
        assertEquals(Gender.Male, single.getGender());
        assertEquals("profile.jpg", single.getPp());
        assertEquals(Gender.Female, single.getPreferedGender());
        assertEquals("Hi, I'm John!", single.getBio());
        assertEquals("New York", single.getLocalisation());
        assertEquals(50, single.getDistance());
        assertEquals(25, single.getMinimunAge());
        assertEquals(40, single.getMaximunAge());
    }
}
