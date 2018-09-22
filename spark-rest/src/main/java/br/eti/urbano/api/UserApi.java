package br.eti.urbano.api;

import br.eti.urbano.model.User;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static spark.Spark.*;

public class UserApi {

    final static Logger LOGGER = Logger.getLogger(UserApi.class);

    public static void main(String[] args) {
        path("/api", () -> {
            path("/user", () -> {
                get("", (request, response) -> {
                    response.type("application/json");
                    response.status(200);

                    User user = new User();
                    user.setUuid(UUID.randomUUID().toString());
                    user.setUserName("Nome do usuario");
                    User user2 = new User();
                    user2.setUuid(UUID.randomUUID().toString());
                    user2.setUserName("Nome do usuario 2");
                    List<User> users = new ArrayList<>();
                    users.add(user);
                    users.add(user2);

                    LOGGER.info("Acesso ao método GET");
                    return new Gson().toJson(users);
                });
                get("/:id", (request, response) -> {
                    response.type("application/json");
                    response.status(200);

                    String id = request.params(":id");
                    LOGGER.info("Acesso ao método GET - Parametro id=" + id);
                    return "Usuario id=" + id;
                });
                post("", (request, response) -> {
                    response.type("application/json");
                    response.status(201);

                    LOGGER.info("Acesso ao método POST");
                    return "Usuario cadastrado";
                });
                put("", (request, response) -> {
                    response.type("application/json");
                    response.status(201);

                    LOGGER.info("Acesso ao método PUT");
                    return "Usuario alterado";
                });
                delete("/:id", (request, response) -> {
                    response.type("application/json");
                    response.status(204);

                    String id = request.params(":id");
                    LOGGER.info("Acesso ao método DELETE - Parametro id=" + id);
                    return "Usuario id=" +id +" deletado";
                });
            });

        });
    }
}
