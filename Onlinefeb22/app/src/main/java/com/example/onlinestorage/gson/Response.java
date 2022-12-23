package com.example.onlinestorage.gson;

import java.util.List;

public class Response{
	private double ppu;
	private Batters batters;
	private String name;
	private String id;
	private String type;
	private List<ToppingItem> topping;

	public double getPpu(){
		return ppu;
	}

	public Batters getBatters(){
		return batters;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public List<ToppingItem> getTopping(){
		return topping;
	}

	@Override
	public String toString() {
		return "Response{" +
				"ppu=" + ppu +
				", batters=" + batters +
				", name='" + name + '\'' +
				", id='" + id + '\'' +
				", type='" + type + '\'' +
				", topping=" + topping +
				'}';
	}
}