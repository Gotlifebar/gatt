package org.gatt.domain.factories;

import java.util.Collection;
import org.gatt.domain.InitialTT;

public interface InitialTTDAO{
	public InitialTT findInitialTT(int id);
	public Collection<InitialTT> findAll();
	public int countInitial_Timetabling();
}