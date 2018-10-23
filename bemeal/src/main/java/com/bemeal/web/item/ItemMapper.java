package com.bemeal.web.item;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.bemeal.web.img.Image;

@Repository
public interface ItemMapper {
	public void post(Item p);
	public List<Item> listAll();
	public List<Item> listSome(Item p);
	public List<Item> retrieve(Item p);
}
