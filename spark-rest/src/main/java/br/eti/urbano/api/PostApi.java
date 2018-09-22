package br.eti.urbano.api;

import br.eti.urbano.model.Post;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static spark.Spark.*;

public class PostApi {

    final static Logger LOGGER = Logger.getLogger(PostApi.class);

    public static void main(String[] args) {
        path("/api", () -> {
            path("/post", () -> {
                get("", (request, response) -> {
                    response.type("application/json");
                    response.status(200);

                    Post post = new Post();
                    post.setTitle("Titulo da postagem");
                    post.setUrl("Url da postagem");
                    Post post2 = new Post();
                    post2.setTitle("Titulo da postagem 2");
                    post2.setUrl("Url da postagem 2");
                    List<Post> posts = new ArrayList<>();
                    posts.add(post);
                    posts.add(post2);

                    LOGGER.info("Acesso ao método GET");
                    return new Gson().toJson(posts);
                });
                get("/:id", (request, response) -> {
                    response.type("application/json");
                    response.status(200);

                    String id = request.params(":id");
                    LOGGER.info("Acesso ao método GET - Parametro id=" + id);
                    return "Postagem id=" + id;
                });
                post("", (request, response) -> {
                    response.type("application/json");
                    response.status(201);

                    LOGGER.info("Acesso ao método POST");
                    return "Postagem cadastrada";
                });
                put("", (request, response) -> {
                    response.type("application/json");
                    response.status(201);

                    LOGGER.info("Acesso ao método PUT");
                    return "Postagem alterada";
                });
                delete("/:id", (request, response) -> {
                    response.type("application/json");
                    response.status(204);

                    String id = request.params(":id");
                    LOGGER.info("Acesso ao método DELETE - Parametro id=" + id);
                    return "Postagem id=" +id +" deletada";
                });
            });

        });
    }
}
