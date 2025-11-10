/**
 * Lớp Node: Đại diện cho một nút (mắt xích) trong danh sách liên kết kép.
 */
public class Node {
    // Dữ liệu mà Node này nắm giữ (chính là thông tin sinh viên)
    private SinhVien data;
    
    // Con trỏ trỏ đến nút đứng trước nó
    private Node prev;
    
    // Con trỏ trỏ đến nút đứng sau nó
    private Node next;

    // Constructor
    public Node(SinhVien data) {
        this.data = data;
        this.prev = null; // Khi mới tạo, chưa liên kết
        this.next = null; // Khi mới tạo, chưa liên kết
    }

    // Các phương thức getter và setter
    public SinhVien getData() {
        return data;
    }

    public void setData(SinhVien data) {
        this.data = data;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}