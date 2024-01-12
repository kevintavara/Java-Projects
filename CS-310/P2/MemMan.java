//TO DO: Complete this class, add JavaDocs

//Do not add any more imports!
import java.util.Iterator;
import java.util.Set;

public class MemMan implements Iterable<MemBlock> {

	//******************************************************
	//****    IMPORTANT: DO NOT CHANGE/ALTER/REMOVE     ****
	//****    ANYTHING PROVIDED LIKE THESE INTS HERE... ****
	//******************************************************
	
	public static final int FIRST_FIT = 0;
	public static final int BEST_FIT = 1;
	public static final int WORST_FIT = 2;
	public static final int NEXT_FIT = 3;
	
	//******************************************************
	//****    IMPORTANT: DO NOT CHANGE/ALTER/REMOVE     ****
	//****    ANYTHING PROVIDED LIKE THE INSTANCE       ****
	//****    VARIABLES IN THIS NESTED CLASS. ALSO      ****
	//****    DON'T REMOVE THE CLASS ITSELF OR ANYTHING ****
	//****    STRANGE LIKE THAT.                        ****
	//******************************************************
	public static class BareNode {
		public MemBlock block;
		public BareNode next;
		public BareNode prev;
		public boolean marked;
		
		public BareNode(MemBlock block) {
			this.block = block;
		}
	}
}