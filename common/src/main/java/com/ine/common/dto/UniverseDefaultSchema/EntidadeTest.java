package com.ine.common.dto.UniverseDefaultSchema;

public class EntidadeTest {

	private String name;
	private String description;

	public EntidadeTest() {
	}

	public EntidadeTest(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Foo{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
