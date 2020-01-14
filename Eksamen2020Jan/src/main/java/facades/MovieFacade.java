package facades;

import dto.*;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManagerFactory;
import utils.DatFetcher;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static ExecutorService ES;
    private static Queue<Future<Object>> futures;

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    //Private Constructor to ensure Singleton
    public MovieFacade() {
    }

    public static MovieFacade getFacade() {
        if (instance == null) {
            instance = new MovieFacade();
        }
        return instance;
    }

    public MovieDTO fetchSimpleMovie(String title) throws IOException, InterruptedException, ExecutionException {
        /*long startTime = System.currentTimeMillis();
        ES = Executors.newCachedThreadPool();
        futures = new ArrayBlockingQueue<>(20); // we do not know how many elements we'll need - can we avoid fixed queue size?
*/
        String url = "http://exam-1187.mydemos.dk/movieInfo/" + title;
        url = url.replace(" ", "%20");
        String url2 = "http://exam-1187.mydemos.dk/movieposter/" + title;
        url2 = url2.replace(" ", "%20");
        Movie mov = DatFetcher.fetchAsObject(Movie.class, url); // Movie with urls as children
        Poster po = DatFetcher.fetchAsObject(Poster.class, url2);
        mov.setPoster(po.getPoster());
        MovieDTO result = new MovieDTO(mov); // Movie with objects as childre
        /* ES.shutdown();
        System.out.println("Execution time: " + ((double) (System.currentTimeMillis() - startTime) / 1000.) + " seconds...");*/
        return result;
    }

}
