package controller;

import controller.util.JsfUtil;
import java.util.ArrayList;
import model.AcademicCalendar;
import java.util.List;
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
import model.Batch;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;


@Named("academicCalendarController")
@SessionScoped
public class AcademicCalendarController extends AbstractController<AcademicCalendar> {

    @EJB
    private ejb.AcademicCalendarFacade ejbFacade;

    private DualListModel<Batch> dualBatches;    

    public AcademicCalendarController() {
        super(AcademicCalendar.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        dualBatches = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
    }

    public String onFlowProcess(FlowEvent event) {
        if ("dates".equals(event.getNewStep())) {
            if (!getSelected().isValidYear()) {
                JsfUtil.addErrorMessage("Invalid Academic Year!");
                return "basic";
            }
        } else if ("batches".equals(event.getNewStep())) {
            if (!getSelected().checkDates()) {
                JsfUtil.addErrorMessage("Invalid Date Setup!");
                return "dates";
            }            
            dualBatches.setSource(ejbFacade.prepareValidBatches(getSelected()));
            dualBatches.getTarget().clear();            
        }
        return event.getNewStep();
    }

    public void onTransfer(TransferEvent event) {        
        JsfUtil.addSuccessMessage("Batch Transferred!");
    }

    public DualListModel<Batch> getDualBatches() {
        return dualBatches;
    }

    public void setDualBatches(DualListModel<Batch> dualBatches) {
        this.dualBatches = dualBatches;
    }

    public AcademicCalendar getAcademicCalendar(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AcademicCalendar> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AcademicCalendar> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AcademicCalendar.class)
    public static class AcademicCalendarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AcademicCalendarController controller = (AcademicCalendarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "academicCalendarController");
            return controller.getAcademicCalendar(getKey(value));
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
            if (object instanceof AcademicCalendar) {
                AcademicCalendar o = (AcademicCalendar) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AcademicCalendar.class.getName()});
                return null;
            }
        }

    }

}
