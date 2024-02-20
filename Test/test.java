// Online Java Compiler
// Use this editor to write, compile and run your Java code online

public  class test {
    static int MIN_PID=300;
    static int Max_PID=304;

    static Node head;
static  Node prev=null;
   static  class Node{
        int data;
        Node next;
     Node(int data,Node next){
        this.data=data;
        this.next=next;
    }
}
static void print_pid_list() {
    Node curr=head;
    while(curr!=null){
        if(curr.data!=0)
        System.out.println("PID "+curr.data+": Allocated");
        curr=curr.next;
    }
    System.out.println();
}

    static void init_list(){
         head=null;
         prev=new Node(MIN_PID-1, null);

    }
     static int allocate_pid(){
        if(head!=null){
            if(head.data==0){
                head.data=MIN_PID;
          
       // prev.data=head.data;
        return head.data;}
        }
Node curr=head;

while(curr!=null){
    if(curr.data==0){
curr.data=prev.data+1;
return curr.data;
    }
    prev=curr;
curr=curr.next;
}
if(prev.data==Max_PID)return -1;
curr=new Node(prev.data+1, null);
if(head==null)
head=curr;
prev.next=curr;
return curr.data;
//return -1;
    }
   static void release_pid(int pid){
   
        Node cur=head;
        while(cur!=null){
            if(cur.data==pid)
            cur.data=0;
            cur=cur.next;
        }
    }
    public static void main(String[] args) {
        init_list();
int p1=allocate_pid();
int p2=allocate_pid();
int p3=allocate_pid();
System.out.println("Allocated PID's");
print_pid_list();
release_pid(p1);  
release_pid(p3);  
System.out.println("Released PID's "+p1+" "+p3);
System.out.println("List After Realased PID's");
print_pid_list();
System.out.println("List after allocating PID "+allocate_pid()+" :");
print_pid_list();

}
}