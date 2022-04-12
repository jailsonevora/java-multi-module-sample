package com.ine.sge.interfaces;

import java.util.List;

/* To allow interface segregation principles, these operation are split from de main basic interface*/
public interface IExtendOperation {

	List<?> search(String query);

	List<?> index();
}
