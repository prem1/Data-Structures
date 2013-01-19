import java.util.ArrayList;
import java.util.List;


public class AVLTree<T extends Comparable<T>> implements BSTInterface<T>{

	public Node<T> root;
	public int size;
	
	
	/**
	 * Adds a new node to the tree.
	 * @param data the data to add
	 */
	@Override
	public void add(T data) {

		root=rotate(add(root,data));
		size++;
	}
	
	private Node<T> add(Node<T> curr,T item){
		if(curr==null){
			return rotate(curr=new Node<T>(item));
			
		}
		else if(item.compareTo(curr.getData())<0){
			
			 curr.setLeft(rotate(add(curr.getLeft(),item)));
			 return curr;
		}
		else{
			
			curr.setLeft(rotate(add(curr.getLeft(),item)));
			return curr;
		    }
		}
	
	public Node<T> rotate(Node<T> curr){
		update(curr);
		
		if(curr.getBf()>1 && curr.getLeft().getBf()>=0){
			curr=rotateRight(curr);
			update(curr);
		}
		else if(curr.getBf()>1 && curr.getLeft().getBf()<0){
			curr=rotateLeftRight(curr);
			update(curr);
		}
		else if(curr.getBf()<-1 && curr.getRight().getBf()<=0){
			curr=rotateLeft(curr);
			update(curr);
		}
		else if(curr.getBf()<-1 && curr.getRight().getBf()>0){
			curr=rotateRightLeft(curr);
			update(curr);
		}
		
		return curr;
	}
	
	public void update(Node<T> n){
		int leftHeight=n.getLeft()==null?-1:n.getLeft().getHeight();
		int rightHeight=n.getRight()==null?-1:n.getRight().getHeight();
		n.setHeight(Math.max(leftHeight,rightHeight)+1);
		n.setBf(leftHeight-rightHeight);
	}
	
	private Node<T> rotateRight(Node<T> curr){
		Node<T> newRoot= curr.getLeft();
		Node<T> temp= curr.getLeft().getRight();
		curr.getLeft().setRight(curr);
		curr.setLeft(temp);
		return newRoot;		
	}
	
	private Node<T> rotateLeft(Node<T> curr){
		Node<T> newRoot= curr.getRight();
		Node<T> temp= curr.getRight().getLeft();
		curr.getRight().setLeft(curr);
		curr.setRight(temp);
		return newRoot;		
	}
	
	private Node<T> rotateLeftRight(Node<T> curr){
		Node<T> temp=curr.getLeft();
		curr.setLeft(curr.getLeft().getRight());
		curr.getLeft().setLeft(temp);
		return rotateRight(curr);
	}
	
	private Node<T> rotateRightLeft(Node<T> curr){
		Node<T> temp=curr.getRight();
		curr.setRight(curr.getRight().getLeft());
		curr.getRight().setRight(temp);
		return rotateLeft(curr);
	}
	

	/**
	 * If a node that equals "data" is in the tree, that
	 * element is removed (and, when required, replaced 
	 * with its successor, smallest node larger than curr).
	 * Otherwise, return null.
	 * 
	 * @param data the data of the node to be removed
	 * @return the element removed from the tree, or null if not found.
	 */
	@Override
	public T remove(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes all elements from the tree.
	 */
	@Override
	public void clear() {
		root.setLeft(null);
		root.setRight(null);
		root=null;
		
	}

	/** 
	 * @return true if and only if the tree is empty.
	 */
	@Override
	public boolean isEmpty() {
		if(size==0)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param data the element to retrieve from the tree
	 * @return the element from the tree that equals the given data, if it
	 * 			exists. Otherwise, null.
	 */
	@Override
	public T get(T data) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns all data found in the tree in the form
	 * of a sorted list. Note that this will be an
	 * O(n) operation.
	 * 
	 * @return a sorted list of all elements from the BST
	 */
	@Override
	public List<T> asSortedList() {
		ArrayList<T> list=new ArrayList<T>();
		return inOrder(list,root);
	}

	/**
	 * 
	 * @return the number of elements in the tree
	 */
	@Override
	public int size() {
		return size;
	}
	
	///////////////////////////////////////////////
	@Override
	public String toString(){
		String result="the current AVL tree is: ";
		List<T> arr=preOrder();
		for(T item:arr){
			result+="{"+item+"} ";
		}
		
		result+="\t Inorder: ";
		
		List<T> arr2=inOrder();
		for(T item:arr2){
			result+="{"+item+"} ";
		}
		result+="\nThe AVL Tree has a current size of: "+this.size();
		return result;
	}
	
	public List<T> preOrder() {
		ArrayList<T> list=new ArrayList<T>();
		return preOrder(list,root);
		
	}
	
	public List<T> inOrder() {
		ArrayList<T> list=new ArrayList<T>();
		return inOrder(list,root);
		
	}
	
	/**
	 * Recursively traverses through the BST and adds the node values to a list which will be returned at the end.
	 * @param list
	 * @param curr
	 * @return
	 */
	private List<T> preOrder(List<T> list, Node<T> curr ){
		
		if(curr==null){
			size=size;
		}
		else{
			
			list.add(curr.getData());
			preOrder(list,curr.getLeft());
			preOrder(list,curr.getRight());
		}
		return list;
	}
	
	private List<T> inOrder(List<T> list, Node<T> curr ){
		
		if(curr==null){
			size=size;
		}
		else{
			
			inOrder(list,curr.getLeft());
			list.add(curr.getData());
			inOrder(list,curr.getRight());
		}
		return list;
	}
	
	public static void main(String args[]){
		AVLTree<Integer> obj=new AVLTree<Integer>();
		obj.add(20);
		obj.add(30);
		obj.add(40);
		obj.add(60);
		obj.add(10);
		
		
		
		System.out.println(obj);
	}

}
