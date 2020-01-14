package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.*;
import entities.StoredM;
import entities.UserRequest;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Request;
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
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    //Private Constructor to ensure Singleton
    public MovieFacade() {
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static MovieFacade getFacade() {
        if (instance == null) {
            instance = new MovieFacade();
        }
        return instance;
    }

    public Movie fetchSimpleMovie(String title) throws IOException, InterruptedException, ExecutionException {
        /*long startTime = System.currentTimeMillis();
        ES = Executors.newCachedThreadPool();
        futures = new ArrayBlockingQueue<>(20); // we do not know how many elements we'll need - can we avoid fixed queue size?
         */
        if(!check(title)){
            Movie mov =GSON.fromJson(getStored(title), Movie.class);
            addSimpelSearched(mov);
            return mov;
        }else{
        String url = CreateUrl("http://exam-1187.mydemos.dk/movieInfo/" + title);
        String url2 = CreateUrl("http://exam-1187.mydemos.dk/movieposter/" + title);
        Movie mov = DatFetcher.fetchAsObject(Movie.class, url); // Movie with urls as children
        Poster po = DatFetcher.fetchAsObject(Poster.class, url2);
        mov.setPoster(po.getPoster());
        addSimpelSearched(mov);
         // Movie with objects as childre
        /* ES.shutdown();
        System.out.println("Execution time: " + ((double) (System.currentTimeMillis() - startTime) / 1000.) + " seconds...");*/
        return mov;
        }
    }

    public MovieDTO fetchFullMovie(String title) throws IOException, InterruptedException, ExecutionException {
        /*long startTime = System.currentTimeMillis();
        ES = Executors.newCachedThreadPool();
        futures = new ArrayBlockingQueue<>(20); // we do not know how many elements we'll need - can we avoid fixed queue size?
         */
        if(!check(title)){
            MovieDTO mov =GSON.fromJson(getStored(title), MovieDTO.class);
            addSearched(mov);
            return mov;
        }else{
        String url = CreateUrl("http://exam-1187.mydemos.dk/movieInfo/" + title);
        String url2 = CreateUrl("http://exam-1187.mydemos.dk/movieposter/" + title);
        String url3 = CreateUrl("http://exam-1187.mydemos.dk/tomatoesScore/" + title);
        String url4 = CreateUrl("http://exam-1187.mydemos.dk/ImdbScore/" + title);
        String url5 = CreateUrl("http://exam-1187.mydemos.dk/metacriticScore/" + title);
        Movie mov = DatFetcher.fetchAsObject(Movie.class, url); // Movie with urls as children
        Poster po = DatFetcher.fetchAsObject(Poster.class, url2);
        ScoreR rsco = DatFetcher.fetchAsObject(ScoreR.class, url3);
        ScoreI isco = DatFetcher.fetchAsObject(ScoreI.class, url4);
        ScoreM msco = DatFetcher.fetchAsObject(ScoreM.class, url5);
        mov.setPoster(po.getPoster());
        MovieDTO result = new MovieDTO(mov); // Movie with objects as childre
        result.setRotten(rsco);
        result.setImdb(isco);
        result.setMeta(msco);
        addSearched(result);
        /* ES.shutdown();
        System.out.println("Execution time: " + ((double) (System.currentTimeMillis() - startTime) / 1000.) + " seconds...");*/
        return result;}
    }

    private void addSearched(MovieDTO add) {
        UserRequest r = new UserRequest(add.getTitle());
        CreateRequest(r);
        if (check(add.getTitle())) {
            String s = GSON.toJson(add);
            StoredM m = new StoredM(add.getTitle(), add.getYear(), add.getCast(), s);
            CreateStoredM(m);
        }
    }
     private void addSimpelSearched(Movie add) {
        UserRequest r = new UserRequest(add.getTitle());
        CreateRequest(r);
    }
    
    private String getStored(String title){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<StoredM> query
                    = em.createQuery("Select c from StoredM c where c.title = :title", StoredM.class);
            query.setParameter("title", title);
            return query.getSingleResult().getJsonStr();
        } finally {
            em.close();
        }
    }

    private void CreateRequest(UserRequest entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void CreateStoredM(StoredM entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private String CreateUrl(String url) {
        url = url.replace(" ", "%20");
        return url;
    }

    private boolean check(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<StoredM> query
                    = em.createQuery("Select c from StoredM c where c.title = :title", StoredM.class);
            query.setParameter("title", title);
            return query.getResultList().isEmpty();
        } finally {
            em.close();
        }
    }
    
    public int count(String title){
         EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<UserRequest> query
                    = em.createQuery("Select c from UserRequest c where c.title = :title", UserRequest.class);
            query.setParameter("title", title);
            return query.getResultList().size();
        } finally {
            em.close();
        }
    }
}
