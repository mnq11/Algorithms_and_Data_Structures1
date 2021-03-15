package com.company;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Main {
    static Random RandomGenerator = new Random();
    static int randomNumber;
    private static int N = 10;
    private static long execution;

    public static void main(String[] args) throws IOException {
//        cleanup Cache
        ResourceBundle.clearCache();
        getArrayFromFile();

//        task 1

//        for (int i = 0; i < 7; i++) {
//        long startTime = System.nanoTime();
//            quickSort(getArrayListFromFile());
//        long endTime = System.nanoTime();
//        execution = execution + (endTime - startTime);
//            NumberFormat formatter = new DecimalFormat("#0.00000");
//            System.out.println(N + "---" + " Execution time is " + formatter.format(execution /1000000d) + " milliseconds   " + " quickSort ");
//            N = N * 10;
//                    ExportFile(quickSort(quickSort(getArrayListFromFile())));
//        }


//        Task 2
        for (int i = 0; i < 7; i++) {
            int[] a = getArrayFromFile();
            /* flips the array to test the jump
            for(int s=0; s<a.length/2; s++){
                int temp = a[s];
                a[s] = a[a.length -s -1];
                a[a.length -s -1] = temp;
            }*/

            long startTime = System.nanoTime();
            insertionSort(a);
            long endTime = System.nanoTime();
            execution = execution + (endTime - startTime);
            NumberFormat formatter = new DecimalFormat("#0.00000");
            System.out.println(N + "---" + " Execution time is " + formatter.format(execution / 1000000d) + " milliseconds   " + "  insertionSort");
            N = N * 2;
        }
//

//        Task 3

//        for (int i = 0; i < 7; i++) {
//            long startTime = System.nanoTime();
//            mergeSort(getArrayFromFile(), getArrayFromFile().length);
//            long endTime = System.nanoTime();
//            execution = execution + (endTime - startTime);
//            NumberFormat formatter = new DecimalFormat("#0.00000");
//            System.out.println(N + "---" + " Execution time is " + formatter.format(execution /1000000d) + " milliseconds   " + " mergeSort");
//            N = N * 10;
//        }

        //        Task 4
//        for (int i = 0; i < 7; i++) {
//            int[] Binary = mergeSort(getArrayFromFile(), getArrayFromFile().length);
//            long startTime = System.nanoTime();
//            RunBinary(Binary, 98);
//            long endTime = System.nanoTime();
//            execution = execution + (endTime - startTime) ;
//            NumberFormat formatter = new DecimalFormat("#0.00000");
//            System.out.println(N + "---" + " Execution time is " + formatter.format(execution /1000000d) + " milliseconds   " + " Binary");
//            N = N * 10;
//        }


//        System.out.println(getArrayListFromFile().toString());
//        getArrayFromFile();
    }


    public static ArrayList<Integer> quickSort(ArrayList<Integer> items) {


        Integer pivot;
        if (items.size() > 1) {
            ArrayList<Integer> smaller = new ArrayList<>();
            ArrayList<Integer> same = new ArrayList<>();
            ArrayList<Integer> larger = new ArrayList<>();

            /*ToDo           median-of-three pivot          */
            pivot = items.get(items.size() / 2);


            /*ToDo            random pivot                     */
//            randomNumber = RandomGenerator.nextInt(items.size()-1);
//            pivot = items.get(randomNumber);



            /* ToDo             pivot [0]                     */
//            pivot = items.get(0);

            for (Integer i : items) {
                if (i < pivot)
                    smaller.add(i);
                else if (i > pivot)
                    larger.add(i);
                else
                    same.add(i);
            }
            quickSort(smaller); // Recursive call!
            quickSort(larger); // Recursive call!
            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }


        return items;
    }

    private static void ExportFile(List<Integer> arrData) throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        for (Integer str : arrData) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

    }

    private static ArrayList<Integer> getArrayListFromFile() throws FileNotFoundException {
        List<Integer> myNumbers = new ArrayList<Integer>();
        File file = new File("src/Seminar 1 - File with random numbers.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null && myNumbers.size() < N) {
                myNumbers.add(Integer.parseInt(text));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return (ArrayList<Integer>) myNumbers;
    }

    private static int[] getArrayFromFile() throws FileNotFoundException {
        ArrayList<Integer> arrayList = getArrayListFromFile();
        int[] ret = new int[arrayList.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrayList.get(i).intValue();
        }
//        System.out.print("[");
//        for (int i = 0; i <ret.length ; i++) {
//            System.out.print(" "+ret[i]);
//
//        }
//        System.out.println("]");

        return ret;
    }

    public static void insertionSort(int[] arr) {


        // Start from the second element
        // (element at index 0 is already sorted)
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i;
            // Find the index j within the sorted subset arr[0..i-1]
            // where element arr[i] belongs
            while (j > 0 && arr[j - 1] > value) {
                arr[j] = arr[j - 1];
                j--;
            }
            // Note that sub-array arr[j..i-1] is shifted to
            // the right by one position i.e. arr[j+1..i]
            arr[j] = value;
        }


        //Array to ArrayList for Export
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            arrayList.add(arr[i]);
//        }
//
//        ExportFile(arrayList);


    }

    public static int[] mergeSort(int[] a, int n) throws IOException {


        if (n < 2) {
            return new int[0];
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);


        return a;

    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static int binarySearch(int[] A, int x) {


        // search space is A[left..right]
        int left = 0, right = A.length - 1;

        // till search space consists of at-least one element
        while (left <= right) {
            // we find the mid value in the search space and
            // compares it with key value

            int mid = (left + right) / 2;

            // overflow can happen. Use:
            // int mid = left + (right - left) / 2;
            // int mid = right - (right - left) / 2;

            // key value is found
            if (x == A[mid]) {
                return mid;
            }

            // discard all elements in the right search space
            // including the mid element
            else if (x < A[mid]) {
                right = mid - 1;
            }

            // discard all elements in the left search space
            // including the mid element
            else {
                left = mid + 1;
            }
        }


        // x doesn't exist in the array
        return -1;


    }

    public static void RunMergeSort() throws IOException {
        int[] integers = getArrayFromFile();
        mergeSort(integers, integers.length);
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0; i < integers.length; i++) {
//            arrayList.add(integers[i]);
//            System.out.println(integers[i]);
//        }
//        ExportFile(arrayList);
    }

    public static void RunBinary(int[] SortedList, int X) throws FileNotFoundException {

        int index = binarySearch(SortedList, X);
        if (index != -1) {
            System.out.println("Element found at index " + index);
        } else {
            System.out.println("Element not found in the array");
        }

    }

}



