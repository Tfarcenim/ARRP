package net.devtech.arrp.json.blockstate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.util.Tuple;


public class JWhen implements Cloneable {
	private final List<Tuple<String, String[]>> OR = new ArrayList<>();

	/**
	 * @see JState#when()
	 */
	public JWhen() {}

	public JWhen add(String condition, String... states) {
		this.OR.add(new Tuple<>(condition, states));
		return this;
	}

	@Override
	public JWhen clone() {
		try {
			return (JWhen) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	public static class Serializer implements JsonSerializer<JWhen> {
		@Override
		public JsonElement serialize(JWhen src, Type typeOfSrc, JsonSerializationContext context) {
			if (src.OR.size() == 1) {
				JsonObject json = new JsonObject();
				Tuple<String, String[]> val = src.OR.get(0);
				json.addProperty(val.getA(), String.join("|", Arrays.asList(val.getB())));
				return json;
			} else {
				JsonArray array = new JsonArray();
				for (Tuple<String, String[]> val : src.OR) {
					JsonObject json = new JsonObject();
					json.addProperty(val.getA(), String.join("|", Arrays.asList(val.getB())));
					array.add(json);
				}
				return array;
			}
		}
	}
}
