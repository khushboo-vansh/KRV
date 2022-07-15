package com.krv.cowin.dto;

public final class UserDto {

	private final String name;
	private final int age;
	private final String department;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getDepartment() {
		return department;
	}

	private UserDto(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.department = builder.department;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(UserDto copy) {
		Builder builder = new Builder();
		builder.name = copy.name;
		builder.age = copy.age;
		builder.department = copy.department;
		return builder;
	}

	public static class Builder {

		private String name;
		private int age;
		private String department;

		private Builder() {
		}

		public Builder(String name, int age, String department) {
			this.name = name;
			this.age = age;
			this.department = department;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withAge(int age) {
			this.age = age;
			return this;
		}

		public Builder withDepartment(String department) {
			this.department = department;
			return this;
		}

		public UserDto build() {
			return new UserDto(this);
		}
	}

}
