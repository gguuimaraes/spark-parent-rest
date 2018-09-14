package br.eti.urbano;

import org.apache.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.post;

public class Exemplo {

    final static Logger LOGGER = Logger.getLogger(Exemplo.class);

    public static void main(String[] args) {
        get("/", (request, response) -> {
            final String mensagem = "Acesso ao método GET";
            LOGGER.info(mensagem);
            response.status(200);
            return mensagem;
        });

        post("/", (request, response) -> {
            LOGGER.info("Acesso ao método POST");
            return request.body();
        });

//        put("/", (request, response) -> {
//
//        });
//
//        delete("/", (request, response) -> {
//            LOGGER.info(request.body());
//        });
//
//        options("/", (request, response) -> {
//            LOGGER.info(request.body());
//        });
    }
}
