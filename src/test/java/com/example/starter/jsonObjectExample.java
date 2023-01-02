package com.example.starter;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class jsonObjectExample {

  //Jsonobject with String Constructord
  @Test
  void jsonObjectCanBeMapped() {
    final JsonObject myJsonObject = new JsonObject();
    myJsonObject.put("id", 1)
      .put("name", "Alice")
      .put("loves_vertX", true);

    final String encodedValue = myJsonObject.encode();
    Assertions.assertEquals("{\"id\":1,\"name\":\"Alice\",\"loves_verts\":true}", encodedValue);

    //JsonObject String constructor
    final JsonObject decodedObject = new JsonObject(encodedValue);

    Assertions.assertEquals(decodedObject, myJsonObject);
  }

  //JsonObject from MAP Constructor
  @Test
  void jsonObjectCreationFromMap() {
    final Map<String, Object> myMap = new HashMap<>();
    myMap.put("id", 2);
    myMap.put("name", "John");
    myMap.put("loves_VertX", false);

    final JsonObject asJsonObject = new JsonObject(myMap);
    Assertions.assertEquals(asJsonObject, myMap);
  }

  //JSON Array
  @Test
  void jsonArrayCanBeMapped() {
    final JsonArray myJsonArray = new JsonArray();
    myJsonArray.add(new JsonObject().put("id", 3))
      .add(new JsonObject().put("id", 4));

    Assertions.assertEquals("[{\"id\":3},{\"id\":4}]", myJsonArray.encode());
  }

  //Creating JsonObject from normal object [Mapping Java Object]
  @Test
  void canMapJavaObject() {
    final JsonObject alica = JsonObject.mapFrom(new Person(1, "Alice", true));
    Assertions.assertEquals(alica.getInteger("id"), 1);

    //Checking for serialization  without default constructor it will fail
    //*** So it is compulsory to provide default constructor if trying to serialize objects

    Person personObject = alica.mapTo(Person.class);

    //this line will give error if no default constructor is their
    // no Creators, like default constructor, exist):
    // cannot deserialize from Object value (no delegate- or property-based Creator)
    // at [Source: UNKNOWN; byte offset: #UNKNOWN]
  }
}
