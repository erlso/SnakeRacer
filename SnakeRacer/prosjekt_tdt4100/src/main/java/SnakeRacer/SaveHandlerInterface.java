package SnakeRacer;

public interface SaveHandlerInterface {

    public void save(String name, int score);

    public void load();

    public void checkValidName(String name);
}
