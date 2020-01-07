package com.java993;

import com.java993.dto.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @BeforeAll
    public static void startServer() {
        Application.main(new String[]{});
    }

    @AfterAll
    public static void stopServer() {
        Spark.stop();
    }


    @Test
    public void testUserList() {
        StdResponse<List<User>> response = get("/users").as(StdResponse.class);
        assertEquals(ResponseStatus.SUCCESS, response.getStatus());
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void testTransfer() {

        //check accounts before Transfer
        StdResponse<List<User>> userListResponse = get("/users").as(new TypeRef<StdResponse<List<User>>>() {});
        assertEquals(BigDecimal.valueOf(100000), getAmountByAccountId(1l, userListResponse.getBody()));
        assertEquals(BigDecimal.valueOf(1000000), getAmountByAccountId(3l, userListResponse.getBody()));

        //transfer operation
        StdResponse transferResponse = given().body(new Transfer(1l, 3l, BigDecimal.valueOf(50000))).post(
                "/transfer"
        ).as(StdResponse.class);

        //check accounts after Transfer
        userListResponse = get("/users").as(new TypeRef<StdResponse<List<User>>>() {});
        assertEquals(BigDecimal.valueOf(50000), getAmountByAccountId(1l, userListResponse.getBody()));
        assertEquals(BigDecimal.valueOf(1050000), getAmountByAccountId(3l, userListResponse.getBody()));

    }

    private BigDecimal getAmountByAccountId(Long accountId, List<User> users){
        return users
                .stream()
                .map(User::getAccounts)
                .flatMap(Collection::stream)
                .filter(x -> accountId.equals(x.getId()))
                .findFirst().get().getAmount();

    }

}