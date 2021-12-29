package controller;

import model.Program;
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

@Named("programController")
@SessionScoped
public class ProgramController extends AbstractController<Program> {

    @EJB
    private ejb.ProgramFacade ejbFacade;

    public ProgramController() {
        super(Program.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }    

    @FacesConverter(forClass = Program.class)
    public static class ProgramControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProgramController controller = (ProgramController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "programController");
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
            if (object instanceof Program) {
                Program o = (Program) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Program.class.getName()});
                return null;
            }
        }

    }

}
