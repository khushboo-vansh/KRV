package com.krv.test;

import java.util.stream.Stream;

public class AddingItemsStream {

	public static void main(String[] args) {
		Stream<Integer> stream = Stream.of(1, 2, 3, 4);
		// Append 5 and 6 to the stream
		Stream<Integer> appenededStream = Stream.concat(stream, Stream.of(5, 6));
		// Verify Stream
		appenededStream.forEach(System.out::print); // Prints 123456

		System.out.println("--------------------------------------------");

		Stream<Integer> stream1 = Stream.of(1, 2, 3, 4);
		// Prepend 0 to the stream
		Stream<Integer> prependedStream = Stream.concat(Stream.of(0), stream1);
		// Verify Stream
		prependedStream.forEach(System.out::print); // Prints 01234

	}

}
