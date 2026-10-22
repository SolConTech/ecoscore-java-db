package br.com.fiap.bo;

import java.util.ArrayList;

public interface IBO {

    public ArrayList<Object> findAll();

    public Object findById(Object id);

    public Object save(Object object);

    public Object update(Object id, Object object);

    public Object delete(Object id);
}
