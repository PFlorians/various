package differentiation.expression_tree;

import java.util.UUID;

import binary_tree.Node;

public class ExpressionNode<N, V> implements Node<N> {
	private ExpressionNode<N, V> left_child, right_child, parent;
	private boolean was_visited;
	private UUID node_id;
	private V value;
	private N expression;
	
	public ExpressionNode()
	{
		was_visited = false;
		node_id = UUID.randomUUID();
	}
	public ExpressionNode( V value, N expression )
	{
		this.value = value;
		this.expression = expression;
		was_visited = false;
		node_id = UUID.randomUUID();
	}
	public ExpressionNode( ExpressionNode<N, V> parent )
	{
		this.parent = parent;
		was_visited = false;
		node_id = UUID.randomUUID();
	}
	public ExpressionNode ( ExpressionNode<N, V> parent, ExpressionNode<N, V> left_child, ExpressionNode<N, V> right_child )
	{
		this.parent = parent;
		this.left_child = left_child;
		this.right_child = right_child;
		was_visited = false;
		node_id = UUID.randomUUID();
	}
	@Override
	public void visit() 
	{
		was_visited = false;
	}
	
	/************************************/
	/*									*/ 
	/* 	   Getters and setters			*/
	/* 									*/
	/* **********************************/
	
	@Override
	public UUID get_uuid() 
	{
		return node_id;
	}

	@Override
	public Node<N> get_left_child() 
	{
		return left_child;
	}

	@Override
	public Node<N> get_right_child() 
	{
		return right_child;
	}

	@Override
	public Node<N> get_parent() 
	{
		return parent;
	}
	@Override
	public void set_left_child( Node<N> n ) 
	{
		left_child = (ExpressionNode<N, V>)n;
	}
	@Override
	public void set_right_child( Node<N> n ) 
	{
		right_child = (ExpressionNode<N, V>)n;
	}
	@Override
	public void set_parent( Node<N> n )
	{
		parent = (ExpressionNode<N, V>)n;
	}
	
	public V get_value() 
	{
		return value;
	}
	public N get_expression()
	{
		return expression;
	}
	public void set_value( V input ) 
	{
		value = input;
	}
	public void set_operator( N expression )
	{
		this.expression = expression;
	}
	
	/************************************/
	/*									*/ 
	/* 	   		  Predicates			*/
	/* 									*/
	/* **********************************/
	
	@Override
	public boolean was_visited() 
	{
		return was_visited;
	}
}
