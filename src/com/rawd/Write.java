package com.rawd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Write {

    //запись массива в файл
    public static void writeToFile (List list, String sortType, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            switch (sortType) {
                case "-i":

                    for(int i=0; i<list.size(); i++){
                        int k = (int) list.get(i);
                        bufferedWriter.write(k + "\n");
                    }
                    bufferedWriter.close();
                    break;

                case "-s":
                    for(int i=0; i<list.size(); i++){
                        String s = (String) list.get(i);
                        bufferedWriter.write(s + "\n");
                    }
                    bufferedWriter.close();
                    break;
            }
        }catch (IOException e){
            System.out.println(e + ": Имя выходного файла введено не верно.");
        }
    }
}
