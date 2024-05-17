package com.notthebest.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PersonDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Person#setId(int)}
     *   <li>{@link Person#setName(String)}
     *   <li>{@link Person#getId()}
     *   <li>{@link Person#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Person person = new Person();
        //this is the test machinee
        // Act
        person.setId(1);
        person.setName("Name");
        int actualId = person.getId();

        // Assert that nothing has changed
        assertEquals("Name", person.getName());
        assertEquals(1, actualId);
    }
}
