package org.s2n.ddt.pojo.def;

import org.apache.log4j.Logger;

//Factory
public class RunnerType {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(RunnerType.class);
	private int id;// sl no
	private boolean active;
	private String name;// (key)
	private String className;
	private Boolean threadSafe;// pass same object or clone
	private Boolean cloneable;// even though cloneable in interface, class can
								// choose not to be
	private Runner runner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Boolean getThreadSafe() {
		return threadSafe;
	}

	public void setThreadSafe(Boolean threadSafe) {
		this.threadSafe = threadSafe;
	}

	public Boolean getCloneable() {
		return cloneable;
	}

	public void setCloneable(Boolean cloneable) {
		this.cloneable = cloneable;
	}

	public Runner getRunner() {
		if (threadSafe) {
			ensureHaveOwn();
			return runner;
		}
		if (cloneable) {
			ensureHaveOwn();
			try {
				return (Runner) runner.clone2();//
			} catch (CloneNotSupportedException e) {
				logger.warn("getRunner Clone" + e, e);
			}
		}
		Runner r = null;

		Class c;
		try {
			c = Class.forName(className);
			r = (Runner) c.newInstance();
			return r;
		} catch (ClassNotFoundException e) {
			logger.fatal("runner class issue " + className + " " +  e, e);
		} catch (InstantiationException e) {
			logger.fatal("runner class issue " + className + " " +  e, e);
		} catch (IllegalAccessException e) {
			logger.fatal("runner class issue " + className + " " +  e, e);
		}
		return null;
	}

	private void ensureHaveOwn() {
		synchronized (this) {
			if (runner == null) {
				//try {
					Class c;
					try {
						c = Class.forName(className);
						runner = (Runner) c.newInstance();
					} catch (ClassNotFoundException e) {
						logger.fatal("runner class issue " + className + " " +  e, e);
					} catch (InstantiationException e) {
						logger.fatal("runner class issue " + className + " " +  e, e);
					} catch (IllegalAccessException e) {
						logger.fatal("runner class issue " + className + " " +  e, e);
					}
					
//				} catch (Throwable e) {
//					logger.fatal("runner class issue " + className + " " + e, e);
//				}
			}
		}
	}

}
