package basic.spring.controllers;

import basic.spring.domain.Customer;
import basic.spring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping({"/list", "/"})
    public String customers(Model model){
        model.addAttribute("customers", customerService.listAll());
        return "customer/list";
    }

    @RequestMapping("/show/{id}")
    public String viewCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/show";
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/customerform";
    }

    @RequestMapping("/new")
    public String createCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer/customerform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createOrUpdateCustomer(Customer customer){
        Customer saved = customerService.saveOrUpdate(customer);
        return "redirect:/customer/show/" + saved.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customer/list";
    }

}
