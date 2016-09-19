
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

    /*1)Recursively print reverse of a list
    * Time complexity O(n) , O(n) Recursive stack space needed*/
    public void printRevList(Node curNode)
    {
        if(curNode.next==null)
        {
            System.out.print(curNode.getInfo()+"->");
            return ;
        }
        else
            printRevList(curNode.next);
        System.out.print(curNode.getInfo()+"->");
        return;


   }

   /*2)Above function can also be done in a tiny different way.
   * Removes an extra print statement. ^.^
   * Time complexity O(n) , O(n) Recursive stack space needed*/
   public void printRevList2(Node curNode)
   {
       if(curNode==null) {
           System.out.println();
           return;
       }
       printRevList2(curNode.next);
       System.out.print(curNode.getInfo()+"->");

   }



    /*3)PROBLEM STATEMENT:
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

    /*4)PROBLEM STATEMENT:
    Find out whether the given linked list has even number of elements or
    odd number of elements.\
    Time complexity O(n/2) for traversing till the end approx O(n)
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

    /*5)PROBLEM STATEMENT:
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
    /*6)PROBLEM STATEMENT:
    Reverse the  linked list recursively.
    Time complexity (O(n) )
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

/*7)PROBLEM STATEMENT: Reverse linked list in pairs of two.
Time complexity : O(n) Space complexity: O(1)
Not the most intuitive solution, work on this more, still a solution nonetheless.
 */
    public void reverseInPairsIter(Node curNode){

        Node prevNode  = null;
        while(curNode.next!=null)
        {

            //Swapping nodes in pairs
          Node nextNode = curNode.next;
          curNode.next = nextNode.next;
            nextNode.next = curNode;



            if(curNode==start) {
                start = nextNode;

            }
            else
                prevNode.next =nextNode;


             prevNode  = curNode;
            //List is even sized and since our loop
            //terminates on checking the 'next' node,
            //this will cause null pointer since null->next is invalid.
            //in this case just move one pointer ahead through nextNode.
            if(curNode.next == null)
                curNode = nextNode.next;
            else
                curNode = curNode.next;



        }

    }


    /*8)PROBLEM STATEMENT:
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
/*9)PROBLEM STATEMENT  : Given two sorted linked lists, merge them together in a 3rd list in a sorted manner.
Time complexity O(m+n) m and n being lengths of the two sorted lists.
 */
    public static void mergeTwoSortedLists(Node firstHead,Node secondHead) {
        LinkedList sortedList = new LinkedList();
        while (firstHead != null && secondHead != null) {
            if (firstHead.info <= secondHead.info) {
                sortedList.add(firstHead.info);
                firstHead = firstHead.next;
            } else {
                sortedList.add(secondHead.info);
                secondHead = secondHead.next;
            }
        }

        if (firstHead == null)
            while (secondHead != null) {
                sortedList.add(secondHead.info);
                secondHead = secondHead.next;
            }
        else
            while (firstHead != null) {
                sortedList.add(firstHead.info);
                firstHead = secondHead.next;
            }

            sortedList.printList();
    }

    /*10)PROBLEM STATEMENT: Find first modular node from the end, modular node is defined as n%k = 0
    for some k where n or length of the list is unknown.
    Once understood clearly, the problem is the same as the nth node from last and can be done in a similar fashion.
    The approach used here is more intuitive than the one used in nth node problem(no successive addition)
    Time complexity : O(n)
     */
    public int firstModularNodeFromEnd(int k)
    {
        Node curNode = start;
        Node modularNode = start;
        //Move curNode k steps ahead (so that the modular node and curNode difference is always k steps)
        for(int i =0;i<k&&curNode.next!=null;i++)
        curNode = curNode.next;
        //Now the current node travels till it reaches the end of the list while the modular node lags behind 'k steps'
        while(curNode.next!=null)
        {
            curNode= curNode.next;
            modularNode = modularNode.next;
        }
        return modularNode.getInfo();
    }

    /*11)PROBLEM STATEMENT: Find last modular node from the beginning, modular node is defined as n%k = 0
    for some k where n or length of the list is unknown.
   This is a variation of the above problem.
     */
    public int lastModularNodeFromBeg(int k)
    {
        Node curNode = start;
        if(start == null)
            return 0;
        Node modularNode = new Node();
        int i;
        //Let the computer do the math.
        //i%k will evaluate to true after i becomes multiples of k and the loop runs till we reach the end
        //of the list therefore we find the last modular node from beginning.
        for( i= 1;curNode.next!=null;curNode = curNode.next,i++)
            if(i%k==0)
                modularNode = curNode;
        if(i<k)
            return(0);
        else
        return modularNode.info;

        }

/*12)PROBLEM STATEMENT: Given two linked lists with each node as a single digit integer number,
add the two linked lists and save the output in third list.
My approach converts both the linked lists into a number, adds the numbers and converts the given number back to a linked list.
Time complexity: For converting each of the linked lists to a number, O(n) + o(m)  n>=m or m>n plus convertint the number back
to a linked list which is O(n+1) or O(m+1) in worst case. Total time complexity : O(n)
NOTE: This can be done recursively. Look for more solutions.
 */
    public static LinkedList addLists(LinkedList list1,LinkedList list2)
    {
        long firstList,secondList;
        System.out.println("\nFirst and second list:");
        list1.printList();
        list2.printList();
        firstList = convertListtoNum(list1);
        secondList = convertListtoNum(list2);
        long thirdlist = firstList+secondList;
        System.out.println("\nThird list:");
        LinkedList thirdList = convertNumtoList(thirdlist);
        thirdList.printList();
        return thirdList;

    }
//Driver function for above problem
    public static long convertListtoNum(LinkedList list)
    {
        long num =0;
        Node curNode = list.start;
        while(curNode!=null)
        {
            num = num*10+curNode.info;
            curNode = curNode.next;

        }
        return num;
    }
//Driver function for above problem
    public static LinkedList convertNumtoList(long num)
    {
        long rem;
        LinkedList thirdList = new LinkedList();
        while(num>0)
        {
            rem = num%10;
            thirdList.add((int)rem,1);
            num = num/10;
        }
        return thirdList;
    }

/*13) Reverse the nodes of a linked list recursively,
in pairs. This is the recursive solution problem number 7.
 Time complexity(O(n) with O(n/2) recursive stack space)
 NOTE: I SPENT A LOT OF TIME DEBUGGING THIS PROBLEM BY CALLING ANOTHER FUNCTION RECURSIVELY
 INSTEAD OF THIS ONE NAME YOUR FUNCTIONS CORRECTLY.*/
    public Node recursiveReverseInPairs(Node curNode)
    {
        if(curNode==null||curNode.next==null)
            return null;
        Node tempNode = curNode.next;
        curNode.next = tempNode.next;
        tempNode.next = curNode;
        if(curNode==start)
        start = tempNode;
        Node newNextNode = recursiveReverseInPairs(curNode.next);
        if(newNextNode==null)
            return tempNode;
        else
        {
            curNode.next = newNextNode;
            return tempNode;
        }

    }

/*PROBLEM STATEMENT: Find √nth node in a linked list , given size of linked list is unknown
* Time complexity is O(n) to scan the list, this required me to use the sqrt function, maybe
* there's a solution without using this?*/
 public Node rootNthNode(Node curNode)
 {
     int counter = 1,rootN =1;
     if(curNode==null)
         return curNode;
     Node rootNthNode = curNode;
     while(curNode!=null)
     {
         if(rootN<(int)Math.sqrt(counter)) {
             rootNthNode = rootNthNode.next;
             rootN++;
         }

             curNode = curNode.next;

         counter++;

     }
     return rootNthNode;
 }
}




public class SinglyLinkedList {



    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        LinkedList testList = new LinkedList();

        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();

        for(int i = 1 ;i<=5;i++)
        {
            list1.add(i);
            list2.add(i);
        }

        LinkedList.addLists(list1,list2);

        for(int i=1;i<=19;i++)
            testList.add(i);
        System.out.println("k=3,n=19,firstModNode from end="+testList.firstModularNodeFromEnd(3));
        System.out.println("k=3,n=19,lastModNode from beg="+testList.lastModularNodeFromBeg(3));
        LinkedList sortedList1 = new LinkedList();
        for(int i=1;i<=5;i++)
            sortedList1.add(i);
        LinkedList sortedList2 = new LinkedList();
        for(int i=2;i<=7;i++)
            sortedList2.add(i);
        LinkedList.mergeTwoSortedLists(sortedList1.start,sortedList2.start);
        for(int i=1;i<=10;i++)
            list.add(i);
        list.printList();
        Node testNode = list.rootNthNode(list.start);
        System.out.println("Root nth node = "+testNode.info);
        list.recursiveReverseInPairs(list.start);
        System.out.println("After reversing in pairs") ;
        list.printList();
        list.printRevList2(list.start);


    }
}