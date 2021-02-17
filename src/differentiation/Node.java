package differentiation;

import java.util.UUID;

public interface Node {
	public UUID get_uuid();
	public Node get_left_child();
	public Node get_right_child();
	public Node get_parent();
	public boolean was_visited();
	public void visit();
	//defined as generic
	public <T> void set_value( T input ); 
	public <T> T get_value(); 
}
