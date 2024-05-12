package com.notthebest.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.notthebest.demo.model.Person;
import com.notthebest.demo.repository.PersonRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonService.class})
@ExtendWith(SpringExtension.class)
class PersonServiceDiffblueTest {
    @MockBean
    private PersonRepo personRepo;

    @Autowired
    private PersonService personService;

    /**
     * Method under test: {@link PersonService#deletePerson(int)}
     */
    @Test
    void testDeletePerson() {
        // Arrange
        Person person = new Person();
        person.setId(1);
        person.setName("Name");
        Optional<Person> ofResult = Optional.of(person);
        doNothing().when(personRepo).delete(Mockito.<Person>any());
        when(personRepo.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        personService.deletePerson(1);

        // Assert that nothing has changed
        verify(personRepo).delete(isA(Person.class));
        verify(personRepo).findById(eq(1));
        assertTrue(personService.getPeople().isEmpty());
    }

    /**
     * Method under test: {@link PersonService#addPerson(Person)}
     */
    @Test
    void testAddPerson() {
        // Arrange
        Person person = new Person();
        person.setId(1);
        person.setName("Name");
        when(personRepo.save(Mockito.<Person>any())).thenReturn(person);

        Person person2 = new Person();
        person2.setId(1);
        person2.setName("Name");

        // Act
        personService.addPerson(person2);

        // Assert that nothing has changed
        verify(personRepo).save(isA(Person.class));
        assertEquals("Name", person2.getName());
        assertEquals(1, person2.getId());
        assertTrue(personService.getPeople().isEmpty());
    }

    /**
     * Method under test: {@link PersonService#getPeople()}
     */
    @Test
    void testGetPeople() {
        // Arrange
        ArrayList<Person> personList = new ArrayList<>();
        when(personRepo.findAll()).thenReturn(personList);

        // Act
        List<Person> actualPeople = personService.getPeople();

        // Assert
        verify(personRepo).findAll();
        assertTrue(actualPeople.isEmpty());
        assertSame(personList, actualPeople);
    }

    /**
     * Method under test: {@link PersonService#generateMatches()}
     */
    @Test
    void testGenerateMatches() {
        // Arrange
        when(personRepo.findAll()).thenReturn(new ArrayList<>());

        // Act
        Map<String, String> actualGenerateMatchesResult = personService.generateMatches();

        // Assert
        verify(personRepo).findAll();
        assertTrue(actualGenerateMatchesResult.isEmpty());
    }

    /**
     * Method under test: {@link PersonService#generateMatches()}
     */
    @Test
    void testGenerateMatches2() {
        // Arrange
        Person person = new Person();
        person.setId(1);
        person.setName("Name");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personRepo.findAll()).thenReturn(personList);

        // Act
        Map<String, String> actualGenerateMatchesResult = personService.generateMatches();

        // Assert
        verify(personRepo).findAll();
        assertEquals(1, actualGenerateMatchesResult.size());
        assertEquals("Nobody", actualGenerateMatchesResult.get("Name"));
    }

    /**
     * Method under test: {@link PersonService#generateMatches()}
     */
    @Test
    void testGenerateMatches3() {
        // Arrange
        Person person = new Person();
        person.setId(1);
        person.setName("Name");

        Person person2 = new Person();
        person2.setId(2);
        person2.setName("Name");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person2);
        personList.add(person);
        when(personRepo.findAll()).thenReturn(personList);

        // Act
        Map<String, String> actualGenerateMatchesResult = personService.generateMatches();

        // Assert
        verify(personRepo).findAll();
        assertEquals(1, actualGenerateMatchesResult.size());
        assertEquals("Name", actualGenerateMatchesResult.get("Name"));
    }

    /**
     * Method under test: {@link PersonService#generateMatches()}
     */
    @Test
    void testGenerateMatches4() {
        // Arrange
        Person person = new Person();
        person.setId(1);
        person.setName("Name");

        Person person2 = new Person();
        person2.setId(2);
        person2.setName("Name");

        Person person3 = new Person();
        person3.setId(3);
        person3.setName("");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person3);
        personList.add(person2);
        personList.add(person);
        when(personRepo.findAll()).thenReturn(personList);

        // Act
        Map<String, String> actualGenerateMatchesResult = personService.generateMatches();

        // Assert
        verify(personRepo).findAll();
        assertEquals(2, actualGenerateMatchesResult.size());
    }
}
