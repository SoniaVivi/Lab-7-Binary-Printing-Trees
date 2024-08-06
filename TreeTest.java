// https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
// https://www.w3schools.com/java/java_arraylist.asp
// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-
// https://en.wikipedia.org/wiki/Tree_traversal#Depth-first_search

public class TreeTest {
  public static void main(String[] args) {
    Tree<Integer> binaryTree = new Tree<>();
    binaryTree.insertNode(5000);

    binaryTree.insertNode(4000);
    binaryTree.insertNode(6000);

    binaryTree.insertNode(3000);
    binaryTree.insertNode(4500);
    binaryTree.insertNode(5500);
    binaryTree.insertNode(7000);

    binaryTree.insertNode(2000);
    binaryTree.insertNode(3500);
    binaryTree.insertNode(4250);
    binaryTree.insertNode(4501);
    binaryTree.insertNode(5250);
    binaryTree.insertNode(5750);
    binaryTree.insertNode(6500);
    binaryTree.insertNode(8000);

    binaryTree.outputTree(3);
    System.out.println();

    binaryTree.recursiveInorderTraversal();
    System.out.println();
    binaryTree.inorderTraversal();
    System.out.println();

    System.out.println();
    binaryTree.recursivePreorderTraversal();
    System.out.println();
    binaryTree.preorderTraversal();
    System.out.println();

    System.out.println();
    binaryTree.postorderTraversal();

    System.out.println();
    System.out.println();
    System.out.println("postorder");
    binaryTree.search(4000, "postorder");
    System.out.println();
    System.out.println("preorder");
    binaryTree.search(4000, "preorder");
    System.out.println();
    System.out.println("inorder");
    binaryTree.search(4000, "inorder");


    System.out.println();
    System.out.println();
    System.out.println("postorder");
    binaryTree.search(5000, "postorder");
    System.out.println();
    System.out.println("preorder");
    binaryTree.search(5000, "preorder");
    System.out.println();
    System.out.println("inorder");
    binaryTree.search(5000, "inorder");

    System.out.println();
    System.out.println();
    System.out.println("postorder");
    binaryTree.search(2000, "postorder");
    System.out.println();
    System.out.println("preorder");
    binaryTree.search(2000, "preorder");
    System.out.println();
    System.out.println("inorder");
    binaryTree.search(2000, "inorder");

    System.out.println();
    System.out.println();
    System.out.println("postorder");
    binaryTree.search(99999, "postorder");
    System.out.println();
    System.out.println("preorder");
    binaryTree.search(99999, "preorder");
    System.out.println();
    System.out.println("inorder");
    binaryTree.search(99999, "inorder");

  }
}
