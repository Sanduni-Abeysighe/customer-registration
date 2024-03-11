package samplejsp.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import samplejsp.example.demo.domain.entities.CustomerModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private CustomerModel results;
}
