// Siona Vivian
// 7/8/24
// CS 143
// Lab 7: Binary Printing Trees
/*
  The program is create a binary search tree and relevant methods.
*/
/* ================= FOR EXTRA CREDIT ==============
                    Wrote the following methods iteratively:
                    2 tree traversals (inorder, preorder)
                    search
                    remove
                    insert
                    getMin

*/
/* SOURCES:
  Provided files
  Dietel
  https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
  https://www.w3schools.com/java/java_arraylist.asp
  https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-
  https://en.wikipedia.org/wiki/Tree_traversal
*/
import java.util.*;

class TreeNode<T extends Comparable<T>> {
   // package access members
   TreeNode<T> leftNode; // left node
   T data; // node value
   TreeNode<T> rightNode; // right node

   // constructor initializes data and makes this a leaf node
   public TreeNode(T nodeData) {
      data = nodeData;
      leftNode = rightNode = null; // node has no children
   }
}

// class Tree definition
public class Tree<T extends Comparable<T>> {
   private TreeNode<T> root;

   // constructor initializes an empty Tree of integers
   public Tree() {
      root = null;
   }
   public Tree(TreeNode<T> root) {
      this.root = root;
   }

   // insert a new node in the binary search tree
   public void insertNode(T insertValue) {
      if (root == null) {
         root = new TreeNode<>(insertValue); // create root node
      } else {

         TreeNode<T> current = root;

         while (current != null) {
            int result = insertValue.compareTo(current.data);

            switch (result) {
               // Continue along branch until the next child on that path is
               // a null, upon which set it to the value to be inserted and return.
               case 1:
                  if (current.rightNode == null) {
                     current.rightNode = new TreeNode<>(insertValue);
                     return;
                  }

                  current = current.rightNode;
                  break;
               case -1:
                  if (current.leftNode == null) {
                     current.leftNode = new TreeNode<>(insertValue);
                     return;
                  }

                  current = current.leftNode;
                  break;
            }
         }
      }
   }

   public void insertNodes(T[] values) {
      for (T value : values) {
         this.insertNode(value);
      }
   }

   public TreeNode<T> remove(T value) {
      // Parent needed to remove the current node and do multiple removals/swaps
      Tree<T> subTree = new Tree<>(this.root);
      TreeNode<T> current = subTree.getRoot();
      T targetValue = value;
      int lastResult = 0;

      while (current != null) {
         int targetCurrentResult = targetValue.compareTo(current.data);

         switch (targetCurrentResult) {
            // If value being searched is too big
            case 1:
               subTree = new Tree<>(current);
               current = current.rightNode;
               break;
            // if value being search is too small
            case -1:
               subTree = new Tree<>(current);
               current = current.leftNode;
               break;
            // Value found
            case 0:
               // Replace with null if childless
               if (current.rightNode == null && current.leftNode == null) {
                  replaceValue(subTree, null, lastResult);
               // If it has only 1 child, replace it with that
               } else if (current.rightNode == null && current.leftNode != null) {
                  replaceValue(subTree, current.leftNode, lastResult);
               } else if (current.leftNode == null && current.rightNode != null) {
                  replaceValue(subTree, current.rightNode, lastResult);
               // If it has both children, replace with min value, then search
               // the subtree of the right branch and repeat until no removals
               // needed
               } else {
                  T minValue = getMin(current.rightNode);
                  current.data = minValue;
                  subTree = new Tree<>(current);
                  current = current.rightNode;
                  targetValue = minValue;
                  break;
               }

               return current;
         }
         // Figure out which child of the parent is the current node
         lastResult = targetCurrentResult;
      }

      return current;
   }

   // Replace subtree root child with replacement value if having traversed once from
   // the root
   public void replaceValue(Tree<T> subTree, TreeNode<T> replacement, int direction) {
      if (direction == -1) {
         subTree.getRoot().leftNode = replacement;
      } else {
         subTree.getRoot().rightNode = replacement;
      }

   }

   public TreeNode<T> getRoot() {
      return this.root;
   }

   public T getMin(TreeNode<T> root) {
      TreeNode<T> current = root;

      while (current.leftNode != null) {
         current = current.leftNode;
      }

      return current.data;
   }

   public Tree<T> search(T value) {
      TreeNode<T> current = root;

      while (current != null) {
         int result = value.compareTo(current.data);
         switch (result) {
            case 0:
               Tree<T> temp = new Tree<>(current);
               return temp;
            case 1:
               current = current.rightNode;
               break;
            case -1:
               current = current.leftNode;
               break;
         }
      }

      return null;
   }

   public void search (T value, String traversal) {
      Tree<T> node = search(value);
      if (node == null) {
         return;
      }

      switch (traversal) {
         case "inorder":
            node.inorderTraversal();
            break;
         case "preorder":
            node.postorderTraversal();
            break;
         case "postorder":
            node.preorderTraversal();
            break;
      }
   }

   public void preorderTraversal() {
      ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
      TreeNode<T> current = root;

      while (nodes.size() > 0 || current != null) {
         // If it is a node, print current value and add children to arraylist
         if (current != null) {
            System.out.printf("%s ", current.data);
            // Add right child to stack then traverse left child
            nodes.add(current.rightNode);
            current = current.leftNode;
         // If it is null, remove the last node in stack, the most recent parent
         // then set current to the next node to be iterated
         } else {
            current = nodes.remove(nodes.size() - 1);
         }
      }


   }

   public void inorderTraversal() {
      ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
      TreeNode<T> current = root;

      while (nodes.size() > 0 || current != null) {
         // If current is a node, add to queue then traverse left branch
         if (current != null) {
            nodes.add(current);
            current = current.leftNode;
         // Upon hitting a dead end, remove and print
         // most recent parent traverse right branch
         } else {
            current = nodes.remove(nodes.size() - 1);
            System.out.printf("%s ", current.data);
            current = current.rightNode;
         }
      }

   }

   // begin postorder traversal
   public void postorderTraversal() {
      postorderHelper(root);
   }

   // recursive method to perform postorder traversal
   private void postorderHelper(TreeNode<T> node) {
      if (node == null) {
         return;
      }

      postorderHelper(node.leftNode); // traverse left subtree
      postorderHelper(node.rightNode); // traverse right subtree
      System.out.printf("%s ", node.data); // output node data
   }

   // begin printing tree
   public void outputTree(int totalSpace) {
      outputTreeHelper(root, totalSpace>= 0 ? totalSpace : 0);
   }

   // recursive method to print tree
   private void outputTreeHelper(TreeNode<T> currentNode, int spaces) {
      // recursively print right branch, then left
      if (currentNode != null) {
         outputTreeHelper(currentNode.rightNode, spaces + 5);

         for (int k = 0; k <spaces; k++) {
            System.out.print(" ");
         }

         System.out.println(currentNode.data);
         outputTreeHelper(currentNode.leftNode, spaces + 5);
      }
   }
}
