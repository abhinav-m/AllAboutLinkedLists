


class Node
{
  protected   Node next;
  protected int info;

 Node()
 {
     next = null;
     info = 0;
 }

 public Node(int d, Node n)
 {
     info = d;
     next = n;
 }

/*Getters and setters */
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}



class LinkedList
{
    protected Node start;
    protected Node end;
    public int size;

    public LinkedList()
    {
        start = null;
        end = null;
        size  = 0;
    }

    public boolean isEmpty()
    {
        return (start==null);
    }


    public int getSize()
    {
        return size;
    }

/*Function to add to the end of the linked list.*/
    public boolean add(int data)
    {

        //Insert one element in the linked list.
        if (isEmpty()) {
            start = new Node(data,null);
            end = start;
            size++;
            return true;
        }
        else {
            //Insert at the end, no need for traversal since we have
            //a reference to the 'end' node
            Node tempNode = end;
            tempNode.setNext(new Node(data,null));
            end = tempNode.getNext();
            size++;
            return true;
        }

    }

    public boolean add(int data,int position) {
        if (position == 1) {
            if (this.isEmpty()) {
                start = new Node(data, null);
                end = start;
                size++;
                return true;
            }
            //Assign a tempnode to hold the value of start
            Node tempNode = start;
            //Assign the new node as start with the tempNode in the next position.
            start = new Node(data, tempNode);
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
            Node newNode = new Node(data, curNode.getNext());
            curNode.setNext(newNode);
            size++;
            return true;

        }
    }

        //Deletes from the end of the list
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
                //Traverse to the node previous to the last node.
                while(curNode.getNext()!= end)
                curNode = curNode.getNext();
                end = curNode;
                curNode.setNext(null);
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
            System.out.print(curNode.getInfo());
            curNode = curNode.getNext();
            if(curNode!=null)
                System.out.print("->");
        }

    }


    /*PROBLEM STATEMENT:
    Find the middle of the linked list in O(n) time.
    The solution to the problem is relatively simple.
    If we apply brute force method, the problem can be solved in O(m)+O(n) m=n/2
    ie,O(n) for traversing the list and O(m) for traversing to the middle once it's found.
    However, a better solution involves running two parallel pointers at double speed which automatically gives us the middle of the linked list in O(n) time.
    A common issue which arises is running both the pointers simultaneously which can give ambiguous results when the size of the linked list is unknown (odd or even).
    Pointing an element to the next of next element can also be used to determine if the linked list has odd or even number of elements.
     */
    public int middleOfList()
    {
        Node speedOneNode,speedTwoNode;
        speedOneNode = speedTwoNode = start;
        int i=0,counter=1;
        if(this.isEmpty())
            return 0;
        else if(start.getNext()==null)
            return 1;
        else {
            while (speedTwoNode.getNext() != null) {
                //SpeedTwoNode always gets increased by one pointer,ie the hare
                if (i == 0) {
                    speedTwoNode = speedTwoNode.getNext();
                    i = 1;
                }
                //speedOneNode gets incremented by one pointer alternatively thus the tortoise "moving slowly"
                //also increment counter here to get the position of the middle element.
                else if (i == 1) {
                    speedTwoNode = speedTwoNode.getNext();
                    speedOneNode = speedOneNode.getNext();
                    counter++;
                    i = 0;
                }


            }
        }

        System.out.println("Middle of the linked list = "+counter);
        return speedOneNode.getInfo();

    }

    /*PROBLEM STATEMENT:
    Find out whether the given linked list has even number of elements or
    odd number of elements.
     */
    public boolean isListEven()
    {
        Node curNode = start;
        while(curNode!=null&&curNode.getNext()!=null)
            curNode = curNode.getNext().getNext();
        if(curNode==null) {
            System.out.println("\nList is even");
            return true;
        }
        else {
            System.out.println("\nList is odd");
            return false;
        }
    }

    /*PROBLEM STATEMENT:
    Reverse the  linked list iteratively.
    This one requires 3 nodes to solve the problem ie, the current node the next node and the previous node.
    Current node is initialised to start node in the beginning, previous node is null( as the list is being reversed)
    As we traverse through the list, WE GET THE VALUE OF THE NEXT NODE FIRST (this is so as to not break the chain)
    then we initialise the previous node (as next to the current node) (Reversing) then interchange the current node
    to be the previous node , and the next node to be the current node.
    Time complexity O(n) to traverse through the list O(1) extra space.
     */
    public void reverseListIter()
    {
        Node curNode,nextNode,prevNode;
        //Initially current node will be the first node,
        //previous node (will be null because list is being reversed)
        //next node will be the next node to start.
        curNode = start;
        prevNode = null;
        while(curNode!=null)
        {
            nextNode = curNode.getNext();
            curNode.setNext(prevNode);
            prevNode = curNode;
            curNode = nextNode;

        }
        start = prevNode;
    }
    /*PROBLEM STATEMENT:
    Reverse the  linked list recursively TBD.
    */
    public Node recursiveReverse(Node curNode)
    {
        //Base case where our recursion should return the last node
        //ie, the new head.
      if(curNode.getNext()==null)
          return curNode;
        //The last node here will be the new head returned by our function.
        Node newHead = recursiveReverse(curNode.getNext());
        curNode.getNext().setNext(curNode);
        //Break the current chain or list will become doubly linked.
        curNode.setNext(null);
        return newHead;

    }
    /*PROBLEM STATEMENT:
    In a singly linked list, print out the nth node from the end.
    Time complexity O(n) (One scan)
     */

    public int nthNodeFromEnd(int n)
    {
        int counter=1;
        if(n>this.size) {
            System.out.println("Cant find " + n + "th node from the end in a list less than size" + n);
            return 0;
        }
        if(this.isEmpty())
            return 0;
        Node curNode = start;
        Node nthNode = start;
        while(curNode.getNext()!=null)
        {
            if(counter == n)
            {
            curNode = curNode.next;
            nthNode = nthNode.next;
            }
            else
            {
                counter++;
                curNode = curNode.next;
            }
        }
        return nthNode.info;
    }
}


public class SinglyLinkedList {



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
        list.add(6);
        list.add(7);
        list.printList();
        list.isListEven();
        list.reverseListIter();
        System.out.print("\nFirst reversal");
        list.printList();
        System.out.println("3rd node from the end is"+list.nthNodeFromEnd(3));
        list.start = list.recursiveReverse(list.start);
        System.out.print("\nSecond reversal");
        list.printList();
        System.out.println("Data at middle ="+list.middleOfList());

    }
}