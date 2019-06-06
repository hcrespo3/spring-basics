package guru.springframework.services;

import guru.springframework.domain.AbstractMapService;
import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    protected void loadDomainObjects() {

        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("customer 1");
        customer1.setLastName("customer 1");
        customer1.setEmail("customer1@fake.com");
        customer1.setState("Cundinamarca");
        customer1.setCity("Bogota");
        customer1.setAddress1("Carrera 1 Calle 1");
        customer1.setAddress2("Teusaquillo");
        customer1.setPhoneNumber("350 1111111");
        customer1.setZipCode("5");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("customer 2");
        customer2.setLastName("customer 2");
        customer2.setEmail("customer2@fake.com");
        customer2.setState("Cundinamarca");
        customer2.setCity("Bogota");
        customer2.setAddress1("Carrera 2 Calle 2");
        customer2.setAddress2("Teusaquillo");
        customer2.setPhoneNumber("350 2222222");
        customer2.setZipCode("5");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("customer 3");
        customer3.setLastName("customer 3");
        customer3.setEmail("customer3@fake.com");
        customer3.setState("Cundinamarca");
        customer3.setCity("Bogota");
        customer3.setAddress1("Carrera 3 Calle 3");
        customer3.setAddress2("Teusaquillo");
        customer3.setPhoneNumber("350 3333333");
        customer3.setZipCode("5");

        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setFirstName("customer 4");
        customer4.setLastName("customer 4");
        customer4.setEmail("customer4@fake.com");
        customer4.setState("Cundinamarca");
        customer4.setCity("Bogota");
        customer4.setAddress1("Carrera 4 Calle 4");
        customer4.setAddress2("Teusaquillo");
        customer4.setPhoneNumber("350 4444444");
        customer4.setZipCode("5");

        Customer customer5 = new Customer();
        customer5.setId(5);
        customer5.setFirstName("customer 5");
        customer5.setLastName("customer 5");
        customer5.setEmail("customer5@fake.com");
        customer5.setState("Cundinamarca");
        customer5.setCity("Bogota");
        customer5.setAddress1("Carrera 5 Calle 5");
        customer5.setAddress2("Teusaquillo");
        customer5.setPhoneNumber("350 5555555");
        customer5.setZipCode("5");


        domainMap.put(1, customer1);
        domainMap.put(2, customer2);
        domainMap.put(3, customer3);
        domainMap.put(4, customer4);
        domainMap.put(5, customer5);
    }
}
