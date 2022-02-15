package sample;

public class Node<N> {
    private N contents;
    private N nextNode;
    int data; //Data in the current node
  //  Node next; //Reference for the next node

    //Constructor which takes an int value which is stored as the data in this Node object.
    Node(int data) {
        this.data = data;
    }

    public Node() {

    }

    public N getContents() {
            return contents;
        }
        public void setContents(N c)
        {contents=c;}

        public N getNextNode() {
            return nextNode;
        }

        public void setNextNode(N nextNode) {
            this.nextNode = nextNode;
        }

    }

