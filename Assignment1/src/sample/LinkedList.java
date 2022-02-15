
//public class LinkedList<L> {
//    Node head;
//
//    //function to add an element to the list at the end if there are element already and at the start if no elements are there
//    public void insert(int data) {
//        Node newNode = new Node(data);
//        if(head == null) {
//            head = newNode;
//        }else {
//            Node n = head;
//            while(n.next != null) {
//                n = n.next;
//            }
//            n.next = newNode;
//        }
//    }
//
//    //function to add an element at the start of the list
//    public void insertAtStart(int data) {
//        Node newNode = new Node(data);
//
//        newNode.next = head;
//        head = newNode;
//    }
//
//    //function to add an element at a given index
//    public void insertAt(int index, int data) {
//        if(index == 0){
//            insertAtStart(data); //We already have a function to do this.
//        }else{
//            Node newNode = new Node(data);
//
//            Node currentNode = head;
//            for(int i = 0; i < index - 1; i++) {
//                currentNode = currentNode.next;
//            }
//            newNode.next = currentNode.next;
//            currentNode.next = newNode;
//        }
//    }
//
//    public void addElement (L e){
//        Node<L> ln = new Node<>();
//        ln.setContents(e);
//        ln.next=head;
//        head=ln;
//    }
//
    //function to delete an element at the given index

//
//    //function to show the list
//    public void show() {
//        Node currentNode = head;
//        if(currentNode == null){
//            System.out.println("Linked list is empty");
//        }
//        else {
//            while(currentNode != null) {
//                System.out.print(currentNode.data + " ");
//                currentNode = currentNode.next;
//            }
//        }
//    }
//}

package sample;
//also handles adding and deleting

public class LinkedList<L> {

    //ghjk LinkedList<String> vaccinationCentreLinkedList = new LinkedList<>();
    public LinkedListNode<L> head = null;

    public void addElement(L e) {
        LinkedListNode<L> ln = new LinkedListNode<>();
        ln.setContents(e);
        ln.next = head;
        head = ln;
    }

//    public void deleteAt(int index) {
//        if(index == 0) {
//            head = head.next;
//        }else{
//            LinkedListNode currentNode = head;
//            for(int i = 0; i < index - 1; i++) {
//                currentNode = currentNode.next;
//            }
//            currentNode.next = currentNode.next.next;
//        }
//    }

    //create a get method to return the object which is the node.

//    Student temp=head;
//while(temp!=null && temp.getStudentId()!=soughtId)
//    temp=temp.next;
//if(temp!=null)
//    {
////Student object temp is the sought object here
//    }

    //https://iq.opengenus.org/search-an-element-in-a-linked-list/

//    public L getNodeSearch(int index) {
//        LinkedListNode<L> temp=head;
//        while(temp!=null)
//        {
//            if (temp.contents == index)
//                return null;
//            temp = temp.next;
//        }
//
//        return index;
//    }


    public void clear() {
        head = null;
    }

    //problem, adds and prints oddly
    //function to delete an element at the given index

    public int size () {
        int size = 0;
        LinkedListNode Current = head;
        while(Current.next != null)
        {
            Current = Current.next;
            size++;
        }
        return size;
    }

    public void deleteNode(int index) {
        //index equal to size
        index=size()-index-1;
        if (index == 0) {
            head = head.next;
            System.out.println("problem");
        } else {
            LinkedListNode currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
        }
    }


    class LinkedListNode<N> {
        public LinkedListNode<N> next = null;
        private N contents;
        private N nextNode;

        public N getContents() {
            return contents;
        }

        public void setContents(N c) {
            contents = c;
        }

        public N getNextNode() {
            return nextNode;
        }

        public void setNextNode(N nextNode) {
            this.nextNode = nextNode;
        }

    }


    public void show() {
        LinkedListNode<L> VC1 = head;
        while (VC1.next != null) {
            System.out.println(VC1.getContents());
            VC1 = VC1.next;
        }
        System.out.println(VC1.getContents());

    }

//    public void deleteNode(String s) {
//        LinkedListNode<L> VC2 = head;
//        if (head == null) {
//            System.out.println("Empty List.");
//        }
//        if (head.contents == VC2.contents) {
//            System.out.println(VC2.next);
//            System.out.println("problem");
//        }
//
//        LinkedListNode temp = head;
//        while (temp.next != null) {
//            if (temp.next.contents == VC2.contents) {
//                temp.next = temp.next.next;
//            }
//            temp = temp.next;
//        }
//        System.out.println("Head: " + head);
//    } //https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/



//    public void deleteNode(L UI) {
//        LinkedListNode<L> VC2 = head, prev = null;
//
//        if (VC2 != null && VC2.contents == UI) {
//            head = VC2.next;
//            return;
//        }
//
//        while (VC2 != null && VC2.contents != UI) {
//            prev = VC2;
//            VC2 = VC2.next;
//        }
//
//        if(VC2 == null)
//            return;
//
//        prev.next=VC2.next;
//
//    }
}



