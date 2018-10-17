package br.eti.urbano.api;

import br.eti.urbano.model.User;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class UserApi {

    final static Logger LOGGER = Logger.getLogger(UserApi.class);
    final static List<User> users = new ArrayList<>();

    public static void main(String[] args) {

        path("/api", () -> {
            path("/user", () -> {
                get("", (request, response) -> {
                    LOGGER.info("Acesso ao método GET");

                    response.type("application/json");
                    response.status(200);
                    return new Gson().toJson(users);
                });
                get("/:id", (request, response) -> {
                    LOGGER.info("Acesso ao método GET");

                    response.type("application/json");
                    String id = request.params(":id");
                    for (User user : users) {
                        if (user.getId().toString().equals(id)) {
                            response.status(200);
                            return new Gson().toJson(user);
                        }
                    }
                    response.status(204);
                    return "";
                });
                post("", (request, response) -> {
                    LOGGER.info("Acesso ao método POST");

                    response.type("application/json");
                    response.status(201);

                    User user = new Gson().fromJson(request.body(), User.class);
                    users.add(user);

                    return new Gson().toJson(user);
                });
                put("", (request, response) -> {
                    LOGGER.info("Acesso ao método PUT");

                    response.type("application/json");

                    User user = new Gson().fromJson(request.body(), User.class);
                    if (users.removeIf(u -> u.getId() == user.getId())) {
                        users.add(user);
                        response.status(201);
                        return new Gson().toJson(user);
                    } else {
                        response.status(204);
                        return "";
                    }
                });
                delete("/:id", (request, response) -> {
                    LOGGER.info("Acesso ao método DELETE");

                    response.type("application/json");

                    String id = request.params(":id");

                    for (User u : users) {
                        if (u.getId().toString().equals(id)) {
                            response.status(201);
                            users.remove(u);
                            return new Gson().toJson(u);
                        }
                    }
                    response.status(204);
                    return "";
                });
            });

        });
    }
}
