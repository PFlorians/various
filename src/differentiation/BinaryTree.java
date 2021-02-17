package differentiation;

import java.util.UUID;

public interface BinaryTree {
	public BinaryTree add(Node n);
	public BinaryTree remove(Node n);
	public Node get_root_node();
	public Node depth_first_search(UUID node_id);
	public Node breadth_first_search(UUID node_id);
	//generic searches by some value
	public <T> Node depth_first_search(T value);
	public <T> Node breadth_first_search(T value);
}
