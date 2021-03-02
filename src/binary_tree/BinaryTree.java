package binary_tree;

import java.util.UUID;

public interface BinaryTree<BT, V>  {
	public BinaryTree<BT, V> add(Node<?> n) throws Exception;
	public BinaryTree<BT, V> remove(Node<?> n);
	public Node<?> get_root_node();
	public Node<?> depth_first_search(UUID node_id);
	public Node<?> breadth_first_search(UUID node_id);
	//generic searches by some value
	public Node<?> depth_first_search(Node<?> value);
	public Node<?> breadth_first_search(Node<?> value);
}
