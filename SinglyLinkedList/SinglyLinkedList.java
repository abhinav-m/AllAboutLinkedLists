


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
        list.delete(1);
        list.printList();

    }
}