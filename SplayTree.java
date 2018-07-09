import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class SplayTree {

    public static long count=0;
    public static long zigzigTi=0;
    public static long zigzagTi=0;

    public static void zip(Node<Integer> A){
        Node leftA=A.left;
        Node rightA=A.right;
        Node APa=A.parent;
        Node AGran=A.parent;

        A.right=APa;
        APa.parent=A;

        APa.left=rightA;
        if (rightA!=null)
            rightA.parent=APa;

        A.parent=AGran;
        if (AGran.right==APa)
            AGran.right=A;
        else
            AGran.left=A;

    }
    public static void zag(Node<Integer> A){
        Node leftA=A.left;
        Node rightA=A.right;
        Node APa=A.parent;
        Node AGran=A.parent;

        A.left=APa;
        APa.parent=A;

        APa.right=leftA;
        if (leftA!=null)
            leftA.parent=APa;

        A.parent=AGran;
        if (AGran.right==APa)
            AGran.right=A;
        else
            AGran.left=A;
    }

    public static void zigzigLeft(Node<Integer> A){
        zigzigTi++;
        Node rightA=A.right;
        Node APa=A.parent;
        Node AGran=APa.parent;
        Node APaRight=APa.right;

        Node AGG=null;
        Boolean ifLeft=false;
        Boolean ifAGGExist=(AGran.parent!=null);
        if (ifAGGExist){
            AGG=AGran.parent;
            ifLeft=leftChild(AGran);
        }


        A.right=APa;
        APa.parent=A;

        APa.left=rightA;
        if (rightA!=null)
            rightA.parent=APa;

        APa.right=AGran;
        AGran.parent=APa;

        AGran.left=APaRight;
        if (APaRight!=null)
            APaRight.parent=AGran;

        if (ifAGGExist){
            if (ifLeft){
                AGG.left=A;
                A.parent=AGG;
            }
            else{
                AGG.right=A;
                A.parent=AGG;
            }
        }
    }

    public static void zigzigRight(Node<Integer> A){
        zigzigTi++;
        Node leftA=A.left;
        Node APa=A.parent;
        Node AGran=APa.parent;
        Node APaLeft=APa.left;
        Node AGG=null;
        Boolean ifLeft=false;
        Boolean ifAGGExist=(AGran.parent!=null);
        if (ifAGGExist){
            AGG=AGran.parent;
            ifLeft=leftChild(AGran);
        }

        A.left=APa;
        APa.parent=A;

        APa.left=AGran;
        AGran.parent=APa;

        APa.right=leftA;
        if (leftA!=null)
            leftA.parent=APa;

        AGran.right=APaLeft;
        if (APaLeft!=null)
            APaLeft.parent=AGran;

        if (ifAGGExist){
            if (ifLeft){
                AGG.left=A;
                A.parent=AGG;
            }
            else{
                AGG.right=A;
                A.parent=AGG;
            }
        }
    }

    public static void zagzig(Node<Integer> A){
        zigzagTi++;
        Node leftA=A.left;
        Node rightA=A.right;
        Node APa=A.parent;
        Node AGran=APa.parent;
        Node AGG=null;
        Boolean ifLeft=false;
        Boolean ifAGGExist=(AGran.parent!=null);
        if (ifAGGExist){
            AGG=AGran.parent;
            ifLeft=leftChild(AGran);
        }

        A.left=APa;
        APa.parent=A;

        A.right=AGran;
        AGran.parent=A;

        APa.right=leftA;
        if (leftA!=null)
            leftA.parent=APa;

        AGran.left=rightA;
        if (rightA!=null)
            rightA.parent=AGran;

        if (ifAGGExist){
            if (ifLeft){
                AGG.left=A;
                A.parent=AGG;
            }
            else{
                AGG.right=A;
                A.parent=AGG;
            }
        }
    }

    public static void zigzag(Node<Integer> A){
        zigzagTi++;
        Node leftA=A.left;
        Node rightA=A.right;
        Node APa=A.parent;
        Node AGran=APa.parent;
        Node AGG=null;
        Boolean ifLeft=false;
        Boolean ifAGGExist=(AGran.parent!=null);
        if (ifAGGExist){
            AGG=AGran.parent;
            ifLeft=leftChild(AGran);
        }

        A.right=APa;
        APa.parent=A;

        A.left=AGran;
        AGran.parent=A;

        AGran.right=leftA;
        if (leftA!=null)
            leftA.parent=AGran;

        APa.left=rightA;
        if (rightA!=null)
            rightA.parent=APa;

        if (ifAGGExist){
            if (ifLeft){
                AGG.left=A;
                A.parent=AGG;
            }
            else{
                AGG.right=A;
                A.parent=AGG;
            }
        }
    }

    public static boolean leftChild(Node<Integer> A){
        return A.parent.left==A;
    }

    public static void zigORzagOperation(Node<Integer> A, Tree tree){

        Boolean iftheRoot=(A.parent.parent==tree.root);
        if (leftChild(A)&&leftChild(A.parent)){
            zigzigLeft(A);
        }

        else if (leftChild(A)&&(!leftChild(A.parent)))
            zigzag(A);
        else if ((!leftChild(A))&&leftChild(A.parent))
            zagzig(A);
        else if ((!leftChild(A))&&(!leftChild(A.parent)))
            zigzigRight(A);
        else
            return;

        if (iftheRoot){
            tree.root=A;
            A.parent=null;
        }

    }

    public static void totalSplay(Node<Integer> A, Tree tree){

        if (tree.root.right!=null&&tree.root.left!=null){
            while (!(((int)(tree.root.e))==((int)(A.e))||((int)(tree.root.right.e))==((int)(A.e))||((int)(tree.root.left.e))==((int)(A.e)))){
                zigORzagOperation(A,tree);
            }
        }
        else if (tree.root.right!=null){
            while (!(((int)(tree.root.e))==((int)(A.e))||((int)(tree.root.right.e))==((int)(A.e)))){
                zigORzagOperation(A,tree);
            }
        }
        else if (tree.root.left!=null){
            while (!(((int)(tree.root.e))==((int)(A.e))||((int)(tree.root.left.e))==((int)(A.e)))){
                zigORzagOperation(A,tree);
            }
        }
        else
            return;

    }

    public static Node<Integer> Search(int a, Node root){
        if (root==null||((int)root.e)==a)
            return root;
        else{
            count++;
            if (((int)root.e)>a)
                return Search(a,root.left);
            else
                return Search(a,root.right);
        }

    }

    public static Node<Integer> Insert(int a, Node<Integer> root){
        count++;
        Node theInsertion=new Node<Integer>(a);
        if (a>=root.e&&root.right==null){
            root.right=theInsertion;
            theInsertion.parent=root;
            return theInsertion;
        }
        else if (a<=root.e&&root.left==null){
            root.left=theInsertion;
            theInsertion.parent=root;
            return theInsertion;
        }
        else {
            if (a<(root.e))
                theInsertion=Insert(a,root.left);
            else if (a>(root.e))
                theInsertion=Insert(a,root.right);
        }
        return theInsertion;
    }

    public static Node<Integer> findMaxOrMin(Node<Integer> start){
        if (start.left!=null){
            start=start.left;
            Node<Integer> theMax=null;
            while (start.right!=null){
                theMax=start.right;
                start=start.right;
            }
            return start;
        }
        else if (start.right!=null){
            start=start.right;
            Node<Integer> theMin=null;
            while (start.left!=null){
                theMin=start.left;
                start=start.left;
            }
            return start;
        }
        else
            return null;
    }

    public static void delete(Node<Integer> deleteOne,Tree tree){
        if(deleteOne.left!=null&&deleteOne.left.right==null&&deleteOne.left.left==null) {
            deleteOne.e=deleteOne.left.e;
            deleteOne.left=null;
        }
        else if(deleteOne.right!=null&&deleteOne.right.right==null&&deleteOne.right.left==null) {
            deleteOne.e=deleteOne.right.e;
            deleteOne.right=null;
        }
        else if (deleteOne.right==null&&deleteOne.left==null){
            deleteOne=null;
        }
        else{
            Node<Integer> theMax=findMaxOrMin(deleteOne);
            deleteOne.e=theMax.e;
            delete(theMax,tree);
        }
        /*while (!(theMax.right==null&&theMax.left==null)){
            Node<Integer> temp=theMax;
            if (deleteOne.parent==null){
                tree.root=theMax;
            }
            else if (leftChild(deleteOne)){
                deleteOne.parent.left=theMax;
                theMax.parent=deleteOne.parent;
            }
            else {
                deleteOne.parent.right=theMax;
                theMax.parent=deleteOne.parent;
            }

            theMax.left=deleteOne.left;
            theMax.right=deleteOne.right;
            if (deleteOne.left!=null)
            deleteOne.left.parent=theMax;
            if (deleteOne.right!=null)
            deleteOne.right.parent=theMax;
            deleteOne.left=null;
            deleteOne.right=null;
            deleteOne.parent=null;
            theMax=findMaxOrMin(theMax);
        }
        */
    }

    public static void postOrder(Node<Integer> root){
        if (root==null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.e+",");
    }

    public static void main(String[] args) {
        Scanner sc=null;
        try {
            sc=new Scanner(new FileInputStream(args[0]));
        }
        catch (FileNotFoundException e){
            System.out.println("The source file is not found, program will exit. Please change path.");
            System.exit(0);
        }
        int steps=0;
        boolean treeBuilt=false;
        Tree<Integer> tree=null;
        while (sc.hasNextLine()){
            steps++;
            String current=sc.nextLine();
            char command=current.charAt(0);
            int addNumber=Integer.parseInt(current.substring(1));

            if (command=='a'){
                if (treeBuilt==false){
                    tree=new Tree<>(addNumber);
                    treeBuilt=true;
                }
                else {
                    Node<Integer> currentInser=Insert(addNumber,tree.root);
                    totalSplay(currentInser,tree);
                }
            }
            else if (command=='r'){
                if (treeBuilt==true){
                    Node<Integer> theDelete=Search(addNumber,tree.root);
                    if (theDelete!=null){
                        Node<Integer> parent=theDelete.parent;
                        delete(theDelete,tree);
                        totalSplay(parent,tree);
                    }
                }
            }
            else{
                if (treeBuilt==true){
                    Node<Integer> theSearch=Search(addNumber,tree.root);
                    if (theSearch!=null){
                        totalSplay(theSearch,tree);
                    }
                }

            }
            if (steps==Integer.parseInt(args[1])){
                System.out.print("\nTraversal at "+steps+": ");
                    postOrder(tree.root);
            }
        }
        System.out.println("\n"+count+" compares");
        System.out.println(zigzigTi+" Zig-Zigs");
        System.out.println(zigzagTi+" Zig-Zags");
    }
}
