package laba2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

import static laba2.JSONworker.toJavaObject;
import static laba2.XMLworker.saveCollection;

/**
 * Created by danil on 01.03.2017.
 */
public class NewCommands {

    /**
     * Метод info
     * Выводит информазии о коллекци с которой ведётся работа:
     * 1)Тип коллекции;
     * 2)Количество элементов в коллекции;
     * 3)Дата инициализации коллекции;
     * 4)Список всех эдлементов коллекции;
     * @param set Ожидается экземпляр колекции, о котором требуется вывести информацию
     * @param date Ожидается Дата инициализации коллекции
     * @version 3
     */
    public static void info(List<FoodResidus> set, Date date){
        System.out.println("Тип коллекции: "+set.getClass()+"\nКоличество элементов: "+set.size()+"\nДата инициализации: "+date.toString());
        System.out.println("Список элементов:");
        for(FoodResidus iter:set){
            System.out.println(iter.name);
        }
        System.out.println("*Ожидание следующей команды*");
    }

    /**
     * Метод clear
     * Очищяает коллекцию
     * @param list Ожидается экземпляр коллекции
     * @version 1
     */
    public static void clear(List<FoodResidus> list){
        list.clear();
        System.out.println("Очищенно");
    }

    /**
     * Метод remove_greater
     * Удаляет все элементы переданной коллекции превышающие заданный элемент
     * @param list Ожидается экземпляр коллекции, элементы которого очищяются
     * @param jsonElement Ожидается Экземпляр класса, записанный в json формате
     * @version 3
     */
    public static void remove_greater(List<FoodResidus> list,String jsonElement){
        try {
            FoodResidus jsonCompared = toJavaObject(jsonElement);
            Iterator<FoodResidus> iterator = list.iterator();
            while(iterator.hasNext()){
                FoodResidus compared = iterator.next();
                if(compared.compareTo(jsonCompared)>0){
                    iterator.remove();
                }
            }
            System.out.println("Все элементы превышающие данный удалены");
        } catch (IOException e) {
            System.out.println("Не верный аргумент");
        }
    }

    /**
     *Метод end
     * С помощью этого метода завершается основной цикл программы
     * while цикл продолжает функционировать, пока переменная cont==true
     * Для завершения цикла переменной cont присваевается end
     * Метод сохраняет текущее состояние коллекции в указанный XML файл
     * @param list Ожидается экземпляр коллекции для сохранения
     * @param cmdArg Ожидается массив элементов, в 0 элементе должен быть записан путь к файлу сохранения
     * @return значение false всегда
     * @throws JAXBException .
     * @throws IOException .
     * @version 1
     */
    public static boolean end(List list,String[] cmdArg) throws JAXBException, IOException {
        System.out.println("Пока");
        saveCollection(cmdArg[0], list);
        return false;
    }

    /**
     * Метод save
     * Сохраняет текущее состояние коллекции
     * @param list Ожидается экземпляр коллекции для сохранения
     * @param cmdArg Ожидается массив элементов, в 0 элементе должен быть записан путь к файлу сохранения
     * @throws JAXBException .
     * @throws IOException .
     * @version 1
     */
    public static void save(LinkedList<FoodResidus> list,String[] cmdArg) throws JAXBException, IOException {
        saveCollection(cmdArg[0], list);
        System.out.println("Засейвленно");
    }
}
