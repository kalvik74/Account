package com.java993;

import com.google.gson.Gson;
import com.java993.dao.impl.MockAccountDaoImpl;
import com.java993.dao.impl.MockUserDaoImpl;
import com.java993.dto.ResponseStatus;
import com.java993.dto.StdResponse;
import com.java993.dto.Transfer;
import com.java993.dto.User;
import com.java993.service.AccountService;
import com.java993.service.UserService;
import com.java993.service.impl.AccountServiceImpl;
import com.java993.service.impl.UserServiceImpl;

import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

import static spark.Spark.*;

public class Application {


    public static void main(String[] args) {
        MockAccountDaoImpl accountDao = new MockAccountDaoImpl();
        UserService userService = new UserServiceImpl(new MockUserDaoImpl(), accountDao);
        AccountService accountService = new AccountServiceImpl(accountDao);

        port(8080);
        get("/users", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StdResponse (
                            ResponseStatus.SUCCESS,
                            userService.getAll()
                    )
            );
        });

        post("/transfer", (request, response) -> {
            response.type("application/json");
            try {
                Transfer transfer = new Gson().fromJson(request.body(), Transfer.class);
                accountService.transfer(transfer);
                return new Gson().toJson(
                        new StdResponse(ResponseStatus.SUCCESS, new Gson()
                                .toJsonTree("OK")));
            } catch (Exception e) {
                return new Gson().toJson(
                        new StdResponse(ResponseStatus.ERROR, new Gson()
                                .toJsonTree(e.getMessage())));
            }
        });
    }
}
