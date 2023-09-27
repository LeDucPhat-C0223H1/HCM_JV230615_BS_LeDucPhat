package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
//    private static final BookService bookService = new BookService();
    private static final Book[] arrBook = new Book[100];
    private static int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("**************** JAVA-HACKATHON-05-BASIC-MENU ***************");
            System.out.println(
                            "1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách\n" +
                            "2. Hiển thị thông tin tất cả sách trong thư viện\n" +
                            "3. Sắp xếp sách theo lợi nhuận tăng dần\n" +
                            "4. Xóa sách theo mã sách\n" +
                            "5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả\n" +
                            "6. Thay đổi thông tin sách theo mã sách\n" +
                            "7. Thoát");
            System.out.print("Hãy nhập lựa chọn: ");
            choice= Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (choice) {
                case 1:
                    addNewBooks(scanner);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    sortBooks();
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    searchBooks(scanner);
                    break;
                case 6:
                    editBook(scanner);
                    break;
                case 7:
                    System.out.println("Thoát khỏi chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không hợp lệ. Vui lòng chọn lại từ (1-7).");
            }
        } while (choice != 7);
    }

    /*Thêm sách mới*/
    private static void addNewBooks(Scanner scanner) {
        System.out.print("Nhập số lượng sách cần thêm: ");
        int numBooks = Integer.parseInt(scanner.nextLine());

        if (bookCount + numBooks > arrBook.length) {
            System.out.println("Không thể thêm nhiều sách hơn. Đã đạt giới hạn 100 sách.");
        }else {
            for (int i = 0; i < numBooks; i++) {
                Book book = new Book();
                System.out.println("* Nhập thông tin cho sách thứ " + (bookCount + 1) + " *");
                book.inputData();
                System.out.println();
                arrBook[bookCount] = book;
                bookCount++;
            }
            System.out.println("Đã thêm " + numBooks + " sách thành công");
        }
        System.out.println();
    }

    /*Hiển thị sách trong kho*/
    private static void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("Không có sách trong thư viện");
        }else {
            System.out.println("* Danh sách sách trong thư viện *");
            for (int i = 0; i < bookCount; i++) {
                arrBook[i].displayData();
                System.out.println();
            }
        }
        System.out.println();
    }

    /*Sắp xếp lợi nhuận tăng dần*/
    private static void sortBooks() {
        if (bookCount == 0) {
            System.out.println("Không có sách trong thư viện");
        }else {
            for (int i = 0; i < bookCount -1; i++) {
                for (int j = i+1; j < bookCount ; j++) {
                    if(arrBook[i].getInterest() > arrBook[j].getInterest()){
                        Book temp = arrBook[i];
                        arrBook[i] = arrBook[j];
                        arrBook[j] = temp;
                    }
                }
            }
            System.out.println("Đã sắp xếp lợi nhuận tăng dần");
        }
        System.out.println();
    }

    /*Xóa sách theo ID*/
    private static void deleteBook(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("Không có sách trong thư viện");
        } else {
            System.out.print("Nhập ID sách cần xoá: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            int indexDelete = -1;

            for (int i = 0; i < bookCount; i++) {
                if(arrBook[i].getBookId() == bookId){
                    indexDelete= i;
                    break;
                }
            }
            if (indexDelete == -1) {
                System.out.println("Không tìm thấy id");
            }else {
                for (int i = indexDelete; i < bookCount-1; i++) {
                    arrBook[i] = arrBook[i+1];
                }
                arrBook[bookCount-1] = null;
                bookCount--;
                System.out.println(" Sách có mã " + bookId + " đã xoá thành công.");
            }
        }
        System.out.println();
    }

    /*Tìm kiếm sách theo tên hoặc mô tả*/
    private static void searchBooks(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("không có sách trong thư viện");
        }else {
            System.out.print("Nhập truy vấn tìm kiếm (tên hoặc mô tả): ");
            String query = scanner.nextLine();

            boolean found = false;
            Book[] arrBookSearch = new Book[bookCount];
            int index = 0;
            for (int i = 0; i < bookCount; i++) {
                if(arrBook[i].getBookName().contains(query) || arrBook[i].getDescriptions().contains(query)){
                    arrBookSearch[index] = arrBook[i];
                    index++;
                    found = true;
                }
            }

            if (found) {
                System.out.println("Kết quả tìm kiếm:\n");
                for (int i = 0; i < arrBookSearch.length; i++) {
                    if(arrBookSearch[i]!=null){
                        arrBookSearch[i].displayData();
                    }
                }
            }else {
                System.out.println("Không có sách phù hợp với truy vấn tìm kiếm.");
            }
        }
        System.out.println();
    }

    /*Thay đổi thông tin theo mã sách*/
    private static void editBook(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("không có sách trong thư viện");
        }else {
            System.out.print("Nhập ID sách để sửa đổi: ");
            int bookId = Integer.parseInt(scanner.nextLine());

            int bookIndex = -1;
            for (int i = 0; i < bookCount; i++) {
                if(arrBook[i].getBookId() == bookId){
                    bookIndex= i;
                    break;
                }
            }

            if (bookIndex == -1) {
                System.out.println("Sách không tồn tại");
            }else {
                System.out.println("Nhập thông tin mới cho sách có ID: " + arrBook[bookIndex].getBookId());
                arrBook[bookIndex].inputData();
                System.out.println("Trạng thái: true - Còn hàng ; false - Hết hàng");
                boolean checkStatus = Boolean.parseBoolean(scanner.nextLine());
                arrBook[bookIndex].setBookStatus(checkStatus);

                System.out.println("Thông tin sách sửa đổi thành công.");
            }
        }
        System.out.println();
    }
}
