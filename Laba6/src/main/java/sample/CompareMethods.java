package sample;

/**
 * Created by danil on 08.05.2017.
 */
public class CompareMethods {
    public static boolean isCorrect=true;
    public static String nameCondition;
    public static String namePattern;
    public static String weightCondition;
    public static int weightPattern;
    public static boolean nameCompare(String name,String textFieldName){
        CompareMethods.nameCondition = textFieldName.substring(0,1);
        CompareMethods.namePattern = textFieldName.substring(1,textFieldName.length());
        int i = name.compareToIgnoreCase(namePattern);
        if(nameCondition.equals(">")){
            return i>0;
        }else if(nameCondition.equals("<")){
            return i<0;
        }else if(nameCondition.equals("=")){
            return i==0;
        }else{
            isCorrect=false;
            return true;
        }
    }
    public static boolean weightCompare(int weight,String textFieldWeight){
        weightCondition = textFieldWeight.substring(0,1);
        try {
            weightPattern = Integer.parseInt(textFieldWeight.substring(1,textFieldWeight.length()));
        }catch (NumberFormatException nfe){
            isCorrect=false;
            return true;
        }
        if(weightCondition.equals(">")){
            return weight>weightPattern;
        }else if(weightCondition.equals("<")){
            return weight<weightPattern;
        }else if(weightCondition.equals("=")){
            return weight==weightPattern;
        }else{
            isCorrect=false;
            return true;
        }
    }
}
