/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.Auto;
import models.Camion;
import models.Moto;
import models.Vehiculo;

import java.lang.reflect.Type;

class VehiculoAdapter implements JsonSerializer<Vehiculo>, JsonDeserializer<Vehiculo> {

    @Override
    public JsonElement serialize(Vehiculo src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = context.serialize(src).getAsJsonObject();
        obj.addProperty("tipoReal", src.getClass().getSimpleName());
        return obj;
    }

    @Override
    public Vehiculo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        JsonObject obj = json.getAsJsonObject();
        String tipo = obj.get("tipoReal").getAsString();

        switch (tipo) {
            case "Auto":
                return context.deserialize(obj, Auto.class);
            case "Moto":
                return context.deserialize(obj, Moto.class);
            case "Camion":
                return context.deserialize(obj, Camion.class);
            default:
                throw new JsonParseException("Tipo desconocido: " + tipo);
        }
    }
}