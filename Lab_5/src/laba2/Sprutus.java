/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class Sprutus extends GroupOfPeople {
    private int degreeOfEarsPolution=0;
    public void hope(){
        System.out.println("�� ����� �� ���������!");
    }
    public void changeDegreeOfEarsPolution(Time d){
        if(independenceDegree<5){
            degreeOfEarsPolution=d.countOfDays()*3;
        }
    }
    public void showPolution(){
        System.out.println("� ���� �������� ��� "+degreeOfEarsPolution+" �����/������ �����");
    }
    public Sprutus(){
        count=1;
        independenceDegree=2;
        msg="�������������...*������ �������*";
    }
    public void callServants(Servants here){
        System.out.println("�������, ����!");
        if(here.showHere()){
            degreeOfEarsPolution=0;
            System.out.println("\"��������, ����� ���������\" - ������� ����");
        }else{
            System.out.println("�� ����� ��� ��� - ������� ����");
        }
    }
}