package org.creators;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class SetOpt<E> {
	private Set<E> set;

	public SetOpt() {
		this.set = new TreeSet<E>();
	}
	
	public SetOpt(Collection<E> c) {
		this.set = (Set<E>) c;
	}
	
	public int size(E e) {
		return this.set.size();
	}
	
	public boolean isEmpty(E e) {
		return this.set.isEmpty();
	}
	
	public boolean contains(Object o) {
		return this.set.contains(o);
	}

	public Set<E> getSet() {
		return set;
	}

	public void setSet(Set<E> set) {
		this.set = set;
	}
	
	public void add(E e){
		this.set.add(e);
	}
	
	public void remove(Object o) {
		this.set.remove(o);
	}
	
	public void clear() {
		this.set.clear();
	}
		
	public SetOpt<E> union(SetOpt<E> a) {
		Set<E> copy1 = new TreeSet<E>(this.set);
		Set<E> copy2 = new TreeSet<E>(a.set);
		SetOpt<E> temp = new SetOpt<E>();
		copy1.addAll(copy2);
        temp.set = copy1;
        return temp;
	}
	
	public SetOpt<E> intersection(SetOpt<E> a) {
		Set<E> copy1 = new TreeSet<E>(this.set);
		Set<E> copy2 = new TreeSet<E>(a.set);
		SetOpt<E> temp = new SetOpt<E>();
		copy1.retainAll(copy2);
        temp.set = copy1;
        return temp;
	}
	
	public SetOpt<E> difference(SetOpt<E> a) {
		Set<E> copy1 = new TreeSet<E>(this.set);
		Set<E> copy2 = new TreeSet<E>(a.set);
		SetOpt<E> temp = new SetOpt<E>();
		copy1.removeAll(copy2);
        temp.set = copy1;
        return temp;
	}
	
	public SetOpt<E> symmetricDifference(SetOpt<E> a) {
		Set<E> copy1 = new TreeSet<E>(this.set);
		Set<E> copy2 = new TreeSet<E>(a.set);
		SetOpt<E> temp = new SetOpt<E>();
		SetOpt<E> temp1 = new SetOpt<E>();
		copy1.addAll(copy2);
		temp.set = copy1;
		copy1 = new TreeSet<E>(this.set);		
		copy1.retainAll(copy2);
		temp1.set = copy1;
		//System.out.println(temp.set + " " + temp1.set);
		temp.set.removeAll(temp1.set);
        return temp;
	}
	
	public boolean isSuperSet(SetOpt<E> a) {
		Set<E> copy1 = new TreeSet<E>(this.set);
		Set<E> copy2 = new TreeSet<E>(a.set);
		return copy1.containsAll(copy2);
	}
	
	public boolean isSubSet(SetOpt<E> a) {
		Set<E> copy1 = new TreeSet<E>(this.set);
		Set<E> copy2 = new TreeSet<E>(a.set);
		return copy2.containsAll(copy1);
	}
	
	@Override
	public String toString() {
		return "" + set;
	}
	
	
}
