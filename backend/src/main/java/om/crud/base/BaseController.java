package om.crud.base;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class BaseController<T extends BaseEntity> {

    protected final BaseService<T> service;

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateFull(@PathVariable Long id, @RequestBody T entity) {
        return service.updateFull(id, entity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<T> updatePartial(@PathVariable Long id, @RequestBody T entity) {
        return service.updatePartial(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<T>> list(Pageable pageable) {
        return service.list(pageable);
    }
}
