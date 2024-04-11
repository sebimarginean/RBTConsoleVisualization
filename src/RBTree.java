
public class RBTree {
    RBNode root;

    public RBTree() {
        this.root = RBNode.Nil;
    }

    public void leftRotate(RBNode x) {
        RBNode y = x.rightChild;
        x.rightChild = y.leftChild;

        if (y.leftChild != RBNode.Nil) {
            y.leftChild.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == RBNode.Nil) {
            root = y;
        } else if (x == x.parent.leftChild) {
            x.parent.leftChild = y;
        } else {
            x.parent.rightChild = y;
        }

        y.leftChild = x;
        x.parent = y;
    }

    public void rightRotate(RBNode y) {
        RBNode x = y.leftChild;
        y.leftChild = x.rightChild;
        if (x.rightChild != RBNode.Nil) {
            x.rightChild.parent = y;
        }

        x.parent = y.parent;
        if (y.parent == RBNode.Nil) {
            root = x;
        } else if (y == y.parent.leftChild) {
            y.parent.leftChild = x;
        } else {
            y.parent.rightChild = x;
        }

        x.rightChild = y;
        y.parent = x;
    }


    public boolean isNil(RBNode node) {
        return (node == RBNode.Nil);
    }

    public RBNode search(RBNode node, int key) {
        if (isNil(node) || node.key == key) {
            return node;
        }

        if (key < node.key) {
            return search(node.leftChild, key);
        } else {
            return search(node.rightChild, key);
        }
    }

    public RBNode maximum(RBNode node) {
        resetVisitStatus();
        RBNode maxNode = getMaximum(node);
        System.out.println();
        displayVisitedNodes(root, 10);
        System.out.println();
        return maxNode;
    }

    public RBNode getMaximum(RBNode node) {
        RBNode current = node;
        markAsVisited(node);
        while (!isNil(current.rightChild)) {
            markAsVisited(current.rightChild);
            current = current.rightChild;
        }
        return current;
    }

    public RBNode minimum(RBNode node) {
        resetVisitStatus();
        RBNode minNode = getMinimum(node);
        System.out.println();
        displayVisitedNodes(root, 10);
        System.out.println();
        return minNode;
    }

    public RBNode getMinimum(RBNode node) {
        RBNode current = node;
        markAsVisited(node);
        while (!isNil(current.leftChild)) {
            markAsVisited(current.leftChild);
            current = current.leftChild;
        }
        return current;
    }

    public RBNode getSuccessor(RBNode node) {
        if (isNil(node))
            return node;

        RBNode current = node;
        markAsVisited(current);
        if (!isNil(current.rightChild)) {
            markAsVisited(current.rightChild);
            return getMinimum(current.rightChild);
        }

        RBNode parentNode = current.parent;
        markAsVisited(parentNode);
        while (!isNil(parentNode) && current == parentNode.rightChild) {
            markAsVisited(parentNode);
            current = parentNode;
            parentNode = current.parent;
        }
        return parentNode;
    }

    public RBNode successor(RBNode node) {
        resetVisitStatus();
        RBNode successorNode = getSuccessor(node);
        System.out.println();
        displayVisitedNodes(root, 10);
        System.out.println();
        return successorNode;
    }

    public RBNode getPredecessor(RBNode node) {
        if (isNil(node))
            return node;

        RBNode current = node;
        markAsVisited(current);
        if (!isNil(current.leftChild)) {
            markAsVisited(current.leftChild);
            return getMaximum(current.leftChild);
        }

        RBNode parentNode = current.parent;
        markAsVisited(parentNode);
        while (!isNil(parentNode) && current == parentNode.leftChild) {
            markAsVisited(parentNode);
            current = parentNode;
            parentNode = current.parent;
        }
        return parentNode;
    }

    public RBNode predecessor(RBNode node) {
        resetVisitStatus();
        RBNode predecessorNode = getPredecessor(node);
        System.out.println();
        displayVisitedNodes(root, 10);
        System.out.println();
        return predecessorNode;
    }

    public void RBInsert(RBNode newNode) {
        resetVisitStatus();

        RBNode parentNode = RBNode.Nil;
        RBNode currentNode = root;

        while (!isNil(currentNode)) {
            parentNode = currentNode;
            if (newNode.key < currentNode.key) {
                System.out.println("\t" + newNode.key + " < " + currentNode.key + " Insert into the left subtree.");
                markAsVisited(currentNode);
                currentNode = currentNode.leftChild;
            } else {
                System.out.println("\t" + newNode.key + " >= " + currentNode.key + " Insert into the right subtree.");
                markAsVisited(currentNode);
                currentNode = currentNode.rightChild;
            }
        }

        newNode.parent = parentNode;
        if (isNil(parentNode)) {
            System.out.println("\t" + "Insert into the empty tree.");
            root = newNode;
        } else if (newNode.key < parentNode.key) {
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }

        newNode.leftChild = newNode.rightChild = RBNode.Nil;
        System.out.println("\t" + "Color the new NODE RED.");
        newNode.color = Color.RED;
        System.out.println(); displayVisitedNodes(root, 10); System.out.println();
        System.out.println(newNode.color);
        RBInsertFixup(newNode);
    }

    public void RBInsertFixup(RBNode currentNode) {
        while (currentNode.parent.color == Color.RED) {
            if (currentNode.parent == currentNode.parent.parent.leftChild) {
                RBNode uncleNode = currentNode.parent.parent.rightChild;
                if (uncleNode.color == Color.RED) {
                    System.out.println("\t" + "The UNCLE node is RED. We RECOLOR the UNCLE, the PARENT and the GRANDPARENT.");
                    currentNode.parent.color = Color.BLACK;
                    uncleNode.color = Color.BLACK;
                    currentNode.parent.parent.color = Color.RED;
                    System.out.println();
                    displayTree(root, 10);
                    System.out.println();
                } else {
                    System.out.println("\t" + "The UNCLE is BLACK.");
                    if (currentNode == currentNode.parent.rightChild) {
                        System.out.println("\t" + "(The node, its PARENT and GRANDPARENT form a TRIANGLE) We ROTATE the PARENT.");
                        currentNode = currentNode.parent;
                        leftRotate(currentNode);
                        System.out.println();
                        displayTree(root, 10);
                        System.out.println();
                    }
                    System.out.println("\t" + "(The node, its PARENT and GRANDPARENT form a LINE) We ROTATE the GRANDPARENT.");
                    currentNode.parent.color = Color.BLACK;
                    currentNode.parent.parent.color = Color.RED;
                    rightRotate(currentNode.parent.parent);
                    System.out.println();
                    displayTree(root, 10);
                    System.out.println();
                }
            } else {
                RBNode uncleNode = currentNode.parent.parent.leftChild;
                if (uncleNode.color == Color.RED) {
                    System.out.println("\t" + "The UNCLE node is RED. We RECOLOR the UNCLE, the PARENT and the GRANDPARENT.");
                    currentNode.parent.color = Color.BLACK;
                    uncleNode.color = Color.BLACK;
                    currentNode.parent.parent.color = Color.RED;
                    currentNode = currentNode.parent.parent;
                    System.out.println();
                    displayTree(root, 10);
                    System.out.println();
                } else {
                    System.out.println("\t" + "The UNCLE is BLACK.");
                    if (currentNode == currentNode.parent.leftChild) {
                        System.out.println("\t" + "(The node, its PARENT and GRANDPARENT form a TRIANGLE) We ROTATE the PARENT.");
                        currentNode = currentNode.parent;
                        rightRotate(currentNode);
                        System.out.println();
                        displayTree(root, 10);
                        System.out.println();
                    }
                    System.out.println("\t" + "(The node, its PARENT and GRANDPARENT form a LINE) We ROTATE the GRANDPARENT.");
                    currentNode.parent.color = Color.BLACK;
                    currentNode.parent.parent.color = Color.RED;
                    leftRotate(currentNode.parent.parent);
                    System.out.println();
                    displayTree(root, 10);
                    System.out.println();
                }
            }
        }
        System.out.println("\t" + "RECOLOR the root BLACK.");
        root.color = Color.BLACK;
        System.out.println(); displayTree(root, 10); System.out.println();
    }

    public RBNode deleteNode(RBNode nodeToDelete) {
        RBNode nodeToReplace = (isNil(nodeToDelete.leftChild) || isNil(nodeToDelete.rightChild))
                ? nodeToDelete
                : successor(nodeToDelete);

        RBNode childOfReplaceNode = !isNil(nodeToReplace.leftChild)
                ? nodeToReplace.leftChild
                : nodeToReplace.rightChild;

        childOfReplaceNode.parent = nodeToReplace.parent;

        if (isNil(nodeToReplace.parent)) {
            root = childOfReplaceNode;
        } else {
            if (nodeToReplace == nodeToReplace.parent.leftChild) {
                nodeToReplace.parent.leftChild = childOfReplaceNode;
            } else {
                nodeToReplace.parent.rightChild = childOfReplaceNode;
            }
        }

        if (nodeToReplace != nodeToDelete) {
            nodeToDelete.key = nodeToReplace.key;
        }

        if (nodeToReplace.color == Color.BLACK) {
            RBDeleteFixup(childOfReplaceNode);
        }

        return nodeToReplace;
    }

    public void RBDeleteFixup(RBNode currentNode) {
        RBNode siblingNode;
        while ((currentNode != root) && (currentNode.color == Color.BLACK)) {
            if (currentNode == currentNode.parent.leftChild) {
                siblingNode = currentNode.parent.rightChild;
                if (siblingNode.color == Color.RED) {
                    siblingNode.color = Color.BLACK;
                    currentNode.parent.color = Color.RED;
                    leftRotate(currentNode.parent);
                    siblingNode = currentNode.parent.rightChild;
                }
                if ((siblingNode.leftChild.color == Color.BLACK) && (siblingNode.rightChild.color == Color.BLACK)) {
                    siblingNode.color = Color.RED;
                    currentNode = currentNode.parent;
                } else {
                    if (siblingNode.rightChild.color == Color.BLACK) {
                        siblingNode.leftChild.color = Color.BLACK;
                        siblingNode.color = Color.RED;
                        rightRotate(siblingNode);
                        siblingNode = currentNode.parent.rightChild;
                    }
                    siblingNode.color = currentNode.parent.color;
                    currentNode.parent.color = Color.BLACK;
                    siblingNode.rightChild.color = Color.BLACK;
                    leftRotate(currentNode.parent);
                    currentNode = root;
                }
            } else {
                siblingNode = currentNode.parent.leftChild;
                if (siblingNode.color == Color.RED) {
                    siblingNode.color = Color.BLACK;
                    currentNode.parent.color = Color.RED;
                    rightRotate(currentNode.parent);
                    siblingNode = currentNode.parent.leftChild;
                }
                if ((siblingNode.rightChild.color == Color.BLACK) && (siblingNode.leftChild.color == Color.BLACK)) {
                    siblingNode.color = Color.RED;
                    currentNode = currentNode.parent;
                } else {
                    if (siblingNode.leftChild.color == Color.BLACK) {
                        siblingNode.rightChild.color = Color.BLACK;
                        siblingNode.color = Color.RED;
                        leftRotate(siblingNode);
                        siblingNode = currentNode.parent.leftChild;
                    }
                    siblingNode.color = currentNode.parent.color;
                    currentNode.parent.color = Color.BLACK;
                    siblingNode.leftChild.color = Color.BLACK;
                    rightRotate(currentNode.parent);
                    currentNode = root;
                }
            }
        }
        currentNode.color = Color.BLACK;
    }


    public void inorderDisplay(RBNode node) {
        if (!isNil(node)) {
            inorderDisplay(node.leftChild);
            System.out.println(node + " ");
            inorderDisplay(node.rightChild);
        }
    }

    public void markAsVisited(RBNode node) {
        node.visitStatus = Status.VISITED;
    }

    public void resetVisitStatus() {
        resetVisitStatusRecursively(root);
    }

    public void resetVisitStatusRecursively(RBNode node) {
        if (!isNil(node)) {
            resetVisitStatusRecursively(node.leftChild);
            node.visitStatus = Status.UNVISITED;
            resetVisitStatusRecursively(node.rightChild);
        }
    }

    public void inorderDisplay() {
        if (isNil(root)) {
            System.out.println("The RB tree is empty");
        } else {
            inorderDisplay(root);
        }
    }

    public void displayTree(RBNode node, int indentLevel) {
        if (!isNil(node)) {
            displayTree(node.rightChild, indentLevel + 2);
            for (int i = 0; i < indentLevel; i++)
                System.out.print(" ");
            System.out.println(node);
            displayTree(node.leftChild, indentLevel + 2);
        }
    }

    public void displayVisitedNodes(RBNode node, int indentLevel) {
        if (!isNil(node)) {
            displayVisitedNodes(node.rightChild, indentLevel + 2);
            for (int i = 0; i < indentLevel; i++)
                System.out.print(" ");
            System.out.println(node.printVisited());
            displayVisitedNodes(node.leftChild, indentLevel + 2);
        }
    }

    public void indentedTreeDisplay() {
        if (isNil(root)) {
            System.out.println("The RB tree is empty");
        } else {
            System.out.println("The RB tree is:");
            displayTree(root, 0);
        }
    }
        

public int countBlackNodes(RBNode node) {
    if (isNil(node)) return 1;
    int leftBlackCount = countBlackNodes(node.leftChild);
    return node.color == Color.BLACK ? leftBlackCount + 1 : leftBlackCount;
}


    public int blackHeight(RBNode node) {
        if (isNil(node)) {
            return 0;
        }
        int leftBlackHeight = blackHeight(node.leftChild) + (node.leftChild.color == Color.BLACK ? 1 : 0);
        return leftBlackHeight;
    }

    public int depth(RBNode node)
    {
        if (isNil(node)) {
            return -1;
        } else {
            int leftDepth = depth(node.leftChild);
            int rightDepth = depth(node.rightChild);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    
    public int depth()
    {
        return depth(root);
    }


    public int maxBlackKey(RBNode node) {
        int maxBlackKey = Integer.MIN_VALUE;
        RBNode currentNode = node;

        while (!isNil(currentNode)) {
            if (currentNode.color == Color.BLACK) {
                maxBlackKey = currentNode.key;
            }
            currentNode = currentNode.rightChild;
        }

        return maxBlackKey;
    }

    public int maxRedKey(RBNode node)
    {
        RBNode currentNode = getMaximum(node);

        while (currentNode.color == Color.BLACK) {
            currentNode = getPredecessor(currentNode);
        }

        return isNil(currentNode) ? Integer.MIN_VALUE : currentNode.key;
    }
}
