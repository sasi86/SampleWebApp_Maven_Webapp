package org.sasi.spring.core.di.main;

import java.io.Serializable;

public class Singleton  implements Cloneable,Serializable{

	private static volatile Singleton singleton = null;
	//private static final Singleton singleton = new Singleton(); //early binding

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (singleton == null)
			synchronized (Singleton.class) {
				if(singleton==null)
					singleton = new Singleton();
			}
		return singleton;
	}
	
	public Object readResolve(){
		return Singleton.getInstance();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		// throw new CloneNotSupportedException();
		return Singleton.getInstance();
	}
}