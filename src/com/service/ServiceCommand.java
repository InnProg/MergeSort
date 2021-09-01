package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceCommand {

    //вывод инструкции
    public static void instruction(){
        System.out.println("\n" + "Программа сортировки строк или целых чисел методом слияния.\n" +
                "Команда -a - это сортировка по возрастанию.\n" +
                "Команда -d - это сортировка по убыванию.\n" +
                "Команда -s - это сортировка строк.\n" +
                "Команда -i - это сортировка целых чисел.");
        System.out.println("Пример команды: (-a или -d) (-i или -s) " +
                "(имя выходного файла).txt " +
                "(имя вход. файла).txt " +
                "(имя вход. файла).txt " +
                "(имя вход. файла).txt");
    }

    //получаем модификатор сортировки
    public static String sortMode(String[] args){

        List<String> listArgs = Arrays.asList(args);

        int indexAsc = listArgs.indexOf("-a");    //сортировка по возрастанию
        int indexDes = listArgs.indexOf("-d");   //сортировка по убыванию
        if(indexAsc<=0 && indexDes<0)
            return "-a";
        else
            return "-d";
    }

    //получаем тип сортировки
    public static String sortType(String[] args){

        List<String> listArgs = Arrays.asList(args);

        int indexLine = listArgs.indexOf("-s");  //сортировка строк
        int indexNum = listArgs.indexOf("-i");   //сортировка чисел
        if(indexNum>=0 && indexLine<0)
            return "-i";
        else
            return "-s";
    }

    //получаем имена входных файлов
    public static List<String> getNamesInFiles(String[] args){

        List<String> nameInFiles = new ArrayList<>();

        for(int i=1; i<3; i++){
            if(args[i].contains(".txt")){
                for(int j=0, k=i+1; j<args.length && k<args.length; j++){
                    nameInFiles.add(j,args[k++]);
                }
                break;
            }
        }

        return nameInFiles;
    }

    //получаем имя входного файла
    public static String getNameOutFile(String[] args){

        String nameOutFile = null;

        for(int i=1, k=0; i<3; i++){
            if(args[i].contains(".txt")){
                nameOutFile = args[i];
                break;
            }
        }

        return nameOutFile;
    }

    //проверка корректности ввода команды
    public static void validationCom(String[] args){

        boolean error = true;
        List<String> errorList = new ArrayList<>();

        //Проверяем корректность ввода режима и типа сортировки, а также выходного файла
        try {
            for(int i=0; i<3; i++){
                switch (i){
                    case 0:{
                        error = args[i].matches("(-[ad])|(-[is])");
                        if(!error){
                            if(!args[i].matches("-[ad]"))
                                errorList.add("Режим сортировки задан не верно.");
                            else
                                errorList.add("Тип сортировки задан не верно");
                        }
                        break;
                    }
                    case 1:{
                        error = args[i].matches("(-[is])|(\\w+\\.txt)");
                        if(!error){
                            if(!args[i].matches("-[is]"))
                                errorList.add("Тип сортировки задан не верно");
                            else
                                errorList.add("Выходной файл в команде задан не верно");
                        }
                        break;
                    }
                    case 2:{
                        if(!args[i].matches("\\w+\\.txt") && !args[i-1].matches("\\w+\\.txt"))
                            errorList.add("Выходной файл в команде задан не верно");
                        break;
                    }
                    default:
                        break;
                }
            }

            //Проверяем корректность ввода входных файлов
            for(int i=1; i<3; i++){
                if(args[i].contains(".txt")){
                    for (int j=i+1, k=1; j<args.length; j++){
                        if(!args[j].matches("\\w+\\.txt")){
                            errorList.add("Имя " + k +"-го " + "входного файла введено не верно");
                        }
                        k++;
                    }
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e + ": Ошибка ввода команды.");
        }


        //если есть ошибки ввода, то выводим в консоль и закрываем программы
        if(errorList.size()!=0){
            for (String s : errorList) System.out.println(s);
            instruction();
            System.exit(0);
        }
    }

    //подсчет времени выполнения команд
    public static long timingExecuteComStart(String startMessage){
        System.out.println("\n" + startMessage);
        long startTime = System.currentTimeMillis();
        return startTime;
    }

    //подсчет времени выполнения команд
    public static long timingExecuteComSEnd(String endMessage, long startTime){
        long endTime = System.currentTimeMillis();
        System.out.println("\n" + endMessage + " " + (endTime-startTime) + " мс");
        return endTime;
    }
}
