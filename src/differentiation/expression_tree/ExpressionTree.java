package differentiation.expression_tree;

import java.util.UUID;

import binary_tree.BinaryTree;
import binary_tree.BinaryTreeException;
import binary_tree.Node;

public class ExpressionTree implements BinaryTree
{
	private ExpressionNode<String, Integer> root_node;
	
	public ExpressionTree()
	{
		root_node = new ExpressionNode<String, Integer>();
	}
	public ExpressionTree( Integer value, String expression )
	{
		root_node = new ExpressionNode<String, Integer>( value, expression );
	}
	public ExpressionTree(ExpressionNode<String, Integer> root)
	{
		root_node = root;
	}
	
	@Override
	public BinaryTree add(Node<?> n) throws Exception 
	{
		ExpressionNode<String, Integer> en = null;
		
		if ( n instanceof ExpressionNode<?, ?> )
		{
			en = (ExpressionNode<String, Integer>) n;
		}
		if( en == null)
		{
			throw new BinaryTreeException().castFailed();
		}
		if( en.get_value() == null )
		{
			throw new BinaryTreeException().valueNotInitialized();
		}
		if( root_node == null )
		{
			root_node = en;
		}
		else
		{
			insert_node( root_node, en );
		}
		return this;
	}
	@Override
	public BinaryTree remove(Node<?> n) 
	{
		// TODO 
		return null;
	}
	@Override
	public Node<?> get_root_node() 
	{
		return root_node;
	}
	
	private void insert_node(ExpressionNode<String, Integer> start, ExpressionNode<String, Integer> n)
	{
		if( start.get_right_child() == null && start.get_left_child() != null &&
			((ExpressionNode<String, Integer>)start).get_value() < n.get_value() )
		{
			n.set_parent(start);
			start.set_right_child(n);
			return;
		}
		else if( start.get_right_child() == null && start.get_left_child() != null &&
				((ExpressionNode<String, Integer>)start).get_value() == n.get_value() )
		{
			insert_node( (ExpressionNode<String, Integer>)start.get_right_child(), n );
		}
		else if( start.get_right_child() == null && start.get_left_child() == null &&
				((ExpressionNode<String, Integer>)start).get_value() == n.get_value() )
		{
			n.set_parent(start);
			start.set_left_child(n);
			return;
		}
		else if( start.get_right_child() == null && start.get_left_child() == null &&
				((ExpressionNode<String, Integer>)start).get_value() < n.get_value() )
		{
			n.set_parent(start);
			start.set_left_child(n);
			return;
		}
	}
	
	@Override
	public Node<?> depth_first_search(UUID node_id) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Node<?> breadth_first_search(UUID node_id) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Node<?> depth_first_search(Node<?> value) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Node<?> breadth_first_search(Node<?> value) 
	{
		// TODO Auto-generated method stub
		return null;
	}


}
