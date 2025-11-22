package om.crud.customer;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import om.crud.base.BaseEntity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {
    private String name;
    private String email;
    private String phone;
}
