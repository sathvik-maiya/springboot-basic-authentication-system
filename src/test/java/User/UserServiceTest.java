package User;


import com.example.basicauthenticationsystem.User.UserService;
import com.example.basicauthenticationsystem.User.model.UserModel;
import com.example.basicauthenticationsystem.User.model.UserRepository;
import com.example.basicauthenticationsystem.Utils.JWTUtils;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig({UserService.class})
 class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    JWTUtils jwtUtils;

    @Test
    @DisplayName("create an user and making post request")
    void postUser(){
        UserModel expectedUser = UserModel.builder().name("sathwik").password("welcome1").build();
        MockHttpServletResponse mockResponse=new MockHttpServletResponse();
        ResponseEntity<String> responseEntity =userService.postUser(expectedUser,mockResponse);
        String responseMessage = responseEntity.getBody();
        Assertions.assertEquals("logged in successfully",responseMessage);
    }

    @Test
    @DisplayName("Getting user page only when token is present")
    void getUserPage(){
        MockHttpServletRequest req=new MockHttpServletRequest();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYXRodmlrIiwiaWF0IjoxNzE0MTM5NTI4LCJleHAiOjE3MTQxNDMxMjh9.59l5h1K2olbavBCdI6FlPWjpqmtBAMBCNFeKHsIUW4A";
        Cookie cookie = new Cookie("auth_token", token);
        req.setCookies(cookie);
        ResponseEntity<String> responseEntity =userService.getUserPage(req);
        String responseMessage = responseEntity.getBody();
        responseMessage = responseMessage.trim();
        Assertions.assertEquals("Welcome to user page",responseMessage);
    }
}
