package database;

import java.util.List;

public interface DataBaseHandler {
    void saveData(int number, List<Integer> data);
    List<Integer> loadData(int number);
}
