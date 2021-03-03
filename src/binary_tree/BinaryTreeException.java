package binary_tree;

public class BinaryTreeException extends Exception implements BinaryExceptionTexts
{
	public Exception valueNotInitialized()
	{
		return new Exception(BinaryExceptionTexts.NodeNotInitialized);
	}
	public Exception castFailed()
	{
		return new Exception(BinaryExceptionTexts.CastFailed);
	}
	public Exception unknownInstance()
	{
		return new Exception(BinaryExceptionTexts.UnknownInstance);
	}
}
