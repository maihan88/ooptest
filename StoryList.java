/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nguyenngocmaihan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author HAN NGUYEN
 */
public class StoryList extends ArrayList<Story> {
    Scanner sc = new Scanner(System.in);
    
    public void output(){ //24-37: dùng foreach duyệt qua danh sách và in từng tiêu đề cột khớp với định dạng của từng đối tượng Story, vời từng story sẽ được gán 1 stt
        System.out.println("===========================================================================================================================");
        System.out.printf("| %-6s | %-7s | %-40s | %-25s | %-5s | %-10s | %-8s |\n", 
                           "Number", "ID", "Title", "Author", "Rate", "Complete", "Language");
        
        int number=1;

        for (Story story : this) {
            story.output(number);
            number++;
        }
        
        System.out.println("===========================================================================================================================");
    }
    
    public void addFromFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            int count=0;

            while ((line = br.readLine()) != null) {
                count++;
                //kiểm tra dòng trắng, nếu có thì sẽ bỏ qua những mã phía dưới mà quay lại bắt đầu tiếp tục đọc dòng tiếp
                if (line.trim().equals("")) continue; 

                try { 
                    String[] arr = line.split("[,]+"); //cắt chuỗi theo dấu , có thể cắt được nhiều dấu phẩy liên tiếp

                    int id = Integer.parseInt(arr[0].trim());
                    String name = arr[1].trim();
                    double rating = Double.parseDouble(arr[2].trim());
                    String author = arr[3].trim();
                    boolean isCompleted = arr[4].trim().equals("1");
                    char language = arr[5].trim().charAt(0);

                    Story story = new Story(id, name, rating, author, isCompleted, language);
                    this.add(story);

                } catch (Exception e) {
                    System.out.println("Invalid data at line " + count + ": " + line+ e.getMessage()); //nơi xử lý các dữ liệu sai định dạng, nếu trong 1 dòng mà bị sai dữ liệu bất kỳ thì sẽ in lỗi dòng đó, những dòng hợp lệ vẫn tiếp tục
                }
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("Can't access to file"+ e.getMessage());
        }
    }
    
    public void sortByTitle() { //79-81:Gọi Collections.sort sử dụng hàm so sánh đã được định nghĩa bên lớp Story (dòng 86-94) để xếp theo tên A-Z.
        Collections.sort(this);
    }
    //83-93: sử dụng một lớp ẩn danh có sử dụng interface Comparator để ghi đè hàm compare.Ưu tiên xếp tên trước, nếu tên trùng nhau thì mới xét đến Rating giảm dần (từ cao xuống thấp).
    public void sortByTitleAndRating() {
        Collections.sort(this, new Comparator<Story>() {
            @Override
            public int compare(Story s1, Story s2) {
                int titleCompare = s1.getTitle().compareTo(s2.getTitle());
                if (titleCompare != 0) 
                    return titleCompare;
                return Double.compare(s2.getRating(), s1.getRating());
            }
        });
    }
    
    public void addStory() { //95-113: yêu cầu ng dùng nhập các thông tin hợp lệ và sau đó sử dụng hàm add để thêm mới đối tượng cùng với các thông tin vừa nhập
        try { //toàn bộ được đặt trong khối lệnh try-catch để tránh ng dùng nhập dữ liệu k hợp lệ
            System.out.print("Enter ID: "); 
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Title: "); 
            String title = sc.nextLine();
            System.out.print("Enter Rating: "); 
            double rating = Double.parseDouble(sc.nextLine());
            System.out.print("Enter Author: "); 
            String author = sc.nextLine();
            System.out.print("Is Completed (true/false): "); 
            boolean isCompleted = Boolean.parseBoolean(sc.nextLine());
            System.out.print("Language (char): "); 
            char lang = sc.nextLine().charAt(0);
            this.add(new Story(id, title, rating, author, isCompleted, lang));
        } catch (Exception e) { 
            System.out.println("Invalid input!"); 
        }
    }
    
    public void updateStory() { //để sửa truyện thì cần biết cụ thể dữ liệu của truyện được sửa nên
        System.out.print("Enter ID to update: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            int pos = searchStoryById(id); //tại dòng 119: em có sử dụng hàm tìm truyện theo id để lấy vị trí của id truyện tương ứng 

            if (pos == -1) {
                System.out.println("ID not found!");
                return;
            }

            Story story = this.get(pos); //Sau đó, em sử dụng hàm get với tham số là vị trí vừa tìm được để lấy ra dữ liệu truyện tương ứng trong danh sách.

            System.out.println("\n UPDATE OPTIONS FOR: " + story.getTitle());
            System.out.println("1. Title");
            System.out.println("2. Rating");
            System.out.println("3. Author");
            System.out.println("4. Completed Status");
            System.out.println("5. Language");
            System.out.println("Example: Enter '1, 3' to update Title and Author.");
            System.out.print("Enter your choices: ");

            String input = sc.nextLine(); //137-138: Người dùng có thể nhập ít nhất 1 lựa chọn để sửa, 
            String[] choices = input.split("[,\\s]+"); //sau đó các số được nhập đó sẽ được hàm split cắt bởi dấu phẩy hay khoảng trắng thành từng phần tử của mảng choices

            for (String choiceStory : choices) { //dùng vòng lặp for để duyệt qua từng lựa chọn người dùng vừa nhập.
                try {
                    int choice = Integer.parseInt(choiceStory);
                    switch (choice) { //143-171: dùng switch case để đi đến thực hiện đổi dữ liệu tương ứng với trường dữ liệu ng dùng muốn sửa
                        case 1 -> {
                            System.out.print("Enter new Title (Current: " + story.getTitle() + "): ");
                            story.setTitle(sc.nextLine()); //146,150,154,158,164: để sửa thông tin thì em sẽ xài hàm set đã định nghĩa bên lớp Story 
                        }
                        case 2 -> {
                            System.out.print("Enter new Rating (Current: " + story.getRating() + "): ");
                            story.setRating(Double.parseDouble(sc.nextLine()));
                        }
                        case 3 -> {
                            System.out.print("Enter new Author (Current: " + story.getAuthor() + "): ");
                            story.setAuthor(sc.nextLine());
                        }
                        case 4 -> {
                            System.out.print("Is Completed (true/false) (Current: " + story.isIsCompleted() + "): ");
                            story.setIsCompleted(Boolean.parseBoolean(sc.nextLine()));
                        }
                        case 5 -> {
                            System.out.print("Enter new Language (Current: " + story.getLanguage() + "): ");
                            String langStr = sc.nextLine();
                            if (!langStr.isEmpty()) {
                                story.setLanguage(langStr.charAt(0));
                            }
                        }
                        default -> System.out.println("Warning: Option '" + choice + "' is invalid and was skipped.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + choiceStory + "' is not a valid number."); //bắt lỗi nhập gì đo không phải số
                }
            }
            System.out.println("Update completed!");

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage()); //bắt lỗi dữ liệu bị hỏng hoặc cái gì đó khác
        }
    }
    
    public void deleteStory() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        int pos = searchStoryById(id); //tại dòng 183: em có sử dụng hàm tìm truyện theo id để lấy số id của truyện tương ứng 
        if (pos >= 0) {
            this.remove(pos); //nếu điều kiện đúng là id hợp lệ thì sẽ dùng hàm remove để xóa dữ liệu truyện có id tương ứng
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("ID not found!"); //nếu sai thì sẽ thông báo không tìm thấy id
        }
    }
    
    public int searchStoryByTitle(String title) {
        int index = 0;
        for (Story story : this) { 
            if (story.getTitle().equalsIgnoreCase(title)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int searchStoryById(int id) {
        int index = 0;
        for (Story story : this) {
            if (story.getId() == id) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int searchStoryByAuthor(String author) {
        int index = 0;
        for (Story story : this) {
            if (story.getAuthor().equalsIgnoreCase(author)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void searchStories() {
        System.out.print("Enter ID, Title or Author to search: ");
        String input = sc.nextLine().trim();

        int pos = -1;

        try {//231-236: mượn try-catch để xem input người dùng đưa vào có phải là 1 số không, nếu có thì tìm theo id
            int id = Integer.parseInt(input); 
            pos = searchStoryById(id);
        } catch (NumberFormatException e) {
            //Nếu không phải số hoặc không tìm thấy ID -> Tự động chuyển sang tìm theo Title (Tên truyện).
        }

        if (pos == -1) {
            pos = searchStoryByTitle(input);//nếu vẫn không thấy thì tìm qua author
        }

        if (pos == -1) {
            pos = searchStoryByAuthor(input);
        }

        if (pos != -1) { //sau 3 hàm mà vẫn k return 1 số khác -1 thì sẽ in ra"Not found", còn khác -1 thì đã tìm thấy và sử dụng hàm get với tham số tương ứng và dùng hàm xuất để in ra có gán tham số 1 biểu trưng cho stt
            System.out.println("Found story:");
            this.get(pos).output(1);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        } else {
            System.out.println("Not found!");
        }
    }
       
    public void saveToFile(String fileName) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            
            for (Story s : this) {
                pw.println("Story," + s.getId() + "," + s.getTitle() + "," + s.getRating() + "," + 
                           s.getAuthor() + "," + (s.isIsCompleted() ? "1" : "0") + "," + s.getLanguage());
            }
            
            pw.close();
            fw.close();
            System.out.println("Exported to file successfully!");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
