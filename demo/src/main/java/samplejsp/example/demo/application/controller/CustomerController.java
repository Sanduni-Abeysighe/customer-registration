package samplejsp.example.demo.application.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.ITemplateEngine;
import samplejsp.example.demo.domain.dto.SearchResponse;
import samplejsp.example.demo.domain.entities.CustomerModel;
import samplejsp.example.demo.domain.dto.SearchRequest;
import samplejsp.example.demo.domain.service.CustomerService;
import java.io.IOException;
import java.io.InputStream;

@Controller
@CrossOrigin({"http://*", "https://*"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ITemplateEngine iTemplateEngine;

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "RegisterPage";
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> getRegisterForm(@RequestBody CustomerModel customerModel) throws Exception {
        String hashedPassword = passwordEncoder.encode(customerModel.getPassword());
        customerModel.setPassword(hashedPassword);
        String response = customerService.saveUserInfo(customerModel);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchData(@RequestBody SearchRequest request) throws Exception {
        SearchResponse response = customerService.searchUserInfo(request.getNicNumber());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/image.jpg")
    public void getImage(HttpServletResponse response) {
        response.setContentType("image/jpeg");
        try {
            InputStream is = getClass().getResourceAsStream("/static/image.jpg");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
