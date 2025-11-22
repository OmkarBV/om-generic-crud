package om.crud.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import om.crud.base.BaseController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController<Customer> {

    public CustomerController(CustomerService service) {
        super(service);
    }
}
