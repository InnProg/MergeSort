import com.rawd.Read;
import com.rawd.Write;
import com.service.ServiceCommand;
import com.sort.MergeSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        long startTime = 0;

        //проверка корректности ввода команды
        ServiceCommand.validationCom(args);

        //режим сортировки
        String sortMode= ServiceCommand.sortMode(args); //-a или -d

        //тип сортировки
        String sortType= ServiceCommand.sortType(args); //-s или -i

        //получаем имя выходного файла
        String nameOut = ServiceCommand.getNameOutFile(args);

        //получаем имена файлов
        List<String> namesInFiles = ServiceCommand.getNamesInFiles(args);

        startTime = ServiceCommand.timingExecuteComStart("Чтение данны с файла(ов)");

        //получаем общий массив данных
        List list = Read.getArray(namesInFiles,sortType);

        ServiceCommand.timingExecuteComSEnd("Время чтения и запись в массив", startTime);

        //получаем отсортированный массив и записываем в файл
        List<Integer> arrayInt = null;
        List<String> arrayStr = null;

        switch (sortType){
            case "-i":
                startTime = ServiceCommand.timingExecuteComStart("Сортировка массива чисел");
                arrayInt = new ArrayList<>(MergeSort.managerSort(list,sortType,sortMode));
                ServiceCommand.timingExecuteComSEnd("Время сортировки:", startTime);

                startTime = ServiceCommand.timingExecuteComStart("Запись отсортированного массива чисел в файл");
                //запись отсортированного массива в файл
                Write.writeToFile(arrayInt,sortType,nameOut);
                ServiceCommand.timingExecuteComSEnd("Время записи:", startTime);

                break;
            case "-s":
                startTime = ServiceCommand.timingExecuteComStart("Сортировка массива символов");
                arrayStr = new ArrayList<>(MergeSort.managerSort(list,sortType,sortMode));
                ServiceCommand.timingExecuteComSEnd("Время сортировки:", startTime);

                startTime = ServiceCommand.timingExecuteComStart("Запись отсортированного массива символов в файл");
                //запись отсортированного массива в файл
                Write.writeToFile(arrayStr,sortType,nameOut);
                ServiceCommand.timingExecuteComSEnd("Время записи:", startTime);
                break;
        }
    }
}