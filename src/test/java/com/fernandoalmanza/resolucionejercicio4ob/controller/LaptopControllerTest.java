package com.fernandoalmanza.resolucionejercicio4ob.controller;

import com.fernandoalmanza.resolucionejercicio4ob.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder =restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @Test
    void findAll() {
        // sin datos creados
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Insertando datos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "fabricante": "Toshiba",
                    "ram": "8",
                    "procesador": "AMD"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        testRestTemplate.exchange("/api/laptops",HttpMethod.POST, request,Laptop.class);
        ResponseEntity<Laptop[]> response2 = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        List<Laptop> result = Arrays.asList(response2.getBody());
        System.out.println(result);
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop>  response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        // Insertando datos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "fabricante": "Toshiba",
                    "ram": "8",
                    "procesador": "AMD"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST, request,Laptop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //ResponseEntity<Laptop[]> response2 = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        //assertEquals(HttpStatus.OK, response2.getStatusCode());
        //List<Laptop> result = Arrays.asList(response2.getBody());
        //System.out.println(result);
    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String jsonBadRequest = """
                {
                    "fabricante": "Toshiba",
                    "ram": "8",
                    "procesador": "AMD"
                }
                """;
        String jsonNotFound = """
                {
                    "serial": "11111111-aaaa-2222-bbbb-333333333333",
                    "fabricante": "Toshiba",
                    "ram": "8",
                    "procesador": "AMD"
                }
                """;

        HttpEntity<String> requestBadRequest = new HttpEntity<>(jsonBadRequest,headers);
        ResponseEntity<Laptop> responseBadRequest = testRestTemplate.exchange("/api/laptops",HttpMethod.PUT, requestBadRequest,Laptop.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseBadRequest.getStatusCode());

        HttpEntity<String> requestPost = new HttpEntity<>(jsonBadRequest,headers);
        ResponseEntity<Laptop> responsePost = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, requestPost, Laptop.class);
        String jsonOk = """
                {
                    "serial": """ + '"' + responsePost.getBody().getSerial() + '"' + ',' + """
                    
                    "fabricante": "Asys",
                    "ram": "16",
                    "procesador": "Intel"
                }
                """;
        System.out.println(jsonOk);
        HttpEntity<String> requestOk = new HttpEntity<>(jsonOk,headers);
        ResponseEntity<Laptop> responseOk = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, requestOk, Laptop.class);
        assertEquals(HttpStatus.OK, responseOk.getStatusCode());

        HttpEntity<String> requestNotFound = new HttpEntity<>(jsonNotFound,headers);
        ResponseEntity<Laptop> responseNotFound = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, requestNotFound, Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
    }

    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String jsonNotFound = """
                {
                    "serial": "1",
                    "fabricante": "Toshiba",
                    "ram": "8",
                    "procesador": "AMD"
                }
                """;
        HttpEntity<String> requestNotFound = new HttpEntity<>(jsonNotFound,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1",HttpMethod.DELETE,requestNotFound, Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        String jsonPost = """
                {
                    "fabricante": "Toshiba",
                    "ram": "8",
                    "procesador": "AMD"
                }
                """;

        HttpEntity<String> requestPost = new HttpEntity<>(jsonPost,headers);
        ResponseEntity<Laptop> responsePost = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, requestPost, Laptop.class);
        String jsonNoContent = """
                {
                    "serial": """ + '"' + responsePost.getBody().getSerial() + '"' + ',' + """
                    
                    "fabricante": "Asys",
                    "ram": "16",
                    "procesador": "Intel"
                }
                """;
        HttpEntity<String> requestNoContent = new HttpEntity<>(jsonNoContent,headers);
        ResponseEntity<Laptop> responseNoContent = testRestTemplate.exchange("/api/laptops/"+responsePost.getBody().getSerial(), HttpMethod.DELETE, requestNoContent, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, responseNoContent.getStatusCode());

    }

    @Test
    void deleteAll() {
        testRestTemplate.delete("/api/laptops");
        assertEquals(HttpStatus.NOT_FOUND, testRestTemplate.getForEntity("/api/laptops", Laptop.class).getStatusCode());
    }
}