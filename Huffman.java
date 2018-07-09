import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Content{
    int frequence;
    char text;
    String code="";

    public String getCode() {
        return code;
    }

    public void addCode(String a){
        code=code+a;
    }

    public Content(char newText){
        text=newText;
        frequence=1;
    }

    public Content(int newfrequence){
        frequence=newfrequence;
        text='-';
    }

    public Content(String newCode, char newText){
        code=newCode;
        text=newText;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public void setText(char text) {
        this.text = text;
    }

    public char getText() {
        return text;
    }

    public int getFrequence() {
        return frequence;
    }

    public void addFrequence(){
        frequence++;
    }
}

class codeForm{
    String Usecode;
    char text;
    public codeForm(String c,char t){
        Usecode=c;
        text=t;
    }
}



public class Huffman {

    public static int getArrayLength(Object[] c1) {
        int i;
        for (i = 0; i < c1.length && c1[i] != null; i++) {
        }
        return i;
    }

    public static void deleteFromStart(Object[] c1){
        int i;
        for (i = 0; i < getArrayLength(c1)-1; i++) {
            c1[i]=c1[i+1];
        }
        c1[i]=null;
    }

    public static Content[] buildFrequency(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        Content[] al = new Content[60];
        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            char[] OldSo = temp.toCharArray();
            char[] so=new char[OldSo.length];   //reverse the String
            for (int i = 0; i < so.length; i++) {
                so[i]=OldSo[so.length-1-i];
            }
            for (int i = 0; i < so.length; i++) {
                int existIndex = -1;
                int alLength = getArrayLength(al);
                for (int j = 0; j < alLength; j++) {
                    if (al[j].getText() == so[i])
                        existIndex = j;
                }
                if (existIndex != -1) {
                    al[existIndex].addFrequence();
                } else {
                    al[getArrayLength(al)] = new Content(so[i]);
                }
            }
        }
        return al;
    }

    public static void InsertionSort(Content[] al) {
        for (int i = 0; i < getArrayLength(al); i++) {
            int j = i - 1;
            Content temp = al[i];
            while (j >= 0 && al[j].getFrequence() > temp.getFrequence()) {
                al[j + 1] = al[j];
                j = j - 1;
            }
            al[j + 1] = temp;
        }
    }

    public static void NodeSort(Node<Content>[] al) {
        for (int i = 0; i < getArrayLength(al); i++) {
            int j = i - 1;
            Node<Content> temp = al[i];
            while (j >= 0 && al[j].e.getFrequence() > temp.e.getFrequence()) {
                al[j + 1] = al[j];
                j = j - 1;
            }
            al[j + 1] = temp;
        }
    }

    public static Node<Content> buildHuffmanTree(Content[] al){
        Node<Content> tr=null;
        Node<Content> le=null;
        Node<Content> ri=null;
        Node<Content>[] treeArray=new Node[60];

        for (int j = 0; j < getArrayLength(al); j++) {
            treeArray[j]=new Node<Content>(al[j]);
            //if you want to see frequency table please use this
            //System.out.println(al[j].text+":"+al[j].frequence);
        }

        while (getArrayLength(treeArray)>=2){
            if (treeArray[0].e.getFrequence()==treeArray[1].e.getFrequence()){
                ri=treeArray[1];
                le=treeArray[0];
            }
            else {
                le=treeArray[0];
                ri=treeArray[1];
            }

            tr=new Node<>(new Content(treeArray[0].e.getFrequence()+treeArray[1].e.getFrequence()));

            tr.left=le;
            tr.right=ri;
            deleteFromStart(treeArray);
            treeArray[0]=tr;
            NodeSort(treeArray);
        }
        return tr;
    }

    public static codeForm[] getEncode(Node<Content> root, codeForm[] cf){
        if (root.left!=null){
            (root.left.e).code=root.e.code+"0";
            getEncode(root.left,cf);
            (root.right.e).code=root.e.code+"1";
            getEncode(root.right,cf);
        }
        else{
            cf[getArrayLength(cf)]=new codeForm(root.e.getCode(),root.e.getText());
        }
        return cf;
    }

    public static String readFile(String fileName){
        Scanner newSc=null;
        String getText=null;
        try {
            newSc=new Scanner(new FileInputStream(fileName));
            getText=newSc.nextLine();
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        return getText;
    }

    public static void main(String[] args) {
        Content[] al=buildFrequency(args[0]); //build the occurance times for each char
        InsertionSort(al);  //sorting these times and char in decending order

        Node<Content> theNewRoot=buildHuffmanTree(al);    //build huffman tree

        codeForm[] encodeTable=new codeForm[60];
        getEncode(theNewRoot,encodeTable);    //get huffman code according to the tree

        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your encoding String:");
        String fileString=sc.nextLine();

        char[] fileChar=fileString.toCharArray();

        for (int i = 0; i < fileChar.length; i++) { //print code for each char in file
            for (int j = 0; j < getArrayLength(encodeTable); j++) {
                if (encodeTable[j].text==fileChar[i]){
                    System.out.print(encodeTable[j].Usecode);
                }
            }
        }

        //if you want to see frequency and encoding table, please use following
        /*
        for (int i = 0; i < encodeTable.length; i++){
            System.out.println(encodeTable[i].text+":"+encodeTable[i].Usecode);
        }
         */

    }
}
