package com.epam.lisovyn.storage;

import com.epam.lisovyn.exceptions.StorageNotFoundException;
import com.epam.lisovyn.model.Model;

import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Created by Andrii_Lisovyn on 15.4.2016.
 */
public class ModelStorage <T extends Model> {
	private Map<Integer, T> storage = new HashMap<Integer, T>();

	public void put(T model) throws StorageNotFoundException {
		if (storage.containsKey(model.getId())) {
			throw new StorageNotFoundException(CONFLICT);
		}
		storage.put(model.getId(), model);
	}

	public T get(int id) throws StorageNotFoundException {
		if (isNotContains(id)) {
			throw new StorageNotFoundException(NOT_FOUND);
		}
		return storage.get(id);
	}

	public T update(T model) throws StorageNotFoundException {
		if (isNotContains(model.getId())) {
			throw new StorageNotFoundException(NOT_FOUND);
		}
		T oldModel = get(model.getId());
		storage.put(model.getId(), model);
		return oldModel;
	}

	public void delete(int id) throws StorageNotFoundException {
		if (isNotContains(id)) {
			throw new StorageNotFoundException(NOT_FOUND);
		}
		storage.remove(id);
	}

	private boolean isNotContains(int id) {
		return !storage.containsKey(id);
	}
}
