// https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
// https://www.w3schools.com/java/java_arraylist.asp
// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-
// https://en.wikipedia.org/wiki/Tree_traversal#Depth-first_search

public class TreeTest {
  public static void main(String[] args) {
    Integer[] values = {5000, 4000, 6000, 3000, 4500, 5500, 7000, 2000,3500,
                        4250, 4501, 5250, 5750, 6500, 8000};
    Tree<Integer> binaryTree = new Tree<>();
    binaryTree.insertNodes(values);

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
    binaryTree.outputTree(3);
    System.out.println();
    System.out.println();

    binaryTree.remove(5000);
    binaryTree.outputTree(3);
    System.out.println();
    System.out.println();

    binaryTree.remove(3000);
    binaryTree.outputTree(3);
    System.out.println();
    System.out.println();

    binaryTree.remove(5500);
    binaryTree.outputTree(3);
    System.out.println();
    System.out.println();

    binaryTree.remove(5250);
    binaryTree.outputTree(3);
    System.out.println();
    System.out.println();


  }
}
