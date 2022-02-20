/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex3_advancedsort;

import java.util.Scanner;

/**
 * implement advanced sorts: Quick Sort, Shell Sort, Merge Sort
 * @author VNHS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Give an integer array seperated by comma (,): ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        Integer[] a = toArray(s);
        System.out.println();
        System.out.println("*** Sorting with ***");
        System.out.println("1. Quick Sort");
        System.out.println("2. Shell Sort");
        System.out.println("3. Merge Sort");
        System.out.print("Please select sorting algorithm (1/2/3): ");
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> quickSort(a, 0, a.length -1);
            case 2 -> shellSort(a);
            case 3 -> mergeSort(a, 0, a.length -1);
            default -> System.out.println("Not sorted");
        }
        displayArray(a);
    }
    
    // convert string to array
    public static Integer[] toArray(String s) {
        String[] st = s.split(",");
        Integer[] a = new Integer[st.length];
        for (int i = 0; i < st.length; i++) {
            a[i] = Integer.parseInt(st[i].trim());
        }
        return a;
    }
    //display array
    public static void displayArray(Integer[] a) {
        String s = "";
        for (int i: a) s += i + " ";
        System.out.println(s);
    }
    
    // Merge Sort
    public static void mergeSort(Integer[] a, int start, int end) {
        if (start >= end) return;
        int middle = (start + end) /2;
        mergeSort(a, start, middle);
        mergeSort(a, middle + 1, end);
        merge(a, start, end);        
    }
    public static void merge(Integer[] a, int start, int end) {
        int middle = (start + end) /2;
        // temporary arrays
        Integer[] left = new Integer[middle - start +1];
        Integer[] right = new Integer[end - middle];
        // copy to temporary arrays
        for (int i = start; i <= end; i++) {
            if (i <= middle) left[i - start] = a[i]; else right[i - middle -1] = a[i];
        }
        // merge left and right to a
        int lh = 0, rh = 0;
        for (int i = start; i <= end; i++) {
            if (lh > middle - start) {
                a[i] = right[rh++]; 
            } else if (rh >= end - middle) {
                a[i] = left[lh++];
            } else if (left[lh] <= right[rh]) {
                a[i] = left[lh++];
            } else {
                a[i] = right[rh++]; 
            }
        }
    }
    
    // Shell Sort
    public static void shellSort(Integer[] a) {
        //Knuth interval
        int interval = 1;
        while (interval < a.length / 3) interval = 3*interval +1;
        
        // insertion sort with interval till interval to 1
        while (interval > 0) {
            // insertion sort with interval
            for (int outer = interval; outer < a.length; outer++) {
                // insert backward
                int inner = outer - interval;
                int toInsert = a[outer];
                while (inner >= 0 && a[inner] > toInsert) {
                    a[inner + interval] = a[inner];
                    inner = inner - interval;
                }
                a[inner + interval] = toInsert;
            }
            
            // reduce interval
            interval = (interval - 1) /3; 
        }
    }
    
    
    // Quick Sort recursively
    public static void quickSort(Integer[] a, int start, int end) {
        if (start >= end) return;
        int pivot = partition(a, start, end);
        quickSort(a, 0, pivot - 1);
        quickSort(a, pivot + 1, end);        
    }
    public static int partition(Integer[] a, int start, int end) {
        int i = start; // the index of element > a[end]
        for (int j = start; j < end; j++) {
            if (a[j] < a[end]) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, end);
        return i;
    }
    
    // swap value at i, j
    public static void swap(Integer[] a, int i, int j) {
        if (i != j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
    
}
