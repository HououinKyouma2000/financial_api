package test.project.financial_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import test.project.financial_api.api.dto.AppUserInfoResponse;
import test.project.financial_api.api.dto.PageResponse;
import test.project.financial_api.api.dto.auth.AuthRequest;
import test.project.financial_api.api.dto.auth.AuthResponse;
import test.project.financial_api.api.dto.transfer.TransferInfoResponse;
import test.project.financial_api.api.dto.transfer.TransferRequest;
import test.project.financial_api.api.dto.user.AppUserCreateRequest;
import test.project.financial_api.api.dto.user.AppUserUpdateRequest;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO написать нормальные unit и api layer тесты

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FinancialApiApplicationIntegrationTest {
  
  
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  private static final String USERNAME = "Okabeqwe";
  
  private static final String PASSWORD = "awfwe123Qwqe";
  
  private static String token;
  
  private static UUID senderAccount;
  
  private static UUID recipientAccount;
  
  @Test
  @Order(1)
  void createSenderUser() throws Exception {
    
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/app-user")
        .headers(getNotAuthHeader())
        .content(objectMapper.writeValueAsString(new AppUserCreateRequest(USERNAME, PASSWORD, 500))))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final AppUserInfoResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), AppUserInfoResponse.class);
    Assertions.assertNotNull(response.id());
    Assertions.assertNotNull(response.account().id());
    senderAccount = response.account().id();
  }
  
  @Test
  @Order(2)
  void loginUser() throws Exception {
    
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
        .headers(getNotAuthHeader())
        .content(objectMapper.writeValueAsString(new AuthRequest(USERNAME, PASSWORD))))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final AuthResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), AuthResponse.class);
    Assertions.assertNotNull(response.token());
    token = response.token();
  }
  
  @Test
  @Order(3)
  void updateUser() throws Exception {
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/app-user")
        .headers(getAuthHeader())
        .content(objectMapper.writeValueAsString(new AppUserUpdateRequest(USERNAME + "upd", PASSWORD + "upd"))))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final AppUserInfoResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), AppUserInfoResponse.class);
    Assertions.assertNotNull(response.id());
    Assertions.assertNotNull(response.account().id());
  }
  
  @Test
  @Order(4)
  void loginUserRepeatAfterUpdate() throws Exception {
    
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
        .headers(getNotAuthHeader())
        .content(objectMapper.writeValueAsString(new AuthRequest(USERNAME + "upd", PASSWORD + "upd"))))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final AuthResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), AuthResponse.class);
    Assertions.assertNotNull(response.token());
    token = response.token();
  }
  
  @Test
  @Order(5)
  void getUserInfo() throws Exception {
    
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/app-user")
        .headers(getAuthHeader()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final AppUserInfoResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), AppUserInfoResponse.class);
    Assertions.assertNotNull(response.id());
    Assertions.assertNotNull(response.account().id());
  }
  
  @Test
  @Order(6)
  void createRecipientUser() throws Exception {
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/app-user")
        .headers(getNotAuthHeader())
        .content(objectMapper.writeValueAsString(new AppUserCreateRequest(USERNAME + "second", PASSWORD, 500))))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final AppUserInfoResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), AppUserInfoResponse.class);
    Assertions.assertNotNull(response.id());
    Assertions.assertNotNull(response.account().id());
    recipientAccount = response.account().id();
  }
  
  @Test
  @Order(7)
  void transfer() throws Exception {
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transfer")
        .headers(getAuthHeader())
        .content(objectMapper.writeValueAsString(new TransferRequest(senderAccount, recipientAccount, 100))))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final TransferInfoResponse response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(), TransferInfoResponse.class);
    Assertions.assertNotNull(response.id());
  }
  
  @Test
  @Order(8)
  void getTransferInfo() throws Exception {
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transfer")
        .headers(getAuthHeader()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final PageResponse<TransferInfoResponse> response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(),
      new TypeReference<PageResponse<TransferInfoResponse>>() {
      });
    Assertions.assertNotNull(response);
    Assertions.assertEquals(1, response.totalElements());
  }
  
  @Test
  @Order(9)
  void getAllTransferInfo() throws Exception {
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transfer/all")
        .headers(getAuthHeader()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final PageResponse<TransferInfoResponse> response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(),
      new TypeReference<PageResponse<TransferInfoResponse>>() {
      });
    Assertions.assertNotNull(response);
    Assertions.assertEquals(3, response.totalElements());
  }
  
  @Test
  @Order(9)
  void getAllTransferInfoByDate() throws Exception {
    final ResultActions createProduct = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transfer/by-date")
        .param("from", "2024-04-01T10:00:00")
        .param("to", "2024-04-02T15:30:00")
        .headers(getAuthHeader()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    
    final PageResponse<TransferInfoResponse> response = objectMapper.readValue(createProduct.andReturn().getResponse().getContentAsString(),
      new TypeReference<PageResponse<TransferInfoResponse>>() {
      });
    Assertions.assertNotNull(response);
    Assertions.assertEquals(2, response.totalElements());
  }
  
  private static HttpHeaders getNotAuthHeader() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
  
  private HttpHeaders getAuthHeader() {
    final HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
