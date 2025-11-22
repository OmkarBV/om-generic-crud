package om.crud.base;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> {

    protected final BaseRepository<T> repository;

    public ResponseEntity<T> getById(Long id) {
        Optional<T> entity = repository.findById(id);
        return entity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<T> create(T entity) {
        beforeCreate(entity);
        T saved = repository.save(entity);
        afterCreate(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Transactional
    public ResponseEntity<T> updateFull(Long id, T newEntity) {
        Optional<T> optional = repository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        T existing = optional.get();

        copyFull(existing, newEntity);
        beforeUpdate(existing);
        T saved = repository.save(existing);
        afterUpdate(saved);

        return ResponseEntity.ok(saved);
    }

    @Transactional
    public ResponseEntity<T> updatePartial(Long id, T patch) {
        Optional<T> optional = repository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        T existing = optional.get();

        copyNonNull(existing, patch);
        beforeUpdate(existing);
        T saved = repository.save(existing);
        afterUpdate(saved);

        return ResponseEntity.ok(saved);
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) {
        Optional<T> optional = repository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        T entity = optional.get();
        entity.setDeleted(true);

        beforeDelete(entity);
        repository.save(entity);
        afterDelete(entity);

        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<Page<T>> list(Pageable pageable) {
        Page<T> page = repository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    protected void beforeCreate(T entity) {}
    protected void afterCreate(T entity) {}
    protected void beforeUpdate(T entity) {}
    protected void afterUpdate(T entity) {}
    protected void beforeDelete(T entity) {}
    protected void afterDelete(T entity) {}


    protected void copyFull(T existing, T incoming) {
        for (Field field : incoming.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (!shouldIgnore(field.getName())) {
                    field.set(existing, field.get(incoming));
                }
            } catch (Exception ignored) {}
        }
    }

    protected void copyNonNull(T existing, T incoming) {
        for (Field field : incoming.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(incoming);
                if (value != null && !shouldIgnore(field.getName())) {
                    field.set(existing, value);
                }
            } catch (Exception ignored) {}
        }
    }

    private boolean shouldIgnore(String field) {
        return field.equals("id") ||
                field.equals("createdAt") ||
                field.equals("updatedAt") ||
                field.equals("isDeleted");
    }
}
