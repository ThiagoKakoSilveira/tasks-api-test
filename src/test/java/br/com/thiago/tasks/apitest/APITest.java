package br.com.thiago.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTasks(){
        RestAssured
            .given()
//                TODO: DEIXEI O LOG PARA REGISTRO USO DOS MÉTODOS PARA ESTUDO
//                .log()
//                .all()
            .when()
                .get("/todo")
            .then()
                .statusCode(200);
    }

    @Test
    public void deveSalvarTasks(){
        RestAssured
                .given()
                    .body("{\"task\":\"Teste com Rest Assured\", \"dueDate\":\"2024-12-12\"}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
//                TODO: DEIXEI O LOG PARA REGISTRO USO DOS MÉTODOS PARA ESTUDO
//                    .log()
//                    .all()
                    .statusCode(201);
    }

    @Test
    public void naoDeveSalvarTasksComDataPassada(){
        RestAssured
                .given()
                    .body("{\"task\":\"Teste com Rest Assured\", \"dueDate\":\"2022-12-12\"}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(400)
                    .body("message", CoreMatchers.is("Due date must not be in past"));
    }
}