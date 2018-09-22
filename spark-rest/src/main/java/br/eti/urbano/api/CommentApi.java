package br.eti.urbano.api;

import br.eti.urbano.model.Comment;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class CommentApi {

    final static Logger LOGGER = Logger.getLogger(CommentApi.class);

    public static void main(String[] args) {
        path("/api", () -> {
            path("/comment", () -> {
                get("", (request, response) -> {
                    response.type("application/json");
                    response.status(200);

                    Comment comment = new Comment();
                    comment.setData("Dados do comentário");
                    Comment comment2 = new Comment();
                    comment2.setData("Dados do comentário 2");
                    List<Comment> comments = new ArrayList<>();
                    comments.add(comment);
                    comments.add(comment2);

                    LOGGER.info("Acesso ao método GET");
                    return new Gson().toJson(comments);
                });
                get("/:id", (request, response) -> {
                    response.type("application/json");
                    response.status(200);

                    String id = request.params(":id");
                    LOGGER.info("Acesso ao método GET - Parametro id=" + id);
                    return "Commentagem id=" + id;
                });
                post("", (request, response) -> {
                    response.type("application/json");
                    response.status(201);

                    LOGGER.info("Acesso ao método POST");
                    return "Commentagem cadastrada";
                });
                put("", (request, response) -> {
                    response.type("application/json");
                    response.status(201);

                    LOGGER.info("Acesso ao método PUT");
                    return "Commentagem alterada";
                });
                delete("/:id", (request, response) -> {
                    response.type("application/json");
                    response.status(204);

                    String id = request.params(":id");
                    LOGGER.info("Acesso ao método DELETE - Parametro id=" + id);
                    return "Commentagem id=" +id +" deletada";
                });
            });

        });
    }
}
