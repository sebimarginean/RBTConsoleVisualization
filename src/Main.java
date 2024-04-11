
public class Main {

	public static void main(String[] args) {
		
		RBTree tree = new RBTree();
		int option;
		
		while(true) {
			option = Operations.getOperation();
			executeOperation(tree, option);
		}

	}
	
	public static void addNode(RBTree tree) {
		int key = Operations.getNodeKey();
		RBNode node = new RBNode(key);
		tree.RBInsert(node);
	}
	
	public static void deleteNode(RBTree tree) {
		int key = Operations.getNodeKey();
		RBNode node = tree.search(tree.root, key);
		if (!tree.isNil(node)) 
			tree.deleteNode(node);
        else System.out.println("RB: Node not found");
	}
	
	public static void minimumNode(RBTree tree) {
		RBNode node = tree.minimum(tree.root);
		if (tree.isNil(node)) 
			System.out.println("RB: Minimum not found");
        else System.out.println("RB: Minimum is: " + node);
	}
	
	public static void maximumNode(RBTree tree) {
		RBNode node = tree.maximum(tree.root);
		if (tree.isNil(node)) 
			System.out.println("RB: Maximum not found");
        else System.out.println("RB: Maximum is: " + node);
	}
	
	public static void successorNode(RBTree tree) {
		int key = Operations.getNodeKey();
		RBNode node = tree.search(tree.root, key);
		if (!tree.isNil(node))
        {
            node = tree.successor(node);
            if (tree.isNil(node))
                System.out.println("RB: Node has no successor.");
            else System.out.println("RB: Successor is: " + node);
        }
        else System.out.println("RB: Node not found.");
	}
	
	public static void predecessorNode(RBTree tree) {
		int key = Operations.getNodeKey();
		RBNode node = tree.search(tree.root, key);
		if (!tree.isNil(node))
        {
            node = tree.predecessor(node);
            if (tree.isNil(node))
                System.out.println("RB: Node has no predecessor.");
            else System.out.println("RB: Predecessor is: " + node);
        }
        else System.out.println("RB: Node not found.");
	}

	public static void executeOperation(RBTree tree, int option) {
	    switch (option)
	    {
			case 1:
				addNode(tree);
				break;
			case 2:
				deleteNode(tree);
				break;
			case 3:
				minimumNode(tree);
				break;
			case 4:
				maximumNode(tree);
				break;
			case 5:
				successorNode(tree);
				break;
			case 6:
				predecessorNode(tree);
				break;
			case 7:
				tree.indentedTreeDisplay();
				break;
			case 8:
				System.out.println("Inorder traversal of RB tree ");
				tree.inorderDisplay();
				System.out.println();
				break;
			case 9:
				System.out.println("The black-height of the RB tree is " + tree.blackHeight(tree.root));
				break;
			case 10:
				System.out.println("The max key of a black node in the RB tree is " + tree.maxBlackKey(tree.root));
				break;
			case 11:
				System.out.println("The max key of a red node in the RB tree is " + tree.maxRedKey(tree.root));
				break;
			case 12:
				System.out.println("The tree depth of RB is " + tree.depth());
				break;
			case 13:
				System.out.println("Exit!");
				System.exit(0);
			}
	}
}
