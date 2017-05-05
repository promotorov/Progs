/**
 * Created by danil on 16.02.2017.
 */
package laba2;

import jdk.nashorn.internal.parser.JSONParser;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.util.*;

import static laba2.JSONworker.*;
import static laba2.NewCommands.*;
import static laba2.XMLworker.*;

public class Laba2 {
    static HashSet<FoodResidus> rubbishBin=new HashSet<FoodResidus>();
    public static void main(String[]args){
        Runtime.getRuntime().addShutdownHook(new Thread(){
                public void run(){
                    try {
                        if(args.length!=0){
                            saveCollection(args[0],rubbishBin);
                            System.out.println("Мы закрылись");
                        }
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
        });

        try{
            boolean cont = true;
            if(args.length==0){
                System.out.println("Путь к файлу не введён");
            }else if(getCollection(args[0])!=null) {
                rubbishBin = getCollection(args[0]);
                Date dateOfInt = new Date();
                System.out.println("Список команд:" +
                        "\ninfo - вывод информации о коллекции" +
                        "\nsave - сохранить текущее состояние коллекции в файл" +
                        "\nclear - очистить коллекцию " +
                        "\nrg {экземпляр класса FoodResidus в формате json} - удалить все элементы, превышающие данный " +
                        "\nend - сохранить текущее состояние коллекции и завершить работу программы");
                while (cont) {
                    Scanner sc = new Scanner(System.in);
                    String command = sc.next();
                    String arg = sc.nextLine();
                    switch (command.trim()) {
                        case "info":
                            if (!arg.isEmpty()) {
                                System.out.println("Не верно введённая команда");
                                break;
                            }
                            info(rubbishBin, dateOfInt);
                            break;
                        case "save":
                            if (!arg.isEmpty()) {
                                System.out.println("Не верно введённая команда");
                                break;
                            }
                            save(rubbishBin, args);
                            break;
                        case "clear":
                            if (!arg.isEmpty()) {
                                System.out.println("Не верно введённая команда");
                                break;
                            }
                            clear(rubbishBin);
                            break;
                        case "rg":
                            if (arg.isEmpty()) {
                                System.out.println("Не верно введённая команда");
                                break;
                            }
                            remove_greater(rubbishBin, arg);
                            break;
                        case "end":
                            if (!arg.isEmpty()) {
                                System.out.println("Не верно введённая команда");
                                break;
                            }
                            cont = end(rubbishBin, args);
                            break;
                        default:
                            System.out.println("Не верно введённая команда");
                            break;
                    }
                }
            }
        }catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Time past=new Time(42);
        Servants oldServants=new Servants(100,false);
        Sprutus oldManSprutus=new Sprutus();
        Flies commonFlies=new Flies();
        past.showChangesAndDays();
        oldManSprutus.changeDegreeOfEarsPolution(past);
        oldManSprutus.showPolution();
        oldManSprutus.hope();
        past.checkCh(oldServants);
        oldServants.showCount();
        oldManSprutus.PrintMSG();
        oldManSprutus.callServants(oldServants);
        oldServants.toCleanUp();
        oldServants.PrintMSG();
        masdam.smell(oldServants);
        masdam.attractFlies(masdam.name);
        redWhine.smell(oldServants);
        redWhine.attractFlies(redWhine.name);
        beef.smell(oldServants);
        beef.attractFlies(beef.name);
        commonFlies.checkAttractionOfCheese(masdam);
        commonFlies.checkAttractionOfWhine(redWhine);
        commonFlies.checkAttractionOfMeet(beef);
        for(String state : oldServants.obyazannosti){
            System.out.println(state);
        }*/
    }
}