
/*UNROLLED LINKED LISTS
TO-DO Deletion and insert at X position, as well as analysis of time complexity.
* Unrolled linked lists are special kind of linked lists which give better performance due to cache reads and spatial locality of arrays */
class Node
{
    protected int[] array;
    protected int numElements;
    protected Node next;

    Node(int size)
    {
        array = new int[size];
        numElements = 0;
        next = null;
    }
}


class ULList
{
protected Node start,end;
//Size of each node, number of nodes.
private int sizeNode;
private int nNode,numElementsinList;


    //User decides the capacity, can also be constant.Ideally
    //should be the size of a cache line to improve performance.
    public ULList(int capacity)
    {
        start = null;
        nNode = 0;
        sizeNode = capacity +1;
    }

    public boolean isEmpty()
    {
        return (start == null);
    }
//Size here will be number of nodes in the list with "m sized array"
    public int getSize()
    {
        return nNode;
    }

    public void  deleteList()
    {
        start = null;
        nNode = 0;
    }

    //Addition will always be done to the last position of a node.
    public void add(int x)
    {
        if(x==7)
            x=x;
        numElementsinList++;
        //If list is empty
        if(start == null){

            start = new Node(sizeNode);
            start.array[0]= x;
            start.numElements++;
            end = start;
            nNode++;
        }
        //If last node still has space for more elements , just insert in last node
        else  if(end.numElements< sizeNode){
            //Insert in the last position of the last node.
            end.array[end.numElements] = x;
            end.numElements++;
        }
        //IF new node needs to be assigned to add more elements to the linked list.
        //IF this is the case, the previous last node has half of its elements  moved to the new last node.
        else
        {
            Node newNode = new Node(sizeNode);
            int j =0;
            int numReduced = end.numElements;
            for(int i = end.numElements/2+1;i<end.numElements;i++) {
                newNode.array[j++] = end.array[i];
                numReduced--;
                end.array[i]=0;
            }
            end.numElements = numReduced;
            newNode.array[j++] = x;
            newNode.numElements = j;

            end.next = newNode;
            end = newNode;
            nNode++;
        }
    }

    public void display()
    {
       if(this.isEmpty())
       System.out.println("List is empty");
        else
       {
           Node curNode = start;
           while(curNode!=null)
           {
               System.out.print("[");
               for(int i =0;i<curNode.numElements;i++)
                   System.out.print("("+curNode.array[i]+")");
               System.out.print("]");
               if(curNode.next !=null)
               System.out.print("->");
               curNode = curNode.next;
           }
       }
    }
}



public class UnrolledLinkedList {

    public static void main(String[] args) {
	ULList unRollList = new ULList(5);
        for(int i = 1;i<=22;i++)
        unRollList.add(i);
        unRollList.display();

    }
}
