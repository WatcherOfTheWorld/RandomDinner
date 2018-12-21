package xyz.codem.randomdinner;

import android.util.Log;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DinnerArray implements Serializable{
    private List<String> allChoose = new ArrayList<>();
    private String lastResult = "never used";
    private Date lastTime;
    private String name = "Unnamed";
    private UUID mId;

    public DinnerArray() {
        this(UUID.randomUUID());
    }

    public DinnerArray(UUID id) {
        mId = id;
        lastTime = new Date();
        lastTime.setTime(0);
    }
    public UUID getId() {
        return mId;
    }


    public List<String> getAllEntry(){
        return allChoose;
    }

    public String allEntry(){
        String str = "";
        try{
            str += allChoose.get(0);
        }catch (IndexOutOfBoundsException e){
            return str;
        }
        int i = 0;
        for(String item: allChoose){
            if(i == 0){
                i++;
                continue;
            }
            str+=",";
            str+=item;
        }
        return str;
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

    public Date getLastTime() {
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

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
