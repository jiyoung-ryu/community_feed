package kr.co.bullets.common;

import kr.co.bullets.common.domain.PositiveIntegerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveIntegerCounterTest {

    @Test
    void givenCreatedWhenIncreaseThenCountIsOne() {
        // given
        PositiveIntegerCounter count = new PositiveIntegerCounter();

        // when
        count.increase();

        // then
        assertEquals(1, count.getCount());
    }

    @Test
    void givenCreatedAndLikedWhenDecreaseThenCountIsOne() {
        // given
        PositiveIntegerCounter count = new PositiveIntegerCounter();
        count.increase();

        // when
        count.decrease();

        // then
        assertEquals(0, count.getCount());
    }

    @Test
    void givenCreatedWhenDecreaseThenCountIsZero() {
        // given
        PositiveIntegerCounter count = new PositiveIntegerCounter();

        // when
        count.decrease();

        // then
        assertEquals(0, count.getCount());
    }
}
