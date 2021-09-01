package com.rawd;

import java.io.*;
import java.util.*;

public class Read {

    //получаем общий массив данных файлов
    public static List getArray(List<String> namesInFiles, String sortType) throws IOException {

        int symbolInt = 0;
        String symbolStr = null;

        List<Integer> arrayListInt =new ArrayList<>();
        List<String> arrayListStr =new ArrayList<>();

        BufferedReader[] readers = new BufferedReader[namesInFiles.size()];
        InputStreamReader inputStreamReader = null;
        Scanner scanner = null;

        for(int i=0; i<namesInFiles.size(); i++){

            try{
                inputStreamReader = new InputStreamReader(new FileInputStream(namesInFiles.get(i)), "UTF-8");
                readers[i] = new BufferedReader(inputStreamReader);
                scanner = new Scanner(readers[i]);
                int error = 0;  //запоминает строку, где произошла ошибка

                while (scanner.hasNext()){
                    error++;
                    switch (sortType){
                        case "-i": {

                            try{
                                symbolInt = scanner.nextInt();
                                arrayListInt.add(symbolInt);

                            }catch (Exception er){
                                System.out.println(er + ": Ошибка чтения файла " + namesInFiles.get(i) + " в строке " + error);
                                scanner.next();
                            }

                        }
                            break;

                        case "-s": {

                            try{
                                symbolStr = scanner.nextLine();
                                if(!symbolStr.equals("") && symbolStr.length()==1)
                                    arrayListStr.add(symbolStr);
                                else
                                    throw new Exception("Ошибка чтения файла " + namesInFiles.get(i) + " в строке " + error);
                            }catch (Exception e){
                                System.out.println(e);
                            }
                        }
                            break;
                    }
                }
            }
            catch (Exception e){
                System.out.println(e + ": Ошибка чтения файла " + namesInFiles.get(i));
            }
        }
        scanner.close();
        for (int i=0; i<readers.length; i++){
            readers[i].close();
        }
        inputStreamReader.close();

        switch (sortType){
            case "-i":
                return arrayListInt;
            case "-s":
                return arrayListStr;
        }

        return null;
    }
}
