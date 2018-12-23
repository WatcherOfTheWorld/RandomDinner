package xyz.codem.randomdinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
        DinnerList list = DinnerList.get(null);
        list.removeArray(this);
        allChoose.add(oneEntry);
        list.addNewArray(this);
    }

    public void removeEntry(String oneEntry){
        for(int i = 0; i < allChoose.size(); i++ ){
            if(allChoose.get(i).equals(oneEntry)){
                DinnerList list = DinnerList.get(null);
                list.removeArray(this);
                allChoose.remove(i);
                list.addNewArray(this);
                return;
            }
        }
    }

    public String getRandomItem(){
        int num =(int)(Math.random() * allChoose.size());
        DinnerList list = DinnerList.get(null);
        list.removeArray(this);
        lastResult = allChoose.get(num);
        lastTime = new Date(System.currentTimeMillis());
        list.addNewArray(this);
        return lastResult;
    }

    public String getLastResult() {
        return lastResult;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public String getTimeString(){
        if(lastTime.getTime()==0){
            return "never used";
        }
        return  "Last Used: "+lastTime.toString();
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
