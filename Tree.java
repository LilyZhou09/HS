public class Tree <E>{
    Node root;
    Tree(E newE){
        root=new Node(newE);
    }
    Tree(Node newNode){
        root=newNode;
    }
    Tree(){
        root=null;
    }
}

class Node<E>{
    E e;
    Node<E> left, right, parent;
    public Node(E newE){
        e=newE;
        left=right=null;
        parent=null;
    }

    public void setE(E e) {
        this.e = e;
    }

    public Node(E newE, Node l, Node r){
        e=newE;
        left=l;
        right=r;
    }
    public Node(E newE, Node l, Node r,Node p){
        e=newE;
        left=l;
        right=r;
        parent=p;
    }
}
