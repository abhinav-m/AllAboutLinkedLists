
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
    public int size;

    public LinkedList()
    {
        start = null;
        size  = 0;
    }

    public boolean isEmpty()
    {
        return (start==null);
    }


    public int getSize()
    {
        if(start==null)
            size =0;
        else {
            size=1;
            Node curNode = start;
            while (curNode.getNext() != start) {
               size++;
                curNode = curNode.getNext();
            }
        }
        return size;
    }

    /*Function to add to the end of the circularly linked list.*/
    public boolean add(int data)
    {
        if(this.isEmpty()) {
            start = new Node(data, start);
            start.setNext(start);
        }

        else {
            Node curNode = start;
        //Traversing till the last node
            while (curNode.getNext() != start)
                curNode = curNode.getNext();
        //Initialising new node with start as it's next node
        //and setting it as curNode's next node.
            curNode.setNext(new Node(data, start));
        }
        size++;
        return true;
    }

    public boolean add(int data,int position) {
        if (position == 1) {
            if (this.isEmpty())
                start = new Node(data, start);
            else {
                //Assign a newNode to point to start as it's next node.
                Node newNode = new Node (data,start);
                Node curNode = start;
                while(curNode.getNext()!=start)
                    curNode = curNode.getNext();
                //Set the last Node's next node as the new node.
                curNode.setNext(newNode);
                //Assign the new node as start w
                start = newNode;

            }
        }
        //Inserting a new element at the end of the list.
        //Can call the above function for this.
        else if (position == this.getSize() + 1) {
            this.add(data);
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


        }
        size++;
        return true;
    }

    //Deletes from the end of the list
    public boolean delete()
    {
        if(this.isEmpty()) {
            System.out.println("List is empty, nothing to delete.");
            return false;
        }
        else if(start.getNext()==start) {
            start=null;


        }
        else
        {
            Node curNode = start;
            //Traverse to the node previous to the last node.
            while(curNode.getNext().getNext()!= start)
                curNode = curNode.getNext();
            curNode.setNext(start);


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
        {
            Node curNode = start;
            while(curNode.getNext()!=start)
                curNode = curNode.getNext();
            curNode.setNext(start.getNext());
            start = start.getNext();
        }


        else {
            Node curNode = start;
            int counter =1 ;
            while(counter!=position-1)
            {
                curNode = curNode.getNext();
                counter++;
            }
            curNode.setNext(curNode.getNext().getNext());



        }
        return true;
    }



    public void printList()
    {
        System.out.println("\nSize of list:"+this.getSize());
        if (this.isEmpty())
            System.out.println("\nList is empty");
        Node curNode = start;
        do
         {
            System.out.print(curNode.getInfo());
            curNode = curNode.getNext();

                System.out.print("->");
        }while(curNode!=start);

    }


}




public class CircularLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.printList();
        list.add(4,4);
        list.add(6,6);
        list.add(1,1);
        list.printList();
        list.delete();
        list.printList();
        list.delete(1);
        list.printList();
    }
}
