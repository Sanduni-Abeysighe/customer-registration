package samplejsp.example.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CustomerTable")
public class CustomerModel {
    @Id
    @Column(name = "NICNumber")
    private String nicNumber;
    @Column(name = "Title")
    private String title;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Age")
    private int age;
    @Column(name = "AnnualIncome")
    private double annualIncome;
    @Column(name = "LoginUsername")
    private String loginUsername;
    @Column(name = "Password")
    private String password;
    @Column(name = "Remarks")
    private String remarks;

    public CustomerModel(String title, String firstName, String lastName, int age, double annualIncome, String loginUsername, String password, String nicNumber, String remarks) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.annualIncome = annualIncome;
        this.loginUsername = loginUsername;
        this.password = password;
        this.nicNumber = nicNumber;
        this.remarks = remarks;
    }
}
