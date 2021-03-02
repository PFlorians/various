package binary_tree;

import java.util.UUID;

public interface Node<N> {
	public UUID get_uuid();
	public Node<N> get_left_child();
	public void set_left_child( Node<N> n );
	public Node<N> get_right_child();
	public void set_right_child( Node<N> n );
	public Node<N> get_parent();
	public void set_parent( Node<N> n );
	public boolean was_visited();
	public void visit();
}
