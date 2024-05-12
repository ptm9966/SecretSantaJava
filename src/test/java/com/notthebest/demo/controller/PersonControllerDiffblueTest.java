package com.notthebest.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.notthebest.demo.model.Person;
import com.notthebest.demo.service.PersonService;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(SpringExtension.class)
class PersonControllerDiffblueTest {
    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    /**
     * Method under test: {@link PersonController#test()}
     */
    @Test
    void testTest() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertEquals("index", (new PersonController()).test());
    }

    /**
     * Method under test: {@link PersonController#addPeoplePage(Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPeoplePage() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.servlet.ServletException: Circular view path [addpeople]: would dispatch back to the current handler URL [/addpeople] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        PersonController personController = new PersonController();

        // Act
        personController.addPeoplePage(new ConcurrentModel());
    }

    /**
     * Method under test: {@link PersonController#addPeopleToRepo(Person, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPeopleToRepo() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.servlet.ServletException: Circular view path [addpeople]: would dispatch back to the current handler URL [/addpeople] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:660)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        PersonController personController = new PersonController();

        Person person = new Person();
        person.setId(1);
        person.setName("Name");

        // Act
        personController.addPeopleToRepo(person, new ConcurrentModel());
    }

    /**
     * Method under test: {@link PersonController#generateMatches(Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateMatches() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.servlet.ServletException: Circular view path [generate]: would dispatch back to the current handler URL [/generate] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        PersonController personController = new PersonController();

        // Act
        personController.generateMatches(new ConcurrentModel());
    }

    /**
     * Method under test:
     * {@link PersonController#deletePeopleToRepo(Integer, Model)}
     */
    @Test
    void testDeletePeopleToRepo() throws Exception {
        // Arrange
        when(personService.getPeople()).thenReturn(new ArrayList<>());
        doNothing().when(personService).deletePerson(anyInt());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/deletepeople");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("id", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("people"))
                .andExpect(MockMvcResultMatchers.view().name("addpeople"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("addpeople"));
    }


}
