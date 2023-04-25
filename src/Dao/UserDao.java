package Dao;

import Model.Client;

import java.util.List;

public interface UserDao {

    Client getById(int id);

    void add(Client client);

   void save(Client client);

    void update(Client client);

    void delete(Client client);

    Client getByUsername(String username);

    Client create (Client client);

    List<Client> getUsers();
}








