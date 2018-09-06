package xyz.codem.randomdinner;

import java.util.ArrayList;
import java.util.List;

public class DinnerList {
    List<DinnerArray> allEntryList = new ArrayList<>();


    public DinnerList(){
    }

    public void addNewArray(DinnerArray entry){
        allEntryList.add(entry);
    }

    public void removeArray(DinnerArray entry){
        allEntryList.remove(entry);
    }

    public int getSize(){
        return allEntryList.size();
    }

    public List<DinnerArray> getEntry(){
        return allEntryList;
    }
}
