/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.Movie;
import dto.MovieDTO;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lukas Bjornvad
 */
public class MovieFacadeIT {
    Movie mov ;
    MovieDTO dto;
    public MovieFacadeIT() throws IOException, InterruptedException, ExecutionException {
        MovieFacade x = new MovieFacade();
        mov = x.fetchSimpleMovie("Die%20Hard");
        mov.setPoster("Poster");
        dto = new MovieDTO(mov);
        dto.setPoster("Poster");
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

  

    /**
     * Test of fetchSimpleMovie method, of class MovieFacade.
     */
    @Test
    public void testFetchSimpleMovie() throws Exception {
        System.out.println("fetchSimpleMovie");
        MovieFacade instance = new MovieFacade();
        int expResult = 1988;
        Movie result = instance.fetchSimpleMovie("Die%20Hard");
        assertEquals(expResult, result.getYear());
    }

    /**
     * Test of fetchFullMovie method, of class MovieFacade.
     */
    @Test
    public void testFetchFullMovie() throws Exception {
        System.out.println("fetchFullMovie");
        String title = "Die%20Hard";
        MovieFacade instance = new MovieFacade();
        MovieDTO result = instance.fetchFullMovie(title);
        assertTrue(result.getTitle().isEmpty() != true);
    }

    /**
     * Test of count method, of class MovieFacade.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        String title = "Die%20Hard";
        MovieFacade instance = new MovieFacade();
        int expResult = 0;
        int result = instance.count(title);
        assertEquals(expResult, result);
    }
    
}
