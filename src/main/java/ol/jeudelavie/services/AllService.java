package ol.jeudelavie.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class AllService {

	public <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}
	
	
	
	public  boolean listEqualsIgnoreOrder( List<?> l1, List<?> l2 ) {
	    // make a copy of the list so the original list is not changed, and remove() is supported
	    ArrayList<?> cp = new ArrayList<>( l1 );
	    for ( Object o : l2 ) {
	        if ( !cp.remove( o ) ) {
	            return false;
	        }
	    }
	    return cp.isEmpty();
	}
}
