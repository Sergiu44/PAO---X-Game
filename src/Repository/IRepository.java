package Repository;

import Model.Basket;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IRepository<T> {

    // Create
    public void add(T entity) throws SQLException;

    // Read
    public T getById(UUID id) throws SQLException;

    public List<T> getAll() throws SQLException;

    // Update
//    public void update(T entity);

    // Delete
//    public void delete(T entity);
    public Basket deleteById(UUID id) throws SQLException;
}