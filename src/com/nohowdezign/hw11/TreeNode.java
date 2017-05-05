package com.nohowdezign.hw11;

public class TreeNode<T> {
    
    private T element;                            // data field
    private TreeNode<T>  left;                    // reference to left child node
    private TreeNode<T>  right;                   // reference to right child node
    
    public TreeNode()                             // POST: empty tree node
    {   element = null;
        left = right = null;
    }
    
    public TreeNode (T elem)                      // POST: tree node with element set
    {   element = elem;
        left = right = null;
    }

    public TreeNode (T elem, TreeNode<T> left, TreeNode<T> right)        // POST: tree node with element and child nodes
    {   element = elem;
        this.left = left;
        this.right = right; 
    }
    
    // accessors and mutators
    public T getElement() {    return element;    }
    public void setElement(T element) {    this.element = element;    }
    public TreeNode<T> getLeft() {    return left;    }
    public void setLeft(TreeNode<T> left) {    this.left = left;    }
    public TreeNode<T> getRight() {    return right;    }
    public void setRight(TreeNode<T> right) {    this.right = right;    }
    
    public boolean isLeaf ()                     // POST: return true if node is a leaf node
    {    return (left == null && right == null);    }
    
}



