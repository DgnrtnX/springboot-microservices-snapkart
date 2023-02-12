//package com.heisenberg.productservice;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.math.BigDecimal;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.heisenberg.productservice.model.ProductRequestModel;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class MircroServicesApplicationTests {
//
//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");
//
//	@Autowired
//	MockMvc mockMvc;
//
//	@Autowired
//	ObjectMapper objectMapper;
//
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//
//	@Test
//	void shouldCreateProduct() throws Exception {
//		ProductRequestModel productRequestModel = getProductRequest();
//		String productRequestModelString = objectMapper.writeValueAsString(productRequestModel);
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON)
//				.content(productRequestModelString)).andExpect(status().isCreated());
//	}
//
//	private ProductRequestModel getProductRequest() {
//		return new ProductRequestModel().setName("Samsung S23").setDescription("2023 latest smartphone by samsung")
//				.setPrice(BigDecimal.valueOf(1199));
//	}
//
//	@Test
//	void shouldGetAllProducts() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")).andExpect(status().isFound());
//	}
//
//}
