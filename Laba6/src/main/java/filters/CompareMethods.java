package filters;

/**
 * Created by danil on 08.05.2017.
 */
public class CompareMethods {
    private static String nameCondition="";
    private static String namePattern;
    private static String weightCondition="";
    private static int weightPattern;
    public static boolean nameCompare(String name,String textFieldName){
        if(textFieldName.length()==0){return true;}
        nameCondition = textFieldName.substring(0,1);
        namePattern = textFieldName.substring(1,textFieldName.length());
        int i = name.compareToIgnoreCase(namePattern);
        if(nameCondition.equals(">")){
            return i>0;
        }else if(nameCondition.equals("<")){
            return i<0;
        }else if(nameCondition.equals("=")){
            return i==0;
        }else{
            return true;
        }
    }
    public static boolean weightCompare(int weight,String textFieldWeight){
        if(textFieldWeight.length()==0){return true;}
        weightCondition = textFieldWeight.substring(0,1);
        try {
            weightPattern = Integer.parseInt(textFieldWeight.substring(1,textFieldWeight.length()));
        }catch (NumberFormatException nfe){
            return true;
        }
        if(weightCondition.equals(">")){
            return weight>weightPattern;
        }else if(weightCondition.equals("<")){
            return weight<weightPattern;
        }else if(weightCondition.equals("=")){
            return weight==weightPattern;
        }else{
            return true;
        }
    }
    public static boolean isCorrect(String textFieldName, String textFieldWeight){
        try{
            if(textFieldWeight.length()>1){
                weightPattern = Integer.parseInt(textFieldWeight.substring(1,textFieldWeight.length()));
            }
        }catch (Exception e){
            return false;
        }
        try{
            nameCondition = textFieldName.substring(0,1);
        }catch(Exception e){}
        try{
            weightCondition = textFieldWeight.substring(0,1);
        }catch(Exception e){}
        return (nameCondition.equals(">")||nameCondition.equals("<")||nameCondition.equals("=")||nameCondition.equals(""))&&
                (weightCondition.equals(">")||weightCondition.equals("<")||weightCondition.equals("=")||weightCondition.equals(""))&&
                (textFieldName.length()!=1)&&
                (textFieldWeight.length()!=1);
    }
}
