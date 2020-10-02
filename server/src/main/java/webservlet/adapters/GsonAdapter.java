package webservlet.adapters;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webservlet.serializer.LocalDateTimeDeserializer;
import webservlet.serializer.LocalDateTimeSerializer;

public class GsonAdapter {
	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		Gson gsonParse = gsonBuilder.create();
		return gsonParse;
	}
}
