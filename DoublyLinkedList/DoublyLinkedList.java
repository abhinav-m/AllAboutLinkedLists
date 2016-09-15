import sun.awt.image.ImageWatched;

class Node
{
    protected int data;
    protected Node previous;
    protected Node next;

    Node()
    {
        previous = null;
        next = null;
        data =0;
    }

    Node(int data,Node next,Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}


  class LinkedList
 {
     int size;
     Node start, end;

     LinkedList()
     {
         size =0;
         start = null;
         end = null;
     }

     public boolean isEmpty()
     {
         return (start==null);
     }


     public int getSize()
     {
         return size;
     }


     public boolean add(int data)
     {
         if (isEmpty()) {
             start = new Node(data,null,null);
             end = start;
             size++;
             return true;
         }
         else {
             //Insert at the end, no need for traversal since we have
             //a reference to the 'end' node
             Node tempNode = end;
             tempNode.setNext(new Node(data,null,tempNode));
             end = tempNode.getNext();
             end.setPrevious(tempNode);
             size++;
             return true;
         }

     }


     public boolean add(int data,int position) {
         if (position == 1) {
             if (this.isEmpty()) {
                 start = new Node(data, null,null);
                 end = start;
                 size++;
                 return true;
             }
             //Assign a tempnode to hold the value of start
             Node tempNode = start;
             //Assign the new node as start with the tempNode in the next position.
             start = new Node(data, tempNode,null);
             size++;
             return true;
         }
         //Inserting a new element at the end of the list.
         //Can call the above function for this.
         else if (position == this.getSize() + 1) {
             this.add(data);
             return true;
         } else {
             int counter = 1;
             Node curNode = new Node();
             curNode = start;
             //Reach the previous node
             while (counter != position - 1) {
                 curNode = curNode.getNext();
                 counter++;
             }
             //Traversing to the previous node before where we want to insert
             //and changing to the nodes they are pointing to.
             //New node will be added as the next one to position n-1 with
             //position n-1 as it's previous node.
             Node newNextNode = curNode.getNext();
             Node newPrevNode = curNode;
             Node newNode = new Node(data,newNextNode,newPrevNode);

             curNode.setNext(newNode);
             newNode.getNext().setPrevious(newNode);
             //The node at position n+1 will have it's previous node adjusted as
             //the new node.

             size++;
             return true;

         }
     }


     public boolean delete()
     {
         if(this.isEmpty()) {
             System.out.println("List is empty, nothing to delete.");
             return false;
         }
         else if(start == end) {
             start=null;
             end = null;
             size--;

         }
         else
         {
             Node curNode = start;
             //Since we have the end node, and it's a doubly linked list
             //we can simply traverse back from the end node
             end = end.getPrevious();
             end.setNext(null);
             size --;


         }
         return true;
     }

     //Deletes from a specific position
     public boolean delete(int position)
     {
         if(this.isEmpty()) {
             System.out.println("List is empty, nothing to delete.");
             return false;
         }

         else if(position == this.getSize())
             delete();

         else if(position ==1)
             start = start.getNext();

         else {
             Node curNode = start;
             int counter =1 ;
             while(counter!=position-1)
             {
                 curNode = curNode.getNext();
                 counter++;
             }
             curNode.getNext().getNext().setPrevious(curNode);
             curNode.setNext(curNode.getNext().getNext());

             size--;

         }
         return true;
     }


     public void printList()
     {
         System.out.println("\nSize of list:"+this.getSize());
         if (this.isEmpty())
             System.out.println("\nList is empty");
         Node curNode = start;
         while(curNode!=null) {
             System.out.print(curNode.getData());
             curNode = curNode.getNext();
             if(curNode!=null)
                 System.out.print("->");
         }

     }
 }
public class DoublyLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.printList();
        list.add(4,4);
        list.add(6,6);
        list.printList();
        list.delete();
        list.printList();
        list.delete(1);
        list.printList();

    }
}
