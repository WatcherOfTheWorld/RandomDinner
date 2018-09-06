package xyz.codem.randomdinner;

import java.util.ArrayList;
import java.util.List;

public class DinnerArray {
    private List<String> allChoose = new ArrayList<>();
    private String lastResult = "never used";
    private String lastTime = "never used";
    private String name = "Unnamed";

    public DinnerArray(List<String> entry){
        allChoose = entry;
    }

    public String[] getAllEntry(){
        return (String[]) allChoose.toArray();
    }

    public void addEntry(String oneEntry){
        allChoose.add(oneEntry);
    }

    public void removeEntry(String oneEntry){
        for(int i = 0; i < allChoose.size(); i++ ){
            if(allChoose.get(i).equals(oneEntry)){
                allChoose.remove(i);
                return;
            }
        }
    }

    public String getRandomItem(){
        return "";
    }

    public String getLastResult() {
        return lastResult;
    }

    public String getLastTime() {
        return lastTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
