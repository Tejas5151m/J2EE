package Testing;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Object> list = new ArrayList();
		
		if(list.size()>0) {
			System.out.println("something");
		}else {
			System.out.println("null");
		}
		
		//list return type is int
		
		if(list!=null) {
			System.out.println("Something..1");
		}else {
			System.out.println("Nothing...1");
		}

	}

}
