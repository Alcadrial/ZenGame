package com.alcadrial.zengame;

import java.util.Objects;

@FunctionalInterface
public interface FloatConsumer {
	
	void accept(float value);
	
	default FloatConsumer andThen(FloatConsumer after)
	{
		Objects.requireNonNull(after);
		return f -> {
			accept(f);
			after.accept(f);
		};
	}
}
