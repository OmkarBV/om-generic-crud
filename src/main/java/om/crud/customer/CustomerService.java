package om.crud.customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import om.crud.base.BaseService;

@Service
public class CustomerService extends BaseService<Customer> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

}
