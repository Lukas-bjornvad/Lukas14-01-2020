package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Movie;
import dto.MovieDTO;
import entities.User;
import facades.MovieFacade;
import facades.UserFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@OpenAPIDefinition(
        info = @Info(
                title = "teamone-ca3",
                version = "0.1",
                description = "Backend of the CA3 project"
        ),
        tags = {
            @Tag(name = "Movie resource", description = "API related to Movie fetch from distant API's")
        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/EksamenJan"
            ),
            @Server(
                    description = "Server API",
                    url = "https://www.lukasbjornvad.dk/Eksamen2020Jan"
            )

        }
)
@Path("movie")
public class MovieResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "A welcome message that confirms the connection to the Movie API",
            tags = {"Movie resource"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The user successfully connected"),
                @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request")})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    @Operation(summary = "A message only accessable by a user",
            tags = {"Movie resource"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The user welcome message is displayed"),
                @ApiResponse(responseCode = "403", description = "Not authenticated - do login")})
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    @Operation(summary = "A message only accessable by an admin",
            tags = {"Movie resource"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The admin welcome message is dislayed"),
                @ApiResponse(responseCode = "403", description = "Not authenticated - do login")})
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-simple/{title}")
    //@RolesAllowed({"user", "admin"})
    @Operation(summary = "Fetches data from distant Movie API's by title",
            tags = {"Movie resource"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
                @ApiResponse(responseCode = "200", description = "The requested resources was returned"),
                @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request and no resources were returned")})
    public Movie getSimpleMovieFetch(@PathParam("title") String title) throws IOException, InterruptedException, ExecutionException {
        MovieFacade fac = new MovieFacade();
        Movie mov = fac.fetchSimpleMovie(title);
        return mov;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-all/{title}")
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Fetches data from distant Movie API's by title",
            tags = {"Movie resource"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
                @ApiResponse(responseCode = "200", description = "The requested resources was returned"),
                @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request and no resources were returned")})
    public MovieDTO getFullMovieFetch(@PathParam("title") String title) throws IOException, InterruptedException, ExecutionException {
        MovieFacade fac = new MovieFacade();
        MovieDTO mov = fac.fetchFullMovie(title);
        return mov;
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-count/{title}")
    @RolesAllowed({"admin"})
    @Operation(summary = "Fetches data from distant Movie API's by title",
            tags = {"Movie resource"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
                @ApiResponse(responseCode = "200", description = "The requested resources was returned"),
                @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request and no resources were returned")})
    public String getSearchCount(@PathParam("title") String title) throws IOException, InterruptedException, ExecutionException {
        MovieFacade fac = new MovieFacade();
        return "{\"msg\": \"Total amount of searches for this title: " + fac.count(title) + "\"}"; 
    }

    // should be in a UserResource instead, since this is a general use endpoint
    // is not yet tested either
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createUser")
    @Operation(summary = "Creates a user and persists it to the database",
            tags = {"Star Wars resource"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
                @ApiResponse(responseCode = "200", description = "The person was created and persisted"),
                @ApiResponse(responseCode = "400", description = "No users was created or persisted")})
    public User createUser(User user) {
        UserFacade facade = new UserFacade();
        facade.create(user);
        return user;
    }
    
}
