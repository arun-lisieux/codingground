public class LinkedList{
    public static void main(String []args){
        // System.out.println("Reversing dummy list...");
        // root = reverseList(root);
        // System.out.println("Printing reversed dummy list...");
        // printList(root);
        
        System.out.println("Creating dummy list...");
        Node root = createDummyList(10);
        printList(root);
        System.out.println("Creating even palindrome list...");
        Node evenPalindromeRoot = createPalindrome(false);
        printList(evenPalindromeRoot);
        System.out.println("Creating odd palindrome list...");
        Node oddPalindromeRoot = createPalindrome(true);
        printList(oddPalindromeRoot);
        
        System.out.println("Checking dummy list...");
        System.out.println("isListPalindrome: " + isListPalindrome(root));
        System.out.println("Checking even palindrome list...");
        System.out.println("isListPalindrome: " + isListPalindrome(evenPalindromeRoot));
        System.out.println("Checking odd palindrome list...");
        System.out.println("isListPalindrome: " + isListPalindrome(oddPalindromeRoot));
        System.out.println("Printing dummy list after palindrome check...");
        printList(root);
        System.out.println("Printing even palindrome list after palindrome check...");
        printList(evenPalindromeRoot);
        System.out.println("Printing odd palindrome list after palindrome check...");
        printList(oddPalindromeRoot);
    }
    
    private static boolean isListPalindrome(Node root) {
        boolean isPalindrome = true;
        if (root == null) {
            return false;
        }
        
        Node singlePointer = root.next;
        Node prev = root;
        
        if (singlePointer == null) {
            return false;
        }
        
        Node doublePointer = root.next.next;
        
        while ((doublePointer != null) && (doublePointer.next != null)) {
            prev = singlePointer;
            singlePointer = singlePointer.next;
            doublePointer = doublePointer.next.next;
        }
        
        Node temp = null;
        Node oldListPrev = null;
        Node newListRoot = null;
        Node newListHead = null;
        if (doublePointer == null) {
            newListRoot = singlePointer;
        } else {
            temp = singlePointer;
            newListRoot = singlePointer.next;
        }
        
        prev.next = null;
        
        newListRoot = reverseList(newListRoot);
        newListHead = newListRoot;
        
        while ((root != null) && (newListRoot != null)) {
            oldListPrev = root;
            if (root.value != newListRoot.value) {
                isPalindrome = false;
            }
            root = root.next;
            newListRoot = newListRoot.next;
        }
        
        newListRoot = reverseList(newListHead);
        
        if (temp != null) {
            oldListPrev.next = temp;
            temp.next = newListRoot;
        } else {
            oldListPrev.next = newListRoot;
        }
        
        return isPalindrome;
    }
    
    private static Node reverseList(Node root) {
        Node prev = null;
        Node current = root;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    private static Node createPalindrome(boolean isOddPalindrome) {
        Node next = null;
        
        for (int i=1; i<5; i++) {
            Node current = new Node(i);
            current.next = next;
            next = current;
        }
        
        if (isOddPalindrome) {
            Node current = new Node(0);
            current.next = next;
            next = current;
        }
        
        for (int i=4; i>0; i--) {
            Node current = new Node(i);
            current.next = next;
            next = current;
        }
        
        return next;
    }
    
    private static Node createDummyList(int size) {
        Node next = null;
        for (int i=size; i>0; i--) {
            Node current = new Node(i);
            current.next = next;
            next = current;
        }
        
        return next;
    }
     
    private static void printList(Node root) {
        StringBuilder listString = new StringBuilder();
        while (root != null) {
            listString.append(root.value);
            listString.append("->");
            root = root.next;
        }
        listString.append(root);
        System.out.println(listString.toString());
    }
}
