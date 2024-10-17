package org.example;

import java.util.Scanner;

public class Lab_1 {
    /*    Bài 1 (Vul5): Mô phỏng lỗ hổng xâu định dạng và lỗ hổng tràn bộ đệm bằng cách kiểm tra kích thước đầu vào và ký tự định dạng.
          Bài 2 (Vul6): Mô phỏng lỗ hổng tràn bộ đệm bằng cách nhập phần tử quá kích thước mảng và kiểm tra chỉ số mảng để tránh lỗi truy cập ngoài giới hạn.
          Bài 3 (Vul7): Mô phỏng lỗ hổng tràn số nguyên và truy cập mảng ngoài phạm vi bằng cách kiểm tra kích thước và chỉ số mảng.*/
    
    // Bài 1: Mô phỏng lỗ hổng xâu định dạng và lỗ hổng tràn bộ đệm
    public static void Vul5(String keyword) {
        char[] buffer = new char[32];

        // Mô phỏng lỗ hổng tràn bộ đệm
        if (keyword.length() > buffer.length) {
            throw new ArrayIndexOutOfBoundsException("Buffer overflow detected with keyword: " + keyword);
        }

        // Mô phỏng lỗ hổng xâu định dạng
        if (keyword.contains("%")) {
            throw new IllegalArgumentException("Format string vulnerability detected with keyword: " + keyword);
        }

        System.out.println("Keyword accepted: " + keyword);
    }

    public static void testVul5Overflow() {
        System.out.println("Test Vul5 with overflow input: abcdefghijklmnopqrstuvwxyz1234567890");
        try {
            String longKeyword = "abcdefghijklmnopqrstuvwxyz1234567890"; // Đầu vào dài hơn 32 ký tự
            Vul5(longKeyword);
        } catch (Exception e) {
            System.err.println("Exception occurred in testVul5Overflow:");
            e.printStackTrace(System.err);
        }
    }

    public static void testVul5FormatString() {
        System.out.println("Test Vul5 with format string input: %s");
        try {
            String formatKeyword = "%s"; // Đầu vào có ký tự xâu định dạng
            Vul5(formatKeyword);
        } catch (Exception e) {
            System.err.println("Exception occurred in testVul5FormatString:");
            e.printStackTrace(System.err);
        }
    }

    // Bài 2: Mô phỏng lỗ hổng tràn bộ đệm và truy cập bộ nhớ sai
    public static void Vul6(int[] arr, int startIdx, int endIdx) {
        // Kiểm tra chỉ số mảng để tránh truy cập ngoài giới hạn
        if (startIdx < 0 || endIdx > arr.length || startIdx > endIdx) {
            throw new ArrayIndexOutOfBoundsException("Invalid array indices: startIdx=" + startIdx + ", endIdx=" + endIdx);
        }

        for (int i = startIdx; i < endIdx; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void testVul6Overflow() {
        System.out.println("Test Vul6 with overflow input: 10");
        try {
            int[] arr = {5, 4, 3, 2, 1}; // Mảng với 5 phần tử
            Vul6(arr, 0, 10); // Chỉ số endIdx vượt quá kích thước mảng
        } catch (Exception e) {
            System.err.println("Exception occurred in testVul6Overflow:");
            e.printStackTrace(System.err);
        }
    }

    public static void testVul6InvalidIndex() {
        System.out.println("Test Vul6 with invalid index: -1");
        try {
            int[] arr = {5, 4, 3, 2, 1}; // Mảng với 5 phần tử
            Vul6(arr, -1, 4); // Chỉ số startIdx không hợp lệ
        } catch (Exception e) {
            System.err.println("Exception occurred in testVul6InvalidIndex:");
            e.printStackTrace(System.err);
        }
    }

    // Bài 3: Mô phỏng lỗ hổng tràn số nguyên
    public static void Vul7(byte[] input, int startIdx, int endIdx) {
        try {
            byte[] subData = subArray(input, startIdx, endIdx);
            System.out.print("Result: ");
            for (int i = 0; i < subData.length; i++) {
                System.out.println("Element " + i + ": " + subData[i]);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static byte[] subArray(byte[] source, int beginIdx, int endIdx) {
        if (beginIdx < 0 || endIdx > source.length || beginIdx > endIdx) {
            throw new IllegalArgumentException("Invalid array range");
        }
        byte[] result = new byte[endIdx - beginIdx];
        int k = 0;
        for (int i = beginIdx; i < endIdx; i++) {
            result[k] = source[i];
            k++;
        }
        return result;
    }

    public static void testVul7OutOfBounds() {
        System.out.println("Test Vul7 with out-of-bounds index: -1");
        try {
            byte[] arr = {1, 2, 3, 4, 5}; // Mảng với 5 phần tử
            Vul7(arr, -1, 10); // Chỉ số ngoài phạm vi mảng
        } catch (Exception e) {
            System.err.println("Exception occurred in testVul7OutOfBounds:");
            e.printStackTrace(System.err);
        }
    }

    public static void runAllTests() {
        System.out.println("Running tests for Lab_1:");

        // Test cho Bài 1
        System.out.println("\n");
        System.out.println("===== Bài 1 =====");
        testVul5Overflow();
        testVul5FormatString();

        // Test cho Bài 2
        System.out.println("\n");
        System.out.println("===== Bài 2 =====");
        testVul6Overflow();
        testVul6InvalidIndex();

        // Test cho Bài 3
        System.out.println("\n");
        System.out.println("===== Bài 3 =====");
        testVul7OutOfBounds();
    }
}
