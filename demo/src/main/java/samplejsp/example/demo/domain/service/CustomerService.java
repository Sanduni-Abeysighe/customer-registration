package samplejsp.example.demo.domain.service;

import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import samplejsp.example.demo.domain.util.CustomException;
import samplejsp.example.demo.domain.dto.SearchResponse;
import samplejsp.example.demo.domain.entities.CustomerModel;
import samplejsp.example.demo.external.repository.CustomerRepository;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CustomerService.class);

    public String saveUserInfo (CustomerModel customerModel) throws Exception {
        try {
            String response;
            Optional<CustomerModel> results = customerRepository.findUserDetails(customerModel.getNicNumber());
            if (results.isPresent()){
                logger.info("Customer already registered for the given NIC number");
                response = "Customer already registered for the given NIC number";
            } else {
                customerRepository.save(customerModel);
                response = "Customer information saved successfully";
                logger.info("Customer information saved successfully: {}", customerModel);
            }
            return response;
        } catch (Exception e){
            logger.error("Error occurred while saving customer details to database", e);
            throw new CustomException("Error occurred while saving customer details to database", HttpStatus.INTERNAL_SERVER_ERROR  );
        }
    }

    public SearchResponse searchUserInfo (String nicNumber) throws Exception {
        try {
            SearchResponse searchResponse = new SearchResponse();
            Optional<CustomerModel> results = customerRepository.findUserDetails(nicNumber);
            if (results.isPresent()){
                searchResponse.setResults(results.get());
                logger.info("Customer details retrieved successfully: {}", searchResponse);
            }
            logger.info("No customer details were found for the given NIC number");
            return searchResponse;
        } catch (Exception e){
            logger.error("Error occurred while searching customer details", e);
            throw new CustomException("Error occurred while searching customer details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
