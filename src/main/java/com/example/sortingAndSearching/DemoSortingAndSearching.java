package com.example.sortingAndSearching;

import java.util.Arrays;

public class DemoSortingAndSearching {

    public static void main(String[] args) {

        Integer[] nums = {1, 12, 4, 8, 9, 5, 10, 17, 4};
        Integer[] nums2 = {3, 4, 9, 1, 7, 0, 5, 2, 6, 8};
        Integer[] nums3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
////
        Sorting.quickSort(nums);
        System.out.println(Arrays.toString(nums));
//
        Sorting.quickSort(nums2);
        System.out.println(Arrays.toString(nums2));
//
        Sorting.quickSort(nums3);
        System.out.println(Arrays.toString(nums3));
////
////
////
////
//        SortingAndSearching.heapSort(nums);
//        System.out.println(Arrays.toString(nums));
////
//        SortingAndSearching.heapSort(nums2);
//        System.out.println(Arrays.toString(nums2));
////
//        SortingAndSearching.heapSort(nums3);
//        System.out.println(Arrays.toString(nums3));
//////
////
////
////
//        SortingAndSearching.mergeSort(nums);
//        System.out.println(Arrays.toString(nums));
////
//        SortingAndSearching.mergeSort(nums2);
//        System.out.println(Arrays.toString(nums2));
////
//        SortingAndSearching.mergeSort(nums3);
//        System.out.println(Arrays.toString(nums3));
    }

}
