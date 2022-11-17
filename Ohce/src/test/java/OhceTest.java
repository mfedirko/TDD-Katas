import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OhceTest {
    List<String> out = new ArrayList<>();
    Clock clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));



    @Test
    void whenBetween20And0Hours_thenGreetingBuenasNoches() {
        clock = Clock.fixed(Instant.ofEpochMilli(1668640823875L), ZoneId.of("UTC")); // 16 Nov 2022 23:20
        Ohce ohce = initOhce();

        assertGreetingEquals("¡Buenas noches PEDRO!");
    }

    @Test
    void whenBetween0And6Hours_thenGreetingBuenasNoches() {
        clock = Clock.fixed(Instant.ofEpochMilli(1679620223875L), ZoneId.of("UTC")); // Fri Mar 24 2023 01:10:23
        Ohce ohce = initOhce();

        assertGreetingEquals("¡Buenas noches PEDRO!");
    }

    @Test
    void whenBetween6And12Hours_thenGreetingBuenosDias() {
        clock = Clock.fixed(Instant.ofEpochMilli(1679650223875L), ZoneId.of("UTC")); // Fri Mar 24 2023 09:30:23
        Ohce ohce = initOhce();

        assertGreetingEquals("¡Buenos días PEDRO!");
    }

    @Test
    void whenBetween12And20Hours_thenGreetingBuenasTardes() {
        clock = Clock.fixed(Instant.ofEpochMilli(1679670223875L), ZoneId.of("UTC")); // Fri Mar 24 2023 15:03:43
        Ohce ohce = initOhce();

        assertGreetingEquals("¡Buenas tardes PEDRO!");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "PEDRO",
            "John",
            "John Smith"
    })
    void greetingContainsUsername(String user) {
        Ohce ohce = new Ohce(out::add, clock, user);

        assertGreetingContains(user);
    }

    @Test
    void whenWriteEmptyString_thenNoOutput() {
        Ohce ohce = initOhce();

        ohce.read("");

        assertOutputDoesNotContain("");
    }

    @Test
    void whenWriteToOhce_thenEchoesReversedInput() {
        Ohce ohce = initOhce();

        ohce.read("hello");

        assertOutputContains("olleh");
    }

    @Test
    void whenWriteToOhce_thenEchoesReversedInput_2() {
        Ohce ohce = initOhce();

        ohce.read("how are you");

        assertOutputContains("uoy era woh");
    }

    @Test
    void whenWriteToOhce_andPalindrome_thenWritesBonitaPalabra() {
        Ohce ohce = initOhce();

        ohce.read("racecar");

        assertOutputContains("¡Bonita palabra!");
    }

    @Test
    void whenWriteStop_thenWritesAdios() {
        Ohce ohce = initOhce();

        ohce.read("Stop!");

        assertOutputContains("Adios PEDRO");
    }

    @Test
    void whenWriteStop_thenExits() {
        Ohce ohce = initOhce();

        ohce.read("Stop!");

        assertTrue(ohce.isExited());
    }

    @Test
    void whenExited_thenDoesNotReactToInput() {
        Ohce ohce = initOhce();

        ohce.read("Stop!");
        ohce.read("hello");
        ohce.read("racecar");
        ohce.read("pots");

        assertTrue(ohce.isExited());
        Assertions.assertThat(out)
                .doesNotContain("olleh", "racecar", "stop");
    }

    @Test
    void whenWriteStop_thenDoesNotEchoReversed() {
        Ohce ohce = initOhce();

        ohce.read("Stop!");

        assertOutputDoesNotContain("!potS");
    }


    @Test
    void whenWriteStopIncorrectly_thenTreatsAsRegularInput_andDoesNotExit() {
        Ohce ohce = initOhce();

        ohce.read("stop");

        assertOutputContains("pots");
        assertOutputDoesNotContain("Adios PEDRO");
        assertFalse(ohce.isExited());
    }

    private Ohce initOhce() {
        return new Ohce(out::add, clock, "PEDRO");
    }


    private void assertGreetingEquals(String expected) {
        Assertions.assertThat(out)
                .hasSizeGreaterThanOrEqualTo(1)
                .element(0).isEqualTo(expected);
    }

    private void assertGreetingContains(String s) {
        Assertions.assertThat(out)
                .hasSizeGreaterThanOrEqualTo(1)
                .element(0).asString().contains(s);
    }

    private void assertOutputContains(String expected) {
        Assertions.assertThat(out)
                .hasSizeGreaterThanOrEqualTo(1)
                .contains(expected);
    }

    private void assertOutputDoesNotContain(String expected) {
        Assertions.assertThat(out)
                .doesNotContain(expected);
    }

}