// Exercise 21.25: Tree.java
// TreeNode and Tree class declarations for a binary search tree.

// class TreeNode definition

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

   // locate insertion point and insert new node; ignore duplicate values
   public void insert(T insertValue) {
      // insert in left subtree
      if (insertValue.compareTo(data) <0) {
         // insert new TreeNode
         if (leftNode == null) {
            leftNode = new TreeNode<>(insertValue);
         }
         else { // continue traversing left subtree recursively
            leftNode.insert(insertValue);
         }
      }
      // insert in right subtree
      else if (insertValue.compareTo(data)> 0) {
         // insert new TreeNode
         if (rightNode == null) {
            rightNode = new TreeNode<>(insertValue);
         }
         else { // continue traversing right subtree recursively
            rightNode.insert(insertValue);
         }
      }
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
      Tree<T> subTree = new Tree<>(this.root);
      TreeNode<T> current = subTree.getRoot();
      T targetValue = value;
      int lastResult = 0;

      while (current != null) {
         int targetCurrentResult = targetValue.compareTo(current.data);

         switch (targetCurrentResult) {
            case 1:
               subTree = new Tree<>(current);
               current = current.rightNode;
               break;
            case -1:
               subTree = new Tree<>(current);
               current = current.leftNode;
               break;
            case 0:
               if (current.rightNode == null && current.leftNode == null) {
                  replaceValue(subTree, null, lastResult);
                  return current;

               } else if (current.rightNode == null && current.leftNode != null) {
                  replaceValue(subTree, current.leftNode, lastResult);
                  return current;

               } else if (current.leftNode == null && current.rightNode != null) {
                  replaceValue(subTree, current.rightNode, lastResult);
                  return current;

               } else {
                  T minValue = getMin(current.rightNode);
                  current.data = minValue;
                  subTree = new Tree<>(current);
                  current = current.rightNode;
                  targetValue = minValue;
               }
         }
         lastResult = targetCurrentResult;
      }

      return current;
   }

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
         if (current != null) {
            System.out.printf("%s ", current.data);
            nodes.add(current.rightNode);
            current = current.leftNode;
         } else {
            current = nodes.remove(nodes.size() - 1);
         }
      }


   }

   // begin preorder traversal
   public void recursivePreorderTraversal() {
      preorderHelper(root);
   }

   // recursive method to perform preorder traversal
   private void preorderHelper(TreeNode<T> node) {
      if (node == null) {
         return;
      }

      System.out.printf("%s ", node.data); // output node data
      preorderHelper(node.leftNode); // traverse left subtree
      preorderHelper(node.rightNode); // traverse right subtree
   }

   // begin inorder traversal
   public void recursiveInorderTraversal() {
      inorderHelper(root);
   }

   public void inorderTraversal() {
      ArrayList<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>();
      TreeNode<T> current = root;

      while (nodes.size() > 0 || current != null) {
         if (current != null) {
            nodes.add(current);
            current = current.leftNode;
         } else {
            current = nodes.remove(nodes.size() - 1);
            System.out.printf("%s ", current.data);
            current = current.rightNode;
         }
      }

   }

   // recursive method to perform inorder traversal
   private void inorderHelper(TreeNode<T> node) {
      if (node == null) {
         return;
      }

      inorderHelper(node.leftNode); // traverse left subtree
      System.out.printf("%s ", node.data); // output node data
      inorderHelper(node.rightNode); // traverse right subtree
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
