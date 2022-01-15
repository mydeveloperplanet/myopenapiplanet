package com.mydeveloperplanet.myopenapiplanet;

import java.util.HashMap;

import com.mydeveloperplanet.myopenapiplanet.api.CustomerApi;
import com.mydeveloperplanet.myopenapiplanet.model.Customer;
import com.mydeveloperplanet.myopenapiplanet.model.CustomerFullData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApi {

    private final HashMap<Long, com.mydeveloperplanet.myopenapiplanet.domain.Customer> customers = new HashMap<>();
    private Long index = 0L;

    @Override
    public ResponseEntity<CustomerFullData> createCustomer(Customer apiCustomer) {
        com.mydeveloperplanet.myopenapiplanet.domain.Customer customer = new com.mydeveloperplanet.myopenapiplanet.domain.Customer();
        customer.setCustomerId(index);
        customer.setFirstName(apiCustomer.getFirstName());
        customer.setLastName(apiCustomer.getLastName());

        customers.put(index, customer);
        index++;

        return ResponseEntity.ok(domainToApi(customer));
    }

    @Override
    public ResponseEntity<CustomerFullData> getCustomer(Long customerId) {
        if (customers.containsKey(customerId)) {
            return ResponseEntity.ok(domainToApi(customers.get(customerId)));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private CustomerFullData domainToApi(com.mydeveloperplanet.myopenapiplanet.domain.Customer customer) {
        CustomerFullData cfd = new CustomerFullData();
        cfd.setCustomerId(customer.getCustomerId());
        cfd.setFirstName(customer.getFirstName());
        cfd.setLastName(customer.getLastName());
        return cfd;
    }
}
