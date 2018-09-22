package br.eti.urbano.service;

import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Exemplo2 {

    final static Logger LOGGER = Logger.getLogger(Exemplo2.class);

    public static void main(String[] args) {
        path("/api", () -> {
            before("/*", (request, response) -> LOGGER.info("Received api call"));
            path("/email", () -> {
                get("", (request, response) -> {
                    return "oi";
                });
                post("", (request, rgeesponse) -> String.format("email POST %s\n", request.body()));
                put("", (request, response) -> String.format("email PUT %s\n", request.body()));
                delete("", (request, response) -> String.format("email DELETE %s\n", request.body()));
                options("", (request, response) -> String.format("email OPTIONS %s\n", request.body()));
            });
            path("/username", () -> {
                get("", (request, response) -> String.format("username GET %s\n", request.body()));
                post("", (request, response) -> String.format("username POST %s\n", request.body()));
                put("", (request, response) -> String.format("username PUT %s\n", request.body()));
                delete("", (request, response) -> String.format("username DELETE %s\n", request.body()));
                options("", (request, response) -> String.format("username OPTIONS %s\n", request.body()));
            });
            options("", (request, response) -> {
                StringBuilder string = new StringBuilder();
                string.append("------------ Opções da API-------------\n");
                string.append("Endereço\t\t\tVerbo\t\tUso\t\t\t\t\t\tParametros\n");
                string.append("\n");
                string.append("/api/email\t\t\tGET\t\t\tObter um e-mail\t\t\t(long) id\n");
                string.append("/api/email\t\t\tPOST\t\tCadastrar um e-mail\t\t(String) endereco\n");
                string.append("/api/email\t\t\tPUT\t\t\tAlterar um e-mail\t\t(long) id, (String) endereco\n");
                string.append("/api/email\t\t\tDELETE\t\tDeletar um e-mail\t\t(long) id\n");
                string.append("/api/email\t\t\tOPTIONS\t\tMais opções de e-mail\n");
                string.append("\n");
                string.append("/api/username\t\tGET\t\t\tObter um usuário\t\t(long) id\n");
                string.append("/api/username\t\tPOST\t\tCadastrar um usuário\t(String) nome, (String) senha\n");
                string.append("/api/username\t\tPUT\t\t\tAlterar um usuário\t\t(long) id, (String) nome, (String) senha\n");
                string.append("/api/username\t\tDELETE\t\tDeletar um usuário\t\t(long) id\n");
                string.append("/api/username\t\tOPTIONS\t\tMais opções de usuário\n");
                string.append("----------------------------------------\n");
                return string.toString();
            });
        });
    }
}
