public class RBNode {

	int key;
	RBNode parent, leftChild, rightChild;
	Color color;
	Status visitStatus;

	static RBNode Nil = new RBNode(0);

	public RBNode(int key) {
		this.key = key;
		this.leftChild = Nil;
		this.rightChild = Nil;
		this.parent = Nil;
		this.color = Color.BLACK;
	}

	public String toString() {
		return "(" + key + ")" + ((color == Color.RED) ? ":r" : ":b");
	}

	public String printVisited() {
		if(visitStatus == Status.VISITED)
			return "[" + key + "]" + ((color == Color.RED) ? ":r" : ":b");
		else
			return "(" + key + ")" + ((color == Color.RED) ? ":r" : ":b");
	}
}
