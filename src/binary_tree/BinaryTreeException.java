package binary_tree;

public class BinaryTreeException extends Exception implements BinaryExceptionTexts
{
	public Exception ValueNotInitialized()
	{
		return new Exception(BinaryExceptionTexts.NodeNotInitialized);
	}
	public Exception castFailed()
	{
		return new Exception(BinaryExceptionTexts.CastFailed);
	}
}
