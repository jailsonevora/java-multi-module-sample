package com.ine.sge.interfaces;

public interface IBasicOperation<T, U> {

	T show(U id);

	T showall();

	T create(U toCreate);

	T update(Long id, U toUpdate);

	T delete(Long id);
}
