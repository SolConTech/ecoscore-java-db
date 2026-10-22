package br.com.fiap.resource;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class AcaoResource implements IResource{

    @Override
    public ResponseEntity<List<Object>> findAll() {
        return null;
    }
    @Override
    public ResponseEntity<Object> findById(Object id) {
        return null;
    }
    @Override
    public ResponseEntity<Object> save(Object body) {
        return null;
    }
    @Override
    public ResponseEntity<Object> update(Object id, Object body) {
        return null;
    }
    @Override
    public ResponseEntity<Void> delete(Object id) {
        return null;
    }
}
