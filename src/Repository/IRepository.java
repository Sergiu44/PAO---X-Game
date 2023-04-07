package Repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {

    // Create
    public void add(T entity);

    // Read
    public T getById(UUID id);
    public List<T> getAll();

    // Update
//    public void update(T entity);

    // Delete
//    public void delete(T entity);
    public void deleteById(UUID id);
}