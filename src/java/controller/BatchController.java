package controller;

import model.Batch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("batchController")
@SessionScoped
public class BatchController extends AbstractController<Batch> {

    @EJB
    private ejb.BatchFacade ejbFacade;

    public BatchController() {
        super(Batch.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    @FacesConverter(forClass = Batch.class)
    public static class BatchControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BatchController controller = (BatchController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "batchController");
            return controller.getItem(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Batch) {
                Batch o = (Batch) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Batch.class.getName()});
                return null;
            }
        }

    }

}
