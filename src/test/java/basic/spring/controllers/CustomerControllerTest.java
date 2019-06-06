package basic.spring.controllers;

import basic.spring.domain.Customer;
import basic.spring.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception{
        List<Customer> list = new ArrayList<>();
        list.add(new Customer());
        list.add(new Customer());

        when(customerService.listAll()).thenReturn((List) list);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception{
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception{
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception{
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testCreateOrUpdate() throws Exception{
        Integer id = 1;
        String firstName = "Hector";
        String lastName = "Crespo";
        String email = "hcrespo3@gmail.com";
        String phoneNumber = "0555555";
        String zipCode = "02354";
        String address1 = "Car 1 Calle 1";
        String address2 = "Teusaquillo";
        String city = "Bogota";
        String state = "Cundinamarca";

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setZipCode(zipCode);
        customer.setAddress1(address1);
        customer.setAddress2(address2);
        customer.setCity(city);
        customer.setState(state);

        when(customerService.saveOrUpdate(ArgumentMatchers.<Customer>any())).thenReturn(customer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("phoneNumber", phoneNumber)
                .param("zipCode", zipCode)
                .param("address1", address1)
                .param("address2", address2)
                .param("city", city)
                .param("state", state))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))))
                .andExpect(model().attribute("customer", hasProperty("address1", is(address1))))
                .andExpect(model().attribute("customer", hasProperty("address2", is(address2))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))));

        // verify properties of bound object
        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);

        // This captures the object being saved for being used by Argument Capture
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());
        assertEquals(zipCode, boundCustomer.getValue().getZipCode());
        assertEquals(address1, boundCustomer.getValue().getAddress1());
        assertEquals(address2, boundCustomer.getValue().getAddress2());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(state, boundCustomer.getValue().getState());

    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 1;
        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }

}
