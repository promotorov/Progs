/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class Time {
    public int days;
    private boolean smthChanged=false;
    public void run(int da, boolean ch){
        days=da;
        smthChanged=ch;
    }
    public void checkCh(Servants serv){
        if(serv.showHere()){
            smthChanged=true;
            System.out.println("Что-то изменилось, т.к. пришли слуги");
        }else{
            if(days/serv.returnCount()>10){
                serv.areHere=true;
                System.out.println("Прошло слишком много времени, слуги решили вернуться");
            }
        }
    }
    public Time(int d){
        days=d;
    }
    public int countOfDays(){
        return days;
    }
    public void showChangesAndDays(){
        System.out.print("Всего прошло "+days+" день/дней, и ");
        if(smthChanged){
            System.out.println("определённо произошли изменения");
        }else{
            System.out.println("ничего не произошло");
        }
    }
}