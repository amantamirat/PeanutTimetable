/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import controller.util.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Amanuel
 */
public abstract class AbstractConverter<T> implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }

        byte[] data = Base64.getDecoder().decode(value);
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            try {
                o = (T) o;
            } catch (ClassCastException cce) {
            }
            ois.close();
            return o;
        } catch (IOException | ClassNotFoundException ex) {
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        try {
            Object o = object;
            o = (T) o;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(o);
                oos.close();
                return Base64.getEncoder().encodeToString(baos.toByteArray());
            } catch (IOException ex) {
                return null;
            }
        } catch (ClassCastException cce) {
            return null;
        }

    }
}
