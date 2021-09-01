package com.sort;

import java.util.*;

public class MergeSort {

    public static <T extends Comparable<? super T>> List<T> managerSort(List<T> list, String sortType, String sortMode){

        List result;
        switch (sortType){

            case "-i":
                result = new ArrayList<>();
                result = mergeSort(list);
                break;

            case "-s":
                result = new ArrayList<>(mergeSort(list));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortType);
        }
        switch (sortMode) {

            case "-a":
                return result;
            case "-d":
                Collections.reverse(result);
                return result;
        }
        return null;
    }

    private static <T extends Comparable<? super T>> List<T> mergeSort(List<T> arrayList) {
        ArrayList<T> list = new ArrayList<>(arrayList);
        int size = list.size();
        if (size < 2) {
            return list;
        }

        List<T> list1 = list.subList(0, size / 2);
        List<T> list2 = list.subList(size / 2, size);

        return merge(mergeSort(list1), mergeSort(list2));
    }

    private static <T extends Comparable<? super T>> List<T> merge(List<T> list1, List<T> list2) {

        List<T> result = new ArrayList<>();

        int i=0, j=0, k=0;

        while(i < list1.size() && j < list2.size()) {

            if(list1.get(i).compareTo(list2.get(j)) < 0)
                result.add(k++,list1.get(i++));
            else
                result.add(k++,list2.get(j++));
        }

        //копируем в массив последнии оставшиеся элементы
        if(i< list1.size()) {

            while(i<list1.size()){
                result.add(k++,list1.get(i++));
            }

        } else if(j< list2.size()) {
            while(j<list2.size()){
                result.add(k++,list2.get(j++));
            }
        }

        return result;
    }

}

