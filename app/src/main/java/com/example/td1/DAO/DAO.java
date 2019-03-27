package com.example.td1.DAO;

public interface DAO<T>{
    void findAll();

    void insert(T objet);

    void traiteFindAll(String result);
}
