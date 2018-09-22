package br.eti.urbano.service;

import org.apache.log4j.Logger;

import java.util.Map;

import static spark.Spark.*;

public class Exemplo {

    final static Logger LOGGER = Logger.getLogger(Exemplo.class);

    public static void main(String[] args) {
        get("/", (request, response) -> {
            final String mensagem = "Acesso ao método GET\n";
            LOGGER.info(mensagem);
            response.status(200);
            return mensagem;
        });

        get("/hello/:name?", (request, response) -> {
            return "Hello: " + request.params(":name");
        });

        post("/", (request, response) -> {
            LOGGER.info("Acesso ao método POST");
            response.status(201);
            return "Objeto criado\n";
        });

        put("/", (request, response) -> {

            response.status(200);
            return "Objeto atualizado\n";
        });

        delete("/", (request, response) -> {
            LOGGER.info(request.body());

            response.status(200);
            return "Objeto deletado\n";
        });

        options("/", (request, response) -> {
            LOGGER.info(request.body());

            response.status(200);

            return "Rota Options\n";
        });
    }
}
