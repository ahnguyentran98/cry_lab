package org.example;

public class Lab_2 {

   /* Bài 1: Sửa Lại Để Kiểm Tra Dữ Liệu Đầu Vào
    Thay đổi: Thêm điều kiện kiểm tra độ dài chuỗi và từ chối nếu vượt quá kích thước cho phép.
    Kiểm tra chuỗi định dạng để ngăn xâu định dạng.*/
    public static void Vul5(String keyword) {
        // Thẩm tra dữ liệu đầu vào
        if (keyword.length() > 32) {
            System.out.println("Error: Input exceeds buffer size.");
            return; // Dừng xử lý
        }

        if (keyword.contains("%")) {
            System.out.println("Error: Invalid format string.");
            return;
        }

        System.out.println("Keyword accepted: " + keyword);
    }

  /*  Bài 2: Sửa Lại Để Kiểm Tra Chỉ Số Mảng
    Thay đổi: Kiểm tra các chỉ số mảng trước khi truy cập để đảm bảo chúng nằm trong phạm vi hợp lệ của mảng.*/
    public static void Vul6(int[] arr, int startIdx, int endIdx) {
        // Kiểm tra chỉ số mảng
        if (startIdx < 0 || endIdx > arr.length || startIdx > endIdx) {
            System.out.println("Error: Invalid array indices.");
            return; // Dừng xử lý
        }

        for (int i = startIdx; i < endIdx; i++) {
            System.out.println(arr[i]);
        }
    }


/*   Bài 3: Chuyển Đổi Kiểu Dữ Liệu tránh tràn số nguyên
   Nếu kiểu dữ liệu không đủ lớn để chứa giá trị, ta chuyển đổi sang kiểu lớn hơn như int hoặc long.*/
    public static void Vul7(int[] input, int startIdx, int endIdx) {
        // Kiểm tra chỉ số mảng
        if (startIdx < 0 || endIdx > input.length || startIdx > endIdx) {
            System.out.println("Error: Invalid array range.");
            return;
        }

        byte[] subData = subArray(input, startIdx, endIdx);
        System.out.print("Result: ");
        for (int i = 0; i < subData.length; i++) {
            System.out.println("Element " + i + ": " + subData[i]);
        }
    }

    public static byte[] subArray(int[] source, int beginIdx, int endIdx) {
        if (beginIdx < 0 || endIdx > source.length || beginIdx > endIdx) {
            throw new IllegalArgumentException("Invalid array range");
        }
        byte[] result = new byte[endIdx - beginIdx];
        for (int i = beginIdx; i < endIdx; i++) {
            result[i - beginIdx] = (byte) source[i];
        }
        return result;
    }

   /* Thực hiện kiểm tra*/
    public static void runAllTests() {
        System.out.println("Running tests for Lab_2:");

        // Test cho Bài 1
        System.out.println("\n===================");
        System.out.println("===== Bài 1 =====");
        Vul5("validInput");
        Vul5("inputWith%");
        Vul5("thisIsAnExtremelyLongKeywordThatExceeds32Characters");

        // Test cho Bài 2
        System.out.println("\n===================");
        System.out.println("===== Bài 2 =====");
        int[] arr = {5, 4, 3, 2, 1};
        Vul6(arr, 0, 5); // Hợp lệ
        Vul6(arr, -1, 4); // Không hợp lệ
        Vul6(arr, 0, 10); // Không hợp lệ

        // Test cho Bài 3
        System.out.println("\n===================");
        System.out.println("===== Bài 3 =====");
        int[] intArray = {1, 2, 3, 4, 5};
        Vul7(intArray, 0, 3);
        Vul7(intArray, -1, 10);
    }
}
