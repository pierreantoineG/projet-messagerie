public interface UserDao {

    public User getById(int id);

    void add(User user);

    public void save(User user);

    public void update(User user);

    public void delete(User user);
}








