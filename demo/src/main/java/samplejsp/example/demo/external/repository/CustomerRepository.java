package samplejsp.example.demo.external.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import samplejsp.example.demo.domain.entities.CustomerModel;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <CustomerModel,Integer > {
    @Query(value = "SELECT ( u.nicNumber, u.title, u.firstName, u.lastName, " +
            "u.age, u.annualIncome, u.loginUsername, u.password, u.remarks) " +
            "FROM CustomerModel u " +
            "WHERE u.nicNumber = :nicNumber ")
    Optional<CustomerModel> findUserDetails (@Param("nicNumber") String nicNumber);
}
