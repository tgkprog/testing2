package org.s2n.ddt.grxHelpers;

import org.s2n.ddt.bean.UtlProps;

public interface IdGen {

	public void init(UtlProps props);
	public String getOne();
}
